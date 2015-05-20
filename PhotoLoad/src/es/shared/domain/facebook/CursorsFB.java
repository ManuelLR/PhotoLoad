
package es.shared.domain.facebook;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CursorsFB implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = 2273022361475895096L;
	private String after;
   	private String before;

 	public String getAfter(){
		return this.after;
	}
	public void setAfter(String after){
		this.after = after;
	}
 	public String getBefore(){
		return this.before;
	}
	public void setBefore(String before){
		this.before = before;
	}
}
