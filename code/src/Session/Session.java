package Session;

import Dependance.Dependance;
import Entity.Utilisateur;
import java.util.Date;

public class Session {
	private Dependance dependance;
	private Utilisateur utilisateur = null;
	private Date start;
	
	public Session(Dependance dependance){
		this.dependance = dependance;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public Date getStart(){
		return this.start;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public void startSession(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
		this.start = new Date();
	}
	
	public void destroySession(){
		this.utilisateur = null;
		this.start = null;
	}
}
