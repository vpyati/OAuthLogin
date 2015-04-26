package com.vikram.openconnect.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vikram.openconnect.login.core.providers.OAuthProvider;


public interface IAccessToken {
	
	public void setAccessToken(String accessToken, HttpServletResponse response,OAuthProvider provider);
	
	public String getAccessToken(HttpServletRequest request,OAuthProvider provider);
}
