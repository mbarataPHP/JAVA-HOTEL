package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Annotation.Model;

@Model(entity="Client")
public class Client extends Parent.ModelParent{
	
	/**
	 * Cette méthode permet de retourne l'email du client
	 * @param mail
	 * @return
	 */
	public Entity.Client getClientByMail(String mail){
		PreparedStatement ts;
		Entity.Client client = null;
		try {
			ts = this.getEntityManager().prepareStatement("SELECT * FROM client where mail=?");
			ts.setString(1, mail);
			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				 client = new Entity.Client();
				 client.setId(rs.getLong("client.id"));
				 client.setFirstname(rs.getString("client.firstname"));
				 client.setLastname(rs.getString("client.lastname"));
				 client.setMail(rs.getString("client.mail"));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return client;
	}
	
	
	public LinkedList<Entity.Client> findAll(){
		PreparedStatement ts;
		LinkedList<Entity.Client> clients = new LinkedList<Entity.Client>();
		Entity.Client client = null;
		try {
			ts = this.getEntityManager().prepareStatement("SELECT * FROM client");
			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				 client = new Entity.Client();
				 client.setId(rs.getLong("client.id"));
				 client.setFirstname(rs.getString("client.firstname"));
				 client.setLastname(rs.getString("client.lastname"));
				 client.setMail(rs.getString("client.mail"));

				 clients.add(client);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clients;
	}
	
	
	/**
	 * 
	 * @param id
	 * @return Entity.Client
	 */
	public Entity.Client find(Long id){
		PreparedStatement ts;
		Entity.Client client = null;
		try {
			ts = this.getEntityManager().prepareStatement("SELECT * FROM client where id=?");
			ts.setLong(1, id);
			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				 client = new Entity.Client();
				 client.setId(rs.getLong("client.id"));
				 client.setFirstname(rs.getString("client.firstname"));
				 client.setLastname(rs.getString("client.lastname"));
				 client.setMail(rs.getString("client.mail"));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return client;
	}
	
	
	/**
	 * Cette méthode permet d'inserer un nouveau client
	 * @param client
	 */
	public void insert(Entity.Client client){
		PreparedStatement ts;
		try {
			ts = this.getEntityManager().prepareStatement("INSERT INTO client(mail, lastname, firstname) VALUES (?, ?, ?)");
			ts.setString(1, client.getMail());
			ts.setString(2, client.getLastname());
			ts.setString(3, client.getFirstname());
			ts.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
