package es;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import es.server.resources.FlickrResource;
import es.shared.domain.dropbox.Contents;
import es.shared.domain.flickr.FlickrAuth;
import es.shared.domain.flickr.FlickrPhoto;

public class FlickrResourceTest {

	private static FlickrAuth oauth;
	String id = "";
	String urlPhoto = "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xfa1/v/t1.0-1/c1.0.50.50/p50x50/312544_2089545043560_1948867135_n.jpg?oh=a1bdaa35df52e253e6639e24b62ab1fd&oe=5609619C&__gda__=1442184241_c0f323b7dd3a5bdb6c381b88db0afaf9";
	private List<FlickrPhoto> fotos;

	@Test
	public void testAuth() {
		if (oauth == null) {
			Scanner in;
			System.out.println("PRUEBA FL1");
			FlickrResource flr = new FlickrResource();
			oauth = flr.authPrim();
			in = new Scanner(System.in);

			// This will make the test to fail if the returned collection is
			// null
			assertNotNull("The link for get the token is null",
					oauth.getUrlForGetBaton());

			System.out.println("Ve a esta página " + oauth.getUrlForGetBaton());
			System.out.print("e introduce el código aquí: ");
			oauth.setVerifierCode(in.nextLine().trim());

			oauth = flr.authSeg(oauth);

			assertNotNull("The token is null", oauth.getAccessToken());

			// Show result
			System.out.println("Este es tu token: " + oauth.getAccessToken());
			in.close();
		}
	}

	@Test
	public void testGetPhotos() {
		if (fotos == null) {
			System.out.println("PRUEBA FL2");
			FlickrResource flr = new FlickrResource();

			if (oauth.getAccessToken() == null) {
				testAuth();
			} else {
				assertNotNull("Token vacío", oauth.getAccessToken());
			}

			fotos = flr.getPhotos(oauth);

			assertNotNull("No se ha recibido ninguna foto", fotos);

			System.out.println("Listing all photos:");
			for (FlickrPhoto c : fotos) {
				System.out.println("ItemID " + c.getID() + " - FileName: "
						+ c.getTitle());
			}
		}
	}

	@Test
	public void testShowPhotos() {
		System.out.println("PRUEBA FL3");
		FlickrResource flr = new FlickrResource();

		if (fotos == null) {
			testGetPhotos();
		} else {
			assertNotNull("Fallo el getPhotos anteriormente", fotos);
		}

		fotos = flr.showPhotos(oauth, fotos);

		assertNotNull("No se ha recibido ninguna foto", fotos);

		System.out.println("Listing all photos:");
		for (FlickrPhoto c : fotos) {
			System.out.println("ItemID " + c.getID() + " - FileName: "
					+ c.getTitle() + " - Link: " + c.getSizes().get(0).getSource());
		}

	}

}
