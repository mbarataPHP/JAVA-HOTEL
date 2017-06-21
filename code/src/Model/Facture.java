package Model;

import Annotation.Model;
import Connection.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

@Model(entity="Facture")
public class Facture extends Parent.ModelParent{
	
	public Collection<Entity.Facture> findAll(){
		PreparedStatement ts;
		LinkedList<Entity.Facture> factures = new LinkedList<Entity.Facture>();
		
		try {
			ts = this.getEntityManager().prepareStatement("SELECT facture.id, facture.date_debut, facture.date_fin, client.id, chambre.id"
					+ "FROM facture "
					+ "LEFT JOIN client ON facture.id_client = client.id "
					+ "LEFT JOIN chambre ON facture.id_chambre = chambre.id");
			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				 Entity.Facture facture = new Entity.Facture();
				 Connection connect = (Connection) this.getDependance().get("connection");
				 Client clientModel = (Client) connect.getModel("Model.Client");
				 Chambre chambreModel = (Chambre) connect.getModel("Model.Facture");
				 
				 
				 facture.setClient(clientModel.find(rs.getLong("client.id")));
				 facture.setChambre(chambreModel.find(rs.getLong("chambre.id")));
				 
				 facture.setDateDebut(rs.getDate("facture.date_debut"));
				 facture.setDateFin(rs.getDate("facture.date_fin"));
				 factures.add(facture);
			 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return factures;
	}

}
