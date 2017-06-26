package Controller;

import Annotation.View;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import Route.Route;

@View(view="Directeur/DirecteurHotel.fxml", css={"base.css"})
public class DirecteurHotelCtrl extends Parent.Ctrl{
	
	
	public void redirection(ActionEvent event){
		 Node node = (Node) event.getSource() ;
		 String data = (String) node.getUserData();
		 Route route = (Route) this.dependance.get("route");
		 switch(data){
		 case "dashboard":
			 
			 route.get("Dashboard.fxml");
			 break;
			 
		 case "role":
			 route.get("Directeur/DirecteurRole.fxml");
			 break;
			 
		 case "user":
			 route.get("Directeur/UserDirecteurHotel.fxml");
			 break;
		 
		 }
	}
	

}
