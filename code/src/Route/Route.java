package Route;


import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Hashtable;
import Dependance.Dependance;
import Enum.Environment;

import Log.Log;
import Metier.Scan;
import Parent.Ctrl;
import Annotation.View;

public class Route {
	private Dependance fb;
	Hashtable<String, Object> routes;
	public Route(Dependance fb){
		this.fb = fb;
		this.routes = new Hashtable<String, Object>();
		this.initRoutes();
	}
	
	private void initRoutes(){
		ArrayList<Class<?>> ctrls = Scan.getClassesForPackage("Controller");
		Log log = (Log) this.fb.get("log");
		log.setLog("logCtrl", "les type de controller: ", Environment.DEV);
		log.setLog("logCtrl", "", Environment.DEV);
		for(java.lang.Class<?> ctrl : ctrls) {
			try {
				Parent.Ctrl unCtrl = (Parent.Ctrl) ctrl.newInstance();
		
			
			
				Annotation column = ctrl.getAnnotation(View.class);
				View viewAnnotation = (View) column;
			
				routes.put(viewAnnotation.view(), unCtrl);
				
				log.setLog("logCtrl", viewAnnotation.view()+" == "+unCtrl.getClass(), Environment.DEV);
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.getLog("logCtrl").close();
	
	}
	
	
	public void get(String cle){
	
		String[][] paramaters = new String[0][0];
		this.callView(cle, paramaters);
	}
	
	
	public void get(String cle, String[][] paramaters){
		this.callView(cle, paramaters);
	}
	
	
	
	private void callView(String cle, String[][] paramaters){
		Object controller = ((Ctrl) routes.get(cle)).initDefault(this.fb);
		
		ViewObject.View view = (ViewObject.View) this.fb.get("view");
		
		((Ctrl) controller).paramaters(paramaters);
		
		view.callView(cle, cle, controller);
		//.callView("home");
	}
}
