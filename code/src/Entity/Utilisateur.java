package Entity;


import java.io.UnsupportedEncodingException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import Metier.Crypt;
import Parent.Personne;

@Entity
@Table(name="utilisateur")
public class Utilisateur extends Personne{
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_role")
	protected Role role;
	
	@Column(name="password")
	private String password;
	
	
	public String getPassword() {
		return password;
	}
	public void setPasswordCrypt(String password){
		String crypt = "hdof,аз=)vqfsxgs+щ;,";
		if(this.mail!=null){
			crypt = this.mail;
		}
		
		try {
			this.password = Crypt.get_SHA_512_SecurePassword(password, crypt);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	
	
	
}
