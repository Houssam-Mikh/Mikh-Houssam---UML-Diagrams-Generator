package org.mql.java.lang.Scanners;

import java.io.File;


public class PackageScanner {
	private static PackageScanner instance;
	private PackageScanner() {}
	public static PackageScanner getInstance() {
		if(instance == null) instance = new PackageScanner();
		return instance;
	}
	public String[] scan(String packageName) {
		//String classPath = System.getProperty("java.class.path");
		//String packageFloder = packageName.replace('.', '\\');
		File directory = new File(packageName);
		File classes [] = directory.listFiles();
		String []names = new String[10]; 
		for(int i=0; i< classes.length;i++) {
			names[i]= packageName+"."+classes[i].getName().replace(".class", "");
		}
		for (String string : names) {
			System.out.println(string);
		}
		
		
		return names; 
	}
	
}