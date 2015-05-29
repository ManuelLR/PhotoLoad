package es;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import es.shared.domain.flickr.FlickrPhoto;

public class testFlickr {

	private static final String PROTECTED_RESOURCE_URL = "https://api.flickr.com/services/rest/";
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
		System.out.println(authorizationUrl + "&perms=read");
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

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource with json and photos...");
		OAuthRequest request3 = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
		request3.addQuerystringParameter("method", "flickr.people.getPhotos");accessToken.getRawResponse();
		request3.addQuerystringParameter("api_key", apiKey);
		request3.addQuerystringParameter("user_id", "me");
		//request3.addQuerystringParameter("method", "flickr.people.getPhotos");

		request3.addQuerystringParameter("format", "json");
		service.signRequest(accessToken, request3);
		System.out.println("Esta será la URL que se mandará: "+request3.getUrl()+" o "+request3.getCompleteUrl());
		System.out.println(request3.toString());
		Response response3 = request3.send();
		//request3.send(Flickr.class);
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response3.getBody());
		try{
			System.out.println("Este es el texto que parseamos: "+response3.getBody().substring(14,response3.getBody().length()-1));
			JSONObject json = new JSONObject(response3.getBody().substring(14,response3.getBody().length()-1));
			JSONObject photos = json.getJSONObject("photos");
			String estado = json.getString("stat").toString();
			System.out.println("Estado de la petición: "+estado);
			JSONArray photo = photos.getJSONArray("photo");
			List<FlickrPhoto> fotos = parsePhoto(photo);
			for(FlickrPhoto foto:fotos){
				System.out.println(foto.getID());
			}
		}
		catch(Exception e){
			System.err.println(e);
		}
		System.out.println();
		System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
	}
	
	private static List<FlickrPhoto> parsePhoto(JSONArray photo) throws JSONException{
		List<FlickrPhoto> res =null;
		if (photo.length() > 0) {
			res=new ArrayList<FlickrPhoto>();
			for(int i=0;i<photo.length();i++){
				JSONObject foto = photo.getJSONObject(i);
				FlickrPhoto fotoParseada = new FlickrPhoto(foto.getString("id"), foto.getString("owner"), foto.getString("title"));
			res.add(fotoParseada);
		}}
		return res;
	}

}
