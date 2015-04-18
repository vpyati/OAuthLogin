package com.vikram.openconnect.login.identity;

public interface Identity {
	
	public boolean isValid();
	
	public String getName();
	
	public String getEmailAddress();
	
	public Identity INVALID_USER = new Identity(){

		public boolean isValid() {
			return false;
		}

		public String getName() {
			return "";
		}

		public String getEmailAddress() {
			return "";
		}		
	};
	
}