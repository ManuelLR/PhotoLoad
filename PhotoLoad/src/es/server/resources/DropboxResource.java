package es.server.resources;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import es.shared.domain.dropbox.Folder;

public class DropboxResource {

	private String uri 			= "https://api.dropbox.com/1/metadata/auto/";
	private String uriDown 			= "https://api-content.dropbox.com/1/files/auto";

	private String access_token;
	
	public DropboxResource(String access_token) {
		this.access_token = access_token;
	}
	
	/**
	 * 
	 * @return Files
	 */
	public Folder getFolder(String id) {	
		ClientResource cr = null;
		Folder folder = null;
		try {
			cr = new ClientResource(uri + id + "?access_token=" + access_token);
			folder = cr.get(Folder.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving all files: " + cr.getResponse().getStatus());
		}
		
		return folder;

	}
	
	
	public String downloadFile(String id) {
		ClientResource cr = null;
		String res = null;
		//Folder folder = null;
		try {
			cr = new ClientResource(uriDown + "/" + id + "?access_token=" + access_token);
			res = uriDown + "/" + id + "?access_token=" + access_token;
			//folder = cr.get(Folder.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving all files: " + cr.getResponse().getStatus());
		}
		return res;
	}
}
