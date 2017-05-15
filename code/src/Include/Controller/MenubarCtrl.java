package Include.Controller;



import Route.Route;
import Session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class MenubarCtrl extends Parent.IncludeCtrl {
	@FXML
	private Text titreRole;
	

	public void deconnexion(ActionEvent event){
		
		Session session = (Session) this.dependance.get("session");
		session.destroySession();
	
		Route route = (Route) this.dependance.get("route");
		route.get("Connexion.fxml");
	}
	
	 @FXML
	 public void initialize(){
		 Session session = (Session) this.dependance.get("session");
		 this.titreRole.setText(session.getUtilisateur().getRole().getTypeRole());
	 }
}
