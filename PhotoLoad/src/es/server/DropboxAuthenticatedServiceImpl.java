package es.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import es.client.services.DropboxAuthenticatedService;
import es.shared.domain.dropbox.Contents;
import es.shared.domain.dropbox.Folder;
import es.server.resources.DropboxResource;

public class DropboxAuthenticatedServiceImpl extends RemoteServiceServlet
		implements DropboxAuthenticatedService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4226871562889402075L;

	@Override
	public Folder getFolder(String access_token, String id) {
		DropboxResource dr = new DropboxResource(access_token);
		return dr.getFolder(id);
	}

	public String downloadFile(String access_token, String id) {
		DropboxResource dr = new DropboxResource(access_token);
		return dr.downloadFile(id);
	}
	
	public String insertFile(String access_token, String path, Contents file, String content) {
		DropboxResource dr = new DropboxResource(access_token);
		return dr.insertFile(path, file, content);
	}


	

}
