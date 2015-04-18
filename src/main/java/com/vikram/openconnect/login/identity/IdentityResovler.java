package com.vikram.openconnect.login.identity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.WebUtils;

import com.vikram.openconnect.login.input.IOAuthCredentials;

public class IdentityResovler implements HandlerMethodArgumentResolver{
	
	@Autowired
	private IOAuthCredentials  inputCredentials;
	

	@Override
	public Object resolveArgument(MethodParameter arg0,
			ModelAndViewContainer arg1, NativeWebRequest request,
			WebDataBinderFactory arg3) throws Exception {
		
		Cookie authCode = WebUtils.getCookie(request.getNativeRequest(HttpServletRequest.class), "authorization_code");
		if(authCode!=null){
			return returnUser(authCode);
		}else{
			return Identity.INVALID_USER;
		}
	}

	private Identity returnUser(Cookie authCode) {		
		try {
			return new AuthCodeIdentity(authCode.getValue());
		} catch (HttpException e) {
			return Identity.INVALID_USER;
		}
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		 return parameter.getParameterType().equals(Identity.class);
	}
	
	
	
	
}