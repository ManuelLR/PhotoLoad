
package es.shared.domain.facebook;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Paging implements Serializable{
   	/**
	 * 
	 */
	
	private static final long serialVersionUID = -7307874555330985480L;
	private String next;
	private CursorsFB cursors;
	//private CursorsFB cursorsFB;

 	public String getNext(){
		return this.next;
	}
	public void setNext(String next){
		this.next = next;
	}
	
	/*public Object getCursors(){
		return null;
	}
	
	public void setCursors(){
		
	}*/
 	/*public CursorsFB getCursors(){
		return this.cursors;
	}
	public void setNext(CursorsFB cursorsFB){
		this.cursors = cursorsFB;
	}*/
}
