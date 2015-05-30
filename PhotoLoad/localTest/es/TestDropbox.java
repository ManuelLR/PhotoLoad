package es;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.apache.commons.io. IOUtils;








import es.shared.domain.dropbox.Contents;

public class TestDropbox {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Contents f = new Contents();
	  	  f.setPath("/15/holitaaa.txt");
	  	  f.setMime_type("text/plane");
	  	  System.out.println(f.getPath());
	  	  insertFile2(f.getPath(), f, "pruebaaaaaaaaa213.txt");
		
	}
	
public static String insertFile(Contents file, String content) throws IOException {
		String uri = "https://api.dropbox.com/1/metadata/auto" + file.getPath();
		String uri_upload = "https://api-content.dropbox.com/1/files_put/auto";
		String access_token = "CLUv1uDVmYkAAAAAAAABbrh7w_ORfD_wJQdwZjFWUaBDgC1rdI_2NxdReFdKV4as";
		FileInputStream myStream = new FileInputStream(new File(content));
		byte[] imageInBytes = IOUtils.toByteArray(myStream);
		ClientResource cr = null;
		String newId = null;
		try {
			cr = new ClientResource(uri + "?access_token="+access_token);
			Contents newFile = cr.post(file,Contents.class);
			newId = newFile.getPath();
			
			cr = new ClientResource(uri_upload + newId+"?access_token="+access_token);
			Map<String,Object> headers = cr.getRequestAttributes();
			headers.put("Content-Type", "image/jpeg");
			//content = content.
			cr.put(imageInBytes);
		} catch (ResourceException re) {
			System.err.println("Error when inserting file: " + cr.getResponse().getStatus());
		}
		return newId;
	}


public static String insertFile2(String path, Contents file, String content) {
	//String uri = "https://api.dropbox.com/1/metadata/auto";
	String uri_upload = "https://api-content.dropbox.com/1/files_put/auto";
	String access_token = "CLUv1uDVmYkAAAAAAAABbrh7w_ORfD_wJQdwZjFWUaBDgC1rdI_2NxdReFdKV4as";
	ClientResource cr = null;
	String newId = null;
	try {
		/*cr = new ClientResource(uri + "?access_token="+access_token);
		Contents newFile = cr.post(file,Contents.class);
		newId = newFile.getPath();
		System.out.println(newId);*/
		
		cr = new ClientResource(uri_upload + path+"?access_token="+access_token);
		//Map<String,Object> headers = cr.getRequestAttributes();
		//headers.put("Content-Type", "text/plane");
		//content = content.
		cr.put(content);
	} catch (ResourceException re) {
		System.err.println("Error when inserting file: " + cr.getResponse().getStatus());
	}
	return newId;
}










































}
