package es.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.shared.domain.googledrive.FileItem;
import es.shared.domain.googledrive.Files;

public interface GoogleDriveAuthenticatedServiceAsync {

	void getFiles(String access_token, AsyncCallback<Files> callback);

	void deleteFile(String access_token, String id,
			AsyncCallback<Boolean> callback);

	void getFile(String access_token, String id,
			AsyncCallback<FileItem> callback);

	void insertFile(String access_token, FileItem item, String content,
			AsyncCallback<String> callback);

	void updateFile(String access_token, FileItem item,	AsyncCallback<Boolean> callback);

}
