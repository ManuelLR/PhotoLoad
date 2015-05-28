package es.client;

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
import com.google.gwt.user.client.ui.VerticalPanel;
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
		dockPanel.addNorth(cabecera(params), 3);
		dockPanel.addSouth(pie(params), 2);
		dockPanel.addEast(lateralDrcho(params), 2);
		
		if (token=="uploadView"){//deprected
			mainPanel.clear();
			dockPanel.add(new PublishView(params));
			mainPanel.add(dockPanel);
		}else if(token=="publishView"){
			mainPanel.clear();
			dockPanel.add(new PublishView(params));
			mainPanel.add(dockPanel);
		}else if(token=="processView"){
			mainPanel.clear();
			dockPanel.add(new ProcessView(params));
			mainPanel.add(dockPanel);			
		}
		else if (token=="downloadView" ){//deprecated
			//NEW WINDOW: p.clear();
			mainPanel.clear();
			dockPanel.add(new ProcessView(params));
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
	public static Widget lateralDrcho(IntViews params){
		
		return new HTML("P</br></br>H</br></br>O</br></br>T</br></br>O</br></br>L</br></br>O</br></br>A</br></br>D</br></br>");
	}
	public static Widget pie(IntViews params){
		
		return new HTML("<center><b>Realizado por:</b> Manuel López Ruiz, Miguel Rodríguez Caballero y David Tinoco Castillo</center>");
	}
	
	public static Widget cabecera(final IntViews params){
		Button buttonUpload = new Button("Inicio !");
//		Button buttonDownload = new Button("Descarga tus fotos !");
		Button buttonInfo = new Button("Acerca de");
		VerticalPanel cabeceraVert = new VerticalPanel();
		cabeceraVert.setWidth(params.getAnchoAbsoluto());
		cabeceraVert.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		HorizontalPanel cabecera = new HorizontalPanel();
		cabecera.setWidth(params.getAnchoAbsoluto()); // Debería ser automático sabiendo justo el tamaño de la ventana del navegador
		cabecera.setHeight("200px");
		cabecera.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		cabecera.add(new HTML("<img src=\"/files/logoAjustado.png\" style=\"width: 25px\">"));
//		cabecera.add(new HTML("<div id=\"foto\" style=\"text-align:center; margin:0px auto;\"><img src=\"/files/logoAjustado.png\" style=\"width: 10px\"></div>"));
		HorizontalPanel cabeceraInt = new HorizontalPanel();

		cabeceraInt.add(buttonUpload);
//		cabecera.add(buttonDownload);
		cabeceraInt.add(buttonInfo);
		buttonUpload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PhotoLoad.go("loginView", params);
			}
		});
/*		buttonDownload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PhotoLoad.go("downloadView", params);
			}
		});*/
		buttonInfo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PhotoLoad.go("info", params);
			}
		});
		cabecera.add(cabeceraInt);
		cabeceraVert.add(cabecera);
		cabeceraVert.add(new HTML("linea en blanco sin estar en blanco"));
		return cabeceraVert;
	}
}