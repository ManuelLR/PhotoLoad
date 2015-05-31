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

public class PublishView extends Composite {


	private VerticalPanel vertPanel;
	
	Button buttFBUpload = new Button("Sube a Facebook (no válido para Google Drive)");
	Button buttDBUpload = new Button("Sube a Dropbox (en pruebas)");
	Button buttFkUpload = new Button("Sube a Flickr (solo funcional en consola y por local)");
	Button buttDoComp = new Button("Descarga al ordenador");

	public PublishView(IntViews params) {
		
		vertPanel= new VerticalPanel();
		vertPanel.setWidth(params.getAnchoRelativo());
		vertPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(vertPanel);
		vertPanel.add(new HTML("<h2>Elige donde deseas subir tus fotos! </h2>"));
		
		inicializaBotones(params);
		
		vertPanel.add(buttFBUpload);
		vertPanel.add(buttDBUpload);
		vertPanel.add(buttFkUpload);
		vertPanel.add(buttDoComp);
/*		String tokensActuales="Actualmente los token guardados son: <ul>";
		tokensActuales +="<li>FB: "+params.getFBToken()+"</li>";
		if(params.getFlickrToken()!=null){
			tokensActuales +="<li>Flickr: "+params.getFlickrToken().getRequestToken()+"</li>";
		}
		tokensActuales +="<li>DB: "+params.getDropboxToken()+"</li>";
		tokensActuales +="<li>GD: "+params.getGDToken()+"</li>";
		tokensActuales +="</ul>";
		vertPanel.add(new HTML(tokensActuales));*/
	}
	private void inicializaBotones(final IntViews params){
		buttFBUpload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				params.setTo(IntViews.To.FACEBOOK);
				vertPanel.clear();
				PhotoLoad.go("processView", params);
			}
		});
		buttDBUpload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				params.setTo(IntViews.To.DROPBOX);
				vertPanel.clear();
				PhotoLoad.go("processView", params);
			}
		});
		buttFkUpload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				params.setTo(IntViews.To.FLICKR);
				vertPanel.clear();
				PhotoLoad.go("processView", params);
			}
		});
		buttDoComp.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.open(params.getLink().get(0), "__blank", "enabled");
				vertPanel.clear();
				PhotoLoad.go("loginView", params);
			}
		});
	}

}
