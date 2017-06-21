package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Annotation.Model;

@Model(entity="Etage")
public class Etage extends Parent.ModelParent{
	/**
	 * 
	 * @param id
	 * @return Entity.Client
	 */
	public Entity.Etage find(Long id){
		PreparedStatement ts;
		Entity.Etage etage = null;
		try {
			ts = this.getEntityManager().prepareStatement("SELECT * FROM etage where id=?");
			ts.setLong(1, id);
			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				 etage = new Entity.Etage();
				 etage.setId(rs.getLong("etage.id"));
				 etage.setNumEtage(rs.getInt("etage.num_etage"));
		
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return etage;
	}
}
