package Model;

import Annotation.Model;
import Annotation.View;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

@Model(entity="Incident")
public class Incident extends Parent.ModelParent{
	
	public void insert(Entity.Incident incident){
		PreparedStatement ts;
		
		try {
			ts = this.getEntityManager().prepareStatement("INSERT INTO incident(description) VALUES (?)");
			ts.setString(1, incident.getDescription());
			ts.executeUpdate();
		}catch (SQLException e) {
			
		}
	}
	
	public Collection<Entity.Incident> findAll(){
		PreparedStatement ts;
		LinkedList<Entity.Incident> incidents = new LinkedList<Entity.Incident>();
		try {
			ts = this.getEntityManager().prepareStatement("SELECT * from incident");
			ResultSet rs = ts.executeQuery();
			while (rs.next()) {
				Entity.Incident incident = new Entity.Incident();
				incident.setId(rs.getLong("id"));
				incident.setDescription(rs.getString("description"));
				
				incidents.add(incident);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return incidents;
	}
}
