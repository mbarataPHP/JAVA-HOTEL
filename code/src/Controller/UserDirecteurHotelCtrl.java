package Controller;


import java.util.Collection;
import Annotation.View;
import Connection.Connection;
import Entity.Utilisateur;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

@View(view="Directeur/UserDirecteurHotel.fxml", css={"base.css"})
public class UserDirecteurHotelCtrl extends Parent.Ctrl{
	
	@FXML
    private TableView<Utilisateur> TVUser;
	

	@Override
	public void initialize() {
		Connection connect = (Connection) this.dependance.get("connection");
		User user = (User) connect.getModel("Model.User");
		Collection<Utilisateur> listeUser = user.listUser();
		
		ObservableList<Utilisateur> data  = FXCollections.observableArrayList(listeUser);
		
		TableColumn firstNameCol = new TableColumn( "prénom" );
        firstNameCol.setCellValueFactory( new PropertyValueFactory<>( "firstname" ) );

        TableColumn lastNameCol = new TableColumn( "nom" );
        lastNameCol.setCellValueFactory( new PropertyValueFactory<>( "lastname" ) );
        
        
        //MODIFICATION
        TableColumn actionModif = new TableColumn( "modif" );
        actionModif.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
        Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFactoryModif =
                new Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<Utilisateur, String> param )
                    {
                        final TableCell<Utilisateur, String> cell = new TableCell<Utilisateur, String>()
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
                                                Utilisateur Utilisateur = getTableView().getItems().get( getIndex() );
                                                System.out.println("modif");
                                                
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
        
        //suppression
        TableColumn actionSupp = new TableColumn( "suppression" );
        actionSupp.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
        Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFactorySupp =
                new Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>>()
                {
                    @Override
                    public TableCell call( final TableColumn<Utilisateur, String> param )
                    {
                        final TableCell<Utilisateur, String> cell = new TableCell<Utilisateur, String>()
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
                                                Utilisateur Utilisateur = getTableView().getItems().get( getIndex() );
                                                
                                                System.out.println("supp");
                                                
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
        
        
        
        TVUser.setItems(data);
        TVUser.getColumns().addAll( firstNameCol, lastNameCol, actionModif, actionSupp);
		/*lastname.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("lastname"));
		firstname.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("firstname"));
		id.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("id"));
		
		 
		TVUser.getItems().setAll(listeUser);*/
		//TVUser.getItems().addAll(listeUser);
        
	}
	
	
	
}
