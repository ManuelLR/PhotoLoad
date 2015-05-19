
package es.shared.domain.dropbox;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)

public class Contents implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = -214019275043797193L;
	private Number bytes;
   	private String client_mtime;
   	private String icon;
   	private boolean is_dir;
   	private String mime_type;
   	private String modified;
   	private Modifier modifier;
   	private String parent_shared_folder_id;
   	private String path;
   	private boolean read_only;
   	private String rev;
   	private Number revision;
   	private String root;
   	private String size;
   	private boolean thumb_exists;

 	public Number getBytes(){
		return this.bytes;
	}
	public void setBytes(Number bytes){
		this.bytes = bytes;
	}
 	public String getClient_mtime(){
		return this.client_mtime;
	}
	public void setClient_mtime(String client_mtime){
		this.client_mtime = client_mtime;
	}
 	public String getIcon(){
		return this.icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
 	public boolean getIs_dir(){
		return this.is_dir;
	}
	public void setIs_dir(boolean is_dir){
		this.is_dir = is_dir;
	}
 	public String getMime_type(){
		return this.mime_type;
	}
	public void setMime_type(String mime_type){
		this.mime_type = mime_type;
	}
 	public String getModified(){
		return this.modified;
	}
	public void setModified(String modified){
		this.modified = modified;
	}
 	public Modifier getModifier(){
		return this.modifier;
	}
	public void setModifier(Modifier modifier){
		this.modifier = modifier;
	}
 	public String getParent_shared_folder_id(){
		return this.parent_shared_folder_id;
	}
	public void setParent_shared_folder_id(String parent_shared_folder_id){
		this.parent_shared_folder_id = parent_shared_folder_id;
	}
 	public String getPath(){
		return this.path;
	}
	public void setPath(String path){
		this.path = path;
	}
 	public boolean getRead_only(){
		return this.read_only;
	}
	public void setRead_only(boolean read_only){
		this.read_only = read_only;
	}
 	public String getRev(){
		return this.rev;
	}
	public void setRev(String rev){
		this.rev = rev;
	}
 	public Number getRevision(){
		return this.revision;
	}
	public void setRevision(Number revision){
		this.revision = revision;
	}
 	public String getRoot(){
		return this.root;
	}
	public void setRoot(String root){
		this.root = root;
	}
 	public String getSize(){
		return this.size;
	}
	public void setSize(String size){
		this.size = size;
	}
 	public boolean getThumb_exists(){
		return this.thumb_exists;
	}
	public void setThumb_exists(boolean thumb_exists){
		this.thumb_exists = thumb_exists;
	}
}
