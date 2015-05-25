package es.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

import es.shared.IntViews;

public class PublishView extends Composite {


	private VerticalPanel vertPanel;
	
	Button buttFBUpload = new Button("Sube a Facebook");

	public PublishView(IntViews params) {
		// TODO Auto-generated constructor stub
		
		vertPanel= new VerticalPanel();
		//vertPanel.setWidth("900px");
		vertPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(vertPanel);
		vertPanel.add(new HTML("<h2>Elige donde deseas subir tus fotos! </h2>"));
		
		inicializaBotones(params);
		
		vertPanel.add(buttFBUpload);
		
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

}
