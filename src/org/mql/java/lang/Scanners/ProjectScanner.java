package org.mql.java.lang.Scanners;

import java.io.*;
import java.util.*;

public class ProjectScanner {
	private static ProjectScanner instance; 
	public List<String> qNameClass; 
	public List<String> qNamePackage; 
	private ProjectScanner() {
		qNameClass = new Vector<String>(); 
		qNamePackage = new Vector<String>();
	}
	
	public static ProjectScanner getInstance() {
		if(instance == null) {
			instance= new ProjectScanner();
		}
		return instance;
	}
	
	
	
	public  void scan(String src) {
		
		File project= new File(src);
		File files [] = project.listFiles();
		
		for (File file : files) {
			if(file.isDirectory()) {
				qNamePackage.add(file.getAbsolutePath());
				scan(file.getAbsolutePath());
			}
			else {
				qNameClass.add(fromPathToQName(file.getAbsolutePath()));
			}
		}
		
	}
	protected String fromPathToQName(String s) {
		String string = s.substring(s.indexOf("src")+4);
		string=string.replace("\\", ".");
		string = string.substring(0,string.length()-string.indexOf(".java")+2);
		return string; 
	}
	public List<String> getPackages(){
		return qNamePackage;
	}
	public List<String> getClasses(){
		return qNameClass;
	}
}
