
package es.shared.domain.facebook;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FBPhotoUpload implements Serializable{

   	/**
	 * 
	 */
	private static final long serialVersionUID = -4786429748489104006L;

	private String url;

   	private String name;


 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
/* 	public Name_tags getName_tags(){
		return this.name_tags;
	}
	public void setName_tags(Name_tags name_tags){
		this.name_tags = name_tags;
	}*/
 
	public void setURL(String text) {
		this.url=text;		
	}
	public String getURL() {
		return url;		
	}
}
