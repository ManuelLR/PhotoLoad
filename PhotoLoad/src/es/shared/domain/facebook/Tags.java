
package es.shared.domain.facebook;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Tags implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = 5696177501140049440L;
	private List data;
   	private Paging paging;

 	public List getData(){
		return this.data;
	}
	public void setData(List data){
		this.data = data;
	}
 	public Paging getPaging(){
		return this.paging;
	}
	public void setPaging(Paging paging){
		this.paging = paging;
	}
}
