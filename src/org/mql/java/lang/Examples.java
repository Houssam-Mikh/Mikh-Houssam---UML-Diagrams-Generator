package org.mql.java.lang;



import java.util.List;

import org.mql.java.lang.Scanners.ProjectScanner;
import org.mql.java.lang.dom.XMLProducer;
import org.mql.java.lang.ui.Frame;

public class Examples {

	public Examples() {
		//exp01();
		exp02();
	}
	void exp01() {
		ProjectScanner s =ProjectScanner.getInstance();
		
		s.scan("D:\\Data\\MQL\\2023_2025\\MyWorkSpace\\Mikh Houssam-UML Diagrams Generator\\src");
		List<String>qNamesClass = s.getClasses();
		@SuppressWarnings("unused")
		XMLProducer cp = new XMLProducer("D:\\Data\\MQL\\2023_2025\\MyWorkSpace\\Mikh Houssam-UML Diagrams Generator\\resources\\XMLfiles\\racine2.xml",qNamesClass);
		/*
		
		
		
		List<String> qNameclasses = s.getClasses();
		System.out.println("dans le parseur pour chaque class trouver on doit spécifier :");
		 
		ClassData cd = new ClassData(qNameclasses.get(0));
		System.out.println(cd.getName());
		System.out.println(cd.getScope());
		System.out.println(cd.getMother());
		*/
	}
	
	void exp02() {
		new Frame();
	}
	public static void main(String[] args) {
		new Examples();
	}
	
}
