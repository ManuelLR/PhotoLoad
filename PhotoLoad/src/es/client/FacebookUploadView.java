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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import es.client.services.FacebookAuthenticatedService;
import es.client.services.FacebookAuthenticatedServiceAsync;
import es.shared.IntViews;
import es.shared.domain.facebook.FBDataPhoto;
import es.shared.domain.facebook.FBPhotoUpload;
import es.shared.domain.facebook.FacebookPhotos;
import es.shared.domain.facebook.Struct;

public class FacebookUploadView extends Composite {

	private final FacebookAuthenticatedServiceAsync facebookService = GWT.create(FacebookAuthenticatedService.class);

	private static final Auth AUTH 	= Auth.get();
	private final VerticalPanel 	mainPanelUpload;
	private ScrollPanel panelScroll = new ScrollPanel();
	final Label labelAccessToken	= new Label("");

	//final Label labelAccessToken = new Label("<<INSERT TOKEN ACCESS AND UNCOMMENT>>");
		Button buttonFBAuth = new Button("Conecta Facebook");

		Button buttonFBUploadPhotos = new Button("Sube la foto a Facebook");

		//final Label labelFB 		= new Label("");
		final TextBox plTextBox = new TextBox();
		final TextBox linkPhotoUpdate = new TextBox();
		final TextBox namePhotoUpdate = new TextBox();
		
	public FacebookUploadView(IntViews params) {

		// MAIN PANEL
		mainPanelUpload = new VerticalPanel();
		initWidget(panelScroll);
		panelScroll.add(mainPanelUpload);
		//initWidget(mainPanelDownload);

		final String FACEBOOKAUTH_URL = "https://www.facebook.com/dialog/oauth";
		final String FACEBOOKCLIENT_ID = "652936521517274";
		//si queremos pedir mas de un parametro se hace con "&". Ej: https://graph.facebook.com/me/photos?fields=likes&access_token=xxxxx
		final HTML widgetHelp = new HTML(helpLoginFacebook());
		buttonFBAuth.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				afterLoginView();
				mainPanelUpload.add(buttonFBAuth);
				mainPanelUpload.add(widgetHelp);
				final AuthRequest req = new AuthRequest(FACEBOOKAUTH_URL, FACEBOOKCLIENT_ID);
				AUTH.login(req, new Callback<String, Throwable>() {
					public void onFailure(Throwable reason) {
						Window.alert("Error en la autenticación: \n"+reason);
					}
					public void onSuccess(String token) {
						plTextBox.setText(token);
						mainPanelUpload.remove(buttonFBAuth);
						mainPanelUpload.remove(widgetHelp);
					}
				});
			}
		});



		buttonFBUploadPhotos.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//if (labelAccessToken.getText()=="")
				if (plTextBox.getText()=="" || plTextBox.getText().isEmpty()){
					Window.alert("Please, login before update Photos");
				}else if(linkPhotoUpdate.getText()=="" || linkPhotoUpdate.getText().isEmpty()){
					Window.alert("Please, pon un link");
				}
				else{
					//facebookService.findPhotos(labelAccessToken.getText(), new AsyncCallback<FacebookPhotos>() {
					//facebookService.findPhotos(tempToken, new AsyncCallback<FacebookPhotos>() {
					//facebookService.findPhotos(plTextBox.getText(), new AsyncCallback<FacebookPhotos>() {
					FBPhotoUpload foto = new FBPhotoUpload();
					foto.setURL(linkPhotoUpdate.getText());
					foto.setName(namePhotoUpdate.getText());
					facebookService.uploadPhoto(plTextBox.getText(), foto, new AsyncCallback<Struct>(){
						@Override
						public void onSuccess(Struct result) {
							//Window.alert(result.toString());
							//Window.alert("Las fotos se han obtenido correctamente, vamos aproceder a mostrarlas");
							//showPhotos(result);
							boolean correct= result != null;
							try{
								correct = !result.getId().isEmpty() && result.getId() != null;
							}catch(Exception e){
								correct =false;}
							if (correct){
								Window.alert("Foto subida correctamente");
								afterLoginView();

							}else{
								Window.alert("La foto no se ha podido subir, disculpe las molestias");
							}
							//Window.alert("Foto subida correctamente"+result.getId());
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Ha habido un error: \n"+caught+". \n"+helpLoginFacebook());
							//helpLoginFacebook();
						}
					});	

				}
			}
		});
		loginView();
		/*mainPanelUpload.add(buttonFBAuth);
		mainPanelUpload.add(labelFB);
		//mainPanelUpload.add(labelAccessToken);
		mainPanelDownload.add(plTextBox);
		mainPanelUpload.add(plTextBox);
		mainPanelUpload.add(linkPhotoUpdate);
		mainPanelUpload.add(buttonFBUploadPhotos);*/

	}

	
	void showPhotos(FacebookPhotos result){
		String output="<fieldset>";
		output += "<legend>Facebook Photos</legend>";
		if (result != null) {
			/*if(result.getData().isEmpty()){
				helpLoginFacebook();
			}else{*/
			for (FBDataPhoto a: result.getData()) {
//				output +="<span>" +  a.getName() + "</span><br/>";
				output +="<span> <a href=\""+a.getLink()+"\"><img src=\"" + a.getPicture()+"\" alt=\""+ a.getName()+" ("+a.getId()+")\"></a></span><br/>";

		}//}
		}else{
			output="<span> No results </span>";
			helpLoginFacebook();
		}
		output +="</fieldset>";
		HTML friends = new HTML(output);
		mainPanelUpload.add(friends);
	}
	
	private void loginView(){
		mainPanelUpload.clear();
		//mainPanelUpload.add(new HTML("<h1> Sube tus fotos ! </h1>"));
		mainPanelUpload.add(buttonFBAuth);
	}

	private String helpLoginFacebook() {
		// TODO Auto-generated method stub
		String contain="Puede que se deba a la denegación de la aplicación por parte de Facebook.";
		contain += "\n Si deseas usarla, deberás usar el token generado desde aquí: \"https://developers.facebook.com/tools/explorer/ \" con el permiso de \"user_photos\" y \"publish_action\" (Situado en \"extended permission\")";
		contain += "";
		return (contain);
	}
	private void afterLoginView(){
		mainPanelUpload.clear();
		//mainPanelUpload.add(new HTML("<h1> Sube tus fotos ! </h1>"));
		mainPanelUpload.add(new HTML("Access Token: "));
		mainPanelUpload.add(plTextBox);
		mainPanelUpload.add(new HTML("Link a la foto que deseas subir: "));
		mainPanelUpload.add(linkPhotoUpdate);
		mainPanelUpload.add(new HTML("Añade un nombre a tu foto: "));
		mainPanelUpload.add(namePhotoUpdate);		
		mainPanelUpload.add(buttonFBUploadPhotos);
	}
}

