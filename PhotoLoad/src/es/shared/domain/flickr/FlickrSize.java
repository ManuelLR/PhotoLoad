package es.shared.domain.flickr;

import java.io.Serializable;

public class FlickrSize implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4530116776730655378L;
	private String label;
	private String url;
	private String source;
	private Integer width;
	private Integer height;
	
	public FlickrSize(){
		
	}
	public FlickrSize(String label, String width, String height, String source, String url){
		this.label=label;
		this.width=Integer.parseInt(width.trim());
		this.height=Integer.parseInt(height.trim());
		this.source=source;
		this.url=url;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}


}
