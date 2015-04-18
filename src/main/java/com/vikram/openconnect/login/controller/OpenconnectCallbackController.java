package com.vikram.openconnect.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public abstract class OpenconnectCallbackController {
	
	private static String AUTHORIZATION_COOKIE_NAME = "authorization_code";
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView callBack(@RequestParam("code")String code, @RequestParam("state")String state, HttpServletRequest request, HttpServletResponse response){
		
		addUpdateCookie(code, state, request, response);
		
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
