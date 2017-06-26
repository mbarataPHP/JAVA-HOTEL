package Controller;

import Annotation.View;
import Route.Route;
import javafx.event.ActionEvent;

@View(view="Directeur/DirecteurRestaurant.fxml", css={"base.css"})
public class DirecteurRestaurantCtrl extends Parent.Ctrl {
	
	
	public void role(ActionEvent event){
		Route route = (Route) this.dependance.get("route");
		route.get("Menu.fxml");
	}
}
