package com.vikram.openconnect.login.google;

import com.vikram.openconnect.login.controller.OpenconnectLoginController;
import com.vikram.openconnect.login.providers.OAuthProvider;

public class GoogleOpenconnectLogin extends OpenconnectLoginController {

	@Override
	protected OAuthProvider getProvider() {
		return OAuthProvider.GOOGLE;
	}
}
