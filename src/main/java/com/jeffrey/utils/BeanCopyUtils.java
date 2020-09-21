package com.jeffrey.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: Bean类数据拷贝工具
 *
 * @author WQ
 * @date 2020/8/24 11:17 AM
 */
public class BeanCopyUtils {
	/**
	 * BeanCopier缓存
	 */
	private static final Map<String, BeanCopier> BEAN_COPIER_MAP = new HashMap<>();

	/**
	 * 数据拷贝
	 *
	 * @param srcObj  数据源
	 * @param destObj 数据目标
	 */
	public static void copy(Object srcObj, Object destObj) {
		String key = getKey(srcObj.getClass(), destObj.getClass());
		BeanCopier copier = null;
		if (!BEAN_COPIER_MAP.containsKey(key)) {
			copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), false);
			BEAN_COPIER_MAP.put(key, copier);
		} else {
			copier = BEAN_COPIER_MAP.get(key);
		}
		copier.copy(srcObj, destObj, null);
	}

	/**
	 * 数据列表拷贝
	 *
	 * @param srcObjList   数据源列表
	 * @param destObjClass 数据目标Class
	 * @return 数据目标列表
	 * @throws Exception 创建destObjClass对象异常或者拷贝异常
	 */
	public static <T> List<T> copyList(List srcObjList, Class<T> destObjClass) throws Exception {
		List<T> destObjList = new ArrayList<>();
		for (int i = 0; i < srcObjList.size(); i++) {
			T destObj = destObjClass.newInstance();
			copy(srcObjList.get(i), destObj);
			destObjList.add(destObj);
		}
		return destObjList;
	}

	/**
	 * @param srcObjList  数据源列表
	 * @param destObjList 数据目标列表
	 * @param srcId       数据源ID
	 * @param destId      数据目标ID
	 * @throws Exception 反射获取id属性异常
	 */
	public static void copyList(List srcObjList, List destObjList, String srcId, String destId) throws Exception {
		for (Object srcObj : srcObjList) {
			for (Object destObj : destObjList) {
				Class<?> srcObjClass = srcObj.getClass();
				Class<?> destObjClass = destObj.getClass();
				Field srcField = srcObjClass.getDeclaredField(srcId);
				Field destField = destObjClass.getDeclaredField(destId);
				srcField.setAccessible(true);
				destField.setAccessible(true);
				// 匹配相同id
				if (srcField.get(srcObj).toString().equals(destField.get(destObj).toString())) {
					copy(srcObj, destObj);
					continue;
				}
			}
		}
	}

	/**
	 * 获取BeanCopier缓存key
	 *
	 * @param srcClazz  数据源class
	 * @param destClazz 数据目标class
	 * @return 缓存key
	 */
	private static String getKey(Class<?> srcClazz, Class<?> destClazz) {
		return srcClazz.getName() + destClazz.getName();
	}

	/**
	 * 同Spring的BeanUtils，区别是null值不会复制(忽略空值)
	 *
	 * @param source the source bean
	 * @param target the target bean
	 * @return count 复制的属性的数量
	 * @throws BeansException if the copying failed
	 * @see BeanUtils
	 * @since 2019年4月28日 下午3:00:33
	 */
	public static int copyProperties(Object source, Object target) throws BeansException {
		return copyProperties(source, target, (String[]) null);
	}

	/**
	 * 同Spring的BeanUtils，区别是null值不会复制(忽略空值)
	 *
	 * @param source           the source bean
	 * @param target           the target bean
	 * @param ignoreProperties array of property names to ignore
	 * @return count 复制的属性数量
	 * @throws BeansException if the copying failed
	 * @see BeanUtils
	 * @since 2019年4月28日 下午3:00:35
	 */
	public static int copyProperties(Object source, Object target, @Nullable String... ignoreProperties) throws BeansException {

		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		int count = 0;
		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							if (value == null)
								continue;
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
							count++;
						} catch (Throwable ex) {
							throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}
		return count;
	}
}