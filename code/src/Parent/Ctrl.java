package Parent;

import Dependance.Dependance;
import Route.Route;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import Annotation.View;
import Controller.HomeCtrl;
/**
 * La mother des controllers
 * @author Mathieu
 *
 */
public abstract class Ctrl {
	
	protected Dependance dependance = null;
	protected String[][] paramaters;
	public Object initDefault(Dependance dependance){
		
		this.dependance = dependance;
		return this;
	}
	
	public void paramaters(String[][] paramaters){
		this.paramaters = paramaters;
	}
	
	public String getParamater(String key){
		String value = null;
		int i = 0;
		while(i<this.paramaters.length && !this.paramaters[i][0].equals(key)){
			i++;
		}
		if(i<this.paramaters.length){
			value = this.paramaters[i][1];
		}
		
		return value;
	}
	
	protected void redirectRoute(String redirect){
		String[][] paramater = new String[0][0];
		this.redirecRoutePrivate(redirect, paramater);
	}
	
	
	protected void redirectRoute(String redirect, String[][] paramater){

		this.redirecRoutePrivate(redirect, paramater);
	}
	
	
	private void redirecRoutePrivate(String redirect, String[][] paramater){
		Route route = (Route) this.dependance.get("route");
		route.get(redirect, paramater);
	}
	
}
