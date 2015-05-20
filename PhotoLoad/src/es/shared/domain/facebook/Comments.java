
package es.shared.domain.facebook;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Comments implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = -4598481910970835884L;
	private List<Data> data;
   	private Paging paging;

 	public List<Data> getData(){
		return this.data;
	}
	public void setData(List<Data> data){
		this.data = data;
	}
 	public Paging getPaging(){
		return this.paging;
	}
	public void setPaging(Paging paging){
		this.paging = paging;
	}
}
