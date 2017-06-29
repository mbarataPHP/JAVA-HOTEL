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
	
	@Test
	/**
	 * Ce test va permettre de savoir si le prix correspond bien
	 */
	public void testFiltrePrix(){
		assertEquals(false, Filter.validatePrix("")); 
		
		assertEquals(true, Filter.validatePrix("12,67")); 
		
		assertEquals(false, Filter.validatePrix("12,67567")); 
		
		assertEquals(false, Filter.validatePrix("B12,67")); 
		
		assertEquals(false, Filter.validatePrix("B12,6A"));
		
		assertEquals(true, Filter.validatePrix("12,6")); 
		
		assertEquals(false, Filter.validatePrix("12")); 
	}
}
