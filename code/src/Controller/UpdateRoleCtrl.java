package Controller;

import Annotation.View;
import Connection.Connection;
import Entity.Role;
import Route.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@View(view="Directeur/UpdateRoleDirecteurHotel.fxml", css={"base.css"})
public class UpdateRoleCtrl extends Parent.Ctrl{
	@FXML
	private TextField text;
	
	private Role role;
	
	@Override
	public void initialize() {
		
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Role roleModel = (Model.Role) connect.getModel("Model.Role");

		Long idRole = Long.parseLong(this.getParamater("idRole"));

		this.role = roleModel.find(idRole);
		
		this.text.setText(this.role.getTypeRole());
	}
	
	public void update(ActionEvent event){
		if(!text.getText().equals("")){
			Connection connect = (Connection) this.dependance.get("connection");
			Model.Role roleModel = (Model.Role) connect.getModel("Model.Role");
			Route route = (Route) this.dependance.get("route");
		
			role.setTypeRole(text.getText());
			
			roleModel.update(role);
			
			route.get("Directeur/DirecteurRole.fxml");
		}
	}
}
