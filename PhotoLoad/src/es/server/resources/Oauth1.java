package es.server.resources;

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.Verifier;
import org.scribe.oauth.*;

import es.shared.domain.flickr.FlickrAuth;
import es.shared.domain.flickr.TokenYo;

public class Oauth1 {

	private static final String FLICKR_API_KEY = "1579e0a3ac1b1c2015f630bf0c36b711";
	private static final String FLICKR_PRIVATE_API_KEY = "f6ed288fd22f97b6";

	private static OAuthService serviceOauth;


	// Primero se debe declarar la clase como new......
	// después se pide getAuto.... para pedirlo al usuario
	// Luego se introduce el verificador que se le muestra al usuario mediante el método introduceVerifierUser
	
	public Oauth1(String service) {
		//oAuth = new OAuthBase();
		if(service=="flickr"){
			serviceOauth = new ServiceBuilder().provider(FlickrApi.class).apiKey(FLICKR_API_KEY).apiSecret(FLICKR_PRIVATE_API_KEY).build();
		}else{
			System.out.println("error en el servicio Oauth");
		}
	}
	public FlickrAuth getAuthoritationLink() {
		// Obtain the Request TokenYo
		//requestToken = serviceOauth.getRequestToken();
		FlickrAuth res = new FlickrAuth();
		res.setRequestToken(cambiaToken(serviceOauth.getRequestToken()));
		//Autorizamos Scribe
		//String authorizationUrl = serviceOauth.getAuthorizationUrl(requestToken);
		res.setUrlForGetBaton(serviceOauth.getAuthorizationUrl(cambiaToken(res.getRequestToken())));
		return res;
	}
	public FlickrAuth introduceVerifierUser(FlickrAuth verifierCode) {
		Verifier verifier = new Verifier(verifierCode.getVerifierCode().trim());
		TokenYo accessToken = cambiaToken(serviceOauth.getAccessToken(cambiaToken(verifierCode.getRequestToken()), verifier));
		//System.out.println("(you can get the username, full name, and nsid by parsing the rawResponse: " + accessToken.getRawResponse() + ")");
		verifierCode.setAccessToken(accessToken);
		return verifierCode;
	}

	public String apikey(){
		return FLICKR_API_KEY;
	}
	
	public String privateapikey(){
		return FLICKR_PRIVATE_API_KEY;
	}
	
	public boolean test(){
			// Now let's go and ask for a protected resource!
		boolean result = true;
/*		String PROTECTED_RESOURCE_URL = "https://api.flickr.com/services/rest/";
			System.out.println("Now we're going to access a protected resource...");
			OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
			request.addQuerystringParameter("method", "flickr.test.login");
			serviceOauth.signRequest(accessToken, request);
			Response response = request.send();
			System.out.println("Got it! Lets see what we found...");
			System.out.println();
			System.out.println(response.getBody());
			if(){
				result = true;
			}*/
		return result;
	}
	
	public static org.scribe.model.Token cambiaToken(TokenYo input){
		 return new org.scribe.model.Token(input.getToken(), input.getSecret(), input.getRawResponse());
	}
	public static TokenYo cambiaToken(org.scribe.model.Token input){
		 return new TokenYo(input.getToken(), input.getSecret(), input.getRawResponse());
	}
}

