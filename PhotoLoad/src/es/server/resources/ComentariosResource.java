package es.server.resources;

import javax.ws.rs.Path;

import es.repository.ComentariosRepository;
import es.repository.ListComentariosRepository;
import es.shared.domain.Comentario;


@Path("/comentarios")
public class ComentariosResource {
	
	private static ComentariosResource _instance = null;
	ComentariosRepository repository;
	
	public ComentariosResource(){
		
		repository = new ListComentariosRepository();
		initialize();
		
	}
	
	public static ComentariosResource getInstance(){
		if(_instance == null){
			_instance = new ComentariosResource();
		}
		return _instance;
	}

	private void initialize() {
		Comentario valoracion = new Comentario();
		valoracion.setContenido("Que gran aplicacion!");
		Comentario quedada = new Comentario();
		quedada.setContenido("¿Por que no quedamos el sabado para celebrar el aprobado?");
		repository.put(valoracion);
		repository.put(quedada);
		
		
	}
	

}
