package com.vikram.openconnect.login.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanUtil implements ApplicationContextAware {
	
	private ApplicationContext ctx;
	
	private static SpringBeanUtil instance = new SpringBeanUtil();
	
	private SpringBeanUtil(){
		
	}
	
	public static SpringBeanUtil getInstance(){
		return instance;
	}
	
	
	public <T> T getBean(String name, Class<T> claz){		
		return ctx.getBean(name, claz);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;
	}
}
