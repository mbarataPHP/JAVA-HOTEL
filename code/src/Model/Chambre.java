package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Annotation.Model;
import Connection.Connection;


@Model(entity="Chambre")
public class Chambre extends Parent.ModelParent{
	
	/**
	 * @return Entity.Client
	 */
	public LinkedList<Entity.Chambre> findAll(){
		PreparedStatement ts;
		LinkedList<Entity.Chambre> chambres = new LinkedList<Entity.Chambre>();

		try {
			ts = this.getEntityManager().prepareStatement("SELECT * FROM chambre "
					+ "LEFT JOIN type_chambre ON chambre.id_type = type_chambre.id "
					+ "LEFT JOIN etage  ON chambre.id_etage = etage.id");

			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				Entity.Chambre chambre = new Entity.Chambre();
				chambre.setId(rs.getLong("chambre.id"));
				chambre.setNbrPlace(rs.getInt("chambre.nbr_place"));
				chambre.setPrix(rs.getFloat("chambre.prix"));
				 
				Connection connect = (Connection) this.getDependance().get("connection");
				
				Etage etageModel = (Etage) connect.getModel("Model.Etage");
				chambre.setEtage(etageModel.find(rs.getLong("etage.id")));
				
				
				TypeChambre typeChambreModel = (TypeChambre) connect.getModel("Model.TypeChambre");
				chambre.setTypeChambre(typeChambreModel.find(rs.getLong("type_chambre.id")));
				
				
				
				chambres.add(chambre);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return chambres;
	}
	

	/**
	 * 
	 * @param id
	 * @return Entity.Client
	 */
	public Entity.Chambre find(Long id){
		PreparedStatement ts;
		Entity.Chambre chambre = null;
		try {
			ts = this.getEntityManager().prepareStatement("SELECT * FROM chambre LEFT JOIN type_chambre ON chambre.id_type = type_chambre.id LEFT JOIN etage  ON chambre.id_etage = etage.id where chambre.id=?");
			ts.setLong(1, id);
			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				 chambre = new Entity.Chambre();
				 chambre.setId(rs.getLong("chambre.id"));
				 chambre.setNbrPlace(rs.getInt("chambre.nbr_place"));
				 chambre.setPrix(rs.getFloat("chambre.prix"));
				 
					Connection connect = (Connection) this.getDependance().get("connection");
					
					Etage etageModel = (Etage) connect.getModel("Model.Etage");
					chambre.setEtage(etageModel.find(rs.getLong("etage.id")));
					
					TypeChambre typeChambreModel = (TypeChambre) connect.getModel("Model.TypeChambre");
					chambre.setTypeChambre(typeChambreModel.find(rs.getLong("type_chambre.id")));
					
				// client.setMail(rs.getString("chambre.mail"));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return chambre;
	}
}