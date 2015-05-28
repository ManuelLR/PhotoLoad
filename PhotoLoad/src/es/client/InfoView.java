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
		panel.add(new HTML(
				"<div id=\"foto\" style=\"text-align:center; margin:0px auto;\"><img src=\"/files/logoAjustado.png\" style=\"width: 10%\"></div>"));
		panel.add(new HTML("<div id=\"titulo\" style=\"margin: 0px auto;\"><h1> PhotoLoad </h1></div>"));
		panel.add(description());
		
		

	}

	private HTML description() {
		String result="<div id=\"texto\" style=\"font-size:medium; width:90%; margin: 0 auto;\"";
		result += "<div id=\"parrafo\">";
		result+= "<p>La aplicación PhotoLoad quiere facilitar la gestión de las fotos online y esto"
				+ " lo conseguiría de una manera muy sencilla para el usuario. Aprovechando que, actualmente,"
				+ " la mayoria de fotos se toman con el móvil y un alto porcentaje poseen la aplicación Google"
				+ " Fotos la cual por defecto sube las fotos a la nube de Google (sin publicarlas), sería posible"
				+ " crear un mashups que te permitiera ver esas fotos que se suben automáticamente con la"
				+ " opción de publicarlas en aplicaciones sociales como Facebook e Instagram. Puesto que no"
				+ " todo el mundo necesita hacer este proceso, el cual se puede hacer directamente desde las"
				+ " aplicaciones nativas, planteamos que esta aplicación, a su vez, pudiera hacer el proceso inverso"
				+ ", es decir, que permita guardar todas las fotos de Facebook e Instagram en Google Fotos o"
				+ " descargarlas para así conservar todos los recuerdos que caen en el olvido en las redes sociales."
				+ " </p>"
				+ " <p>En resumen un mashup que permita descargar y subir fotos a Google Drive, Facebook, "
				+ "Dropbox y FlickR.</p> "
				+ "<p>Hemos desplegado las distintas apis en 4 URL's distintas, para que en ésta entrega queden"
				+ " bien diferenciadas una de otras y así pueda resultar más secillo trabajar con ellas. Dichas URL's"
				+ " serían: </p> <ul> "
				+ "<li><a href=\"https://drivfac.appspot.com/\">Google Drive</a> </li>"
				+ " <li><a href=\"https://manuejemploaiss1.appspot.com\">Facebook</li>"
				+ " <li><a href=\"https://photoloaddropbox.appspot.com/\">Dropbox</a> </li>"
				+ " <li><a href=\"https://manuejemploaiss2.appspot.com\">FlickR</a></li> </ul>"
				+ " </div>";
		
		result+="<div class=\"title\" style=\"width:90%; margin: 0 auto;\"><h2>Api Rest</h2></div>";
		result+="<div id=\"Api Rest\" style=\"font-size:medium; width:90%; margin: 0 auto;\"><p>";
		result+="La URI principal será http://etsiiphotoload.appspot.com/api/photo";
		result+="</p><table border=\"1\" style=\"text-align:center;\">";
		result+="<tr bgcolor=\"#6FBEFB\"><th style=\"border: 1px solid black; border-collapse: collapse;\">HTTP</th><th>Plantilla URI</th><th>Descripción</th></tr>";
		result+="<tr><td>POST</td><td>/foto</td><td align=\"justify\">Añade una nueva foto.</br>Si se añade correctamente devuelve un mensaje “201 Uploaded”.</br>Si ocurre algún error devuelve “400 Error”.</td><tr>";
		result+="<tr bgcolor=\"#6FBEFB\"><td>POST</td><td>/foto</td><td align=\"justify\">Añade una nueva foto.</br>Si se añade correctamente devuelve un mensaje “201 Uploaded”.</br>Si ocurre algún error devuelve “400 Error”.</td><tr>";
		result+="</table></div>";
		
		
		result+="<div class=\"title\" style=\"width:90%; margin: 0 auto;\"><h2>Trabajo realizado por: </h2></div>";		
		result += "<div id=\"realizadoPor\" style=\"font-size:large; width:90%; margin: 0 auto;\">";
		result+="<ul>";
		result+="<li>David Tinoco Castillo</li>";
		result+="<li>Manuel López Ruiz</li>";
		result+="<li>Miguel Rodríguez Caballero</li>";
		result+="</ul>";
		result+="</div>";
		
		result+="</div>";

		return new HTML(result);
	}

}
