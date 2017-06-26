package Metier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter {
	public static final Pattern VALID_PRICE_REGEX = Pattern.compile("^[0-9]+([,.][0-9]{1,2})$", Pattern.CASE_INSENSITIVE);
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
	    return matcher.find();
	}
		
	public static boolean validatePrix(String prix){
		Matcher matcher = VALID_PRICE_REGEX .matcher(prix);
		return matcher.find();
	}
}
