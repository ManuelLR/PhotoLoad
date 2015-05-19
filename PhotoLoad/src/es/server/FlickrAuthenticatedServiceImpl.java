package es.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import es.client.services.FlickrAuthenticatedService;
import es.server.resources.FlickrResource;
import es.shared.domain.flickr.FlickrAuth;
import es.shared.domain.flickr.FlickrPhoto;


public class FlickrAuthenticatedServiceImpl extends RemoteServiceServlet implements FlickrAuthenticatedService{
	
	//public static final FlickrServiceImpl INSTANCE = new FlickrServiceImpl();
	/**
	 * 
	 */
	/*public FlickrServiceImpl(){
		
	}*/
	private static final long serialVersionUID = 1L;
	FlickrResource flr = new FlickrResource();
	
	public FlickrAuth authPrim(){
		return flr.authPrim();
		//return "Prueba primera autenticacion";
	}
	
	public  FlickrAuth authSeg(FlickrAuth verifierCode){
		return flr.authSeg(verifierCode);
		//return "Prueba segunda autenticación: \"" + key+"\".";
	}

	@Override
	public List<FlickrPhoto> getPhotos(FlickrAuth auth) {
		return flr.getPhotos(auth);
	}

	@Override
	public List<FlickrPhoto> showPhotos(FlickrAuth auth, List<FlickrPhoto> toShow) {
		// TODO Auto-generated method stub
		return flr.showPhotos(auth, toShow);
	}
}
