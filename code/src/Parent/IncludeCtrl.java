package Parent;

import Dependance.Dependance;

public abstract class IncludeCtrl extends Ctrl{
	public IncludeCtrl(){
		this.dependance = Dependance.dependanceKernel;
		
	}
}
