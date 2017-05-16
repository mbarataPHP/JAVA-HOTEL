package Model;

import java.util.Collection;

import javax.persistence.Query;

import Annotation.Model;
import Entity.Utilisateur;
import Session.Session;

@Model(entity="Utilisateur")
public class User extends Parent.ModelParent{
	
	/**
	 * 
	 * @return
	 */
	public Collection<Utilisateur> listUser(){
		Session session = (Session) this.getDependance().get("session");
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Utilisateur u WHERE u.mail NOT IN (:user) ");
		query.setParameter("user", session.getUtilisateur().getMail());
		
		
		Collection<Utilisateur> col = query.getResultList();
		
		 return col;
	}
	
	/**
	 * Cette méthode vérifie si l'email existe bien
	 * @param mail
	 * @return
	 */
	public boolean MailExist(String mail){
		boolean isExist = false;
		Query query = this.getEntityManager().createQuery("SELECT u FROM Utilisateur u WHERE u.mail IN (:user) ");
		query.setParameter("user", mail);
		
		Utilisateur user = (Utilisateur) this.singleOrNullResult(query);
		
		if(user!=null){
			isExist = true;
		}
		
		return isExist;
	}
}
