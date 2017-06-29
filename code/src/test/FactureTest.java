package test;

import static org.junit.Assert.*;
import org.junit.Test;
import Connection.Connection;
import Metier.DateMetier;
import Parent.TestParent;

public class FactureTest extends TestParent{
	
	@Test
	/**
	 * Ce test va permettre de savoir si les la chambre est libre ou pas par rapport à la date
	 */
	public void testReservationFree(){

		Connection connect = (Connection) this.dependance.get("connection");
		Model.Facture factureModel = (Model.Facture) connect.getModel("Model.Facture");
		Model.Chambre chambreModel = (Model.Chambre) connect.getModel("Model.Chambre");
		Entity.Chambre chambre = chambreModel.find(6L);

		
		Entity.Facture facture = factureModel.isChambreFree(DateMetier.initDate("2017-01-02"), DateMetier.initDate("2017-01-05"), chambre);
		assertNotNull(facture);
		
		
		Entity.Facture facture2 = factureModel.isChambreFree(DateMetier.initDate("2017-01-02"), DateMetier.initDate("2017-01-25"), chambre);
		assertNotNull(facture2);
		
		
		Entity.Facture facture3 = factureModel.isChambreFree(DateMetier.initDate("2016-01-02"), DateMetier.initDate("2017-01-25"), chambre);	
		assertNotNull(facture3);
		
		
		Entity.Facture facture4 = factureModel.isChambreFree(DateMetier.initDate("2016-01-02"), DateMetier.initDate("2017-01-05"), chambre);		
		assertNotNull(facture4);
		
		
		Entity.Facture facture5 = factureModel.isChambreFree(DateMetier.initDate("2016-01-02"), DateMetier.initDate("2015-01-05"), chambre);		
		assertNull(facture5);
		
		Entity.Facture facture6 = factureModel.isChambreFree(DateMetier.initDate("2018-01-02"), DateMetier.initDate("2018-01-05"), chambre);		
		assertNull(facture6);
	}


	
}
