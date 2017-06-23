package Controller;
import java.util.Optional;

import Annotation.View;
import Connection.Connection;
import Entity.Facture;
import Entity.Role;
import Enum.FactureType;
import Metier.DateMetier;
import Route.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

/**
 * 
 * @author Mathieu
 *
 */
@View(view="Directeur/ChefDeReception.fxml", css={"base.css"})
public class ChefDeReceptionCtrl extends Parent.Ctrl {
	@FXML
    private TableView<Facture> factures;
	
	
	public void ajoutReservation(ActionEvent event){
		//Reservation/AjoutReservation.fxml
		
		Route route = (Route) this.dependance.get("route");
		route.get("Reservation/AjoutReservation.fxml");
	}
	
	@Override
	public void initialize() {
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Facture factureModel = (Model.Facture) connect.getModel("Model.Facture");
	
		
		ObservableList<Facture> data  = FXCollections.observableArrayList(factureModel.findAll());
		
		
		//DATE DE DEBUT
        TableColumn actionDateDebut = new TableColumn( "date de debut" );
        actionDateDebut.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
        Callback<TableColumn<Facture, String>, TableCell<Facture, String>> cellFactoryDateDebut =
                new Callback<TableColumn<Facture, String>, TableCell<Facture, String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<Facture, String> param )
                    {
                        final TableCell<Facture, String> cell = new TableCell<Facture, String>()
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
                                	Facture facture = getTableView().getItems().get( getIndex() );
                                	setText(DateMetier.getFormatDateFr(facture.getDateDebut()));
                                    
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionDateDebut.setCellFactory( cellFactoryDateDebut );
		
		
      //DATE DE FIN
        TableColumn actionDateFin = new TableColumn( "date de fin" );
        actionDateFin.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
        Callback<TableColumn<Facture, String>, TableCell<Facture, String>> cellFactoryDateFin =
                new Callback<TableColumn<Facture, String>, TableCell<Facture, String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<Facture, String> param )
                    {
                        final TableCell<Facture, String> cell = new TableCell<Facture, String>()
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
                                	Facture facture = getTableView().getItems().get( getIndex() );
                                	setText(DateMetier.getFormatDateFr(facture.getDateFin()));
                                	DateMetier.nbrJourIntervalle(facture.getDateDebut(), facture.getDateFin());
                                }
                            }
                        };
                        return cell;
                    }
                };

       actionDateFin.setCellFactory( cellFactoryDateFin );
  
                
		this.factures.setItems(data);
        this.factures.getColumns().addAll( 
        		actionDateDebut, 
        		actionDateFin, 
        		this.getDurerColumn(), 
        		this.getClientColumn(), 
        		this.getPrixColumn(), 
        		this.getButtonEncaissement()
        		
        );
	}
	
	/**
	 * Cette méthode permet de créer une column sur la durée du séjour.
	 * @return TableColumn
	 */
	private TableColumn getDurerColumn(){
		 TableColumn actionFacture = new TableColumn( "durer de la réservation (en jour)" );
		 actionFacture.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
	        Callback<TableColumn<Facture, String>, TableCell<Facture, String>> cellFactoryDateFin =
	                new Callback<TableColumn<Facture, String>, TableCell<Facture, String>>()
	                {
	                    @Override
	                    public TableCell call( final TableColumn<Facture, String> param )
	                    {
	                        final TableCell<Facture, String> cell = new TableCell<Facture, String>()
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
	                                	Facture facture = getTableView().getItems().get( getIndex() );
	                                	setText(DateMetier.nbrJourIntervalle(facture.getDateDebut(), facture.getDateFin())+"");
	                                	
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
	 * Cette méthode permet de créer une column sur pour savoir qui à réserver
	 * @return TableColumn
	 */
	private TableColumn getClientColumn(){
		 TableColumn actionFacture = new TableColumn( "reserver par:" );
		 actionFacture.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
	        Callback<TableColumn<Facture, String>, TableCell<Facture, String>> cellFactoryDateFin =
	                new Callback<TableColumn<Facture, String>, TableCell<Facture, String>>()
	                {
	                    @Override
	                    public TableCell call( final TableColumn<Facture, String> param )
	                    {
	                        final TableCell<Facture, String> cell = new TableCell<Facture, String>()
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
	                                	Facture facture = getTableView().getItems().get( getIndex() );
	                                	setText(facture.getClient().toString());
	                                	
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
	 * Cette méthode permet de créer une column sur les prix des reservation
	 * @return TableColumn
	 */
	private TableColumn getPrixColumn(){
		 TableColumn actionFacture = new TableColumn( "prix:" );
		 actionFacture.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
	        Callback<TableColumn<Facture, String>, TableCell<Facture, String>> cellFactoryDateFin =
	                new Callback<TableColumn<Facture, String>, TableCell<Facture, String>>()
	                {
	                    @Override
	                    public TableCell call( final TableColumn<Facture, String> param )
	                    {
	                        final TableCell<Facture, String> cell = new TableCell<Facture, String>()
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
	                                	Facture facture = getTableView().getItems().get( getIndex() );
	                                	setText(facture.getPrix()+"");
	                                	
	                                }
	                            }
	                        };
	                        return cell;
	                    }
	                };

	       actionFacture.setCellFactory( cellFactoryDateFin );
	       return actionFacture;
	}
	
	
	private TableColumn getButtonEncaissement(){
		Route route = (Route) this.dependance.get("route");
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Facture factureModel = (Model.Facture) connect.getModel("Model.Facture");
		 TableColumn actionFacture = new TableColumn( "encaisser:" );
		 actionFacture.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
	        Callback<TableColumn<Facture, String>, TableCell<Facture, String>> cellFactoryDateFin =
	                new Callback<TableColumn<Facture, String>, TableCell<Facture, String>>()
	                {
	                    @Override
	                    public TableCell call( final TableColumn<Facture, String> param )
	                    {
	                        final TableCell<Facture, String> cell = new TableCell<Facture, String>()
	                        {

	                        	final Button btn = new Button( "encaisser" );
	                        		
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
	                                	Facture facture = getTableView().getItems().get( getIndex() );
	                                	if(facture.getFactureType().getText().equals(FactureType.EN_COURS.getText())){
	                                		btn.setOnAction( ( ActionEvent event ) ->
	                                        {
	                                        	factureModel.encaisser(facture);
	                                        	route.get("Directeur/ChefDeReception.fxml");
	                                        } );
		                                	setGraphic( btn );
		                                	setText( null );
	                                	}else{
	                                		setGraphic( null );
		                                	setText( "-" );
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
