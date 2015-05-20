
package es.shared.domain.facebook;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FacebookPhotos implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = 7615830497749499027L;
	private List<FBDataPhoto> data;
//	private List data;
   	private Paging paging;

 	public List<FBDataPhoto> getData(){
		return this.data;
	}
	public void setData(List<FBDataPhoto> data){
		this.data = data;
	}
 	public Paging getPaging(){
		return this.paging;
	}
	public void setPaging(Paging paging){
		this.paging = paging;
	}
}
