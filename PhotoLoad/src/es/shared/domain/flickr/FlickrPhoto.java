package es.shared.domain.flickr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class FlickrPhoto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3960713058537691537L;
	private String id;
	private String owner;
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	private List<FlickrSize> sizes = new ArrayList<FlickrSize>();
	public FlickrPhoto(){
		
	}
	public FlickrPhoto(String id, String owner, String title){
		this.id=id;
		this.owner=owner;
		this.title=title;
	}
	
	public String getID(){
		return id;
	}
	public void setID(String id){
		this.id=id;
	}
	public String getOwner(){
		return owner;
	}
	public void setOwner(String owner){
		this.owner=owner;
	}
	public List<FlickrSize> getSizes(){
		return sizes;
	}
	public void addSize(FlickrSize size){
		sizes.add(size);
	}
	
	public FlickrSize getSize(String a){
		for (FlickrSize e:sizes){
			if (e.getLabel().equals(a)){
				return e;
			}
		}
		return null;
	}

}
