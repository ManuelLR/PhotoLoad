package es.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.shared.domain.flickr.FlickrAuth;
import es.shared.domain.flickr.FlickrPhoto;
import es.shared.domain.flickr.FlickrUploadPhoto;

@RemoteServiceRelativePath("flickr")
public interface FlickrAuthenticatedService extends RemoteService{

	public FlickrAuth authPrim();
	public FlickrAuth authSeg(FlickrAuth verifierCode);
	public List<FlickrPhoto> getPhotos(FlickrAuth auth);
	public List<FlickrPhoto> showPhotos(FlickrAuth auth, List<FlickrPhoto> toShow);
	public boolean uploadPhotos(FlickrAuth auth, List<FlickrUploadPhoto> toUpload);
}
