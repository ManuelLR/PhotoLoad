package es.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import es.client.services.FacebookAuthenticatedService;
import es.server.resources.FacebookPhotosResource;
import es.shared.domain.facebook.FBPhotoUpload;
import es.shared.domain.facebook.FacebookPhotos;
import es.shared.domain.facebook.Struct;

public class FacebookAuthenticatedServiceImpl extends RemoteServiceServlet implements FacebookAuthenticatedService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3934320926799535188L;

	@Override
	public FacebookPhotos findPhotos(String access_token){
			//throws IllegalArgumentException {
		System.out.println("Entra en FacebookAuthenticatedServiceImpl");
		FacebookPhotosResource fbr = new FacebookPhotosResource(access_token);
		return fbr.getPhotos();
	}
	public Struct uploadPhoto(String access_token, FBPhotoUpload foto){
		//Struct res= new Struct();
		FacebookPhotosResource fbr = new FacebookPhotosResource(access_token);
		Struct res = fbr.uploadPhoto(foto);
		return res;
	}

}
