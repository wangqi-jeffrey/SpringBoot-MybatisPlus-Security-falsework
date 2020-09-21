/**
 * Project Name: hhz-platform
 * File Name: AnalyzeUtil.java
 * Package Name: com.huizhaofang.hhz.utils
 * Date: 2019/3/18 21:04
 */
package com.jeffrey.utils;


import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 列表分析
 * @Author:滕国栋
 * @Date:2019/3/18
 */
public class AnalyzeUtil {
    /**
     * 比较两个列表，返回需要新增、编辑和删除的列表
     * left：编辑列表(元素来源于l2)
     * middle：新增列表(元素来源于l1)
     * right：删除列表(元素来源于l2)
     *
     * @param l1  新列表，通常代表界面入参
     * @param l2  原有列表，通常代表数据库已有内容
     * @param <T>
     * @return
     */
    public static <T extends Analyzable, K extends Analyzable> Triple<K, T> analyze(List<T> l1, List<K> l2) {
        List<K> left = new ArrayList<>();
        List<T> middle = new ArrayList<>();
        List<K> right = new ArrayList<>();

        if (CollectionUtils.isEmpty(l1) && CollectionUtils.isNotEmpty(l2)) {
            right = l2;
        }

        if (CollectionUtils.isNotEmpty(l1) && CollectionUtils.isEmpty(l2)) {
            middle = l1;
        }

        if (CollectionUtils.isNotEmpty(l1) && CollectionUtils.isNotEmpty(l2)) {
            Map<Object, T> map1 = new HashMap<>();
            List<String> keys1 = new ArrayList<>();
            l1.stream().forEach(o -> {
                map1.put(o.primaryCode(), o);
                keys1.add(o.primaryCode());
            });

            Map<Object, K> map2 = new HashMap<>();
            List<String> keys2 = new ArrayList<>();
            l2.stream().forEach(o -> {
                map2.put(o.primaryCode(), o);
                keys2.add(o.primaryCode());
            });

            List<String> keys = new ArrayList<>();
            // 编辑列表
            keys.addAll(keys1);
            keys.retainAll(keys2);
            if (CollectionUtils.isNotEmpty(keys)) {
                for (String key : keys) {
                    left.add(map2.get(key));
                }
            }
            keys.clear();

            // 新增列表
            keys.addAll(keys1);
            keys.removeAll(keys2);
            if (CollectionUtils.isNotEmpty(keys)) {
                for (String key : keys) {
                    middle.add(map1.get(key));
                }
            }
            keys.clear();

            // 删除列表
            keys.addAll(keys2);
            keys.removeAll(keys1);
            if (CollectionUtils.isNotEmpty(keys)) {
                for (String key : keys) {
                    right.add(map2.get(key));
                }
            }
            keys.clear();
        }

        return new Triple<>(left, middle, right);
    }

    /**
     * 要进行列表分析需继承该类，并重写primaryCode()方法
     */
    public static class Analyzable {
        /**
         * 对象的唯一标识码，作为对象之间相互比较时的判断标准；
         * 默认是hashCode，大多情况下需要自定义
         *
         * @return
         */
        public String primaryCode() {
            return Integer.toString(hashCode());
        }
    }

    /**
     * 三元组
     */
    public static class Triple<K, T> {
        private List<K> left;

        private List<T> middle;

        private List<K> right;

        public Triple(List<K> left, List<T> middle, List<K> right) {
            this.left = left;
            this.middle = middle;
            this.right = right;
        }

        public List<K> getLeft() {
            return left;
        }

        public void setLeft(List<K> left) {
            this.left = left;
        }

        public List<T> getMiddle() {
            return middle;
        }

        public void setMiddle(List<T> middle) {
            this.middle = middle;
        }

        public List<K> getRight() {
            return right;
        }

        public void setRight(List<K> right) {
            this.right = right;
        }
    }
}
