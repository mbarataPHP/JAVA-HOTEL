package Controller;

import Annotation.View;
import Connection.Connection;
import Entity.Chambre;
import Entity.Client;
import Metier.DateMetier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import Metier.DateMetier;


@View(view="Reservation/AjoutReservation.fxml", css={"base.css"})
public class AddReservaionCtrl extends Parent.Ctrl{
	@FXML
	private DatePicker debut;
	
	@FXML
	private DatePicker fin;
	
	@FXML
	private ComboBox<Chambre> chambres = new ComboBox<Chambre>();
	
	@FXML
	private ComboBox<Client> clients = new ComboBox<Client>();
	
	public void create(ActionEvent event){

		if(debut.getValue()!=null && fin.getValue()!=null){
			if(DateMetier.compare(debut.getValue(), fin.getValue())){ //on vérifie si la date est bien dans les intervalles
				
			}
		}
	}
	
	@Override
	public void initialize() {
		//initilise tout les chambres
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Chambre chambreModel = (Model.Chambre) connect.getModel("Model.Chambre");
		Collection<Entity.Chambre> chambres = chambreModel.findAll();
		for(Entity.Chambre chambre : chambres){
			this.chambres.getItems().add(chambre);
		}
		
		
		
		
	}
}
