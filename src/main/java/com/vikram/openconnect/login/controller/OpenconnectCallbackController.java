package com.vikram.openconnect.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vikram.openconnect.login.IIdentityFetcher;
import com.vikram.openconnect.login.TokenResponse;

public abstract class OpenconnectCallbackController {
	
	private static String AUTHORIZATION_COOKIE_NAME = "access_token";
	
	@Autowired
	private IIdentityFetcher identityFetcher;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView callBack(@RequestParam("code")String code, @RequestParam("state")String state, HttpServletRequest request, HttpServletResponse response){
		
		TokenResponse tokenResponse = identityFetcher.getTokenResponse(code);
		
		addUpdateCookie(tokenResponse.getAccess_token(), state, request, response);
		
		return redirect();	
	}

	public abstract String getRedirectView();

	private ModelAndView redirect() {
		RedirectView view = new RedirectView(getRedirectView(),true);		
		return new ModelAndView(view);
	}


	protected void addUpdateCookie(String code, String state, HttpServletRequest request, HttpServletResponse response) {
		response.addCookie(new Cookie(AUTHORIZATION_COOKIE_NAME, code));
	}
}
