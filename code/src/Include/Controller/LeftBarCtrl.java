package Include.Controller;

import Route.Route;
import Session.Session;
import javafx.event.ActionEvent;

public class LeftBarCtrl extends Parent.IncludeCtrl{
	
	/**
	 * Cette mï¿½thode permet de revenir ï¿½ l'accueil
	 * @param action
	 */
	public void accueil(ActionEvent action){
		Session session = (Session) this.dependance.get("session");
		Route route = (Route) this.dependance.get("route");
		String role = session.getUtilisateur().getRole().getTypeRole();
	
		switch(role){
			case "Directeur de l’hôtel":
				route.get("Directeur/DirecteurHotel.fxml");
			break;
			case "Directeur d’hébergement":
				route.get("Directeur/DirecteurHebergement.fxml");
			break;
			case "Chef de réception":
				route.get("Directeur/ChefDeReception.fxml");
			break;
			case "Directeur du restaurant":
				route.get("Directeur/DirecteurRestaurant.fxml");
			break;
		}
	}
}
