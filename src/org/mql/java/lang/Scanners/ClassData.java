package org.mql.java.lang.Scanners;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class ClassData {
	
	private String scope; 
	private String type; 
	private String name; 
	private String mother; 
	private List<String> interfaces;
	private List<Field> fields; 
	private List<Method> methods;
	private List<Class<?>>associatedClass;
	
	
	public ClassData(String qName) {
		try {
			Class<?> cls = Class.forName(qName);
			if(cls != null) {
				this.scope     = Outils.getScope(cls.getModifiers());
				this.type      = Outils.isClass(cls);
				this.name	   = Outils.getName(cls);
				this.mother    = Outils.getMotherClass(cls);
				this.interfaces= Outils.getInterfaces(cls);
				this.fields    = Outils.getFields(cls);
				this.methods   = Outils.getMethods(cls);
				this.associatedClass= Outils.getAssociations(cls);
				/*
				 * TODO : determiner la composition et l'agrégation :
				 * 
				 */
				
				
				
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public List<Class<?>> getAssociatedClass() {
		return associatedClass;
	}


	public void setAssociatedClass(List<Class<?>> associatedClass) {
		this.associatedClass = associatedClass;
	}


	public String getScope() {
		return scope;
	}


	public void setScope(String scope) {
		this.scope = scope;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMother() {
		return mother;
	}


	public void setMother(String mother) {
		this.mother = mother;
	}


	public List<String> getInterfaces() {
		return interfaces;
	}


	public void setInterfaces(List<String> interfaces) {
		this.interfaces = interfaces;
	}


	public List<Field> getFields() {
		return fields;
	}


	public void setFields(List<Field> fields) {
		this.fields = fields;
	}


	public List<Method> getMethods() {
		return methods;
	}


	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	
}
