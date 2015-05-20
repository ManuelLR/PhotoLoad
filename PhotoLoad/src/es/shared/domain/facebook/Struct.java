package es.shared.domain.facebook;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class Struct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6190734661741712834L;
	private String id;
	private String post_id;

 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getPost_id(){
		return this.post_id;
	}
	public void setPost_id(String post_id){
		this.post_id = post_id;
	}
}
