package Entity;


import java.io.UnsupportedEncodingException;
import Metier.Crypt;
import Parent.Personne;


public class Utilisateur extends Personne{
	


	protected Role role;
	

	private String password;
	

	private String login;
	
	public String getPassword() {
		return password;
	}
	public void setPasswordCrypt(String password){
		/*String crypt = "hdof,��=)vqfsxgs+�;,";
		if(this.login!=null){
			crypt = this.login;
		}
		
		try {
			this.password = Crypt.get_SHA_512_SecurePassword(password, crypt);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		this.password = password;

	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getMailLastNameFirstName(){
		return this.mail+"_"+this.lastname+"_"+this.firstname;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	
}
