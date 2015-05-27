package es.client;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import es.shared.IntViews;

public class InfoView extends Composite {

	private ScrollPanel panelScroll = new ScrollPanel();
	private VerticalPanel panel = new VerticalPanel();


	public InfoView(IntViews params) {
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
