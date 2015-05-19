package es.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PhotoLoad implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
/*	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";*/

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		go("uploadView", new HashMap<String,Object>());
	};
	public static void go(String token){
			PhotoLoad.go(token, new HashMap<String,Object>());
	}
		
	public static void go(String token, Map<String,Object> params){
		RootLayoutPanel mainPanel = RootLayoutPanel.get();
		DockLayoutPanel dockPanel = new DockLayoutPanel(Unit.EM);
		dockPanel.addNorth(new HTML("<center>Cabecera</center>"), 2);
		dockPanel.addSouth(new HTML("<center>Pie de página</center>"), 2);
		dockPanel.addEast(new HTML("Por este lateral deberían ir los logos!  ! ! !"), 2);
		
		if (token=="uploadView"){
			//mainPanel.clear();
			dockPanel.add(new UploadView(params));
			mainPanel.add(dockPanel);
		}else if (token=="downloadView" ){
			//NEW WINDOW: p.clear();
			mainPanel.clear();
			mainPanel.add(new DownloadView(params));
		}else if (token=="loginView"){
			//NEW WINDOW: p.clear();
			mainPanel.clear();
			mainPanel.add(new LoginView(params));
		}
	}
}