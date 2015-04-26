package com.vikram.openidconnect.login.web.identity;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.vikram.openidconnect.login.core.identity.Identity;
import com.vikram.openidconnect.login.core.identity.IdentityAccessor;
import com.vikram.openidconnect.login.core.providers.OAuthProvider;
import com.vikram.openidconnect.login.web.IAccessToken;

public class IdentityResovler implements HandlerMethodArgumentResolver{
	
	private IdentityAccessor identityAccessor;
	
	private IAccessToken accessToken;

	public IdentityResovler(IdentityAccessor accessor, IAccessToken accessToken){
		this.identityAccessor = accessor;
		this.accessToken = accessToken;
	}
	
	@Override
	public Object resolveArgument(MethodParameter arg0,
			ModelAndViewContainer arg1, NativeWebRequest request,
			WebDataBinderFactory arg3) throws Exception {
		
		HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
		CurrentCredentials currentCredentials = getCurrentCredentials(servletRequest);
		if(currentCredentials == null){
			return Identity.INVALID_USER;
		}
				
		try {
			return identityAccessor.getIdentity(currentCredentials.accessTokenString, currentCredentials.provider);
		} catch (HttpException e) {
			return Identity.INVALID_USER;
		}
	}

	private CurrentCredentials getCurrentCredentials(HttpServletRequest servletRequest) {

		CurrentCredentials currentCredentials = null;
		
		for(OAuthProvider provider:OAuthProvider.values()){			
			String accessTokenString = accessToken.getAccessToken(servletRequest, provider);
			if(accessTokenString!=null){
				currentCredentials = new CurrentCredentials(accessTokenString, provider);
			}
		}
		return currentCredentials;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		 return parameter.getParameterType().equals(Identity.class);
	}
	
	
	private class CurrentCredentials{
		
		String accessTokenString;
		OAuthProvider provider;
		
		CurrentCredentials(String accessTokenString,OAuthProvider provider) {
			this.accessTokenString = accessTokenString;
			this.provider = provider;
		}
		
	}
	
}