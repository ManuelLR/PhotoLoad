package es.shared.domain.facebook;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Images implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4935476637435469145L;
	
	private String height;
	private String source;
	private String width;
	
	public String getHeight() {
		return height;
	}
	public String getSource() {
		return source;
	}
	public String getWidth() {
		return width;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setWidth(String width) {
		this.width = width;
	}



}
