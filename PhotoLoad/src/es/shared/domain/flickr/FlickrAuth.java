package es.shared.domain.flickr;

import java.io.Serializable;



public class FlickrAuth implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9100221051121580353L;

	private String urlForGetBaton;
	private TokenYo requestToken;
	private TokenYo accessToken;
	private String verifierCode;
	
	public FlickrAuth(){
		
	}
	public String getUrlForGetBaton() {
		return urlForGetBaton;
	}

	public void setUrlForGetBaton(String urlForGetBaton) {
		this.urlForGetBaton = urlForGetBaton;
	}

	public TokenYo getRequestToken() {
		return requestToken;
	}

	public void setRequestToken(TokenYo requestToken) {
		this.requestToken = requestToken;
	}

	public TokenYo getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(TokenYo accessToken) {
		this.accessToken = accessToken;
	}

	public String getVerifierCode() {
		return verifierCode;
	}

	public void setVerifierCode(String verifierCode) {
		this.verifierCode = verifierCode;
	}


}
