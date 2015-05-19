package es.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.shared.domain.dropbox.Folder;
@RemoteServiceRelativePath("dropbox")
public interface DropboxAuthenticatedService extends RemoteService {

	public Folder getFolder(String access_token, String id);
	public String downloadFile(String access_token, String id);


}
