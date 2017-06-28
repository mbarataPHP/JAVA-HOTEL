package Controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Date;

import Annotation.View;
import Connection.Connection;
import Metier.DateMetier;
import Model.Facture;
import Model.User;
import Entity.Role;
import Entity.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

@View(view="Dashboard.fxml", css={"base.css"})
public class DashboardCtrl extends Parent.Ctrl{
	@FXML
	private Label tauxRemp;
	@FXML
	private Label moySejour;
	
	@FXML
    private TableView<Role> roles;
	
	@Override
	public void initialize() {
		//getTauxRemp
		double tauxRemp, moySejour;
		Long totalSejour = 0L;
		Connection connect;
		Facture factureModel;
		Model.Role roleModel;
		Collection<Entity.Facture> factures;
		
		connect = (Connection) this.dependance.get("connection");
		factureModel = (Facture) connect.getModel("Model.Facture");
		roleModel = (Model.Role) connect.getModel("Model.Role");
		
		tauxRemp = factureModel.getTauxRemp(new Date());
		
		
		NumberFormat nf = new DecimalFormat("0.##");

		this.tauxRemp.setText(nf.format(tauxRemp)+" %");
		
		factures = factureModel.findAll();
		
		
		for(Entity.Facture facture : factures){
			totalSejour += DateMetier.nbrJourIntervalle(facture.getDateDebut(), facture.getDateFin());
		}
		
		if(factures.size()==0){
			moySejour = (double) totalSejour / factures.size();
			
			this.moySejour.setText(nf.format(moySejour)+" jour(s)");
		}else{
			this.moySejour.setText("0 jour");
		}
		
		
		ObservableList<Role> data  = FXCollections.observableArrayList(roleModel.all());
		
		
		this.roles.setItems(data);
		
		TableColumn typeRoleCol = new TableColumn( "service (role)" );
		typeRoleCol.setCellValueFactory( new PropertyValueFactory<>( "typeRole" ) );
        
        
        this.roles.getColumns().addAll(typeRoleCol, this.getNbrPersonnesByPost());

	}
	
	private TableColumn getNbrPersonnesByPost(){
		Connection connect;
		User userModel;
		connect = (Connection) this.dependance.get("connection");
		userModel = (User) connect.getModel("Model.User");
		
		 TableColumn actionFacture = new TableColumn( "total" );
		 actionFacture.setCellValueFactory( new PropertyValueFactory<>( "MODIF" ) );
	        Callback<TableColumn<Role, String>, TableCell<Role, String>> cellFactoryDateFin =
	                new Callback<TableColumn<Role, String>, TableCell<Role, String>>()
	                {
	                    @Override
	                    public TableCell call( final TableColumn<Role, String> param )
	                    {
	                        final TableCell<Role, String> cell = new TableCell<Role, String>()
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
	                                	Role role = getTableView().getItems().get( getIndex() );
	                                	
	                                	Collection<Utilisateur> users = userModel.getUsersByRole(role);
	                                	setText(""+users.size());
	                                	
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