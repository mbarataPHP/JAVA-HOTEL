package Controller;

import java.util.Optional;

import Annotation.View;
import Connection.Connection;
import Entity.Menu;
import Entity.Utilisateur;
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

@View(view="Menu.fxml", css={"base.css"})
public class MenuCtrl extends Parent.Ctrl {
	
	@FXML
	private TableView<Menu> menus;
	
	public void ajout(ActionEvent action){
		Route route = (Route) this.dependance.get("route");
		route.get("AddMenu.fxml");
	}
	
	@Override
	public void initialize() {
		
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Menu menuModel = (Model.Menu) connect.getModel("Model.Menu");
		ObservableList<Menu> data  = FXCollections.observableArrayList(menuModel.findAll());
		
		TableColumn nomMenuType = new TableColumn( "menu" );
		nomMenuType.setCellValueFactory( new PropertyValueFactory<>( "nomMenu" ) );
		
		TableColumn prixType = new TableColumn( "prix" );
		prixType.setCellValueFactory( new PropertyValueFactory<>( "prix" ) );
		
		this.menus.setItems(data);
		this.menus.getColumns().addAll( nomMenuType, prixType, this.suppColumn(), this.modifColumn());
	}
	
	
	
	/**
	 * Cette méthode permet de supp un menu
	 * @return TableColumn
	 */
	private TableColumn suppColumn(){
		Route route = (Route) this.dependance.get("route");
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Menu menuModel = (Model.Menu) connect.getModel("Model.Menu");
		 TableColumn actionFacture = new TableColumn( "supp" );
		 actionFacture.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
	        Callback<TableColumn<Menu, String>, TableCell<Menu, String>> cellFactoryDateFin =
	                new Callback<TableColumn<Menu, String>, TableCell<Menu, String>>()
	                {
	                    @Override
	                    public TableCell call( final TableColumn<Menu, String> param )
	                    {
	                        final TableCell<Menu, String> cell = new TableCell<Menu, String>()
	                        {

	             
	                        	final Button btn = new Button( "supp" );
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
	                                	 btn.setOnAction( ( ActionEvent event ) ->
                                         {
                                             Menu menu = getTableView().getItems().get( getIndex() );
                                             
                                             Alert alert = new Alert(AlertType.CONFIRMATION);
                                             
                                             alert.setGraphic(null);
                                             alert.setTitle("Suppression");
                                             alert.setHeaderText("Confirmation de la suppression");
                                             alert.setContentText("Etes vous sur de supprimer "+menu.getNomMenu()+"?");
                                             
                                             ButtonType buttonOK = new ButtonType("OK");
                                             ButtonType buttonFERMER = new ButtonType("Fermer", ButtonData.CANCEL_CLOSE);
                                             
                                             alert.getButtonTypes().setAll(buttonOK, buttonFERMER);
                                             Optional<ButtonType> result = alert.showAndWait();
                                             if (result.get() == buttonOK){
                                             	 //user.remove(Utilisateur);
                                            	 menuModel.remove(menu);
                                                 route.get("Menu.fxml");
                                             }
                                             
                                             /*
                                              
                                             paramaters[0][0] = "idUser";
                                             paramaters[0][1] = Long.toString(Utilisateur.getId());
                                             
                                             route.get("Directeur/UserDirecteurUpdate.fxml", paramaters);*/
                                             
		                                 } );
		                                 setGraphic( btn );
		                                 setText( null );
	                                	
	                                }
	                            }
	                        };
	                        return cell;
	                    }
	                };

	       actionFacture.setCellFactory( cellFactoryDateFin );
	       return actionFacture;
	}
	
	
	private TableColumn modifColumn(){
		Route route = (Route) this.dependance.get("route");
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Menu menuModel = (Model.Menu) connect.getModel("Model.Menu");
		 TableColumn actionFacture = new TableColumn( "modif" );
		 actionFacture.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
	        Callback<TableColumn<Menu, String>, TableCell<Menu, String>> cellFactoryDateFin =
	                new Callback<TableColumn<Menu, String>, TableCell<Menu, String>>()
	                {
	                    @Override
	                    public TableCell call( final TableColumn<Menu, String> param )
	                    {
	                        final TableCell<Menu, String> cell = new TableCell<Menu, String>()
	                        {

	             
	                        	final Button btn = new Button( "modif" );
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
	                                	 btn.setOnAction( ( ActionEvent event ) ->
                                         {
                                        	 Menu menu = getTableView().getItems().get( getIndex() );
                                             
                                             
                                             String paramaters[][] = new String[1][2];
                                             
                                             
                                             paramaters[0][0] = "idMenu";
                                             paramaters[0][1] = Long.toString(menu.getId());
                                             
                                             route.get("UpdateMenu.fxml", paramaters);
                                             
		                                 } );
		                                 setGraphic( btn );
		                                 setText( null );
	                                	
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
