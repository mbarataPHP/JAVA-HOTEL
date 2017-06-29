package Controller;

import java.util.Optional;

import Annotation.View;
import Connection.Connection;
import Entity.Role;
import Route.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

@View(view="Directeur/DirecteurRole.fxml", css={"base.css"})
public class DirecteurRoleCtrl  extends Parent.Ctrl {
	
	@FXML
    private TableView<Role> TVRole;
	
	public void create(ActionEvent event){
		Route route = (Route) this.dependance.get("route");
		route.get("Directeur/CreateRoleDirecteurHotel.fxml");
	}
	
	@Override
	public void initialize() {
		Connection connect = (Connection) this.dependance.get("connection");
		Model.Role roleModel = (Model.Role) connect.getModel("Model.Role");
		Route route = (Route) this.dependance.get("route");
		
		ObservableList<Role> data  = FXCollections.observableArrayList(roleModel.getListeRole());
		
		TableColumn roleType = new TableColumn( "role" );
		roleType.setCellValueFactory( new PropertyValueFactory<>( "typeRole" ) );
        
      //MODIFICATION
        TableColumn actionModif = new TableColumn( "modifier" );
        actionModif.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
        Callback<TableColumn<Role, String>, TableCell<Role, String>> cellFactoryModif =
                new Callback<TableColumn<Role, String>, TableCell<Role, String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<Role, String> param )
                    {
                        final TableCell<Role, String> cell = new TableCell<Role, String>()
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
                                                Role role = getTableView().getItems().get( getIndex() );
                                              
                                                String paramaters[][] = new String[1][2];
                                                paramaters[0][0] = "idRole";
                                                paramaters[0][1] = Long.toString(role.getId());
                                                
                                                route.get("Directeur/UpdateRoleDirecteurHotel.fxml", paramaters);
                                                
                                    } );
                                    setGraphic( btn );
                                    setText( null );
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionModif.setCellFactory( cellFactoryModif );
        
        
      //SUPRESSION
        TableColumn actionSupp = new TableColumn( "suppression" );
        actionSupp.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
        Callback<TableColumn<Role, String>, TableCell<Role, String>> cellFactorySupp =
                new Callback<TableColumn<Role, String>, TableCell<Role, String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<Role, String> param )
                    {
                        final TableCell<Role, String> cell = new TableCell<Role, String>()
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
                                                Role role = getTableView().getItems().get( getIndex() );
                                               
                                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                                
                                                alert.setGraphic(null);
                                                alert.setTitle("Suppression");
                                                alert.setHeaderText("Confirmation de la suppression");
                                                alert.setContentText("Etes vous sur de supprimer "+role.getTypeRole()+"?");
                                                
                                                ButtonType buttonOK = new ButtonType("OK");
                                                ButtonType buttonFERMER = new ButtonType("Fermer", ButtonData.CANCEL_CLOSE);
                                                
                                                alert.getButtonTypes().setAll(buttonOK, buttonFERMER);
                                                Optional<ButtonType> result = alert.showAndWait();
                                                if (result.get() == buttonOK){
                                                	roleModel.remove(role);
                                                	route.get("Directeur/DirecteurRole.fxml");
                                                }
                                                
                                    } );
                                    setGraphic( btn );
                                    setText( null );
                                }
                            }
                        };
                        return cell;
                    }
                };

                actionSupp.setCellFactory( cellFactorySupp );
        
        TVRole.setItems(data);
        TVRole.getColumns().addAll( roleType, actionModif, actionSupp);
	}
}