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
import es.shared.IntViews;
import es.shared.domain.flickr.FlickrAuth;
import es.shared.domain.flickr.FlickrPhoto;

public class FlickrUploadView extends Composite {

	private final FlickrAuthenticatedServiceAsync flickrService = GWT
			.create(FlickrAuthenticatedService.class);

	private ScrollPanel panelScroll = new ScrollPanel();
	private VerticalPanel panel = new VerticalPanel();
	private Button buttonFlAuth = new Button("Conecta Flickr");
	private Button buttonFlAuthCont = new Button("Continuar login a Flickr");
	private Button buttonFlShow = new Button("Visualiza tus fotos de Flickr");
	private Button buttonFlReturn = new Button("Volver");
//Info interesante -> http://crest.codegist.org/sample-flickr.html
	private TextBox batonUser = new TextBox();
	private TextBox accessToken = new TextBox();
	private String urlForGetBaton;
	
	private static FlickrAuth auth;

	public FlickrUploadView(IntViews params) {
		initWidget(panelScroll);
		panelScroll.add(panel);
		login();
		// panel.add(new HTML("Pues aquí ira flickr jeiiiiiii"));
	}

	private void login() {
		loginViewFirst();

		panel.add(buttonFlAuth);
		buttonFlAuth.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				flickrService.authPrim(new AsyncCallback<FlickrAuth>() {
					public void onSuccess(FlickrAuth result) {
						// Window.alert(result);
						auth=result;
						urlForGetBaton =result.getUrlForGetBaton();
						loginSec();
					}

					public void onFailure(Throwable caught) {
						Window.alert("Error en el servidor: "
								+ caught.toString()+". Probemos de nuevo.");
						loginViewFirst();
					}
				});
			}
		});
	}

	private void loginSec() {
		loginViewSec();
		buttonFlAuthCont.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(batonUser.getText()==null || batonUser.getText().isEmpty()){
					Window.alert("Por favor, introduzca el código si desea continuar");
				}else{
					auth.setVerifierCode(batonUser.getText().trim());
				flickrService.authSeg(auth,
						new AsyncCallback<FlickrAuth>() {
							public void onFailure(Throwable caught) {
								Window.alert("Error: \n" + caught.toString());
							}

							public void onSuccess(FlickrAuth result) {
								// Window.alert("Valor devuelto: "+result);
								auth=result;
								accessToken.setText(result.getAccessToken().getToken());
								afterLogin();
							}
						});
			}}
		});
	}

	private void afterLogin() {
		loginViewSucces();
		buttonFlShow.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				flickrService.getPhotos(auth, new AsyncCallback<List<FlickrPhoto>>() {
					public void onFailure(Throwable caught) {
						Window.alert("Error del servidor: " + caught.toString());
					}

					public void onSuccess(List<FlickrPhoto> result) {
						showPhotos(result);
					}
				});
			}
		});
	}

	private void loginViewFirst() {
		panel.add(buttonFlAuth);
	}

	private void loginViewSec() {
		panel.clear();
		String definition = "Ve a esta página \""+ urlForGetBaton+ "\" y pega en el siguiente recuadro el código obtenido desde Flickr:";
		Window.open(urlForGetBaton, "__blank", "enabled");
		panel.add(new HTML(definition));
		panel.add(batonUser);
		panel.add(buttonFlAuthCont);
	}

	private void loginViewSucces() {
		panel.clear();
		panel.add(new HTML("Te has logueado exitosamente !. Tu token es: "
				+ accessToken.getText()));
		panel.add(buttonFlShow);

	}

	private void showPhotos(List<FlickrPhoto> input) {
		panel.clear();
		panel.add(buttonFlReturn);
		buttonFlReturn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginViewSucces();
			}
		});
		if (input == null) {
			Window.alert("Ha habido un problema en el servidor obteniendo los IDs de las fotos, recarga la página e intentelo nuevamente");
		} else if (input.isEmpty()) {
			panel.add(new HTML("No tienes ninguna foto en Flickr"));
		} else {
			flickrService.showPhotos(auth, input,
					new AsyncCallback<List<FlickrPhoto>>() {
						public void onFailure(Throwable caught) {
							Window.alert("Error del servidor: "
									+ caught.toString());
						}

						public void onSuccess(List<FlickrPhoto> result) {
							for (FlickrPhoto actual : result) {
								String link;
								String linkOri = actual.getSizes()
										.get(actual.getSizes().size() - 1)
										.getSource();
								try {
									link = actual.getSize("Small").getSource();
								} catch (NullPointerException e) {
									link = linkOri;
								}
								panel.add(new HTML("Foto: " + actual.getTitle()
										+ "(" + actual.getID() + ")"));
								panel.add(new HTML("<a href=\"" + linkOri
										+ "\"><img alt=\"" + actual.getTitle()
										+ "\" src=\"" + link + "\"></img> </a>"));
							}
							// panel.add(new HTML(res));
							// Window.alert("resultado: "+result);
						}
					});
		}
	}
}
