package Metier;
import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Cette classe permet de gérer les metier sur les dates
 * @author Mathieu
 *
 */
public class DateMetier {
	
	/**
	 * Cette méthode permet d'initialiser LocalDate
	 * @param String dateString
	 * @return LocalDate
	 */
	public static LocalDate initLocalDate(String dateString){
		DateTimeFormatter formatter;
		dateString.replace('/', '-');
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter = formatter.withLocale( Locale.FRENCH );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
		LocalDate date = LocalDate.parse(dateString, formatter);

		return date;
	}
	
	/**
	 * Cette méthode convertie java.time.LocalDate en Date
	 * @param local
	 * @return
	 */
	public static Date converLocaleToDate(LocalDate local){
		Date date = Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		return date;
	}
	
	/**
	 * 
	 * @param local
	 * @param local2
	 * @return
	 */
	public static boolean compare(LocalDate localBefore, LocalDate localAfter){
		boolean bool = false;
		Date dateBefore = DateMetier.converLocaleToDate(localBefore);
		Date dateAfter = DateMetier.converLocaleToDate(localAfter);
		
		if(dateBefore.before(dateAfter) || dateBefore.equals(dateAfter)){
			bool = true;
		}
		
		return bool;
	}
}
