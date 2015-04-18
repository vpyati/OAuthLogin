package com.vikram.openconnect.login;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.vikram.openconnect.login.identity.IdentityResovler;

@Configuration
public class OlaAppConfig extends WebMvcConfigurerAdapter{

	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(getIdentityResolver());
	}
	
	@Bean
	public IdentityResovler getIdentityResolver(){
		return new IdentityResovler();
	}
}
