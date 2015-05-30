package es.repository;

import java.util.ArrayList;
import java.util.List;

import es.shared.domain.Comentario;

public class ListComentariosRepository implements ComentariosRepository {
	
	private List<Comentario> misComentarios = new ArrayList<Comentario>();
	
	public ListComentariosRepository() {
	
		//misComentarios = new ArrayList<Comentario>();

	}

	@Override
	public List<Comentario> getAll() {

		return misComentarios;
		
	}

	@Override
	public void post(Comentario c) {

		misComentarios.add(c);
		
	}

	@Override
	public void put(Comentario c, Integer i) {

		misComentarios.add(i, c);
		
	}

	@Override
	public void remove(Integer i) {
		
		//misComentarios.remove(i);
		//misComentarios.set(i, null);
		misComentarios.remove(misComentarios.get(i));
		
	}

	@Override
	public Comentario get(Integer i) {
		return misComentarios.get(i);
	}

}
