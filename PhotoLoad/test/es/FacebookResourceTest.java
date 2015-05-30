package es;

import static org.junit.Assert.*;

import org.junit.Test;

import es.server.resources.FacebookPhotosResource;
import es.shared.domain.facebook.FBDataPhoto;
import es.shared.domain.facebook.FBPhotoUpload;
import es.shared.domain.facebook.FacebookPhotos;
import es.shared.domain.facebook.Struct;

public class FacebookResourceTest {

	String token = "CAACEdEose0cBALzgJys2zRNdZC5jmtRavmcXxU1MosQIIwZAlagcPcLW3JY59MtIbOkur3OFZAAWMzTWCJtI9sxSeKB5jGHB2rLYQKcCGjsbOoChtv5MNA5bdkOypgqUXuQBxFr6pjCZBX1szhZAfrvKvjyzwYw81ZBsIZApKcH9AXJrnZAOdPixX2zxZA99v8vOqzf1S9bGPIToDTIgIkp9vUkerOnwKjHwZD";
	//El token se debe coger de la siguiente web: https://developers.facebook.com/tools/explorer
	String id = "";
	String urlPhoto="https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xfa1/v/t1.0-1/c1.0.50.50/p50x50/312544_2089545043560_1948867135_n.jpg?oh=a1bdaa35df52e253e6639e24b62ab1fd&oe=5609619C&__gda__=1442184241_c0f323b7dd3a5bdb6c381b88db0afaf9";
	
	@Test
	public void testGetPhotos() {
		System.out.println("PRUEBA FB1");
		FacebookPhotosResource plr = new FacebookPhotosResource(token);
		FacebookPhotos folder = plr.getPhotos();
		
		// This will make the test to fail if the returned collection is null
		assertNotNull("The collection of photos is null", folder);
		
		// Show result
		System.out.println("Listing all hotos:");
		for(FBDataPhoto c:folder.getData()){
			System.out.println("ItemID "+c.getId()+" - Link: "+c.getLink());
		}
		
	}
	

	
	@Test
	public void testUploadPhoto() {
		System.out.println("PRUEBA FB2");
		FacebookPhotosResource plr = new FacebookPhotosResource(token);
		FBPhotoUpload foto = new FBPhotoUpload();
		foto.setURL(urlPhoto);
		Struct folder = plr.uploadPhoto(foto);
		
		// This will make the test to fail if the returned collection is null
		assertNotNull("The Photo is not publish", folder);
		
		System.out.println("ItemID "+folder.getId());
				
	}

	
	
}
