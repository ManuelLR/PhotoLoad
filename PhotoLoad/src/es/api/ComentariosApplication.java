package es.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import es.server.resources.ComentariosResource;

public class ComentariosApplication extends Application{
	
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();
	
	public ComentariosApplication(){
		
		singletons.add(ComentariosResource.getInstance());
		
	}
	
	
	public Set<Class<?>> getClasses() {
		
		return classes;
		
	}
	
	public Set<Object> getSingletons() {
		
		return singletons;
		
	}
	

}
