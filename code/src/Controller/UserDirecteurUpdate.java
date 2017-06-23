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
import javafx.scene.control.TextField;

@View(view="Directeur/UserDirecteurUpdate.fxml", css={"base.css"})
public class UserDirecteurUpdate extends Parent.Ctrl{
	private Utilisateur user;
	
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
	
	public void update(ActionEvent event){
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
					!userModel.MailExistUpdate(mail.getText(), user.getId()) 
					&& 
					Filter.validate(mail.getText())
					&&
					!userModel.LoginExistUpdate(login.getText(), user.getId()) 
				){
	
				
				this.user.setFirstname(firstname.getText());
				this.user.setLastname(lastname.getText());
				this.user.setLogin(login.getText());
				this.user.setMail(mail.getText());
				this.user.setPasswordCrypt(password.getText());
				this.user.setRole(rolesBoxs.getValue());
				
				userModel.update(user);
				
				Route route = (Route) this.dependance.get("route");
				route.get("Directeur/UserDirecteurHotel.fxml");
			}
			
		}
		
	}
	
	
	@Override
	public void initialize() {
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Role roleModel = (Model.Role) connect.getModel("Model.Role");
		Model.User userModel = (Model.User) connect.getModel("Model.User");
		
		
		Collection<Entity.Role> roles = roleModel.all();
		for(Entity.Role role : roles){		
			rolesBoxs.getItems().add(role);
		}
		
	
		
		Long idRole = Long.parseLong(this.getParamater("idUser"));
		
		this.user = userModel.find(idRole);
		
		this.firstname.setText(user.getFirstname());
		this.lastname.setText(user.getLastname());
		this.mail.setText(user.getMail());
		this.login.setText(user.getLogin());
		this.password.setText(user.getPassword());
		
		rolesBoxs.setValue(user.getRole());
	}
}
