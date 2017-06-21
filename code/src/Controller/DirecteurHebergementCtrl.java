package Controller;

import Annotation.View;
import Route.Route;
import javafx.event.ActionEvent;

/**
 * Created by alexw on 19/05/2017.
 */
@View(view="Directeur/DirecteurHebergement.fxml", css={"base.css"})
public class DirecteurHebergementCtrl extends Parent.Ctrl {
	
	
	public void createClient(ActionEvent event){
		//Client/CreateClient.fxml
		
		Route route = (Route) this.dependance.get("route");
		route.get("Client/CreateClient.fxml");
	}
	
	
	public void reservation(ActionEvent event){
		Route route = (Route) this.dependance.get("route");
		route.get("Reservation/Reservation.fxml");
	}
	
}
