package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Annotation.Model;

@Model(entity="TypeChambre")
public class TypeChambre  extends Parent.ModelParent{
	
	/**
	 * 
	 * @param id
	 * @return Entity.Client
	 */
	public Entity.TypeChambre find(Long id){
		PreparedStatement ts;
		Entity.TypeChambre typeChambre = null;
		try {
			ts = this.getEntityManager().prepareStatement("SELECT * FROM type_chambre where id=?");
			ts.setLong(1, id);
			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				 typeChambre = new Entity.TypeChambre();
				 typeChambre.setId(rs.getLong("type_chambre.id"));
				 typeChambre.setSuite(rs.getString("type_chambre.suite"));
		
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return typeChambre;
	}
}
