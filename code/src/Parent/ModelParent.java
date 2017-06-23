package Parent;


import Connection.Connection;
import Dependance.Dependance;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import Annotation.Model;
import Annotation.View;

public abstract class ModelParent {
	private Dependance dependance;
	private java.sql.Connection em;
	private ArrayList<Object> persists = new ArrayList<Object>();
	private ArrayList<Object> removes = new ArrayList<Object>();
	
	public void initModel(Dependance dependance){
		this.dependance = dependance;
		this.em = Connection.getEm();
	}
	public java.sql.Connection getEntityManager(){
		return this.em;
	}
	public Dependance getDependance(){
		return this.dependance;
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
	
	
	public java.sql.Date convertToSqlDate(Date utilDate){
	    return new java.sql.Date(utilDate.getTime());
	}
	
}
