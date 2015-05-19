
package es.shared.domain.dropbox;

import java.io.Serializable;
import java.util.List;



import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)

public class Folder implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = -4733887086147804814L;
	private Number bytes;
   	private List<Contents> contents;
   	private String hash;
   	private String icon;
   	private boolean is_dir;
   	private String modified;
   	private String modifier;
   	private String path;
   	private boolean read_only;
   	private String rev;
   	private Number revision;
   	private String root;
   	private Shared_folder shared_folder;
   	private String size;
   	private boolean thumb_exists;

 	public Number getBytes(){
		return this.bytes;
	}
	public void setBytes(Number bytes){
		this.bytes = bytes;
	}
 	public List<Contents> getContents(){
		return this.contents;
	}
	public void setContents(List<Contents> contents){
		this.contents = contents;
	}
 	public String getHash(){
		return this.hash;
	}
	public void setHash(String hash){
		this.hash = hash;
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
 	public String getModified(){
		return this.modified;
	}
	public void setModified(String modified){
		this.modified = modified;
	}
 	public String getModifier(){
		return this.modifier;
	}
	public void setModifier(String modifier){
		this.modifier = modifier;
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
 	public Shared_folder getShared_folder(){
		return this.shared_folder;
	}
	public void setShared_folder(Shared_folder shared_folder){
		this.shared_folder = shared_folder;
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
