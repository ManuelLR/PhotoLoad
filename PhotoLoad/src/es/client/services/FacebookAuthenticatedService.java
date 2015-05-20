package es.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.shared.domain.facebook.FBPhotoUpload;
import es.shared.domain.facebook.FacebookPhotos;
import es.shared.domain.facebook.Struct;

@RemoteServiceRelativePath("facebook")
public interface FacebookAuthenticatedService extends RemoteService {
	
	public FacebookPhotos findPhotos(String access_token);
	Struct uploadPhoto(String access_token, FBPhotoUpload foto);

}
