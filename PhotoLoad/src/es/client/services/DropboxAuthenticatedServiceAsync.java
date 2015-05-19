package es.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.shared.domain.dropbox.Folder;

public interface DropboxAuthenticatedServiceAsync {

	public void getFolder(String access_token, String id, AsyncCallback<Folder> callback);
	public void downloadFile(String access_token, String id, AsyncCallback<String> callback);

}
