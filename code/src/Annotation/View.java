package Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * Cette interce est utiliser avec les controllers
 * @see Controller
 * @author Mathieu
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface View {
	
	String view();
	
	String[] css() default "";
}
