package Dependance;
import Connection.Connection;
import javafx.stage.Stage;

public class Dependance {
	private Stage stage;
	private Connection connection = null;
	public Dependance(Stage stage){
		this.stage = stage;
		this.init();
	}
	
	private void init(){
		if(this.connection==null){
			this.connection = new Connection(this);
			
		}
	}
	
	/**
	 * Cette méthode retourne une dépendance 
	 * @param id
	 * @return
	 */
	public Object get(String id){
		Object value = null;
		if(id.equals("connection")){
			value = this.connection;
		}else if(id.equals("stage")){
			value = this.stage;
		}
		return value;
	}
}
