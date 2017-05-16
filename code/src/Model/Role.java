package Model;
import java.util.Collection;

import javax.persistence.Query;

import Annotation.Model;

@Model(entity="Role")
public class Role extends Parent.ModelParent{
	public Collection<Entity.Role> all(){

		Query query = this.getEntityManager().createQuery("SELECT o FROM Role o");
		
		return query.getResultList();
	}
}
