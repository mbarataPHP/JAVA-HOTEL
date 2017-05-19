package test;

import static org.junit.Assert.*;
import org.junit.Test;
import Entity.Utilisateur;
import Parent.TestParent;

public class CryptTest extends TestParent{

	
	@Test
	public void testCryptage() {
		String login = "jst-pierre";
		String password = "123456";
		
		
		Utilisateur user = new Utilisateur();
		
		user.setLogin(login);
		
		user.setPasswordCrypt(password);

		
		assertEquals(
				"3a1f6075b52053bde4725183dd16f71f65e03921754342280263fac0190fde5cb79b7da8a4966169545087f765c66d7afd66c7b27623bf82346917e0b0f9bd1d", 
				user.getPassword()
			); 
	}

}
