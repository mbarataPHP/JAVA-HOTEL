package Log;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import Enum.Environment;

public class LogFile {
	
	private PrintWriter writer = null;
	private Environment env;
	private String title;
	private String dir;
	public LogFile(String dir, String title, Environment env){
		
		this.dir = dir;
		this.title = title;
		this.env = env;
	}
	public void read(String read, Environment env){
		
	}
	public void close(){
		
	}
	/*
	public void read(String read, Environment env){
		
		//Si le read n'existe pas encore
		if(this.writer==null && (env==this.env || this.env==Environment.ALL)){
			try {
				
				this.writer = new PrintWriter(this.dir+this.title+".txt", "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(env==this.env || this.env==Environment.ALL){

			this.writer.println(read);
		}
			
		
	}
	
	
	public void close(){
		
		this.writer.close();
	}*/
}
