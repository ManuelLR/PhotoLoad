package es.shared;

import java.util.ArrayList;
import java.util.List;

import es.shared.domain.flickr.FlickrAuth;

public class IntViews {

	public enum To {
		FACEBOOK, DROPBOX, GOOGLEDRIVE, FLICKR, NONE
	}
	
	private  String FBToken="", DropboxToken="", GDToken="";
	private  FlickrAuth flickrToken = null;
	private  To to= To.NONE;
	private  List<String> link = new ArrayList<String>();
	private String anchoAbsoluto="1366px", anchoRelativo="1366px";
	
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
	public void addLink(String link) {
		if(this.link.isEmpty()){
			this.link.add(link);
		}else{
			this.link.set(0, link);
		}
	}

	public String getAnchoAbsoluto() {
		return anchoAbsoluto;
	}

	public String getAnchoRelativo() {
		return anchoRelativo;
	}

	public void setAnchoAbsoluto(String anchoAutomatico) {
		this.anchoAbsoluto = anchoAutomatico;
	}

	public void setAnchoRelativo(String anchoRelativo) {
		this.anchoRelativo = anchoRelativo;
	}
	
	
}
