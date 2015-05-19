package es.shared.domain.flickr;

import java.io.Serializable;

public class TokenYo implements Serializable {
	private static final long serialVersionUID = 715000866082812683L;

	private String token;
	private String secret;
	private String rawResponse;

	/**
	 * Default constructor
	 *
	 * @param token
	 *            token value. Can't be null.
	 * @param secret
	 *            token secret. Can't be null.
	 */
	
	public TokenYo(){
		
	}
	public TokenYo(String token, String secret) {
		this(token, secret, null);
	}

	public TokenYo(String token, String secret, String rawResponse) {
		checkNotNull(token, "TokenYo can't be null");
		checkNotNull(secret, "Secret can't be null");

		this.token = token;
		this.secret = secret;
		this.rawResponse = rawResponse;
	}

	public String getToken() {
		return token;
	}

	public String getSecret() {
		return secret;
	}

	public String getRawResponse() {
		if (rawResponse == null) {
			throw new IllegalStateException(
					"This token object was not constructed by scribe and does not have a rawResponse");
		}
		return rawResponse;
	}

	@Override
	public String toString() {
		String res = "TokenYo[" + token + "," + secret + "]";
		// return String.format("TokenYo[%s , %s]", token, secret);
		return res;
	}

	/**
	 * Returns true if the token is empty (token = "", secret = "")
	 */
	public boolean isEmpty() {
		return "".equals(this.token) && "".equals(this.secret);
	}

	/**
	 * Factory method that returns an empty token (token = "", secret = "").
	 *
	 * Useful for two legged OAuth.
	 */
	public static TokenYo empty() {
		return new TokenYo("", "");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		TokenYo that = (TokenYo) o;
		return token.equals(that.token) && secret.equals(that.secret);
	}

	@Override
	public int hashCode() {
		return 31 * token.hashCode() + secret.hashCode();
	}

	private void checkNotNull(Object input, String message) {
		if (input == null) {
			throw new NullPointerException(message);
		}
	}
}
