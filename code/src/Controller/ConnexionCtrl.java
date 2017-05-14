package Controller;
import Annotation.View;
import Connection.Connection;
import javafx.event.ActionEvent;

@View(view="Connexion.fxml")
public class ConnexionCtrl extends Parent.Ctrl{
	
	public void test(ActionEvent event){
		Model.Role role = (Model.Role) this.getModel("Model.Role");
		role.findAll();
		
	}
}
