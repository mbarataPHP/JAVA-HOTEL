package Model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import Annotation.Model;
import Session.Session;

@Model(entity="Role")
public class Role extends Parent.ModelParent{
	/**
	 * 
	 * @return
	 */
	public Collection<Entity.Role> all(){
		PreparedStatement ts;
		LinkedList<Entity.Role> roles = new LinkedList<Entity.Role>();
		
		try {
			ts = this.getEntityManager().prepareStatement("SELECT * FROM role");
			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				 Entity.Role role = new Entity.Role();
				 role.setId(rs.getLong("id"));
				 role.setTypeRole(rs.getString("type_role"));
				 
				 roles.add(role);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		return roles;
	}
	
	/**
	 * Cette méthode 
	 * @return
	 */
	public Collection<Entity.Role> getListeRole(){
		Session session = (Session) this.getDependance().get("session");
		
		PreparedStatement ts;
		LinkedList<Entity.Role> roles = new LinkedList<Entity.Role>();
		
		try {
			ts = this.getEntityManager().prepareStatement("SELECT * FROM role WHERE id != ?");
			ts.setLong(1, session.getUtilisateur().getRole().getId());
			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				 Entity.Role role = new Entity.Role();
				 role.setId(rs.getLong("id"));
				 role.setTypeRole(rs.getString("type_role"));
				 
				 roles.add(role);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return roles;
	}
	
	
	
	public void insert(Entity.Role role){
		PreparedStatement ts;
		try {
			ts = this.getEntityManager().prepareStatement("INSERT INTO role(type_role) values (?)");
			ts.setString(1, role.getTypeRole());
			ts.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void remove(Entity.Role role){
		PreparedStatement ts;
		try {
			ts = this.getEntityManager().prepareStatement("DELETE FROM role WHERE id = ?");
			ts.setLong(1, role.getId());
			ts.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
