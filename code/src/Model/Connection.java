package Model;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Annotation.Model;
import Entity.Utilisateur;

@Model(entity="Utilsateur")
public class Connection extends Parent.ModelParent{
	
	/**
	 * Cette méthode permet de vérifier les identifiant de l'utilsateur
	 * @param email
	 * @param password
	 * @return Utilsateur|null
	 */
	public Utilisateur verifyLogin(String login, String password){
		Utilisateur user = null;
		

		
		PreparedStatement ts;
		try {
			ts = this.getEntityManager().prepareStatement("SELECT * FROM utilisateur LEFT JOIN role ON utilisateur.id_role = role.id WHERE login=? AND password=?");
			ts.setString(1, login);
			ts.setString(2, password);
			
			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				 user = new Utilisateur();
				 
				 user.setId(rs.getLong("utilisateur.id"));
				 user.setLastname(rs.getString("lastname"));
				 user.setFirstname(rs.getString("firstname"));
				 user.setLogin(rs.getString("login"));
				 user.setPassword(rs.getString("password"));
				 
				 Entity.Role role = new Entity.Role();
				 role.setId(rs.getLong("role.id"));
				 role.setTypeRole(rs.getString("type_role"));
				 
				 user.setRole(role);
			 }
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Utilisateur) user;
	}

}
