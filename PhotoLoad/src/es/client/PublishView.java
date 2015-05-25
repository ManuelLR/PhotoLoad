package es.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import es.shared.IntViews;


public class PublishView extends Composite {


	private SplitLayoutPanel splitPanel;
	private VerticalPanel vertPanel;
	
	Button buttFBUpload = new Button("Sube a Facebook");

	public PublishView(IntViews params) {
		// TODO Auto-generated constructor stub
		
		vertPanel= new VerticalPanel();
		//vertPanel.setWidth("900px");
		vertPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(vertPanel);
//		Pongo horizontal panel y añado los vertical panel con cada cosa 
		vertPanel.add(new HTML("<h2>Elige donde deseas subir las fotos</h2>"));
		vertPanel.add(new HTML("Vista incompleta !  ! ! ! !! ! ! ! ! "));
		if(params != null){
			if(params.getTo().equals(IntViews.To.NONE)){
				//acción normal
				vertPanel.add(new HTML("<h2>Elige donde deseas subir las fotos</h2>"));
				inicializaBotones(params);
				vertPanel.add(buttFBUpload);
			}else{
				//acción cuando la acción va para alguien
				switch (params.getTo()) {
				case FACEBOOK:
					vertPanel.add(new FacebookUploadView(params));
					break;
				default:
					Window.alert("La acción no está implementada actualmente");
					break;
				}
			}
		}
/*		splitPanel = new SplitLayoutPanel();
		//initWidget(splitPanel);
		splitPanel.addNorth(splitUp(params), 200);
		splitPanel.addEast(splitDownLeft(params), 800);
		splitPanel.add(splitDownRight(params));*/
	}
	private void inicializaBotones(final IntViews params){
		buttFBUpload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				vertPanel.clear();
				vertPanel.add(new FacebookUploadView(params));
			}
		});
	}
	
	private Widget splitUp(IntViews params){
		return new FacebookUploadView(params);
	}
/*	private Widget splitDownLeft(){
		return new HTML("donde descargar las fotos 1");
	}*/
	private Widget splitDownLeft(IntViews params){
		return new FlickrUploadView(params);
	}
	private IsWidget splitDownRight(IntViews params){
		//return new HTML("vISTAAAAAA");
		return new DropboxListView(params);
	}

}
