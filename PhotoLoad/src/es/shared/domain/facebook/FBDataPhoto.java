
package es.shared.domain.facebook;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FBDataPhoto implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = 3308802113335647951L;
	private Comments comments;
   	private String created_time;
   	private From from;
   	private Number height;
   	private String icon;
   	private String id;
   	private List<String> images;
   	private Likes likes;
   	private String link;
   	private String name;
//   	private Name_tags name_tags;
   	private String picture;
   	private String source;
   	private Tags tags;
   	private String updated_time;
   	private Number width;

 	public Comments getComments(){
		return this.comments;
	}
	public void setComments(Comments comments){
		this.comments = comments;
	}
 	public String getCreated_time(){
		return this.created_time;
	}
	public void setCreated_time(String created_time){
		this.created_time = created_time;
	}
 	public From getFrom(){
		return this.from;
	}
	public void setFrom(From from){
		this.from = from;
	}
 	public Number getHeight(){
		return this.height;
	}
	public void setHeight(Number height){
		this.height = height;
	}
 	public String getIcon(){
		return this.icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public List<String> getImages(){
		return this.images;
	}
	public void setImages(List<String> images){
		this.images = images;
	}
 	public Likes getLikes(){
		return this.likes;
	}
	public void setLikes(Likes likes){
		this.likes = likes;
	}
 	public String getLink(){
		return this.link;
	}
	public void setLink(String link){
		this.link = link;
	}
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
 	public String getPicture(){
		return this.picture;
	}
	public void setPicture(String picture){
		this.picture = picture;
	}
 	public String getSource(){
		return this.source;
	}
	public void setSource(String source){
		this.source = source;
	}
 	public Tags getTags(){
		return this.tags;
	}
	public void setTags(Tags tags){
		this.tags = tags;
	}
 	public String getUpdated_time(){
		return this.updated_time;
	}
	public void setUpdated_time(String updated_time){
		this.updated_time = updated_time;
	}
 	public Number getWidth(){
		return this.width;
	}
	public void setWidth(Number width){
		this.width = width;
	}
}
