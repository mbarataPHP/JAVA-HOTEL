package Parent;

import java.util.HashMap;



public class Personne{

	protected Long id;
	

	protected String firstname;
	

	protected String lastname;
	

	protected String mail;
	
	
	
	/*
	 * GETTER SETTER
	 */
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
