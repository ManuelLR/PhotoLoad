package es.repository;

import java.util.ArrayList;
import java.util.List;

import es.shared.domain.Comentario;

public class ListComentariosRepository implements ComentariosRepository {
	
	List<Comentario> misComentarios;
	
	public ListComentariosRepository() {
	
		misComentarios = new ArrayList<Comentario>();

	}

	@Override
	public List<Comentario> get() {

		return misComentarios;
		
	}

	@Override
	public void post(Comentario c) {

		misComentarios.add(c);
		
	}

	@Override
	public void put(Comentario c, Integer i) {

		misComentarios.get(i).setContenido(c.getContenido());
		
	}

	@Override
	public void remove(Integer i) {
		
		misComentarios.remove(i);
		
	}

	@Override
	public Comentario get(Integer i) {
		return misComentarios.get(i);
	}

}
