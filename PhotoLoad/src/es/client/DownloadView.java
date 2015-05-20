package es.client;

import java.util.Map;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class DownloadView extends Composite {
	
	private SplitLayoutPanel splitPanel;

	public DownloadView(Map<String, Object> params) {
		// TODO Auto-generated constructor stub
		splitPanel = new SplitLayoutPanel();
		initWidget(splitPanel);
		splitPanel.addNorth(splitUp(), 200);
		splitPanel.addEast(splitDownLeft(), 800);
		splitPanel.add(splitDownRight());
	}
	
	private Widget splitUp(){
		return new HTML("Eleccion de fotos");
	}
/*	private Widget splitDownLeft(){
		return new HTML("donde descargar las fotos 1");
	}*/
	private Widget splitDownLeft(){
		return new FlickrUploadView(null);
	}
	private IsWidget splitDownRight(){
		//return new HTML("vISTAAAAAA");
		return new DropboxListView(null);
	}

}
