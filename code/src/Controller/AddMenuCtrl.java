package Controller;

import Annotation.View;
import Connection.Connection;
import Metier.Filter;
import Route.Route;
import Entity.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@View(view="AddMenu.fxml", css={"base.css"})
public class AddMenuCtrl extends Parent.Ctrl {
	
	@FXML
	private TextField menu;
	
	@FXML
	private TextField prix;
	
	
	public void ajout(ActionEvent action){
		Menu menu;
		String prix;
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Menu menuModel = (Model.Menu) connect.getModel("Model.Menu");
		if(Filter.validatePrix(this.prix.getText()) && this.menu.getText()!=""){
			prix = this.prix.getText().replace(',', '.');
			
			menu = new Menu();
			menu.setPrix( Float.parseFloat(prix));
			menu.setNomMenu(this.menu.getText());
			
			
			menuModel.insert(menu);
			
			Route route = (Route) this.dependance.get("route");
			route.get("Menu.fxml");
		}
	}
}
