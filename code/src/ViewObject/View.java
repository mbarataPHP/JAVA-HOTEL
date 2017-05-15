package ViewObject;

import java.io.IOException;

import Dependance.Dependance;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class View {
	public Dependance dependance;
	private Stage primaryStage;
	public View(Dependance dependance){
		this.dependance = dependance;
		primaryStage = (Stage) this.dependance.get("stage");
	}
	
	public void callView(String url, String title, Object controller, String[] css){
		try {
		
			this.callViewScene(url, title, controller, css);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	
	private void callViewScene(String url, String title,  Object controller, String[] css) throws IOException{
		
		url = "../view/"+url;
	
		

		// Localisation du fichier FXML.
	      // Création du loader.
			FXMLLoader fxmlLoader;
	
	    	   fxmlLoader = new FXMLLoader( getClass().getResource(url));
	    
	    	   fxmlLoader.setController(controller);
	      // Chargement du FXML.
	       AnchorPane root = (AnchorPane) fxmlLoader.load();
	       //root.getChildren().setAll( fxmlLoader.load());
	     
	      // Création de la scène.
	      Scene scene = new Scene(root);

	      
	      for(int i=0;i<css.length;i++){
	    	  if(!css[i].equals("")){
	    		  scene.getStylesheets().add(getClass().getResource("../css/"+css[i]).toExternalForm());
	    	  }
	      }
	      //Scene scene2 = primaryStage.getScene();
	    
	      
	      primaryStage.setScene(scene);
	      primaryStage.setTitle(title);
		  primaryStage.show();
		  
	}
	
}
