package es.client;

import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginView extends Composite {

	private VerticalPanel verPanel;
	private Button buttonUpload = new Button("Publica tus fotos !");
	private Button buttonDownload = new Button("Descarga tus fotos !");
	private Button buttonInfo = new Button("Acerca de");

	
	public LoginView(Map<String, Object> params) {
		// TODO Auto-generated constructor stub
		verPanel=new VerticalPanel();
		verPanel.setWidth("900px"); // Debería ser automático sabiendo justo el tamaño de la ventana del navegador
		verPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verPanel);
		verPanel.add(new HTML("<h1> Bienvenido a Photoload ! ! ! </h1>"));
		verPanel.add(buttonUpload);
		buttonUpload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PhotoLoad.go("uploadView");
			}
		});
		//verPanel.add(new HTML(""));
		verPanel.add(buttonDownload);
		buttonDownload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PhotoLoad.go("downloadView");
			}
		});
		verPanel.add(buttonInfo);
		buttonInfo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				PhotoLoad.go("info");
			}
		});
	}

}
