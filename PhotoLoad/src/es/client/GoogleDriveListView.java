package es.client;

import java.util.*;

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
import com.google.gwt.user.client.ui.VerticalPanel;

import es.client.services.GoogleDriveAuthenticatedService;
import es.client.services.GoogleDriveAuthenticatedServiceAsync;
import es.shared.domain.googledrive.FileItem;
import es.shared.domain.googledrive.Files;

public class GoogleDriveListView extends Composite {

	private static final Auth AUTH = Auth.get();
	private VerticalPanel mainPanel;
	private final Label labelAccessToken = new Label("");
	private final Label labelDownload = new Label("");
	private final FlexTable filesTable;

	private final GoogleDriveAuthenticatedServiceAsync googleDriveService = GWT
			.create(GoogleDriveAuthenticatedService.class);

	public GoogleDriveListView(Map<String, Object> params) {

		mainPanel = new VerticalPanel();
		initWidget(mainPanel);

		final String GOOGLEAUTH_URL = "https://accounts.google.com/o/oauth2/auth";
		final String GOOGLECLIENT_ID = "852892677602-vsmcqs164dmafn4idik3ml3q0fn5siek.apps.googleusercontent.com";
		final String GOOGLEDRIVE_SCOPE = "https://www.googleapis.com/auth/drive";

		Button buttonGD = new Button("Authenticate with Drive");
		Button buttonGDFiles = new Button("Get Google Drive Files");

		filesTable = new FlexTable();

		filesTable.setStylePrimaryName("filesTable");
		filesTable.getRowFormatter().setStylePrimaryName(0, "firstRow");
		filesTable.setWidget(0, 0, new Label("Nombre del archivo"));
		filesTable.setWidget(0, 1, new Label("Tipo de archivo"));
		filesTable.setWidget(0, 1, new Label("Descargar"));

		buttonGD.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				final AuthRequest req = new AuthRequest(GOOGLEAUTH_URL,
						GOOGLECLIENT_ID).withScopes(GOOGLEDRIVE_SCOPE);
				AUTH.login(req, new Callback<String, Throwable>() {

					@Override
					public void onFailure(Throwable reason) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(String token) {
						// TODO Auto-generated method stub
						labelAccessToken.setText(token);

					}

				});

			}
		});

		buttonGDFiles.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (labelAccessToken.getText() == "")
					Window.alert("Please, login before getting files");
				else {
					googleDriveService.getFiles(labelAccessToken.getText(),
							new AsyncCallback<Files>() {

								@Override
								public void onSuccess(Files result) {
									showFiles(result);
								}

								@Override
								public void onFailure(Throwable caught) {
								}
							});
				}
			}
		});

	}

	void showFiles(Files result) {

		int i = 0;

		if (result != null) {
			for (final FileItem a : result.getItems()) {
				filesTable.setWidget(i + 1, 0, new Label(a.getTitle()));
				filesTable.setWidget(i + 1, 1, new Label(a.getMimeType()));

				Button downloadButton = new Button("Download");
				downloadButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						googleDriveService.getFile(labelAccessToken.getText(),
								a.getId(), new AsyncCallback<FileItem>() {

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										Window.alert("Aquí falla");

									}

									@Override
									public void onSuccess(FileItem result) {
										// TODO Auto-generated method stub
										Window.alert(result.getWebContentLink()
												+ " o este otro: "
												+ result.getDownloadUrl());
										labelDownload.setText(result
												.getWebContentLink());

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
