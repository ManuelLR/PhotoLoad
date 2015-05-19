
package es.shared.domain.dropbox;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)

public class Shared_folder implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = 2508174103543525790L;
	private String shared_folder_id;

 	public String getShared_folder_id(){
		return this.shared_folder_id;
	}
	public void setShared_folder_id(String shared_folder_id){
		this.shared_folder_id = shared_folder_id;
	}
}
