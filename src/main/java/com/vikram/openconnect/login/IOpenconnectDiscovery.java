package com.vikram.openconnect.login;

public interface IOpenconnectDiscovery {
	
	public String getIssuer();

	public String getAuthorization_endpoint();

	public String getToken_endpoint();

	public String getUserinfo_endpoint();

	public String getRevocation_endpoint() ;

	public String getJwks_uri() ;

	public String[] getResponse_types_supported();

	public String[] getSubject_types_supported();

	public String[] getId_token_alg_values_supported();

}
