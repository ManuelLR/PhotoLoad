package es;

import static org.junit.Assert.*;

import org.junit.Test;

import es.server.resources.GoogleDriveResource;
import es.shared.domain.googledrive.FileItem;
import es.shared.domain.googledrive.Files;

public class GDResourceTest {
	
	String token ="ya29.gwFZkXK8A2nSdbUa0Tx_hi2QmAOrzgoRxvqGRsC7m8BibdjKCnImBXwnDfCtIVJEwsQgB27DcGhxtQ";
	String id ="";
	
	@Test
	public void testGetAll(){
		System.out.println("Prueba GD1");
		GoogleDriveResource gdr = new GoogleDriveResource(token);
		Files files = gdr.getFiles();
		
		assertNotNull("The collection of files is nulll", files);
		
		System.out.println("Listing all files:");
		for(FileItem fi : files.getItems()){
			System.out.println("FileItemID " + fi.getId() + " - FileName:" + fi.getOriginalFilename());
		}
	}
	
	@Test
	public void testDownload(){
		System.out.println("Prueba GD2");
		String url;
		GoogleDriveResource gdr = new GoogleDriveResource(token);
		url = gdr.getFiles().getItems().get(1).getWebContentLink();
		assertNotNull("The url is null", url);
		System.out.println(url);
	}

}
