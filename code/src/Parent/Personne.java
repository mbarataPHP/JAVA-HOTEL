package Parent;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Personne {
	@Id
	@Column(name="id")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	protected Long id;
	
	@Column(name="firstname")
	protected String firstname;
	
	@Column(name="lastname")
	protected String lastname;
	
	@Column(unique=true, name="mail")
	protected String mail;
	

	
	/*
	 * GETTER SETTER
	 */
	
	public Long getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	
	
}
