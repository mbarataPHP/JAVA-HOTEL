package Entity;

public class Chambre {
	
	private Long id;
	
	private int nbrPlace;
	
	private Etage etage;
	
	
	private float prix;
	
	private TypeChambre typeChambre;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNbrPlace() {
		return nbrPlace;
	}

	public void setNbrPlace(int nbrPlace) {
		this.nbrPlace = nbrPlace;
	}

	public Etage getEtage() {
		return etage;
	}

	public void setEtage(Etage etage) {
		this.etage = etage;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public TypeChambre getTypeChambre() {
		return typeChambre;
	}

	public void setTypeChambre(TypeChambre typeChambre) {
		this.typeChambre = typeChambre;
	}

	public String toString(){
		return this.typeChambre.getSuite()+" ("+this.nbrPlace+") "+this.prix+" euros";
	}
}
