package es.server.resources;

import java.io.File;
import java.util.List;

import org.apache.http.entity.mime.MultipartEntity;

import org.apache.http.entity.mime.content.FileBody;
import org.mortbay.util.ByteArrayOutputStream2;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import es.shared.domain.flickr.FlickrAuth;
import es.shared.domain.flickr.FlickrUploadPhoto;

public class FlicUpReso {
	
	private OAuthService service;
	private static final String FLICKR_API_KEY = "1579e0a3ac1b1c2015f630bf0c36b711";
	private static final String FLICKR_PRIVATE_API_KEY = "f6ed288fd22f97b6";
	private static final String PROTECTED_UPLOAD_URL="https://up.flickr.com/services/upload/";


	@SuppressWarnings("deprecation")
	public static boolean uploadPhoto(OAuthService service, FlickrAuth auth, List<FlickrUploadPhoto> input){
		
		service = new ServiceBuilder().provider(FlickrApi.class)
				.apiKey(FLICKR_API_KEY).apiSecret(FLICKR_PRIVATE_API_KEY)
				.build();
		System.out.println("We try to update a photo");
		String rutaArchivo = "/home/manolo/Desktop/rect4207.png";
		//String rutaArchivo = "";
		OAuthRequest request3 = new OAuthRequest(Verb.POST, PROTECTED_UPLOAD_URL);
		String photo=rutaArchivo;
		
		//request3.addQuerystringParameter("method", "flickr.test.login");
		//request3.addQuerystringParameter("format", "json");
		
		service.signRequest(Oauth1.cambiaToken(auth.getAccessToken()), request3);
		
		//request3.addQuerystringParameter("photo", rutaArchivo);
//		request3.addPayload(payload);
		
		try
	    {
	        MultipartEntity entity = new MultipartEntity();

	        //entity.addPart("status", new StringBody(message));       // THIS IS THE TWITTER MESSAGE
	        entity.addPart("photo", new FileBody(new File(photo)));  // THIS IS THE PHOTO TO UPLOAD

	        ByteArrayOutputStream2 out = new ByteArrayOutputStream2();
	        	entity.writeTo(out);

	        request3.addPayload(out.toByteArray());
	        request3.addHeader(entity.getContentType().getName(), entity.getContentType().getValue());
	    }
/*	    catch (UnsupportedEncodingException e) {e.printStackTrace();}
	    catch (IOException e) {e.printStackTrace();}*/
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Response response3 = request3.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response3.getBody());
		
		//ByteArrayOutputStream2 out = new ByteArrayOutputStream2();*/
		return false;
	}

}
