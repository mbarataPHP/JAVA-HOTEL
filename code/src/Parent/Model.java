package Parent;

import javax.persistence.EntityManager;

import Connection.Connection;
import Dependance.Dependance;

public abstract class Model {
	private Dependance dependance;
	private EntityManager em;
	
	public void initModel(Dependance dependance){
		this.dependance = dependance;
		this.em = Connection.getEm();
	}
	
	
	public EntityManager getEntityManager(){
		return this.em;
	}
}
