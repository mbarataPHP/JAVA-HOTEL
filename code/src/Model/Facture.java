package Model;

import Annotation.Model;
import Connection.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Date;
import Enum.FactureType;

@Model(entity="Facture")
public class Facture extends Parent.ModelParent{
	
	/**
	 * Cette méthode permet de vérifie si la chambre est libre par raport la date de debut et la date de fin
	 * @param dateBefore
	 * @param dateAfter
	 * @param chambre
	 * @return
	 */
	public Entity.Facture isChambreFree(Date dateBefore, Date dateAfter, Entity.Chambre chambre){
		Entity.Facture facture = null;
		PreparedStatement ts;
		
		
		try{
			ts = this.getEntityManager().prepareStatement(""
					+ " SELECT facture.facture_type, facture.id, facture.date_debut, facture.date_fin, client.id, chambre.id "
					+ " FROM facture "
					+ " LEFT JOIN client ON facture.id_client = client.id "
					+ " LEFT JOIN chambre ON facture.id_chambre = chambre.id"
					+ " WHERE chambre.id=?"
					+ " AND ("
					+ " (facture.date_debut<=? and facture.date_fin>=?) "
					+ " OR"
					+ " (facture.date_debut>=? and facture.date_fin<=?)"
					+ " OR "
					+ " (facture.date_debut>=? and facture.date_fin>=? and facture.date_fin>=? and facture.date_debut<=?)"
					+ " OR"
					+ " (facture.date_debut<=? and facture.date_fin<=? and facture.date_fin>=?)"
					+ " )");
			ts.setLong(1, chambre.getId());
			
			ts.setDate(2, this.convertToSqlDate(dateBefore));
			ts.setDate(3, this.convertToSqlDate(dateAfter));
			
			ts.setDate(4, this.convertToSqlDate(dateBefore));
			ts.setDate(5, this.convertToSqlDate(dateAfter));
			
			ts.setDate(6, this.convertToSqlDate(dateBefore));
			ts.setDate(7, this.convertToSqlDate(dateBefore));
			ts.setDate(8, this.convertToSqlDate(dateAfter));
			ts.setDate(9, this.convertToSqlDate(dateAfter));
			
			
			ts.setDate(10, this.convertToSqlDate(dateBefore));
			ts.setDate(11, this.convertToSqlDate(dateAfter));
			ts.setDate(12, this.convertToSqlDate(dateBefore));
			
			ResultSet rs = ts.executeQuery();
			while (rs.next()) {
				facture = new Entity.Facture();
				 Connection connect = (Connection) this.getDependance().get("connection");
				 Client clientModel = (Client) connect.getModel("Model.Client");
				 Chambre chambreModel = (Chambre) connect.getModel("Model.Chambre");
				 
				 
				 facture.setClient(clientModel.find(rs.getLong("client.id")));
				 facture.setChambre(chambreModel.find(rs.getLong("chambre.id")));
				 facture.setFactureType(FactureType.fromString(rs.getString("facture.facture_type")));
				 facture.setDateDebut(rs.getDate("facture.date_debut"));
				 facture.setDateFin(rs.getDate("facture.date_fin"));;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return facture;
	}
	
	/**
	 * Cette méthode permet d'inserer une facture dans la base de données
	 * @param Entity.Facture facture
	 */
	public void insert(Entity.Facture facture){
		PreparedStatement ts;
		
		try {
			ts = this.getEntityManager().prepareStatement("INSERT INTO facture(date_debut, date_fin, facture_type, id_client, id_chambre) VALUES (?, ?, ?, ?, ?)");
			ts.setDate(1, this.convertToSqlDate(facture.getDateDebut()));
			ts.setDate(2, this.convertToSqlDate(facture.getDateFin()));
			ts.setString(3, facture.getFactureType().getText());
			ts.setLong(4, facture.getClient().getId());
			ts.setLong(5, facture.getChambre().getId());
			ts.executeUpdate();
		}catch (SQLException e) {
			
		}
	}
	
	
	public void encaisser(Entity.Facture facture){
		PreparedStatement ts;
		try {
			ts = this.getEntityManager().prepareStatement("UPDATE facture SET facture_type = ? WHERE id = ? ");
			ts.setString(1, FactureType.PAYER.getText());
			ts.setLong(2, facture.getId());
			ts.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public Collection<Entity.Facture> findAll(){
		PreparedStatement ts;
		LinkedList<Entity.Facture> factures = new LinkedList<Entity.Facture>();
		
		try {
			ts = this.getEntityManager().prepareStatement("SELECT facture.facture_type, facture.id, facture.date_debut, facture.date_fin, client.id, chambre.id"
					+ " FROM facture "
					+ " LEFT JOIN client ON facture.id_client = client.id "
					+ " LEFT JOIN chambre ON facture.id_chambre = chambre.id");
			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				 Entity.Facture facture = new Entity.Facture();
				 Connection connect = (Connection) this.getDependance().get("connection");
				 Client clientModel = (Client) connect.getModel("Model.Client");
				 Chambre chambreModel = (Chambre) connect.getModel("Model.Chambre");
				 
				 facture.setId(rs.getLong("facture.id"));
				 facture.setClient(clientModel.find(rs.getLong("client.id")));
				 facture.setChambre(chambreModel.find(rs.getLong("chambre.id")));
				 facture.setFactureType(FactureType.fromString(rs.getString("facture.facture_type")));
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
