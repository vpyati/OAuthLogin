package com.vikram.openconnect.login.identity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.WebUtils;

import com.vikram.openconnect.login.IIdentityFetcher;

public class IdentityResovler implements HandlerMethodArgumentResolver{
	
	private IIdentityFetcher identityFetcher;

	public IdentityResovler(IIdentityFetcher identityFetcher){
		this.identityFetcher = identityFetcher;
	}
	
	@Override
	public Object resolveArgument(MethodParameter arg0,
			ModelAndViewContainer arg1, NativeWebRequest request,
			WebDataBinderFactory arg3) throws Exception {
		
		Cookie accessToken = WebUtils.getCookie(request.getNativeRequest(HttpServletRequest.class), "access_token");
		if(accessToken!=null){
			return returnIdentity(accessToken);
		}else{
			return Identity.INVALID_USER;
		}
	}

	private Identity returnIdentity(Cookie authCode) {		
		try {
			return new AuthCodeIdentity(authCode.getValue(),identityFetcher);
		} catch (HttpException e) {
			return Identity.INVALID_USER;
		}
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		 return parameter.getParameterType().equals(Identity.class);
	}
	
	
	
	
}