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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import es.client.services.FacebookAuthenticatedService;
import es.client.services.FacebookAuthenticatedServiceAsync;
import es.shared.IntViews;
import es.shared.domain.facebook.FBDataPhoto;
import es.shared.domain.facebook.FacebookPhotos;
import es.shared.domain.facebook.Images;

public class FacebookDownloadView extends Composite {

	private final FacebookAuthenticatedServiceAsync facebookService = GWT.create(FacebookAuthenticatedService.class);

	private final String FACEBOOKAUTH_URL = "https://www.facebook.com/dialog/oauth";
	private	final String FACEBOOKCLIENT_ID = "652936521517274";
	private static final Auth AUTH 	= Auth.get();
	private final VerticalPanel 	mainPanelDownload;
	private final FlexTable filesTable = new FlexTable();


	private ScrollPanel panelScroll = new ScrollPanel();
	private IntViews interaccion;
	//final Label labelAccessToken	= new Label("");
	final TextBox plTextBox = new TextBox();
	Button buttonFBAuth = new Button("Conecta Facebook");
	final Button buttonFBDownloadPhotos = new Button("Obtén las fotos de Facebook");
	
	public FacebookDownloadView(IntViews params) {
		// MAIN PANEL
		
		mainPanelDownload=new VerticalPanel();
		initWidget(panelScroll);
		panelScroll.add(mainPanelDownload);	
			
		if(params.getFBToken().isEmpty()){
			interaccion=new IntViews();
			loginView();			
		}else{
			plTextBox.setText(params.getFBToken());
			afterLoginView();
		}
		//si queremos pedir mas de un parametro se hace con "&". Ej: https://graph.facebook.com/me/photos?fields=likes&access_token=xxxxx

	}

	private void loginView(){
		mainPanelDownload.clear();
		buttonFBAuth.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					afterLoginView();
					mainPanelDownload.add(buttonFBAuth);
					final AuthRequest req = new AuthRequest(FACEBOOKAUTH_URL, FACEBOOKCLIENT_ID);
					AUTH.login(req, new Callback<String, Throwable>() {
						public void onFailure(Throwable reason) {
							Window.alert("Error en la autenticación: \n" + reason);
							afterLoginView();
							mainPanelDownload.add(buttonFBAuth);
						}
						public void onSuccess(String token) {
							interaccion.setFBToken(token);
							plTextBox.setText(interaccion.getFBToken());
							afterLoginView();
						}
					});
				}
			});
		//mainPanelDownload.add(new HTML("<h1> Descarga tus fotos ! </h1>"));
		mainPanelDownload.add(buttonFBAuth);
	}

	private String helpLoginFacebook() {
		// TODO Auto-generated method stub
		String contain="Puede que se deba a la denegación de la aplicación por parte de Facebook.";
		contain += "\n Si deseas usarla, podrás usar el token generado desde aquí: \"https://developers.facebook.com/tools/explorer/ \" con el permiso de \"user_photos\" y \"publish_action\" (Situado en \"extended permission\")";
		contain += "";
		return (contain);
	}
	private void afterLoginView(){
		mainPanelDownload.clear();
		//mainPanelDownload.add(new HTML("<h1> Descarga tus fotos ! </h1>"));
		mainPanelDownload.add(new HTML("<center>Access Token: </center>"));
		buttonFBDownloadPhotos.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (plTextBox.getText()=="" || plTextBox.getText().isEmpty()){
					Window.alert("Please, login before getting Photos List");		}
				else{
					facebookService.findPhotos(plTextBox.getText(), new AsyncCallback<FacebookPhotos>() {
						public void onSuccess(FacebookPhotos result) {
							photosView(result);						}
						public void onFailure(Throwable caught) {
							Window.alert("Ha habido un error: \n"+caught+"\n"+helpLoginFacebook());						}
					});	
				}
			}
		});
		mainPanelDownload.add(plTextBox);
		mainPanelDownload.add(buttonFBDownloadPhotos);
	}


	void photosView(FacebookPhotos result){
		int i = 0;
				
		filesTable.setStylePrimaryName("filesTable");
		filesTable.getRowFormatter().setStylePrimaryName(0, "firstRow");
		filesTable.setWidget(0, 0, new Label("Foto"));
		filesTable.setWidget(0, 1, new Label("Seleccionar"));

		mainPanelDownload.clear();
		//mainPanelDownload.add(new HTML("<h1> Descarga tus fotos ! </h1>"));
		Button refresh =  new Button("Refrescar");
		refresh.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				afterLoginView();}
		});
		
		mainPanelDownload.add(refresh);
		String output;
		//output += "<legend>Facebook Photos</legend>";
		if (result != null && !result.getData().isEmpty()) {
			for (FBDataPhoto a: result.getData()) {
				Button descargar = new Button("Seleccionar");
				final String link=a.getImages().get(0).getSource();
/*				for(Images ja:a.getImages()){
					link+="<a href=\""+ja.getSource()+"\"> "+ja.getHeight()+"</a>";
				}*/
				descargar.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						interaccion.getLink().add(link);
						PhotoLoad.go("publishView", interaccion);
					}
				});
				filesTable.setWidget(i+1, 2, new HTML(link));
				output ="<span> <a href=\""+a.getLink()+"\"><img src=\"" + a.getPicture()+"\" alt=\""+ a.getName()+" ("+a.getId()+")\"></a></span><br/>";
				filesTable.setWidget(i+1, 0, new HTML(output));
				filesTable.setWidget(i+1, 1, descargar);
				i ++;
			}
		}else{
			/*output="<span> No results </span>";
			output+= "\n <p>"+helpLoginFacebook()+"</p>";*/
		}
		//output +="</fieldset>";
		//HTML friends = new HTML(output);
		//mainPanelDownload.add(friends);
		mainPanelDownload.add(filesTable);
	}
}

