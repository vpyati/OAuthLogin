package com.vikram.openconnect.login.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class SpringBeanUtil {
	
	@Autowired
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
}
