package Entity;



import java.util.List;


public class Role {

	protected Long id;
	

	protected String typeRole;
	

	private List<Utilisateur> Utilisateurs;

	public String getTypeRole() {
		return typeRole;
	}

	public void setTypeRole(String typeRole) {
		this.typeRole = typeRole;
	}

	public List<Utilisateur> getUtilisateurs() {
		return Utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		Utilisateurs = utilisateurs;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String toString(){
		return this.typeRole;
	}
}
