package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.Month;

import Metier.DateMetier;
import java.time.LocalDate;
public class DateMetierTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInitLocalDate() {
		LocalDate localDate = DateMetier.initLocalDate("2017-06-21");
		
		assertEquals(2017, localDate.getYear()); //year
		assertEquals(Month.JUNE, localDate.getMonth()); //month
		assertEquals(21, localDate.getDayOfMonth()); //june
		
	}
	
	@Test
	public void testCompareDate(){
		LocalDate localDateBefore1 = DateMetier.initLocalDate("2017-06-21");
		LocalDate localDateAfter1 = DateMetier.initLocalDate("2017-06-25");
		
		assertEquals(true, DateMetier.compare(localDateBefore1, localDateAfter1));
		
		
		assertEquals(false, DateMetier.compare(localDateAfter1, localDateBefore1));
		
		
		
		assertEquals(true, DateMetier.compare(localDateBefore1, localDateBefore1));
	}
	
	


}
