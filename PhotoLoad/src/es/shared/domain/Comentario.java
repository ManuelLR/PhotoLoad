package es.shared.domain;

import java.io.Serializable;

public class Comentario implements Serializable{

	private static final long serialVersionUID = -6435131293699935179L;
	private String contenido;
	
	
	public Comentario(){
		
	}
	
	public Comentario(String c){
		contenido = c;
	}
	
	public String getContenido(){
		
		return contenido;
		
	}
	
	public void setContenido(String c){
		
		contenido = c;
		
	}

}
