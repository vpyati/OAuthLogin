package com.vikram.openconnect.login.input;

import java.util.List;

import com.vikram.openconnect.login.exception.NoOAuthCredentialException;
import com.vikram.openconnect.login.providers.OAuthProvider;

public class OAuthCredentials implements IOAuthCredentials {
	
	private List<ICredentialInput> input;
	
	public OAuthCredentials(List<ICredentialInput> input){
		
		if(input == null || input.isEmpty()){
			throw new NoOAuthCredentialException("No OAuth credentials available");
		}
		
		this.input = input;
	}
	
	public ICredentialInput getCredentialByProvider(OAuthProvider provider){
		
		ICredentialInput credential = null;
		
		for(ICredentialInput inputCredential:input){
			if(inputCredential.getProvider() == provider){
				credential = inputCredential;
				break;
			}
		}
		
		return credential;
	}
}
