package com.jeffrey.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: bean工具类
 * ClassName: BeanUtil
 * date: 2018年11月11日
 *
 * @author YGC
 * @since JDK 1.8
 */
public class BeanUtil {

    private static final String SETTER_PREFIX = "set";

    private static final String GETTER_PREFIX = "get";

    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 获取类的所有父类（除了Object类）及其本身
     *
     * @param clazz
     * @return
     */
    public static List<Class<?>> getSuperClasses(Class<?> clazz) {
        List<Class<?>> clazzs = new ArrayList<>();
        if (clazz == null) {
            return clazzs;
        }
        while (clazz != Object.class) {
            clazzs.add(clazz);
            clazz = clazz.getSuperclass();
        }
        return clazzs;
    }

    /**
     * 判断给定类型是不是java内部类型
     *
     * @return
     */
    public static boolean isJavaInsideCLass(Class<?> c) {
        List<Class<?>> classList = Arrays.asList(new Class[]{Byte.class, Short.class, Integer.class, Long.class,
                Float.class, Double.class, Character.class, Boolean.class});
        return classList.contains(c);
    }

    /**
     * 按照参数名称的字典排序
     *
     * @param params
     * @return
     */
    public static List<String> sortParamValues(Map<String, String> params) {
        List<String> sortResult = new ArrayList<>();
        if (params == null || params.isEmpty()) {
            return sortResult;
        }
        List<String> pKeys = new ArrayList<>();
        pKeys.addAll(params.keySet());
        Collections.sort(pKeys, new Comparator<String>() {

            @Override
            public int compare(String key1, String key2) {
                return key1.compareTo(key2);
            }
        });

        for (String pKey : pKeys) {
            sortResult.add(params.get(pKey));
        }
        return sortResult;
    }

    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, String> beanToMap(T bean) {
        Map<String, String> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                if (beanMap.get(key) != null) {
                    map.put(key + "", beanMap.get(key) + "");
                }
            }
        }
        return map;
    }

    /**
     * 清除map中空值
     *
     * @param params
     * @return
     */
    public static Map<String, String> cleanNullToMap(Map<String, String> params) {
        Map<String, String> map = Maps.newHashMap();
        if (params != null) {
            for (String key : params.keySet()) {
                if (StringUtils.isNotEmpty(params.get(key)) && !"null".equalsIgnoreCase(params.get(key).trim())) {
                    map.put(key, params.get(key));
                }
            }
        }
        return map;
    }

    /**
     * 比较传入对象的值的改变
     *
     * @param oldObj
     * @param newObj
     */
    public static List<Map<String, Object>> compareOldAndNewObject(Object oldObj, Object newObj) throws IllegalAccessException {
        List<Map<String, Object>> resultList = new ArrayList<>();
        //获取对象的class
        Class<?> oldClass = oldObj.getClass();
        Class<?> newClass = newObj.getClass();
        //获取对象的属性列表
        Field[] oldField = oldClass.getDeclaredFields();
        Field[] newField = newClass.getDeclaredFields();
        //遍历属性列表oldField
        for (int i = 0; i < oldField.length; i++) {
            //遍历属性列表newField
            for (int j = 0; j < newField.length; j++) {
                //如果oldField属性名与newField属性名内容相同
                if (oldField[i].getName().equals(newField[j].getName())) {
                    oldField[i].setAccessible(true);
                    newField[j].setAccessible(true);
                    //如果oldField属性值与newField属性值内容不相同
                    if (compareEachOther(oldField[i].get(oldObj), newField[j].get(newObj))) {
                        Map<String, Object> map2 = new HashMap<>();
                        map2.put("field", oldField[i].getName());
                        map2.put("old", null == oldField[i].get(oldObj) ? "" : oldField[i].get(oldObj));
                        map2.put("new", null == oldField[j].get(newObj) ? "" : oldField[j].get(newObj));
                        resultList.add(map2);
                    }
                    break;
                }
            }
        }
        return resultList;
    }

    /**
     * 对比两个数据是否内容相同
     *
     * @param object1,object2
     * @return boolean类型
     */
    public static boolean compareEachOther(Object object1, Object object2) {

        if (null == object1 && null == object2) {
            return false;
        }
        if (null == object1 && null != object2) {
            return true;
        }
        return !object1.equals(object2);
    }
}
