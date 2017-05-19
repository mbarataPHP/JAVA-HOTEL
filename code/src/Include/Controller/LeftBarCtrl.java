package Include.Controller;

import Route.Route;
import Session.Session;
import javafx.event.ActionEvent;

public class LeftBarCtrl extends Parent.IncludeCtrl{
	
	/**
	 * Cette m�thode permet de revenir � l'accueil
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
		}
	}
}
