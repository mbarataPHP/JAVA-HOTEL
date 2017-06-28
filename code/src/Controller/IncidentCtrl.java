package Controller;

import Annotation.View;
import Entity.Incident;
import Entity.Menu;
import Route.Route;
import Connection.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

@View(view="Maintenance/Maintenance.fxml", css={"base.css"})
public class IncidentCtrl extends Parent.Ctrl {
	
	@FXML
	private TableView incidents;
	
	@FXML
	private TextArea incidentText;
	
	
	public void ajout(ActionEvent action){
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Incident incidentModel = (Model.Incident) connect.getModel("Model.Incident");
		if(!this.incidentText.getText().equals("")){
			Incident incident = new Incident();
			incident.setDescription(this.incidentText.getText());
			incidentModel.insert(incident);
			
			Route route = (Route) this.dependance.get("route");
			route.get("Maintenance/Maintenance.fxml");
		}
			//insert
	}
	
	
	@Override
	public void initialize() {
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Incident incidentModel = (Model.Incident) connect.getModel("Model.Incident");
		
		ObservableList<Incident> data  = FXCollections.observableArrayList(incidentModel.findAll());
		
		TableColumn descriptionType = new TableColumn( "description" );
		descriptionType.setCellValueFactory( new PropertyValueFactory<>( "description" ) );
		
		this.incidents.setItems(data);
		this.incidents.getColumns().addAll(descriptionType);
	}
	
}
