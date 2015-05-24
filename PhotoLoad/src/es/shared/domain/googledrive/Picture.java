
package es.shared.domain.googledrive;

import java.io.Serializable;
import java.util.List;

public class Picture implements Serializable{
   	private String url;

 	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
}
