package es.client;

import java.util.List;

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
	private IntViews interaccion;
	private TextBox batonUser = new TextBox();
	private TextBox accessToken = new TextBox();
	private String urlForGetBaton;
	
//	private static FlickrAuth auth;

	public FlickrUploadView(IntViews params) {
		initWidget(panelScroll);
		panelScroll.add(panel);
		
		if(params.getFlickrToken()==null){
			interaccion=new IntViews();
			login();
		}else{
			interaccion=params;
			//auth=params.getFlickrToken();
			accessToken.setText(interaccion.getFlickrToken().getAccessToken().toString());
			afterLogin();
		}
		
		
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
						interaccion.setFlickrToken(result);
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
					interaccion.getFlickrToken().setVerifierCode(batonUser.getText().trim());
				flickrService.authSeg(interaccion.getFlickrToken(),
						new AsyncCallback<FlickrAuth>() {
							public void onFailure(Throwable caught) {
								Window.alert("Error: \n" + caught.toString());
							}

							public void onSuccess(FlickrAuth result) {
								// Window.alert("Valor devuelto: "+result);
								interaccion.setFlickrToken(result);
								accessToken.setText(result.getAccessToken().getToken());
								afterLogin();
							}
						});
			}}
		});
	}

	private void afterLogin() {
		loginViewSuccess();
		buttonFlShow.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				flickrService.getPhotos(interaccion.getFlickrToken(), new AsyncCallback<List<FlickrPhoto>>() {
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

	private void loginViewSuccess() {
		panel.clear();
		panel.add(new HTML("Te has logueado exitosamente ! Tu token es: "
				+ accessToken.getText()));
		panel.add(buttonFlShow);
	}

	private void showPhotos(List<FlickrPhoto> input) {
		panel.clear();
		panel.add(buttonFlReturn);
		buttonFlReturn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginViewSuccess();
			}
		});
		if (input == null) {
			Window.alert("Ha habido un problema en el servidor obteniendo los IDs de las fotos, recarga la página e intentelo nuevamente");
		} else if (input.isEmpty()) {
			panel.add(new HTML("No tienes ninguna foto en Flickr"));
		} else {
			flickrService.showPhotos(interaccion.getFlickrToken(), input,
					new AsyncCallback<List<FlickrPhoto>>() {
						public void onFailure(Throwable caught) {
							Window.alert("Error del servidor: "
									+ caught.toString());
						}
						public void onSuccess(List<FlickrPhoto> result) {
							FlexTable filesTable = new FlexTable();
							panel.add(filesTable);
							filesTable.getRowFormatter().setStylePrimaryName(0, "firstRow");
							filesTable.setWidget(0, 0, new Label("Foto"));
							filesTable.setWidget(0, 1, new Label("Acción"));
							int i=1;
							for (final FlickrPhoto actual : result) {
								Button descargar = new Button("Seleccionar");								
								String link;
								/*String toPrint="";
								for(FlickrSize sz:actual.getSizes()){
									toPrint+="<a href=\""+sz.getSource()+"\"> "+sz.getWidth()+" </a>";
								}*/
								String linkOri = actual.getSizes()
										.get(actual.getSizes().size() - 1)
										.getSource();
								try {
									link = actual.getSize("Small").getSource();
								} catch (NullPointerException e) {
									link = linkOri;
								}
								/*panel.add(new HTML("Foto: " + actual.getTitle()
										+ "(" + actual.getID() + ")"));*/
								/*panel.add(new HTML("<a href=\"" + linkOri
										+ "\"><img alt=\"" + actual.getTitle()
										+ "\" src=\"" + link + "\"></img> </a>"));*/
								filesTable.setWidget(i, 0, new HTML("<a href=\"" + linkOri
										+ "\"><img alt=\"" + actual.getTitle()
										+ "\" src=\"" + link + "\"></img> </a>"));
								descargar.addClickHandler(new ClickHandler() {
									public void onClick(ClickEvent event) {
										String linkBueno=actual.getSizes().get(actual.getSizes().size()-1).getSource();
										interaccion.addLink(linkBueno);
										PhotoLoad.go("publishView", interaccion);
									}
								});
								filesTable.setWidget(i, 1, descargar);
								//filesTable.setWidget(i, 2, new HTML(toPrint));
								i++;
							}
							// panel.add(new HTML(res));
							// Window.alert("resultado: "+result);
						}
					});
		}
	}
}
