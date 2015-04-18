package com.vikram.openconnect.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.vikram.openconnect.login.IOpenconnectDiscovery;
import com.vikram.openconnect.login.IOpenconnectDiscoveryFactory;
import com.vikram.openconnect.login.input.ICredentialInput;
import com.vikram.openconnect.login.input.IOAuthCredentials;
import com.vikram.openconnect.login.providers.OAuthProvider;
import com.vikram.openconnect.login.util.SpringBeanUtil;

public abstract class OpenconnectLoginController {

	@Autowired
	ApplicationContext ctx;
	
	
	private ICredentialInput getCredentialByProvider(OAuthProvider provider){
		return SpringBeanUtil.getInstance().getBean("oauthCredentials", IOAuthCredentials.class).getCredentialByProvider(provider);
	}
	
	protected abstract OAuthProvider getProvider();

	
	public String getOpenconnectUrl(){
		
		ICredentialInput credential = getCredentialByProvider(getProvider());
		IOpenconnectDiscovery discovery = ((IOpenconnectDiscoveryFactory) ctx.getBean("discoveryFactory")).get(getProvider());

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
