package es.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.shared.domain.flickr.FlickrAuth;
import es.shared.domain.flickr.FlickrPhoto;

public interface FlickrAuthenticatedServiceAsync {

	void authPrim(AsyncCallback<FlickrAuth> callback);
	void authSeg(FlickrAuth verifierCode, AsyncCallback<FlickrAuth> callback);
	void getPhotos(FlickrAuth auth, AsyncCallback<List<FlickrPhoto>> callback);
	void showPhotos(FlickrAuth auth, List<FlickrPhoto> toShow,
			AsyncCallback<List<FlickrPhoto>> callback);


}
