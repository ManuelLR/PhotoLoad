package es.server.resources;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import es.shared.domain.facebook.FBPhotoUpload;
import es.shared.domain.facebook.FacebookPhotos;
import es.shared.domain.facebook.Struct;

public class FacebookPhotosResource {

	private String uri = "https://graph.facebook.com/me/photos";
	private String access_token = null;
	
	
	public FacebookPhotosResource(String access_token) {
		this.access_token = access_token;
	}
		
	/**
	 * 
	 * @return FacebookFriends
	 */
	public FacebookPhotos getPhotos() {
		
		ClientResource cr = null;
		FacebookPhotos list = null;
		
		try {
			cr = new ClientResource(uri + "?access_token=" + access_token);
			System.out.println("Ha creado el recurso cliente");
			list = cr.get(FacebookPhotos.class);
			System.out.println("ha traido las fotos");
			
		} catch (ResourceException re) {
			System.err.println("Error al traer las fotos: " + cr.getResponse().getStatus());
			System.err.println(uri + "?access_token" + access_token);
		}
		
		return list;
	}
	
	public Struct uploadPhoto(FBPhotoUpload aSubir){
		ClientResource cr = null;
		Struct res=null;
		try{
			cr= new ClientResource(uri + "?access_token=" + access_token);
			/*Map<String,Object> headers = new HashMap<String,Object>();
			headers.put("access_token", access_token);*/
			//headers.put("Content-Type", "text/plain");
			res = cr.post(aSubir, Struct.class);
			//Struct newStruct = cr.post(aSubir);//, Struct.class);
			//cr.put(headers);
		}catch  (ResourceException re) {
			System.err.println("Este es el error :"+re);
		}
		return res;
	}
}
