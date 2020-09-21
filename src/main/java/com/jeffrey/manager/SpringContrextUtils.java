package com.jeffrey.manager;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Description: Spring 操作容器
 *
 * @author WQ
 * @date 2020/8/24 11:12 AM
 */
@Component
public class SpringContrextUtils implements ApplicationContextAware {

	public static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;

	}

	/**
	 * 获取applicationContext对象
	 *
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 根据bean的id来查找对象
	 *
	 * @param id
	 * @return
	 */
	public static Object getObject(String id) {

		return applicationContext.getBean(id);
	}

	/**
	 * 根据bean的class来查找对象
	 *
	 * @param c
	 * @param <T>
	 * @return
	 */
	public static <T> T getBeanByClass(Class<T> c) {

		return applicationContext.getBean(c);
	}

	/**
	 * 根据bean的名称和class来查找对象
	 *
	 * @param name
	 * @param c
	 * @param <T>
	 * @return
	 */
	public static <T> T getBeanByName(String name, Class<T> c) {

		return applicationContext.getBean(name, c);
	}
}
