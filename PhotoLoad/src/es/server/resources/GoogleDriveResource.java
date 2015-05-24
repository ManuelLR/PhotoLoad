package es.server.resources;
import java.util.Map;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import es.shared.domain.googledrive.Files;
import es.shared.domain.googledrive.FileItem;

public class GoogleDriveResource {
	private String uri 			= "https://www.googleapis.com/drive/v2/files";
	private String access_token;
	private String uri_upload 	= "https://www.googleapis.com/upload/drive/v2/files";
	
	public GoogleDriveResource(String access_token) {
		this.access_token = access_token;
	}
	
	/**
	 * 
	 * @return Files
	 */
	public Files getFiles() {	
		ClientResource cr = null;
		Files files = null;
		try {
			cr = new ClientResource(uri + "?access_token=" + access_token);
			files = cr.get(Files.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving all files: " + cr.getResponse().getStatus());
		}
		
		return files;

	}
	
	public FileItem getFile(String id) {
		
		ClientResource cr = null;
		FileItem file = null;
		try {
			cr = new ClientResource(uri + "/"+id+"?access_token=" + access_token);
			file = cr.get(FileItem.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving file: " + cr.getResponse().getStatus());
		}
		
		return file;

	}
	
	public String insertFile(FileItem file, String content) {
		
		ClientResource cr = null;
		String newId = null;
		try {
			cr = new ClientResource(uri + "?access_token=" + access_token);
			FileItem newFile = cr.post(file,FileItem.class);
			newId = newFile.getId();		
			
			cr = new ClientResource(uri_upload + "/"+ newId+"?uploadType=media&access_token=" + access_token);
			Map<String,Object> headers = cr.getRequestAttributes();
			headers.put("Content-Type", "text/plain");
			cr.put(content);
		} catch (ResourceException re) {
			System.err.println("Error when inserting file: " + cr.getResponse().getStatus());
		}
		return newId;
	}
	
	
	public boolean updateFile(FileItem file) {
		
		ClientResource cr = null;
		boolean result = true;
		try {			
			cr = new ClientResource(uri + "/"+ file.getId()+"?access_token=" + access_token);
			cr.put(file);
		} catch (ResourceException re) {
			System.err.println("Error when updating file: " + cr.getResponse().getStatus());
			result = false;
		}
		return result;
	}
	
	
	
	public boolean deleteFile(String id) {
		
		ClientResource cr = null;
		boolean result = true;
		try {
			cr = new ClientResource(uri + "/"+ id+"?access_token=" + access_token);
			cr.delete();
		} catch (ResourceException re) {
			System.err.println("Error when deleting file: " + cr.getResponse().getStatus());
			result = false;
		}
		return result;

	}
	
}

