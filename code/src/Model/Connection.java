package Model;

import static java.lang.Math.toIntExact;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.persistence.Query;
import Annotation.Model;
import Entity.Utilisateur;
import Metier.Crypt;
import Session.Session;

@Model(entity="Utilsateur")
public class Connection extends Parent.ModelParent{
	
	/**
	 * Cette méthode permet de vérifier les identifiant de l'utilsateur
	 * @param email
	 * @param password
	 * @return Utilsateur|null
	 */
	public Utilisateur verifyLogin(String email, String password){
		try {
			password = Metier.Crypt.get_SHA_512_SecurePassword(password, email); //on crypte les mot passe
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Utilisateur u WHERE u.mail=:email AND u.password=:password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		return (Utilisateur) this.singleOrNullResult(query);
	}

}
