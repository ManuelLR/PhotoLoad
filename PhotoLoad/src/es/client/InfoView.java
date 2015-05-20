package es.client;

import java.util.List;
import java.util.Map;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import es.client.services.FlickrAuthenticatedService;
import es.client.services.FlickrAuthenticatedServiceAsync;
import es.shared.domain.flickr.FlickrAuth;
import es.shared.domain.flickr.FlickrPhoto;

public class InfoView extends Composite {

	private ScrollPanel panelScroll = new ScrollPanel();
	private VerticalPanel panel = new VerticalPanel();


	public InfoView(Map<String, Object> params) {
		initWidget(panelScroll);
		panelScroll.add(panel);
		principalInfo();
		// panel.add(new HTML("Pues aquí ira flickr jeiiiiiii"));
	}


	private void principalInfo() {
		// TODO Auto-generated method stub
		panel.add(new HTML("Aquí deberíamos de poner toda la info del sistema"));
	}


}
