package com.vikram.openconnect.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface IAccessToken {
	
	public void setAccessToken(String accessToken, HttpServletResponse response);
	
	public String getAccessToken(HttpServletRequest request);
}
