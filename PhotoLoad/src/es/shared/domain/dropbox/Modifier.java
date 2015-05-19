
package es.shared.domain.dropbox;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)

public class Modifier implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = -8182955200393502749L;
	private String display_name;
   	private Number uid;

 	public String getDisplay_name(){
		return this.display_name;
	}
	public void setDisplay_name(String display_name){
		this.display_name = display_name;
	}
 	public Number getUid(){
		return this.uid;
	}
	public void setUid(Number uid){
		this.uid = uid;
	}
}
