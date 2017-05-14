package Parent;

import javax.persistence.EntityManager;


import Connection.Connection;
import Dependance.Dependance;
import java.lang.annotation.Annotation;
import Annotation.Model;
import Annotation.View;

public abstract class ModelParent {
	private Dependance dependance;
	private EntityManager em;
	
	public void initModel(Dependance dependance){
		this.dependance = dependance;
		this.em = Connection.getEm();
	}
	
	
	public EntityManager getEntityManager(){
		return this.em;
	}
	
	private Object getObject(){
		Annotation column = this.getClass().getAnnotation(Model.class);
		Model ModelAnnotation = (Model) column;
		Object entity = null;
		String entityString = "Entity."+ModelAnnotation.entity();
		try {
			Class<?> clazz = Class.forName(entityString);
			entity = clazz.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entity;
	}
	
	
	
	
}
