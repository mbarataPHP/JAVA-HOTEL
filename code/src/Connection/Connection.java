package Connection;

import Enum.Environment;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Dependance.Dependance;
import Log.Log;
import Metier.Scan;
import Model.Test;
public class Connection {
	private Dependance fb;
	private static EntityManager em = null;
	
	private ArrayList<Object> entities;
	private Hashtable<String, Object> models;
	
	
	public Connection(Dependance fb){
		this.fb = fb;
		if(em==null){
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("em.test");
			em = entityManagerFactory.createEntityManager();
			this.entities = new ArrayList<Object>();
			this.models = new Hashtable<String, Object>();
			this.mesModel();
		}
	}
	
	/**
	 * Cette méthode permet de récupérer tout les models automatiquement
	 */
	private void mesModel(){
		
		ArrayList<Class<?>> models = Scan.getClassesForPackage("Model");
		
		Log log = (Log) this.fb.get("log");
		log.setLog("logModel", "les type de models: ", Environment.DEV);
		log.setLog("logModel", "", Environment.DEV);
		for(java.lang.Class model : models) {
		
			
			try {
				
				Parent.Model unModel = (Parent.Model) model.newInstance();
				unModel.initModel(this.fb);
				this.models.put(model.getName(), unModel);
				
				log.setLog("logModel", model.getName(), Environment.DEV);
				  
				
			} catch (InstantiationException |  IllegalAccessException e) {
				// TODO Auto-generated catch block
				//System.out.println("erreur "+e.get);
				e.printStackTrace();
			}catch(java.lang.ClassCastException e){
				System.out.println("<!---------------------------------");
				System.out.println("attention le Model "+model.getName());
				System.out.println("ne contient pas de \"extends Parent.Model\"");
				System.out.println("---------------------------------!>");
				System.out.println("");
			}
		}
	
		log.getLog("logModel").close();
	}
	
	
	public static EntityManager getEm() {
		return em;
	}
	
	
	public void persist(Object entity){
		this.entities.add(entity);
	}
	
	public void flush(){
		EntityTransaction transac = em.getTransaction();
		transac.begin();
		
		
		for(Object entitie : this.entities){
			em.persist(entitie);
		}
		
	    transac.commit();
	
		this.entities = new ArrayList<Object>();
	    //em.getEntityManagerFactory().getCache().evictAll();
	    
	}
}
