package es.client;

import java.util.Map;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import es.shared.IntViews;

public class DownloadView extends Composite {
	
	private SplitLayoutPanel splitPanel;

	public DownloadView(IntViews params) {
		// TODO Auto-generated constructor stub
		splitPanel = new SplitLayoutPanel();
		initWidget(splitPanel);
		splitPanel.addNorth(splitUp(params), 200);
		splitPanel.addEast(splitDownLeft(params), 800);
		splitPanel.add(splitDownRight(params));
	}
	
	private Widget splitUp(IntViews params){
		return new FacebookDownloadView(params);
	}
/*	private Widget splitDownLeft(){
		return new HTML("donde descargar las fotos 1");
	}*/
	private Widget splitDownLeft(IntViews params){
		return new HTML("Hueco inutilizado");
	}
	private IsWidget splitDownRight(IntViews params){
		//return new HTML("vISTAAAAAA");
		return new DropboxListView(params);
	}

}
