package com.vikram.openconnect.login.identity;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.vikram.openconnect.login.IAccessToken;
import com.vikram.openconnect.login.IIdentityFetcher;

public class IdentityResovler implements HandlerMethodArgumentResolver{
	
	private IIdentityFetcher identityFetcher;
	
	private IAccessToken accessToken;

	public IdentityResovler(IIdentityFetcher identityFetcher, IAccessToken accessToken){
		this.identityFetcher = identityFetcher;
		this.accessToken = accessToken;
	}
	
	@Override
	public Object resolveArgument(MethodParameter arg0,
			ModelAndViewContainer arg1, NativeWebRequest request,
			WebDataBinderFactory arg3) throws Exception {
		
		try {
			return new AuthCodeIdentity(accessToken.getAccessToken(request.getNativeRequest(HttpServletRequest.class)),identityFetcher);
		} catch (HttpException e) {
			return Identity.INVALID_USER;
		}
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		 return parameter.getParameterType().equals(Identity.class);
	}	
}