package es.repository;

import java.util.List;

import es.shared.domain.Comentario;

public interface ComentariosRepository {
	
	public List<Comentario> get(Comentario c); // consultar

	public void post(Comentario c); // insertar 

	public void put(Comentario c); // actualizar

	public void remove(Comentario c); // eliminar 

}
