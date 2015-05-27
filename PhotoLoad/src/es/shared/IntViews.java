package es.shared;

import java.util.ArrayList;
import java.util.List;

import es.shared.domain.flickr.FlickrAuth;

public class IntViews {

	public enum To {
		FACEBOOK, DROPBOX, GOOGLEDRIVE, FLICKR
	}
	
	private  String FBToken, DropboxToken, GDToken;
	private  FlickrAuth flickrToken = new FlickrAuth();
	private  To to;
	private  List<String> link = new ArrayList<String>();
	
	
	
	
	
	public IntViews() {
	}
	
	
	
	public String getFBToken() {
		return FBToken;
	}
	public void setFBToken(String fBToken) {
		FBToken = fBToken;
	}
	public String getDropboxToken() {
		return DropboxToken;
	}
	public void setDropboxToken(String dropboxToken) {
		DropboxToken = dropboxToken;
	}
	public String getGDToken() {
		return GDToken;
	}
	public void setGDToken(String gDToken) {
		GDToken = gDToken;
	}
	public FlickrAuth getFlickrToken() {
		return flickrToken;
	}
	public void setFlickrToken(FlickrAuth flickrToken) {
		this.flickrToken = flickrToken;
	}
	public To getTo() {
		return to;
	}
	public void setTo(To to) {
		this.to = to;
	}
	public List<String> getLink() {
		return link;
	}
	public void setLink(List<String> link) {
		this.link = link;
	}
	
	
	
}
