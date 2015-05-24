package es.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.shared.domain.googledrive.FileItem;
import es.shared.domain.googledrive.Files;
@RemoteServiceRelativePath("googledrive")
public interface GoogleDriveAuthenticatedService extends RemoteService {

	public Files 			getFiles(String access_token);
	public FileItem			getFile(String access_token,String id);
	public String			insertFile(String access_token,FileItem item, String content);
	public boolean 			updateFile(String access_token,FileItem item);
	public boolean			deleteFile(String access_token,String id);
	
	
}
