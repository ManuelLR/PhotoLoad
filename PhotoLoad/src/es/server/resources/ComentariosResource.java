package es.server.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import es.repository.ComentariosRepository;
import es.repository.ListComentariosRepository;
import es.shared.domain.Comentario;


@Path("/comments")
public class ComentariosResource {
	
	private static ComentariosResource _instance = null;
	
	ComentariosRepository repository;
	
	private ComentariosResource(){
		
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
		repository.post(valoracion);
		repository.post(quedada);
		
		
	}
	
	@GET
	@Produces("application/json")
	public List<Comentario> getAll(){
		
		if(repository==null){
			throw new NotFoundException("La lista no puede estar vacia");
		}
		
		return repository.getAll();
		
	}

	@GET
	@Path("/{index}")
	@Produces("application/json")
	public Comentario get(@PathParam("index") Integer i){
		return repository.get(i);
		
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addComentario(@Context UriInfo uriInfo, Comentario c){
		
		if(c == null || c.getContenido().equals(""))
			throw new BadRequestException("El comentario no puede ser null o vacio");
		
		
		repository.post(c);
		
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(repository.getAll().size());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(c);
				
		return resp.build();
		
	}

	@PUT
	@Path("/edit/{index}")
	@Consumes("application/json")
	public Response updateComentario(@PathParam("index") Integer i, Comentario c){
		
		
		if(i >= repository.getAll().size()){
			
			throw new NotFoundException("El comentario con indice" + i + "no se encuentra en la lista");
			
		}
		
		if(c == null || c.getContenido().equals("")){
			
			throw new BadRequestException("El comentario no puede ser null o vacio");
			
		}
		
		repository.remove(i);
		repository.put(c, i);
		
		return Response.noContent().build();
		
	}
	
	@DELETE
	@Path("/delete/{index}")
	public void RemoveComentario(@PathParam("index") Integer i){
		
		if(i >= repository.getAll().size()){
			
			throw new NotFoundException("El comentario con indice" + i + "no se encuentra en la lista");
			
		}else{
			
			repository.remove(i);
			
		}
		
	}
	
}
