package Parent;

import javax.persistence.EntityManager;


import Connection.Connection;
import Dependance.Dependance;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Query;
import Annotation.Model;
import Annotation.View;

public abstract class ModelParent {
	private Dependance dependance;
	private EntityManager em;
	private ArrayList<Object> persists = new ArrayList<Object>();
	private ArrayList<Object> removes = new ArrayList<Object>();
	
	public void initModel(Dependance dependance){
		this.dependance = dependance;
		this.em = Connection.getEm();
	}
	
	public Dependance getDependance(){
		return this.dependance;
	}
	public EntityManager getEntityManager(){
		return this.em;
	}
	
	public void persist(Object persist){
		persists.add(persist);
	}
	public void remove(Object remove){
		removes.add(remove);
	}
	public void flush(){
		em.getTransaction().begin();
		
		for(Object remove : removes){
			em.remove(remove);
		}
		
		for(Object persist : persists){
			em.persist(persist);
		}
		
		 
		  
		em.getTransaction().commit();
		persists = new ArrayList<Object>();
		removes = new ArrayList<Object>();
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
	
	/**
	 * Cette méthode retourne l'entité à partir de sa clé primaire
	 * @param id
	 * @return
	 */
	public Object find(int id){
		Object ob = this.getObject();

		
        return em.find(ob.getClass(), Long.valueOf(id));

	}
	
	/**
	 * Cette méthode retourne toute la liste complet de l'entité
	 * @return
	 */
	public Collection<Object> findAll(){
		Object ob = this.getObject();
		Query query = em.createQuery("SELECT o FROM "+ob.getClass().getSimpleName()+" o");
		
	
	
		return (Collection<Object>) query.getResultList();
	}
	
	
	public Object singleOrNullResult(Query query){
		Object object = null;
		Collection<Object> objects = (Collection<Object>) query.getResultList();
		if(objects.size()>0){
			object = objects.iterator().next();
		}
		
		return object;
	}
	
}
