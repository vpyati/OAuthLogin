package test.com.vikram.openidconnect.login.web.google;

import org.springframework.util.Assert;

import com.vikram.openidconnect.login.core.google.GoogleOpenconnectDiscovery;

public class GoogleOpenconnectDiscoveryTest {
	
	public void testConstructor(){
		GoogleOpenconnectDiscovery discovery = new GoogleOpenconnectDiscovery();
		Assert.notNull(discovery.getIssuer());
		Assert.notNull(discovery.getAuthorization_endpoint());
		Assert.notNull(discovery.getToken_endpoint());
	}

	
	public static void main(String[] args){
		GoogleOpenconnectDiscoveryTest test = new GoogleOpenconnectDiscoveryTest();
		test.testConstructor();
	}
}
