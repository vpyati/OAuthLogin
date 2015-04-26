package com.vikram.openconnect.login.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vikram.openconnect.login.core.IIdentityFetcher;
import com.vikram.openconnect.login.core.TokenResponse;
import com.vikram.openconnect.login.core.providers.OAuthProvider;
import com.vikram.openconnect.login.web.IAccessToken;
import com.vikram.openconnect.login.web.util.AuthCodeResolver;

public abstract class OpenconnectCallbackController {
	
	@Autowired
	private IIdentityFetcher identityFetcher;
		
	@Autowired
	private IAccessToken accessToken;

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView callBack(@RequestParam("code")String code, @RequestParam("state")String state, HttpServletResponse response){
		
		OAuthProvider provider = AuthCodeResolver.resolveState(state);
		TokenResponse tokenResponse = identityFetcher.getTokenResponse(code,provider);
		accessToken.setAccessToken(tokenResponse.getAccess_token(),response,provider);	
		return redirect();	
	}

	public abstract String getRedirectView();

	private ModelAndView redirect() {
		RedirectView view = new RedirectView(getRedirectView(),true);		
		return new ModelAndView(view);
	}

}
