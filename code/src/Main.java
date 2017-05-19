import javafx.application.Application;
import javafx.stage.Stage;
import Enum.Environment;
import Route.Route;
import Dependance.Dependance;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Dependance dependance = new Dependance(arg0, Environment.DEV);
		Route route = (Route) dependance.get("route");
		route.get("Connexion.fxml");
		
	}

}
