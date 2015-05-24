
package es.shared.domain.googledrive;

import java.io.Serializable;
import java.util.List;

public class Down implements Serializable{
   	private String alternateLink;
   	private boolean appDataContents;
   	private boolean copyable;
   	private String createdDate;
   	private String downloadUrl;
   	private boolean editable;
   	private String etag;
   	private String fileExtension;
   	private String fileSize;
   	private String headRevisionId;
   	private String iconLink;
   	private String id;
   	private String kind;
   	private Labels labels;
   	private LastModifyingUser lastModifyingUser;
   	private String lastModifyingUserName;
   	private String markedViewedByMeDate;
   	private String md5Checksum;
   	private String mimeType;
   	private String modifiedDate;
   	private String originalFilename;
   	private List ownerNames;
   	private List owners;
   	private List parents;
   	private String quotaBytesUsed;
   	private String selfLink;
   	private boolean shared;
   	private String thumbnailLink;
   	private String title;
   	private UserPermission userPermission;
   	private String version;
   	private String webContentLink;
   	private boolean writersCanShare;

 	public String getAlternateLink(){
		return this.alternateLink;
	}
	public void setAlternateLink(String alternateLink){
		this.alternateLink = alternateLink;
	}
 	public boolean getAppDataContents(){
		return this.appDataContents;
	}
	public void setAppDataContents(boolean appDataContents){
		this.appDataContents = appDataContents;
	}
 	public boolean getCopyable(){
		return this.copyable;
	}
	public void setCopyable(boolean copyable){
		this.copyable = copyable;
	}
 	public String getCreatedDate(){
		return this.createdDate;
	}
	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}
 	public String getDownloadUrl(){
		return this.downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl){
		this.downloadUrl = downloadUrl;
	}
 	public boolean getEditable(){
		return this.editable;
	}
	public void setEditable(boolean editable){
		this.editable = editable;
	}
 	public String getEtag(){
		return this.etag;
	}
	public void setEtag(String etag){
		this.etag = etag;
	}
 	public String getFileExtension(){
		return this.fileExtension;
	}
	public void setFileExtension(String fileExtension){
		this.fileExtension = fileExtension;
	}
 	public String getFileSize(){
		return this.fileSize;
	}
	public void setFileSize(String fileSize){
		this.fileSize = fileSize;
	}
 	public String getHeadRevisionId(){
		return this.headRevisionId;
	}
	public void setHeadRevisionId(String headRevisionId){
		this.headRevisionId = headRevisionId;
	}
 	public String getIconLink(){
		return this.iconLink;
	}
	public void setIconLink(String iconLink){
		this.iconLink = iconLink;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getKind(){
		return this.kind;
	}
	public void setKind(String kind){
		this.kind = kind;
	}
 	public Labels getLabels(){
		return this.labels;
	}
	public void setLabels(Labels labels){
		this.labels = labels;
	}
 	public LastModifyingUser getLastModifyingUser(){
		return this.lastModifyingUser;
	}
	public void setLastModifyingUser(LastModifyingUser lastModifyingUser){
		this.lastModifyingUser = lastModifyingUser;
	}
 	public String getLastModifyingUserName(){
		return this.lastModifyingUserName;
	}
	public void setLastModifyingUserName(String lastModifyingUserName){
		this.lastModifyingUserName = lastModifyingUserName;
	}
 	public String getMarkedViewedByMeDate(){
		return this.markedViewedByMeDate;
	}
	public void setMarkedViewedByMeDate(String markedViewedByMeDate){
		this.markedViewedByMeDate = markedViewedByMeDate;
	}
 	public String getMd5Checksum(){
		return this.md5Checksum;
	}
	public void setMd5Checksum(String md5Checksum){
		this.md5Checksum = md5Checksum;
	}
 	public String getMimeType(){
		return this.mimeType;
	}
	public void setMimeType(String mimeType){
		this.mimeType = mimeType;
	}
 	public String getModifiedDate(){
		return this.modifiedDate;
	}
	public void setModifiedDate(String modifiedDate){
		this.modifiedDate = modifiedDate;
	}
 	public String getOriginalFilename(){
		return this.originalFilename;
	}
	public void setOriginalFilename(String originalFilename){
		this.originalFilename = originalFilename;
	}
 	public List getOwnerNames(){
		return this.ownerNames;
	}
	public void setOwnerNames(List ownerNames){
		this.ownerNames = ownerNames;
	}
 	public List getOwners(){
		return this.owners;
	}
	public void setOwners(List owners){
		this.owners = owners;
	}
 	public List getParents(){
		return this.parents;
	}
	public void setParents(List parents){
		this.parents = parents;
	}
 	public String getQuotaBytesUsed(){
		return this.quotaBytesUsed;
	}
	public void setQuotaBytesUsed(String quotaBytesUsed){
		this.quotaBytesUsed = quotaBytesUsed;
	}
 	public String getSelfLink(){
		return this.selfLink;
	}
	public void setSelfLink(String selfLink){
		this.selfLink = selfLink;
	}
 	public boolean getShared(){
		return this.shared;
	}
	public void setShared(boolean shared){
		this.shared = shared;
	}
 	public String getThumbnailLink(){
		return this.thumbnailLink;
	}
	public void setThumbnailLink(String thumbnailLink){
		this.thumbnailLink = thumbnailLink;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public UserPermission getUserPermission(){
		return this.userPermission;
	}
	public void setUserPermission(UserPermission userPermission){
		this.userPermission = userPermission;
	}
 	public String getVersion(){
		return this.version;
	}
	public void setVersion(String version){
		this.version = version;
	}
 	public String getWebContentLink(){
		return this.webContentLink;
	}
	public void setWebContentLink(String webContentLink){
		this.webContentLink = webContentLink;
	}
 	public boolean getWritersCanShare(){
		return this.writersCanShare;
	}
	public void setWritersCanShare(boolean writersCanShare){
		this.writersCanShare = writersCanShare;
	}
}
