package Model;
import java.util.Collection;

import javax.persistence.Query;

import Annotation.Model;
import Session.Session;

@Model(entity="Role")
public class Role extends Parent.ModelParent{
	public Collection<Entity.Role> all(){

		Query query = this.getEntityManager().createQuery("SELECT o FROM Role o");
		
		return query.getResultList();
	}
	
	
	public Collection<Entity.Role> getListeRole(){
		Session session = (Session) this.getDependance().get("session");
		Query query = this.getEntityManager().createQuery("SELECT o FROM Role o WHERE o.id NOT IN (:id)");
		query.setParameter("id", session.getUtilisateur().getRole().getId());
		
		return query.getResultList();
	}
}
