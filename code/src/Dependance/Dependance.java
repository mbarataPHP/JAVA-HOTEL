package Dependance;

import javax.xml.bind.ValidationException;
import Enum.Environment;
import Log.Log;
import Connection.Connection;
import Route.Route;
import Session.Session;
import ViewObject.View;
import javafx.stage.Stage;

public class Dependance {
	
	private Environment env;
	private Stage stage;
	private Connection connection = null;
	private Route route;
	private Log log;
	private View view;
	private Session session;
	public Dependance(Stage stage, Environment env){
		this.env = env;
		this.log = new Log(this);
		this.stage = stage;
		
		this.init();
	}
	
	private void init(){
		if(this.connection==null){
			this.connection = new Connection(this);
			this.route = new Route(this);
			this.view = new View(this);
			this.session = new Session(this);
		}
	}
	
	/**
	 * Cette méthode permet retourne une dépendance 
	 * @param id
	 * @return
	 */
	public Object get(String id){
		Object value = null;
		if(id.equals("connection")){
			value = this.connection;
		}else if(id.equals("stage")){
			value = this.stage;
		}else if(id.equals("route")){
			value = this.route;
		}else if(id.equals("log")){
			value = this.log;
		}else if(id.equals("view")){
			value = this.view;
		}else if(id.equals("session")){
			value = this.session;
		}else{
			try {
				throw new ValidationException("<!---------------\nLa clé "+id+" n'existe pas\n---------------!>");
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return value;
	}
	
	public Environment getEnvironment(){
		return this.env;
	}
}
