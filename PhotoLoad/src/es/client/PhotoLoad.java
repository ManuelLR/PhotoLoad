package es.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import es.shared.IntViews;


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
		go("loginView", new IntViews());
	};
	public static void go(String token){
			PhotoLoad.go(token, new IntViews());
	}
		
	public static void go(String token, IntViews params){
		RootLayoutPanel mainPanel = RootLayoutPanel.get();
		DockLayoutPanel dockPanel = new DockLayoutPanel(Unit.EM);
		dockPanel.addNorth(cabecera(params), 2);
		dockPanel.addSouth(new HTML("<center>Pie de página</center>"), 2);
		dockPanel.addEast(pie(params), 2);
		
		if (token=="uploadView"){
			mainPanel.clear();
			dockPanel.add(new UploadView(params));
			mainPanel.add(dockPanel);
		}else if (token=="downloadView" ){
			//NEW WINDOW: p.clear();
			mainPanel.clear();
			dockPanel.add(new DownloadView(params));
			mainPanel.add(dockPanel);
		}else if (token=="loginView"){
			//NEW WINDOW: p.clear();
			mainPanel.clear();
			mainPanel.add(new LoginView(params));
		}else if(token=="info"){
			mainPanel.clear();
			dockPanel.add(new InfoView(params));
			mainPanel.add(dockPanel);
		}
	}
	public static Widget pie(IntViews params){
		
		return new HTML("Por este lateral deberían ir los logos!  ! ! !");
	}
	
	public static Widget cabecera(final IntViews params){
		Button buttonUpload = new Button("Publica tus fotos !");
		Button buttonDownload = new Button("Descarga tus fotos !");
		Button buttonInfo = new Button("Acerca de");

		HorizontalPanel cabecera = new HorizontalPanel();
		cabecera.setWidth("900px"); // Debería ser automático sabiendo justo el tamaño de la ventana del navegador
		cabecera.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		cabecera.add(buttonUpload);
		cabecera.add(buttonDownload);
		cabecera.add(buttonInfo);
		buttonUpload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PhotoLoad.go("uploadView", params);
			}
		});
		buttonDownload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PhotoLoad.go("downloadView", params);
			}
		});
		buttonInfo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PhotoLoad.go("info", params);
			}
		});
		return cabecera;
	}
}