package es;

import static org.junit.Assert.*;
//import junit.framework.Assert;

import org.junit.Test;

import es.server.resources.DropboxResource;
import es.shared.domain.dropbox.Contents;
import es.shared.domain.dropbox.Folder;

public class DropboxResourceTest {

	String token = "CLUv1uDVmYkAAAAAAAABiCm0dgbjg8R-C4fv90D4QWlsbVTfRSrf1vJQX-dxCjGw";
	String id = "";
	
	@Test
	public void testGetAll() {
		System.out.println("PRUEBA 1");
		DropboxResource plr = new DropboxResource(token);
		Folder folder = plr.getFolder(id);
		
		// This will make the test to fail if the returned collection is null
		assertNotNull("The collection of files is null", folder);
		
		// Show result
		System.out.println("Listing all files:");
		for(Contents c:folder.getContents()){
			System.out.println("ItemID "+c.getRev()+" - FileName: "+c.getPath());
		}
		
	}
	

	
	@Test
	public void testInsert() {
		System.out.println("PRUEBA 2");
		String path;
		Contents cont = new Contents();
		cont.setMime_type("text/plain");
		path = id + "/" + "Fichero_a_insertar.txt";
		cont.setPath(path);
		DropboxResource plr = new DropboxResource(token);
		String content = "Contenido de Prueba";
		
		plr.insertFile(path, cont, content);
		
		Folder fileCheck = plr.getFolder(cont.getPath());
		System.out.println(cont.getPath());
		// This will make the test to fail if the returned collection is null
		assertNotNull("The file is null", fileCheck);
		
		System.out.println("ItemID "+fileCheck.getRev()+" - FileName: "+fileCheck.getPath());
				
	}
	
	@Test
	public void testDownload() {
		System.out.println("PRUEBA 3");
		String url;
		DropboxResource plr = new DropboxResource(token);
		url = plr.downloadFile("Fichero_a_insertar.txt");
		// This will make the test to fail if the returned collection is null
		assertNotNull("The url is null", url);
		System.out.println(url);
				
	}
	
	
}
