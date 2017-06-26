package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import Annotation.Model;

@Model(entity="Menu")
public class Menu extends Parent.ModelParent{
	
	/**
	 * Cette méthode permet de supp un menu
	 * @param menu
	 */
	public void remove(Entity.Menu menu){
		PreparedStatement ts;
		try {
			ts = this.getEntityManager().prepareStatement("DELETE FROM menu WHERE id = ?");
			ts.setLong(1, menu.getId());
			ts.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Cette méthode permet d'inserer des menu
	 * @param menu
	 */
	public void insert(Entity.Menu menu){
		PreparedStatement ts;
		try {
			ts = this.getEntityManager().prepareStatement("INSERT INTO menu(nom_menu, prix) values (?, ?)");
			ts.setString(1, menu.getNomMenu());
			ts.setFloat(2, menu.getPrix());
			ts.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Cette méthode permet de modifier un menu
	 * @param menu
	 */
	public void update(Entity.Menu menu){
		PreparedStatement ts;
		try {
			ts = this.getEntityManager().prepareStatement("UPDATE menu SET nom_menu=?, prix=? WHERE id=?");
			ts.setString(1, menu.getNomMenu());
			ts.setFloat(2, menu.getPrix());
			ts.setLong(3, menu.getId());
			ts.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Cette méthode retourne un menu
	 * @param id
	 * @return
	 */
	public Entity.Menu find(Long id){
		PreparedStatement ts;
		Entity.Menu menu = null;
		try {
			ts = this.getEntityManager().prepareStatement("SELECT * FROM menu WHERE id = ?");
			ts.setLong(1, id);
			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				 menu = new Entity.Menu();
				 menu.setId(rs.getLong("id"));
				 menu.setNomMenu(rs.getString("nom_menu"));
				 menu.setPrix(rs.getFloat("prix"));
		
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return menu;
	}
	
	/**
	 * Cette méthode retourne tout les menus
	 * @return
	 */
	public Collection<Entity.Menu> findAll(){
		PreparedStatement ts;
		LinkedList<Entity.Menu> menus = new LinkedList<Entity.Menu>();
		
		try {
			ts = this.getEntityManager().prepareStatement("SELECT * FROM menu");
			ResultSet rs = ts.executeQuery();
			 while (rs.next()) {
				 Entity.Menu menu = new Entity.Menu();
				 menu.setId(rs.getLong("id"));
				 menu.setNomMenu(rs.getString("nom_menu"));
				 menu.setPrix(rs.getFloat("prix"));
				 menus.add(menu);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return menus;
	}
}
