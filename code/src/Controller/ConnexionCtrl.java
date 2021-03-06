package Controller;
import Annotation.View;
import Connection.Connection;
import Entity.Utilisateur;
import Route.Route;
import Session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@View(view="Connexion.fxml", css={"base.css", "connexion.css"})
public class ConnexionCtrl extends Parent.Ctrl{
	
	@FXML
	private Label erreur;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextField password;
	
	public void test(ActionEvent event){
		String email = this.email.getText();
		String password = this.password.getText();
		
		Model.Connection connect = (Model.Connection) this.getModel("Model.Connection");
		
		Utilisateur utilisateur = connect.verifyLogin(email, password);
		
		if(utilisateur!=null){
			Session session = (Session) this.dependance.get("session");
			session.startSession(utilisateur);
			
			if(utilisateur.getRole().getTypeRole().equals("Directeur de l�h�tel")){
				Route route = (Route) this.dependance.get("route");
				route.get("Directeur/DirecteurHotel.fxml");
			}
			else if (utilisateur.getRole().getTypeRole().equals("Directeur d�h�bergement")){
				Route route = (Route) this.dependance.get("route");
				route.get("Directeur/DirecteurHebergement.fxml");
			}else if (utilisateur.getRole().getTypeRole().equals("Chef de r�ception")){
				Route route = (Route) this.dependance.get("route");
				route.get("Directeur/ChefDeReception.fxml");
			}else if(utilisateur.getRole().getTypeRole().equals("Directeur du restaurant")){
				Route route = (Route) this.dependance.get("route");
				route.get("Directeur/DirecteurRestaurant.fxml");
			}else if(utilisateur.getRole().getTypeRole().equals("Gouvernante g�n�rale")){
				Route route = (Route) this.dependance.get("route");
				route.get("Gouvernante/Gouvernante.fxml");
			}else if(utilisateur.getRole().getTypeRole().equals("Chef de maintenance")){
				Route route = (Route) this.dependance.get("route");
				route.get("Maintenance/Maintenance.fxml");
			}
			
			//Gouvernante/Gouvernante.fxml
		}else{
			this.erreur.setText("connexion invalide");
		}
	}
}
