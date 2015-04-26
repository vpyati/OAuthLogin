package com.vikram.openconnect.login.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vikram.openconnect.login.core.IOpenconnectDiscovery;
import com.vikram.openconnect.login.core.IOpenconnectDiscoveryFactory;
import com.vikram.openconnect.login.core.input.ICredentialInput;
import com.vikram.openconnect.login.core.input.IOAuthCredentials;
import com.vikram.openconnect.login.core.providers.OAuthProvider;

public abstract class OpenconnectLoginController {

	@Autowired
	private IOAuthCredentials oauthCredentials;
	
	@Autowired
	private IOpenconnectDiscoveryFactory discoveryFactory ;
	
	
	private ICredentialInput getCredentialByProvider(OAuthProvider provider){
		return oauthCredentials.getCredentialByProvider(provider);
	}
	
	protected abstract OAuthProvider getProvider();

	
	public String getOpenconnectUrl(){
		
		ICredentialInput credential = getCredentialByProvider(getProvider());
		IOpenconnectDiscovery discovery = discoveryFactory.get(getProvider());

		String authEndpointURL = discovery.getAuthorization_endpoint();

		StringBuilder url = new StringBuilder(authEndpointURL);
		url.append("?").append("client_id=").append(credential.getClientId() + "&").append("response_type=").append("code&").append("scope=")
				.append("openid%20email&").append("redirect_uri=").append(credential.getRedirectUri() + "&").append("state="+getProvider().name());

		return url.toString();	
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(){
				
		RedirectView rv =  new RedirectView(getOpenconnectUrl());
		return new ModelAndView(rv);				
	}

	
}
