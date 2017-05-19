package test;

import static org.junit.Assert.*;
import org.junit.Test;
import Parent.TestParent;
import Metier.Filter;

public class FilterTest extends TestParent{

	

	@Test
	/**
	 *  Ce test permet de tester si l'adresse email est valide
	 */
	public void testMail() {
		
		assertEquals(false, Filter.validate("")); 
		
		assertEquals(false, Filter.validate("sdcsdc.fr")); 
		
		assertEquals(true, Filter.validate("toto@toto.fr")); 
	}

}
