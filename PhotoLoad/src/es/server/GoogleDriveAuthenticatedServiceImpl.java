package es.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import es.client.services.GoogleDriveAuthenticatedService;
import es.server.resources.GoogleDriveResource;
import es.shared.domain.googledrive.FileItem;
import es.shared.domain.googledrive.Files;

public class GoogleDriveAuthenticatedServiceImpl extends RemoteServiceServlet
		implements GoogleDriveAuthenticatedService {

	@Override
	public Files getFiles(String access_token) {
		GoogleDriveResource gdr = new GoogleDriveResource(access_token);
		return gdr.getFiles();
	}

	@Override
	public FileItem getFile(String access_token, String id) {
		GoogleDriveResource gdr = new GoogleDriveResource(access_token);
		return gdr.getFile(id);
	}

	@Override
	public String insertFile(String access_token, FileItem item, String content) {
		GoogleDriveResource gdr = new GoogleDriveResource(access_token);
		return gdr.insertFile(item,content);
	}

	@Override
	public boolean updateFile(String access_token, FileItem item) {
		GoogleDriveResource gdr = new GoogleDriveResource(access_token);
		return gdr.updateFile(item);
	}

	@Override
	public boolean deleteFile(String access_token, String id) {
		GoogleDriveResource gdr = new GoogleDriveResource(access_token);
		return gdr.deleteFile(id);
	}

	

}
