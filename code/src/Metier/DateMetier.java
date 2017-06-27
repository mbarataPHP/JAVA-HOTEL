package Metier;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
	 * Cette méthode convertire java.time.LocalDate en Date
	 * @param local
	 * @return
	 */
	public static Date converLocaleToDate(LocalDate local){
		
		Date date = Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		return date;
	}
	
	
	/**
	 * Cette méthode permet de convertire Date en java.time.LocalDate
	 * @param date
	 * @return
	 */
	public static LocalDate converDateToLocal(Date date){
		  Instant instant = Instant.ofEpochMilli(date.getTime());
		  return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
	}
	
	/**
	 * Cette méthode compare si la date localBefore est bien plus petit ou egal à la date localAfter
	 * @param localBefore
	 * @param localAfter
	 * @return boolean
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
	
	/**
	 * Cette méthode converti un object Date en chaine de caractere sous un format FR
	 * @param date
	 * @return String
	 */
	public static String getFormatDateFr(Date date){
		SimpleDateFormat ft = new SimpleDateFormat ("EEEE dd MMMM yyyy", Locale.FRENCH);
		
		
		return ft.format(date);
	}
	
	
	/**
	 * Cette méthode compte le nbr jour intervalle entre deux dates
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	public static long nbrJourIntervalle(Date dateDebut, Date dateFin){

	    long diff = dateFin.getTime() - dateDebut.getTime();
	    
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+1;
	}
}
