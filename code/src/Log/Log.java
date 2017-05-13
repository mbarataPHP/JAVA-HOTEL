package Log;
import java.util.Hashtable;

import Dependance.Dependance;
import Enum.Environment;

public class Log {
	private Hashtable<String, LogFile> logFiles;
	
	private Dependance dependance;
	
	private String dir = "log\\";
	public Log(Dependance dependance){
		this.logFiles = new Hashtable<String, LogFile>();
		this.dependance = dependance;
	}
	
	public void setLog(String title, String message){
		this.readLog(title, message, null);
	}

	public void setLog(String title, String message, Environment env){
		this.readLog(title, message, env);
	}
	public LogFile getLog(String title){
		return this.logFiles.get(title);
	}
	/**
	 * 
	 * @param title
	 * @param message
	 * @param env
	 */
	private void readLog(String title, String message, Environment env){
		if(this.logFiles.containsKey(title)){
			
			LogFile logFile = this.logFiles.get(title);
			logFile.read(message, this.dependance.getEnvironment());
		}else{
			if(env==null){
				env = Environment.ALL;
			}
			this.logFiles.put(title, new LogFile(this.dir, title, env));
			
			LogFile logFile = this.logFiles.get(title);
			logFile.read(message, this.dependance.getEnvironment());
		}
		
	}
}
