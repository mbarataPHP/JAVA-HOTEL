package Metier;
import java.lang.reflect.Field;

/**
 * La classe permet de setter des attribut private
 * @author Mathieu
 *
 */
public class Reflection {
	
	public static Object set(Object obj, String field, String value){
		Object objReturn = null;
		try {
			objReturn =  init(obj, field, value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objReturn;
	}
	public static Object set(Object obj, String field, int value){
		Object objReturn = null;
		try {
			objReturn =  init(obj, field, value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objReturn;
	}
	public static Object set(Object obj, String field, Long value){
		Object objReturn = null;
		try {
			objReturn =  init(obj, field, value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objReturn;
	}
	
	
	private static Object init(Object obj, String field, Object value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Field f1 = null;
		
		f1 = obj.getClass().getDeclaredField(field); //NoSuchFieldException
		f1.setAccessible(true);
		f1.set(obj, value);
		
		return obj;
	}

}
