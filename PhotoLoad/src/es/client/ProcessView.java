package es.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

import es.shared.IntViews;

public class ProcessView extends Composite {
	
	private VerticalPanel vertPanel;
	
	Button buttFBDownload = new Button("Descarga desde Facebook");
	Button buttFlickrDownload = new Button("Descarga desde Flickr");
	Button buttDBDownload = new Button("Descarga desde Dropbox");
	Button buttGDDownload = new Button("Descarga desde Google Drive");


	public ProcessView(IntViews params) {
		// TODO Auto-generated constructor stub
		
		vertPanel= new VerticalPanel();
		//vertPanel.setWidth("900px");
		vertPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(vertPanel);
		
		if(params!= null){
			if(params.getTo().equals(IntViews.To.NONE)){
				vertPanel.add(new HTML("<h2>Elige desde donde deseas descargar las fotos</h2>"));
				inicializaBotones(params);
				vertPanel.add(buttFBDownload);
				vertPanel.add(buttFlickrDownload);
				vertPanel.add(buttDBDownload);
				vertPanel.add(buttGDDownload);
			}else{//Para acciones predefinidas
				switch (params.getTo()) {
				case FACEBOOK:
					vertPanel.add(new FacebookUploadView(params));
					break;
				case DROPBOX:
					vertPanel.add(new DropboxUploadView(params));
					break;
				case FLICKR:
					vertPanel.add(new FlickrUploadView(params));
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
		buttFBDownload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				vertPanel.clear();
				vertPanel.add(new FacebookDownloadView(params));
			}
		});
		buttFlickrDownload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				vertPanel.clear();
				vertPanel.add(new FlickrDownloadView(params));
			}
		});
		buttDBDownload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				vertPanel.clear();
				vertPanel.add(new DropboxDownloadView(params));
			}
		});
		buttGDDownload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				vertPanel.clear();
				vertPanel.add(new GoogleDriveListView(params));
			}
		});
	}

}
