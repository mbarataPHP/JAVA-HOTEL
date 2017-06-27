package Controller;

import Annotation.View;
import Connection.Connection;
import Entity.Role;
import Route.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@View(view="Directeur/CreateRoleDirecteurHotel.fxml", css={"base.css"})
public class CreateRoleDirecteurHotelCtrl extends Parent.Ctrl{
	
	@FXML
	private TextField text;
	
	@FXML
	private Label erreur;
	
	public void create(ActionEvent event){
		if(!text.getText().equals("")){
			Connection connect = (Connection) this.dependance.get("connection");
			Model.Role roleModel = (Model.Role) connect.getModel("Model.Role");
			Route route = (Route) this.dependance.get("route");
			Role role = new Role();
			role.setTypeRole(text.getText());
			
			roleModel.insert(role);
			
			route.get("Directeur/DirecteurRole.fxml");
		}else{
			this.erreur.setText("le champ ne doit pas être vide");
		}
	}
}
