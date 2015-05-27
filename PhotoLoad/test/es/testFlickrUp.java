package es;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.mortbay.util.ByteArrayOutputStream2;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;


public class testFlickrUp {

	private static final String PROTECTED_RESOURCE_URL = "https://api.flickr.com/services/rest/";
	private static final String PROTECTED_UPLOAD_URL="https://up.flickr.com/services/upload/";
	private static Scanner in;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Replace these with your own api key and secret
		String apiKey = "1579e0a3ac1b1c2015f630bf0c36b711";
		String apiSecret = "f6ed288fd22f97b6";
		OAuthService service = new ServiceBuilder().provider(FlickrApi.class).apiKey(apiKey).apiSecret(apiSecret).build();
		in = new Scanner(System.in);

		System.out.println("=== Flickr's OAuth Workflow ===");
		System.out.println();

		// Obtain the Request TokenYo
		System.out.println("Fetching the Request TokenYo...");
		Token requestToken = service.getRequestToken();
		System.out.println("Got the Request TokenYo!");
		System.out.println();

		System.out.println("Now go and authorize Scribe here:");
		String authorizationUrl = service.getAuthorizationUrl(requestToken);
		System.out.println(authorizationUrl + "&perms=write");
		System.out.println("And paste the verifier here");
		System.out.print(">>");
		Verifier verifier = new Verifier(in.nextLine());
		System.out.println();

		// Trade the Request TokenYo and Verfier for the Access TokenYo
		System.out.println("Trading the Request TokenYo for an Access TokenYo...");
		Token accessToken = service.getAccessToken(requestToken, verifier);
		System.out.println("Got the Access TokenYo!");
		System.out.println("(if your curious it looks like this: " + accessToken + " )");
		System.out.println("(you can get the username, full name, and nsid by parsing the rawResponse: " + accessToken.getRawResponse() + ")");
		System.out.println();

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
		request.addQuerystringParameter("method", "flickr.test.login");
		// request.addQuerystringParameter("format", "json");
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getBody());

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource with json...");
		OAuthRequest request2 = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
		request2.addQuerystringParameter("method", "flickr.test.login");
		request2.addQuerystringParameter("format", "json");
		service.signRequest(accessToken, request2);
		Response response2 = request2.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response2.getBody());
		
		
		
/*
 * ACABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAR
 */
		
		
		
		//Now we are try to update a photo
		
		System.out.println("We try to update a photo");
		String rutaArchivo = "/home/manolo/Desktop/rect4207.png";
		//String rutaArchivo = "";
		OAuthRequest request3 = new OAuthRequest(Verb.POST, PROTECTED_UPLOAD_URL);
		String photo=rutaArchivo;
		
		//request3.addQuerystringParameter("method", "flickr.test.login");
		//request3.addQuerystringParameter("format", "json");
		
		service.signRequest(accessToken, request3);
		
		//request3.addQuerystringParameter("photo", rutaArchivo);
//		request3.addPayload(payload);
		
		try
	    {
	        MultipartEntity entity = new MultipartEntity();

	        //entity.addPart("status", new StringBody(message));       // THIS IS THE TWITTER MESSAGE
	        entity.addPart("photo", new FileBody(new File(photo)));  // THIS IS THE PHOTO TO UPLOAD

	        //ByteArrayOutputStream out = new ByteArrayOutputStream();
			ByteArrayOutputStream2 out = new ByteArrayOutputStream2();
	        entity.writeTo(out);

	        request3.addPayload(out.toByteArray());
	        request3.addHeader(entity.getContentType().getName(), entity.getContentType().getValue());
	    }
	    catch (UnsupportedEncodingException e) {e.printStackTrace();}
	    catch (IOException e) {e.printStackTrace();}
		
		
		Response response3 = request3.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response3.getBody());
	}
}
