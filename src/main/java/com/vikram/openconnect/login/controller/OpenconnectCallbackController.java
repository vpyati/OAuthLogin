package com.vikram.openconnect.login.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vikram.openconnect.login.IAccessToken;
import com.vikram.openconnect.login.IIdentityFetcher;
import com.vikram.openconnect.login.TokenResponse;

public abstract class OpenconnectCallbackController {
	
	@Autowired
	private IIdentityFetcher identityFetcher;
		
	@Autowired
	private IAccessToken accessToken;

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView callBack(@RequestParam("code")String code, @RequestParam("state")String state, HttpServletResponse response){
				
		TokenResponse tokenResponse = identityFetcher.getTokenResponse(code);
		accessToken.setAccessToken(tokenResponse.getAccess_token(),response);	
		return redirect();	
	}

	public abstract String getRedirectView();

	private ModelAndView redirect() {
		RedirectView view = new RedirectView(getRedirectView(),true);		
		return new ModelAndView(view);
	}

}
