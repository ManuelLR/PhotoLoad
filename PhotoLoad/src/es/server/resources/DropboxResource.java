package es.server.resources;



import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import es.shared.domain.dropbox.Contents;
import es.shared.domain.dropbox.Folder;

public class DropboxResource {

	private String uri 			= "https://api.dropbox.com/1/metadata/auto";
	private String uriDown 			= "https://api-content.dropbox.com/1/files/auto";
	private String uri_upload = "https://api-content.dropbox.com/1/files_put/auto";

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
	
	
	public String insertFile(String path, Contents file, String content) {
		ClientResource cr = null;
		String newId = null;
		try {
			/*cr = new ClientResource(uri + path + "?access_token="+access_token);
			Contents newFile = cr.post(file,Contents.class);
			newId = newFile.getPath();*/
			
			cr = new ClientResource(uri_upload + path+"?access_token="+access_token);
			/*Map<String,Object> headers = cr.getRequestAttributes();
			headers.put("Content-Type", "text/plane");*/
			//content = content.
			cr.put(content);
		} catch (ResourceException re) {
			System.err.println("Error when inserting file: " + cr.getResponse().getStatus());
		}
		return newId;
	}
}
