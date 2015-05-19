package es.server.resources;

import java.util.ArrayList;
import java.util.List;


//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import es.shared.domain.flickr.FlickrAuth;
import es.shared.domain.flickr.FlickrPhoto;
import es.shared.domain.flickr.FlickrSize;

public class FlickrResource {

	private static Oauth1 oauth;
	private static final String RESOURCE_URL = "https://api.flickr.com/services/rest/";
	private OAuthService service;

	public FlickrResource() {

	}

	public FlickrAuth authPrim() {
		oauth = new Oauth1("flickr");
		//String link = oauth.getAuthoritationLink();
		return oauth.getAuthoritationLink();
	}

	public FlickrAuth authSeg(FlickrAuth verifierCode) {
		//oauth.introduceVerifierUser(code);
		//String res = oauth.getToken().getToken();
		// service = new
		// ServiceBuilder().provider(FlickrApi.class).apiKey(oauth.apikey()).apiSecret(oauth.privateapikey()).build();
		return oauth.introduceVerifierUser(verifierCode);
	}

	public List<FlickrPhoto> getPhotos(FlickrAuth auth) {
		/*
		 * Si devuelve null es que ha habido un error Si devuelve una lista
		 * vacia es que no hay elementos (fotos)
		 */
		service = new ServiceBuilder().provider(FlickrApi.class)
				.apiKey(oauth.apikey()).apiSecret(oauth.privateapikey())
				.build();
		List<FlickrPhoto> res = null;
		OAuthRequest request = new OAuthRequest(Verb.GET, RESOURCE_URL);
		request.addQuerystringParameter("method", "flickr.people.getPhotos");
		request.addQuerystringParameter("api_key", oauth.apikey());
		request.addQuerystringParameter("user_id", "me");
		request.addQuerystringParameter("format", "json");

		service.signRequest(Oauth1.cambiaToken(auth.getAccessToken()), request);
		Response response = request.send();
		if (response.isSuccessful()) {
			try {
				if (response.getBody().length() > 14) {
					String respuestaStr = response.getBody();
					String textoAPasar = respuestaStr.substring(14,
							respuestaStr.length() - 1);

					JSONObject respuesta = new JSONObject(textoAPasar);
					if (respuesta.getString("stat").toString().equals("ok")) {
						res = parsePhotos(respuesta);
						new ArrayList<FlickrPhoto>(res);
					}

				} else {
					System.out.println("Esta es la respuesta incorrecta: "
							+ response.getBody());
				}
			} catch (JSONException e) {
				System.err.println("Se ha producido el siguiente error: " + e);
			}
		}
		return res;
	}

	private static List<FlickrPhoto> parsePhotos(JSONObject input)
			throws JSONException {
		List<FlickrPhoto> res = new ArrayList<FlickrPhoto>();
		JSONObject photos = input.getJSONObject("photos");
		JSONArray photo = photos.getJSONArray("photo");
		if (photo.length() > 0) {
			for (int i = 0; i < photo.length(); i++) {
				JSONObject foto = photo.getJSONObject(i);
				FlickrPhoto fotoParseada = new FlickrPhoto(
						foto.getString("id"), foto.getString("owner"), foto.getString("title"));
				res.add(fotoParseada);
			}
		}
		return res;
	}

	public List<FlickrPhoto> showPhotos(FlickrAuth auth, List<FlickrPhoto> input) {
		List<FlickrPhoto> res = null;
		if (input != null) {
			res = new ArrayList<FlickrPhoto>();
			if (!input.isEmpty()) {
				service = new ServiceBuilder().provider(FlickrApi.class)
						.apiKey(oauth.apikey())
						.apiSecret(oauth.privateapikey()).build();
				for (FlickrPhoto actualPhoto : input) {
					OAuthRequest request = new OAuthRequest(Verb.GET,
							RESOURCE_URL);
					request.addQuerystringParameter("method",
							"flickr.photos.getSizes");
					request.addQuerystringParameter("api_key", oauth.apikey());
					request.addQuerystringParameter("photo_id",
							actualPhoto.getID());
					request.addQuerystringParameter("format", "json");

					service.signRequest(Oauth1.cambiaToken(auth.getAccessToken()), request);
					Response response = request.send();
					if (response.isSuccessful()) {
						try {
							if (response.getBody().length() > 14) {
								String textoAPasar = response
										.getBody()
										.substring(14,
												response.getBody().length() - 1);
								JSONObject respuesta = new JSONObject(
										textoAPasar);
								if (respuesta.getString("stat").toString()
										.equals("ok")) {
									res.add(parseSizesPhoto(actualPhoto, respuesta));
								}
							} else {
								System.out
										.println("Esta es la respuesta incorrecta: "
												+ response.getBody());
							}
						} catch (JSONException e) {
							System.err
									.println("Se ha producido el siguiente error: "
											+ e);
						}
					}
				}
				new ArrayList<FlickrPhoto>(res);
			}
		}
		return res;
	}

	private FlickrPhoto parseSizesPhoto(FlickrPhoto inputPhoto, JSONObject input)
			throws JSONException {
		JSONObject sizes = input.getJSONObject("sizes");
		JSONArray sizeArray = sizes.getJSONArray("size");
		if (sizeArray.length() > 0) {
			for (int i = 0; i < sizeArray.length(); i++) {
				JSONObject a = sizeArray.getJSONObject(i);
				FlickrSize res = new FlickrSize(a.getString("label"),
						a.getString("width"), a.getString("height"),
						a.getString("source"), a.getString("url"));
				inputPhoto.addSize(res);
			}
		}
		return inputPhoto;
	}
}
