package Controller;

import Annotation.View;
import Connection.Connection;
import Metier.Filter;
import Route.Route;
import Entity.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@View(view="UpdateMenu.fxml", css={"base.css"})
public class UpdateMenuCtrl extends Parent.Ctrl {
	private Menu menuObj;
	
	
	@FXML
	private TextField menu;
	
	@FXML
	private TextField prix;
	
	@Override
	public void initialize() {
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Menu menuModel = (Model.Menu) connect.getModel("Model.Menu");
		
		this.menuObj = menuModel.find( Long.parseLong(this.getParamater("idMenu")) );
		//getParamater("idMenu")
		
		this.menu.setText(this.menuObj.getNomMenu());
		
		this.prix.setText(this.menuObj.getPrix()+"");
	}
	
	
	
	public void ajout(ActionEvent action){
		Menu menu;
		String prix;
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Menu menuModel = (Model.Menu) connect.getModel("Model.Menu");
		if(Filter.validatePrix(this.prix.getText()) && this.menu.getText()!=""){
			prix = this.prix.getText().replace(',', '.');
			
		
			this.menuObj.setPrix( Float.parseFloat(prix));
			this.menuObj.setNomMenu(this.menu.getText());
			
			
			menuModel.update(this.menuObj);
			
			Route route = (Route) this.dependance.get("route");
			route.get("Menu.fxml");
		}
	}
}
