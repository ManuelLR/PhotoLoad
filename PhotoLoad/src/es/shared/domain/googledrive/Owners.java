
package es.shared.domain.googledrive;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Owners implements Serializable{
   	/**
	 * 
	 */
   	private String displayName;
   	private String emailAddress;
   	private boolean isAuthenticatedUser;
   	private String kind;
   	private String permissionId;
   	private Picture picture;

 	public String getDisplayName(){
		return this.displayName;
	}
	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}
 	public String getEmailAddress(){
		return this.emailAddress;
	}
	public void setEmailAddress(String emailAddress){
		this.emailAddress = emailAddress;
	}
 	public boolean getIsAuthenticatedUser(){
		return this.isAuthenticatedUser;
	}
	public void setIsAuthenticatedUser(boolean isAuthenticatedUser){
		this.isAuthenticatedUser = isAuthenticatedUser;
	}
 	public String getKind(){
		return this.kind;
	}
	public void setKind(String kind){
		this.kind = kind;
	}
 	public String getPermissionId(){
		return this.permissionId;
	}
	public void setPermissionId(String permissionId){
		this.permissionId = permissionId;
	}
 	public Picture getPicture(){
		return this.picture;
	}
	public void setPicture(Picture picture){
		this.picture = picture;
	}
}
