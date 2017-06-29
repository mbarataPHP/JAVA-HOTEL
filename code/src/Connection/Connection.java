package Connection;

import Enum.Environment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;


import Dependance.Dependance;
import Log.Log;
import Metier.Scan;

public class Connection {
	private Dependance fb;
	private static java.sql.Connection em = null;
	

	private Hashtable<String, Object> models;
	
	
	public Connection(Dependance fb){
		this.fb = fb;
		if(em==null){
			
			if(this.fb.getEnvironment()==Environment.PROD){
				try {
					em = new MySQLSSHConnector().connection_ssh_db();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					em = new MySQLSSHConnector().connection_db();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			this.models = new Hashtable<String, Object>();
			this.mesModel();
		}
	}
	
	/**
	 * Cette méthode permet de récupérer tout les models automatiquement
	 */
	private void mesModel(){
		
		ArrayList<Class<?>> models = Scan.getClassesForPackage("Model");
		
		

		for(java.lang.Class model : models) {
		
			
			try {
				
				Parent.ModelParent unModel = (Parent.ModelParent) model.newInstance();
				unModel.initModel(this.fb);
				this.models.put(model.getName(), unModel);
				

				  
				
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
	
	}
	
	
	public static java.sql.Connection getEm() {
		return em;
	}
	
	public Object getModel(String key){
		return this.models.get(key);
	}
	
	

}
