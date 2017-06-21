package Controller;

import Annotation.View;
import Connection.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import Entity.Client;
import Metier.Filter;
import Route.Route;


@View(view="Client/CreateClient.fxml", css={"base.css"})
public class CreateClientCtrl extends Parent.Ctrl {
	
	@FXML
	private TextField firstname;
	
	@FXML
	private TextField lastname;
	
	@FXML
	private TextField mail;
	
	public void insertClient(ActionEvent event){
		Client client, clientExist;
		Connection connect;
		Model.Client clientModel;
		
		if(!this.firstname.getText().equals("") && !this.lastname.getText().equals("") && !this.mail.getText().equals("")){
			connect = (Connection) this.dependance.get("connection");
			clientModel = (Model.Client) connect.getModel("Model.Client");
			
			clientExist = clientModel.getClientByMail(this.mail.getText());
			
			if(clientExist==null && Filter.validate(mail.getText())){ //il faut que le client ne soit pas déjà inscrit dans l'hotel
				client = new Client();
				client.setFirstname(this.firstname.getText());
				client.setLastname(this.lastname.getText());
				client.setMail(this.mail.getText());
				
				clientModel.insert(client);
				
				Route route = (Route) this.dependance.get("route");
				route.get("Directeur/DirecteurHebergement.fxml");
			}
		}
	}
}
