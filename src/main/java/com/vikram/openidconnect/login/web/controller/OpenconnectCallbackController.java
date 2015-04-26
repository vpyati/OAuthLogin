package com.vikram.openidconnect.login.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vikram.openidconnect.login.core.IIdentityFetcher;
import com.vikram.openidconnect.login.core.TokenResponse;
import com.vikram.openidconnect.login.core.providers.OAuthProvider;
import com.vikram.openidconnect.login.web.IAccessToken;
import com.vikram.openidconnect.login.web.util.AuthCodeResolver;

public abstract class OpenconnectCallbackController {
	
	@Autowired
	private IIdentityFetcher identityFetcher;
		
	@Autowired
	private IAccessToken accessToken;

	private static Logger logger = LoggerFactory.getLogger(OpenconnectCallbackController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView callBack(@RequestParam("code")String code, @RequestParam("state")String state, HttpServletResponse response){
		
		logger.info("Resolving OAuthprovider");
		OAuthProvider provider = AuthCodeResolver.resolveState(state);
		
		logger.info("Fetching token response");
		TokenResponse tokenResponse = identityFetcher.getTokenResponse(code,provider);
		
		logger.info("Setting Access token");
		accessToken.setAccessToken(tokenResponse.getAccess_token(),response,provider);	
		
		logger.info("Redirecting after login");
		return redirect();	
	}

	public abstract String getRedirectView();

	private ModelAndView redirect() {
		RedirectView view = new RedirectView(getRedirectView(),true);		
		return new ModelAndView(view);
	}

}
