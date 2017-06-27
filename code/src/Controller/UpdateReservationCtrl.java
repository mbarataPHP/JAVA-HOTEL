package Controller;

import java.util.Collection;
import java.util.Date;

import Annotation.View;
import Connection.Connection;
import Entity.Chambre;
import Entity.Client;
import Enum.FactureType;
import Enum.TypeEnum;
import Metier.DateMetier;
import Route.Route;
import Session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

@View(view="Reservation/UpdateReservation.fxml", css={"base.css"})
public class UpdateReservationCtrl extends Parent.Ctrl{
	
	private Entity.Facture facture;
	
	@FXML
	private DatePicker debut;
	
	@FXML
	private DatePicker fin;
	
	@FXML
	private ComboBox<Chambre> chambres = new ComboBox<Chambre>();
	
	@FXML
	private ComboBox<Client> clients = new ComboBox<Client>();
	
	public void create(ActionEvent event){
		Connection connect;
		Date dateBefore, dateAfter;
		Model.Facture factureModel;
		
		
		if(debut.getValue()!=null && fin.getValue()!=null && chambres.getValue()!=null && clients.getValue()!=null){ //est ce que tout les champs sont remplies
			if(DateMetier.compare(debut.getValue(), fin.getValue())){ //on vérifie si la date est bien dans les intervalles
				connect = (Connection) this.dependance.get("connection");
				factureModel = (Model.Facture) connect.getModel("Model.Facture");

				dateBefore = DateMetier.converLocaleToDate(debut.getValue());
				dateAfter = DateMetier.converLocaleToDate(fin.getValue());
				
				if(factureModel.isChambreFreeUpdate(dateBefore, dateAfter, chambres.getValue(), this.facture)==null){ //je vérifie si la chambre est libre
					
					
					facture.setChambre(chambres.getValue());
					facture.setClient(clients.getValue());
					facture.setDateDebut(dateBefore);
					facture.setDateFin(dateAfter);
					
					factureModel.update(facture); //on insert a facture dans l'entité
					
					Route route = (Route) this.dependance.get("route");
					Session session = (Session) this.dependance.get("session");
					if(session.getUtilisateur().getRole().getTypeRole().equals(TypeEnum.DIRECTEUR_HEBERGEUR.getText())){
						route.get("Reservation/Reservation.fxml");
					}else{
						route.get("Directeur/ChefDeReception.fxml");
					}
					
				}
				
			}
		}
	}
	
	@Override
	public void initialize() {
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Facture factureModel = (Model.Facture) connect.getModel("Model.Facture");
		
		//initilise tout les chambres
		Model.Chambre chambreModel = (Model.Chambre) connect.getModel("Model.Chambre");
		Collection<Entity.Chambre> chambres = chambreModel.findAll();
		for(Entity.Chambre chambre : chambres){
			this.chambres.getItems().add(chambre);
		}
		

		Model.Client clientModel = (Model.Client) connect.getModel("Model.Client");
		Collection<Entity.Client> clients = clientModel.findAll();
		for(Entity.Client client : clients){
			this.clients.getItems().add(client);
		}
		
		
		this.facture = factureModel.find(Long.parseLong(this.getParamater("idFacture")));
		
		this.clients.setValue(facture.getClient());
		this.chambres.setValue(facture.getChambre());
		this.debut.setValue(DateMetier.converDateToLocal(facture.getDateDebut()));
		this.fin.setValue(DateMetier.converDateToLocal(facture.getDateFin()));
		
		
	}
}
