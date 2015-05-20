package es.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.shared.domain.facebook.FBPhotoUpload;
import es.shared.domain.facebook.FacebookPhotos;
import es.shared.domain.facebook.Struct;

public interface FacebookAuthenticatedServiceAsync {

	void findPhotos(String access_token, AsyncCallback<FacebookPhotos> callback);

	void uploadPhoto(String access_token, FBPhotoUpload foto,
			AsyncCallback<Struct> callback);

}
