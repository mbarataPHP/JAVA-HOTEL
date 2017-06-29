package Parent;



import org.junit.Before;
import org.junit.BeforeClass;

import Dependance.Dependance;
import Enum.Environment;
import javafx.stage.Stage;
import javafx.application.Application;


public abstract class TestParent{
	protected Dependance dependance = null;
	
	@Before
	public void setUp() throws Exception {
	
	
		this.dependance = new Dependance(Environment.TEST);
		
	}

	

}
