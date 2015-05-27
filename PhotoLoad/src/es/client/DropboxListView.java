package es.client;

import java.util.Map;

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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import es.shared.IntViews;
import es.shared.domain.dropbox.Contents;
import es.shared.domain.dropbox.Folder;
import es.client.services.DropboxAuthenticatedService;
import es.client.services.DropboxAuthenticatedServiceAsync;

public class DropboxListView extends Composite {

	private static final Auth AUTH = Auth.get();
	private IntViews intViews;
	private VerticalPanel mainPanel;
	private ScrollPanel panelScroll = new ScrollPanel();
	private final Label labelAccessToken = new Label("");
	private final FlexTable filesTable;

	final TextBox plTextBox = new TextBox();

	private final DropboxAuthenticatedServiceAsync dropboxService = GWT
			.create(DropboxAuthenticatedService.class);

	public DropboxListView(IntViews params) {
		initWidget(panelScroll);
		mainPanel = new VerticalPanel();
		panelScroll.add(mainPanel);

		/** TODO PRACTICA7 */
		final String DROPBOX_URL = "https://www.dropbox.com/1/oauth2/authorize";
		final String DROPBOX_ID = "t9zcgs18eplaqm4";

		Button buttonGD = new Button("Por favor, inicie sesión en Dropbox");
		
		Button buttonGDFiles = new Button("Obten tus archivos de Dropbox");

		final Label labelGD = new Label("");
		if (params==null) {
			intViews  = new IntViews();
			intViews.setDropboxToken("");
			Window.alert("El if es true1");
		} else {
			intViews = params;
			if(intViews.getDropboxToken().isEmpty()){
				intViews  = new IntViews();
				intViews.setDropboxToken("");
				Window.alert("El if es true2");
			}else{
				Window.alert("El if es false");			
			}
		}
		/*intViews  = new IntViews();
		intViews.setDropboxToken("");*/

		filesTable = new FlexTable();
		// TABLE AND HEADER
		filesTable.setStylePrimaryName("filesTable");
		filesTable.getRowFormatter().setStylePrimaryName(0, "firstRow");
		filesTable.setWidget(0, 0, new Label("File Name"));
		filesTable.setWidget(0, 1, new Label("Size"));
		filesTable.setWidget(0, 2, new Label("Date"));
		filesTable.setWidget(0, 3, new Label("ID"));
		filesTable.setWidget(0, 4, new Label("Download"));

		buttonGD.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final AuthRequest req = new AuthRequest(DROPBOX_URL, DROPBOX_ID);
				AUTH.login(req, new Callback<String, Throwable>() {

					@Override
					public void onFailure(Throwable reason) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						//labelAccessToken.setText(result);
						intViews.setDropboxToken(result);
						Window.alert("perfecto, este es su Token: "
								//+ labelAccessToken.getText());
								+ intViews.getDropboxToken());
					}
				});
			}
		});

		buttonGDFiles.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//if (labelAccessToken.getText() == "")
				if (intViews.getDropboxToken() == "")
					Window.alert("Por favor, inicia sesion antes de pedir datos");
				else {
					//dropboxService.getFolder(labelAccessToken.getText(),
					dropboxService.getFolder(intViews.getDropboxToken(),
							plTextBox.getText(), new AsyncCallback<Folder>() {

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									Window.alert("Algo Falla");
								}

								@Override
								public void onSuccess(Folder result) {
									// TODO Auto-generated method stub
									showFiles(result);
								}

							});
				}
			}
		});


		Label label = new Label(intViews.getDropboxToken());
		
		mainPanel.add(buttonGD);
		//mainPanel.add(new Label("Escriba aqui la ruta de la carpeta (si lo dejas en blanco ira a la raiz)"));
		mainPanel.add(plTextBox);
		mainPanel.add(labelGD);
		//mainPanel.add(labelAccessToken);
		mainPanel.add(label);
		//mainPanel.add(new HTML(intViews.getDropboxToken()));
		mainPanel.add(buttonGDFiles);
		mainPanel.add(filesTable);
		// mainPanel.add(newFileButton);
	}

	void showFiles(Folder result) {
		int i = 0;

		filesTable.clear();
		filesTable.setStylePrimaryName("filesTable");
		filesTable.getRowFormatter().setStylePrimaryName(0, "firstRow");
		filesTable.setWidget(0, 0, new Label("File Name"));
		filesTable.setWidget(0, 1, new Label("Size"));
		filesTable.setWidget(0, 2, new Label("Date"));
		filesTable.setWidget(0, 3, new Label("ID"));
		//filesTable.setWidget(0, 4, new Label("Download"));

		if (result != null) {
			for (final Contents c : result.getContents()) {
				Button enter = new Button(c.getPath());
				
				if (new Integer((Integer) c.getBytes()) == 0) {
				  enter.addClickHandler(new ClickHandler() {
				  
				  @Override public void onClick(ClickEvent event) { // TODO
				  //Auto-generated method stub
				  //dropboxService.getFolder(labelAccessToken.getText(),
					dropboxService.getFolder(intViews.getDropboxToken(),
				  c.getPath(), new AsyncCallback<Folder>() {
				  
				  @Override public void onFailure(Throwable caught) { // TODO
				  //Auto-generated method stub
				  
				  }
				  
				  @Override public void onSuccess(Folder result) { // TODO
				  //Auto-generated method stub
					  showFiles(result); } }); } });
				} else {
					enter.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							// TODO Auto-generated method stub
							dropboxService.downloadFile(intViews.getDropboxToken(), c.getPath(), new AsyncCallback<String>() {

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									Window.alert("Fallo al descargar");
								}

								@Override
								public void onSuccess(String result) {
									// TODO Auto-generated method stub
									if (c.getPath().contains(".png") || c.getPath().contains(".jpg")) {
										intViews.getLink().add(result);
										afterSelect();
									//Window.open(result, "", "");
									} else {
										Window.alert("No tienes permiso de acceso a este archivo");
									}
								}
							});
						}
					});
					
				}
				 
				filesTable.setWidget(i + 1, 0, enter);
				filesTable.setWidget(i + 1, 1, new Label(c.getSize()));
				filesTable.setWidget(i + 1, 2, new Label(c.getModified()));
				filesTable.setWidget(i + 1, 3, new Label(c.getRev()));
				// final int index = i+1;
				Button downloadButton = new Button("Descargar");
				// final Map<String,Object> params = new
				// HashMap<String,Object>();
				downloadButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						if (new Integer((Integer) c.getBytes()) == 0
								|| !c.getPath().contains(".")) {
							Window.alert("No es una foto, luego no se puede descargar");
						} else {
							dropboxService.downloadFile(
									//labelAccessToken.getText(), c.getPath(),
									intViews.getDropboxToken(), c.getPath(),
									new AsyncCallback<String>() {

										@Override
										public void onFailure(Throwable caught) {
											// TODO Auto-generated method stub
											Window.alert("Fallo al descargar");
										}

										@Override
										public void onSuccess(String result) {
											// TODO Auto-generated method stub
											Window.open(result, "", "");
										}
									});
						}

					}
				});
				//filesTable.setWidget(i + 1, 4, downloadButton);

				i++;
			}
		}
	}

	private void afterSelect() {
		// TODO Auto-generated method stub
		mainPanel.clear();
		mainPanel.add(new Label("Selecciona a donde subir"));
		Button subirFacebook = new Button("subir a facebook");
		
		subirFacebook.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				intViews.setTo(IntViews.To.FACEBOOK);
				PhotoLoad.go("uploadView", intViews);
			}
		});
		mainPanel.add(subirFacebook);
	}
}


