package Controller;

import java.util.Date;
import java.util.Optional;

import Annotation.View;
import Connection.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Entity.Chambre;
import Entity.Menu;
import Route.Route;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

@View(view="Gouvernante/Gouvernante.fxml", css={"base.css"})
public class GouvernanteCtrl extends Parent.Ctrl {
	
	@FXML
	private TableView chambres;
	
	
	@Override
	public void initialize() {
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Chambre chambreModel = (Model.Chambre) connect.getModel("Model.Chambre");

		
		ObservableList<Chambre> data  = FXCollections.observableArrayList(chambreModel.findAll());
		
		this.chambres.setItems(data);
		
		TableColumn nomMenuType = new TableColumn( "menu" );
		nomMenuType.setCellValueFactory( new PropertyValueFactory<>( "nomMenu" ) );
		
		
		this.chambres.getColumns().addAll(this.getChambre(), this.getStatusChambre());
	}
	
	
	/**
	 * Cette méthode permet de voir une chamre
	 * @return TableColumn
	 */
	private TableColumn getChambre(){
		Route route = (Route) this.dependance.get("route");
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Chambre menuModel = (Model.Chambre) connect.getModel("Model.Chambre");
		 TableColumn actionFacture = new TableColumn( "chambre" );
		 actionFacture.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
	        Callback<TableColumn<Chambre, String>, TableCell<Chambre, String>> cellFactoryDateFin =
	                new Callback<TableColumn<Chambre, String>, TableCell<Chambre, String>>()
	                {
	                    @Override
	                    public TableCell call( final TableColumn<Chambre, String> param )
	                    {
	                        final TableCell<Chambre, String> cell = new TableCell<Chambre, String>()
	                        {

	             
	                 
	                            @Override
	                            public void updateItem( String item, boolean empty )
	                            {
	                                super.updateItem( item, empty );
	                                if ( empty )
	                                {
	                                    setGraphic( null );
	                                    setText( null );
	                                }
	                                else
	                                {
	                                	Chambre chambre = getTableView().getItems().get( getIndex() );
		                                 setGraphic( null );
		                                 setText(chambre.toString());
	                                	
	                                }
	                            }
	                        };
	                        return cell;
	                    }
	                };

	       actionFacture.setCellFactory( cellFactoryDateFin );
	       return actionFacture;
	}
	
	/**
	 * Cette méthode permet de voir une chamre
	 * @return TableColumn
	 */
	private TableColumn getStatusChambre(){
		Route route = (Route) this.dependance.get("route");
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Chambre menuModel = (Model.Chambre) connect.getModel("Model.Chambre");
		 TableColumn actionFacture = new TableColumn( "libre" );
		 actionFacture.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
	        Callback<TableColumn<Chambre, String>, TableCell<Chambre, String>> cellFactoryDateFin =
	                new Callback<TableColumn<Chambre, String>, TableCell<Chambre, String>>()
	                {
	                    @Override
	                    public TableCell call( final TableColumn<Chambre, String> param )
	                    {
	                        final TableCell<Chambre, String> cell = new TableCell<Chambre, String>()
	                        {

	             
	                 
	                            @Override
	                            public void updateItem( String item, boolean empty )
	                            {
	                                super.updateItem( item, empty );
	                                if ( empty )
	                                {
	                                    setGraphic( null );
	                                    setText( null );
	                                }
	                                else
	                                {
	                                	Chambre chambre = getTableView().getItems().get( getIndex() );
	                                	Model.Facture factureModel = (Model.Facture) connect.getModel("Model.Facture");
	                                	Entity.Facture facture =factureModel.isFreeChambre(chambre, new Date());
		                                 setGraphic(null);
		                                 if(facture!=null){
		                                	 setText("non"); 
		                                 }else{
		                                	 setText("oui");
		                                 }
		                                 
	                                	
	                                }
	                            }
	                        };
	                        return cell;
	                    }
	                };

	       actionFacture.setCellFactory( cellFactoryDateFin );
	       return actionFacture;
	}
}
