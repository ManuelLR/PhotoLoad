package es.repository;

import java.util.List;

import es.shared.domain.Comentario;

public interface ComentariosRepository {
	
	public List<Comentario> getAll(); // consultar lista

	public void post(Comentario c); // insertar 

	public void put(Comentario c, Integer i); // actualizar

	public void remove(Integer i); // eliminar 
	
	public Comentario get(Integer i); //consultar

}
