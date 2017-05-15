package Controller;




import java.util.Collection;

import Annotation.View;
import Connection.Connection;
import Entity.Utilisateur;
import Model.User;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;


@View(view="Directeur/UserDirecteurHotel.fxml", css={"base.css"})
public class UserDirecteurHotelCtrl extends Parent.Ctrl{
	
	@Override
	public void initialize() {
		Connection connect = (Connection) this.dependance.get("connection");
		User user = (User) connect.getModel("Model.User");
		Collection<Utilisateur> listeUser = user.listUser();
		
		
		
		
	}
	
}
