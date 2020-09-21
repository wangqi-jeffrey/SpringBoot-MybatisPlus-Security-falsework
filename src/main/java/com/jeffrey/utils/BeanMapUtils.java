package com.jeffrey.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: BeanMapUtils
 *
 * @author WQ
 * @date 2020/8/24 11:19 AM
 */
public class BeanMapUtils {

	/**
	 * bean 转 map
	 *
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		Map<String, Object> map = Maps.newHashMap();
		if (null != bean) {
			BeanMap beanMap = BeanMap.create(bean);
			for (Object key : beanMap.keySet()) {
				if (beanMap.get(key) instanceof Date) {
					String pattern = DateUtil.PATTERN_DATETIME;
					Field[] fields = bean.getClass().getDeclaredFields();
					if (null != fields) {
						for (Field field : fields) {
							if (field.getName().equals(key)) {
								field.setAccessible(true);
								JsonFormat annon = field.getAnnotation(JsonFormat.class);
								if (null != annon) {
									pattern = annon.pattern();
								}
							}
						}
					}
					map.put(key.toString(), DateUtil.format(pattern, (Date) beanMap.get(key)));
				} else {
					map.put(key.toString(), beanMap.get(key));
				}
			}
		}
		return map;
	}

	/**
	 * map 转 bean
	 *
	 * @param map
	 * @param clazz
	 * @param <T>
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) throws InstantiationException, IllegalAccessException {
		T result = clazz.newInstance();
		BeanMap beanMap = BeanMap.create(result);
		beanMap.putAll(map);
		return result;
	}

	/**
	 * list to map
	 *
	 * @param objList
	 * @param <T>
	 * @return
	 */
	public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
		final List<Map<String, Object>> result = Lists.newLinkedList();
		if (CollectionUtils.isEmpty(objList)) {
			return result;
		}
		for (T t : objList) {
			result.add(beanToMap(t));
		}
		return result;
	}

	/**
	 * map to list
	 *
	 * @param maps
	 * @param clazz
	 * @param <T>
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz)
			throws InstantiationException, IllegalAccessException {
		final List<T> result = Lists.newLinkedList();
		if (CollectionUtils.isEmpty(maps)) {
			return result;
		}
		for (Map<String, Object> map : maps) {
			result.add(mapToBean(map, clazz));
		}
		return result;
	}

	/**
	 * 对传入的对象进行数据清洗，将属性值为null的去掉，其他字段名和属性值存入map集合
	 *
	 * @param requestParameters
	 * @return
	 * @throws IllegalAccessException
	 */
	public static Map<String, Object> objectToMap(Object requestParameters) throws IllegalAccessException {
		Map<String, Object> map = new HashMap<>();
		Field[] fields = requestParameters.getClass().getDeclaredFields();
		for (int i = 0, len = fields.length; i < len; i++) {
			String varName = fields[i].getName();
			boolean accessFlag = fields[i].isAccessible();
			fields[i].setAccessible(true);
			Object o = fields[i].get(requestParameters);
			if (o != null) {
				if (o instanceof Date) {
					map.put(varName, DateUtil.formatDateTime((Date) o));
				} else {
					map.put(varName, o);
				}
				fields[i].setAccessible(accessFlag);
			}
		}
		return map;
	}

	public static void main(String[] args) {

	}

}
