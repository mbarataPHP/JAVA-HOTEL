package Controller;

import java.util.Collection;

import Annotation.View;
import Connection.Connection;
import Entity.Role;
import Entity.Utilisateur;
import Metier.Filter;
import Model.User;
import Route.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@View(view="Directeur/UserDirecteurCreate.fxml", css={"base.css"})
public class UserDirecteurCreate extends Parent.Ctrl{
	
	@FXML
	private Label erreur;
	
	@FXML
	private TextField firstname;
	
	@FXML
	private TextField lastname;
	
	@FXML
	private TextField mail;
	
	@FXML
	private TextField login;
	
	@FXML
	private TextField password;
	
	@FXML
	private ComboBox<Role> rolesBoxs = new ComboBox<Role>();
	
	public void ajout(ActionEvent event){
		Connection connect = (Connection) this.dependance.get("connection");
		User userModel = (User) connect.getModel("Model.User");
		
		
		
		if(rolesBoxs.getValue()!=null 
				&& !firstname.getText().equals("") 
				&& !lastname.getText().equals("") 
				&& !mail.getText().equals("") 
				&& !password.getText().equals("")
				&& !login.getText().equals("")
			){
			
			if(
					!userModel.MailExist(mail.getText()) 
					&& 
					Filter.validate(mail.getText())
					&&
					!userModel.LoginExist(login.getText()) 
				){
				Utilisateur user = new Utilisateur();
				
				user.setFirstname(firstname.getText());
				user.setLastname(lastname.getText());
				user.setLogin(login.getText());
				user.setMail(mail.getText());
				user.setPasswordCrypt(password.getText());
				user.setRole(rolesBoxs.getValue());
				
				userModel.insert(user);
				
				Route route = (Route) this.dependance.get("route");
				route.get("Directeur/UserDirecteurHotel.fxml");
			}else{
				this.erreur.setText("Le login ou l'email existe déjà");
			}
			
		}else{
			this.erreur.setText("Un ou plusieurs champs sont vides.");
		}
		
	}
	
	
	@Override
	public void initialize() {
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Role roleModel = (Model.Role) connect.getModel("Model.Role");
		Collection<Entity.Role> roles = roleModel.all();
		for(Entity.Role role : roles){
			rolesBoxs.getItems().add(role);
		}
		
		
	}
}
