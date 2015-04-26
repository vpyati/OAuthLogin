package com.vikram.openconnect.login.identity;

import com.vikram.openconnect.login.providers.OAuthProvider;

public interface Identity {
	
	public boolean isValid();
	
	public String getName();
	
	public String getEmailAddress();
	
	public String getAccessToken();
	
	public OAuthProvider getProvider();
	
	public Identity INVALID_USER = new Identity(){

		@Override
		public boolean isValid() {
			return false;
		}

		@Override
		public String getName() {
			return "";
		}

		@Override
		public String getEmailAddress() {
			return "";
		}

		@Override
		public String getAccessToken() {
			return null;
		}

		@Override
		public OAuthProvider getProvider() {
			return null;
		}		
	};
	
}