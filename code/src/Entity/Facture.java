package Entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import Enum.FactureType;
import Metier.DateMetier;

public class Facture {
	private Long id;
	
	private FactureType factureType;
	
	private Date dateDebut;
	
	private Date dateFin;
	
	private Client client;
	
	private Chambre chambre;
	
	public void setDateDebutStringToDate(String dateString){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			this.dateDebut = format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setDateFinStringToDate(String dateString){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			this.dateFin = format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Cette méthode va retourner le prix
	 * @return float
	 */
	public float getPrix(){
		Long jour = DateMetier.nbrJourIntervalle(this.dateDebut, this.dateFin);
		
		return jour * this.getChambre().getPrix();
	}
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Chambre getChambre() {
		return chambre;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}

	public FactureType getFactureType() {
		return factureType;
	}

	public void setFactureType(FactureType factureType) {
		this.factureType = factureType;
	}
	
	
	
}


