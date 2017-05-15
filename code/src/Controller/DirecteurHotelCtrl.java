package Controller;

import Annotation.View;
import javafx.event.ActionEvent;
import javafx.scene.Node;

@View(view="Directeur/DirecteurHotel.fxml", css={"base.css"})
public class DirecteurHotelCtrl extends Parent.Ctrl{
	
	
	public void redirection(ActionEvent event){
		 Node node = (Node) event.getSource() ;
		 String data = (String) node.getUserData();
		
		 switch(data){
		 case "dashboard":
			 
			 break;
		 
		 case "user":
			 
			 break;
			 
		 case "role":
			 
			 break;
		 
		 }
	}
	

}
