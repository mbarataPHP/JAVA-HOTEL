package Controller;

import Annotation.View;
import Connection.Connection;
import Route.Route;
import javafx.event.ActionEvent;

@View(view="Reservation/Reservation.fxml", css={"base.css"})
public class ReservationCtrl extends Parent.Ctrl{
	
	public void ajoutReservation(ActionEvent event){
		//Reservation/AjoutReservation.fxml
		
		Route route = (Route) this.dependance.get("route");
		route.get("Reservation/AjoutReservation.fxml");
	}
	
	@Override
	public void initialize() {
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Facture factureModel = (Model.Facture) connect.getModel("Model.Facture");
	}
}
