package es.client;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import es.client.services.GoogleDriveAuthenticatedService;
import es.client.services.GoogleDriveAuthenticatedServiceAsync;
import es.shared.IntViews;
import es.shared.domain.googledrive.FileItem;
import es.shared.domain.googledrive.Files;

public class GoogleDriveListView extends Composite {

	private static final Auth AUTH = Auth.get();
	private VerticalPanel mainPanel = new VerticalPanel();
	private  FlexTable filesTable;
	

	final String GOOGLEAUTH_URL = "https://accounts.google.com/o/oauth2/auth";
	final String GOOGLECLIENT_ID = "910016834382-gkigh6b0hoe73li4ni69l6j36c85384m.apps.googleusercontent.com";
	final String GOOGLEDRIVE_SCOPE = "https://www.googleapis.com/auth/drive";
	
	private IntViews interaccion;

	private final GoogleDriveAuthenticatedServiceAsync googleDriveService = GWT
			.create(GoogleDriveAuthenticatedService.class);

	public GoogleDriveListView(IntViews params) {

		initWidget(mainPanel);
		interaccion = params;
		if(interaccion.getGDToken().isEmpty()){
			loginView();	
		}else{
			getFiles();
		}
		

	}

	private void loginView(){
		
		mainPanel.clear();
		
		Button buttonGD = new Button("Authenticate with Drive");
		
		buttonGD.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				final AuthRequest req = new AuthRequest(GOOGLEAUTH_URL,
						GOOGLECLIENT_ID).withScopes(GOOGLEDRIVE_SCOPE);
				AUTH.login(req, new Callback<String, Throwable>() {

					@Override
					public void onFailure(Throwable reason) {

					}

					@Override
					public void onSuccess(String token) {
						interaccion.setGDToken(token);
						getFiles();
						

					}

				});

			}
		});
		
		mainPanel.add(buttonGD);
		
	}

	private void getFiles(){
		
		mainPanel.clear();
		mainPanel.add(new HTML(interaccion.getGDToken()));

		Button buttonGDFiles = new Button("Get Google Drive Files");

		filesTable = new FlexTable();

		filesTable.setStylePrimaryName("filesTable");
		filesTable.getRowFormatter().setStylePrimaryName(0, "firstRow");
		filesTable.setWidget(0, 0, new Label("Nombre del archivo"));
		filesTable.setWidget(0, 1, new Label("Tipo de archivo"));
		filesTable.setWidget(0, 2, new Label("Descargar"));	

		buttonGDFiles.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
					googleDriveService.getFiles(interaccion.getGDToken(),
							new AsyncCallback<Files>() {
								@Override
								public void onSuccess(Files result) {
									showFiles(result);
								}

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Algo ha ido mal " + caught);
									loginView();
								}
							});
				}
		});
		
		
		mainPanel.add(buttonGDFiles);
		mainPanel.add(filesTable);
		
	}
	
	void showFiles(Files result) {

		int i = 0;

		if (result != null) {
			for (final FileItem a : result.getItems()) {
				if (a.getMimeType().contains("image")) {
					filesTable.setWidget(i + 1, 0, new Label(a.getTitle()));
					filesTable.setWidget(i + 1, 1, new Label(a.getMimeType()));

					Button downloadButton = new Button("Download");
					downloadButton.addClickHandler(new ClickHandler() {
						
						public void onClick(ClickEvent event) {
							googleDriveService.getFile(
									interaccion.getGDToken(), a.getId(),
									new AsyncCallback<FileItem>() {

										@Override
										public void onFailure(Throwable caught) {
											// TODO Auto-generated method stub
											Window.alert("Aquí falla " + caught);

										}

										@Override
										public void onSuccess(FileItem result) {
											// TODO Auto-generated method stub
//											Window.alert(result
//													.getWebContentLink()
//													+ " o este otro: "
//													+ result.getDownloadUrl());
//											labelDownload.setText(result
//													.getWebContentLink());
											
											interaccion.addLink(result.getWebContentLink());
											PhotoLoad.go("publishView", interaccion);

										}
									});
						}
					});
					filesTable.setWidget(i + 1, 2, downloadButton);
					i++;

				}
			}
		}
	}

}
