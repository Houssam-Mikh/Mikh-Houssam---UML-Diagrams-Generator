package org.mql.java.lang.Scanners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Outils {
	public Outils() {
	
	}
	
	public static String getScope(int modifier) {
		String portee=""; 
		if(Modifier.isPublic(modifier)) {
			portee += "public"; 
		}
		else if(Modifier.isProtected(modifier)) {
			portee="protected";
		}
		else if(Modifier.isPrivate(modifier)) {
			portee="private"; 
		}
		else {
			portee += "Default";
		}
			portee +=" ";
		if(Modifier.isFinal(modifier)) {
			portee +="final";
		}
		if(Modifier.isAbstract(modifier)) {
			portee +="abstract";
		}
		if(Modifier.isStatic(modifier)) {
			portee +="static";
		}
		if(Modifier.isSynchronized(modifier)) {
			portee +="synchronized";
		}
		
		
		return portee; 
	}
	
	public static String getName(Class<?> c) {
		if(c != null )return c.getSimpleName() +" ";
		return null;
	}
	public static String isClass(Class<?> cls) {
		if(cls.isInterface()) return "interface";
		return "class";
	}


	/*public static Class<?> getMotherClass(Class<?> cls) {
		Class<?> mother = cls.getSuperclass();
		if(mother != null && !"java.lang.Object".equals(mother.getName())) 
			return mother; 
		return Object.class;
	}
	
	public static List<Class<?>> getInterfaces(Class<?> c) {
		Class<?> interfaces[] = c.getInterfaces(); 
		if(interfaces.length != 0) {
			List<Class<?>> listInterfaces = new Vector<Class<?>>();
			for (Class<?> class1 : interfaces) {
				listInterfaces.add(class1);
			}
			return listInterfaces;
		}
		return null;
	}
	
	public static List<Field> getFields(Class<?> c) {
		Field fields [] =c.getDeclaredFields();
		if(fields.length != 0) {
			List<Field> listFields = new Vector<Field>();
			for (Field field : fields) {
				listFields.add(field);
			}
			return listFields;
		}
		return null; 
	}
	public static List<Method> getMethods(Class<?>c){
		Method methods [] = c.getDeclaredMethods();
			if(methods.length != 0) {
				List<Method> listMethods = new Vector<Method>();
				for (Method method : methods) {
					listMethods.add(method);
				}
			return listMethods;
			}
		return null; 
	}
	
	*/
	public static String getMotherClass(Class<?> cls) {
		Class<?> mother = cls.getSuperclass();
		if(mother != null && !"java.lang.Object".equals(mother.getName())) 
			return "extends "+mother.getName()+" " ; 
		return " ";
	}
	public static List<String> getInterfaces(Class<?> c) {
		List<String> interfaceList = new LinkedList<String>();
		Class<?> interfaces[] = c.getInterfaces(); 
		if(interfaces.length != 0) {
			
			for(int i = 0 ; i < interfaces .length; i++) {
				interfaceList.add(interfaces[i].getSimpleName());
			}
		}
		return interfaceList;
	}
	
	public String getInterfacesString(Class<?> c) {
		String chaine = " "; 
		Class<?> interfaces[] = c.getInterfaces(); 
		if(interfaces.length != 0) {
			chaine = "implements "; 
			for(int i = 0 ; i < interfaces .length - 1 ; i++) {
				chaine += interfaces[i].getName()+" , ";
			}
			chaine += interfaces[interfaces.length - 1].getName()+" ";
		}
		return chaine;
	}
	public static List<Field> getFields(Class<?> c) {
		Field fields [] =c.getDeclaredFields();
		if(fields.length != 0) {
			List<Field> listFields = new Vector<Field>();
			for (Field field : fields) {
				listFields.add(field);
			}
			return listFields;
		}
		return null; 
	}
	public static List<String> getFieldsString(Class<?> c) {
		Field fields[] = c.getDeclaredFields();
		List<String>fieldsList = new LinkedList<String>();
			for (Field field : fields) {
				//string += getScope(field.getModifiers())+" "+field.getGenericType().getTypeName()+" "+field.getName()+";\n"; 
				fieldsList.add(field.getName());
			}
		return fieldsList;
	}
	
	
	public static List<Method> getMethods(Class<?> c) {
		Method methods [] = c.getDeclaredMethods();
		if(methods.length != 0) {
			List<Method> listMethod = new Vector<Method>();
			for (Method method : methods) {
				listMethod.add(method);
			}
			return listMethod;
		}
		 return null; 
		}
	
	
	public static  String getConstructors(Class<?> cls) {
		Constructor<?> constructors [] = cls.getDeclaredConstructors();
		String string =""; 
		for (Constructor<?> constructor : constructors) {
			string += getScope(constructor.getModifiers()) +" "+constructor.getName()+"("+getArguments(constructor)+");\n"; 
		}
		
		return string ; 
	}
	
	public static String  getMethodsString(Class<?> c) {
		Method methods [] = c.getDeclaredMethods();
		String string =""; 
		for (Method method : methods) {
			string += getScope(method.getModifiers()) +" "+method.getGenericReturnType().getTypeName()+" "+method.getName()+"("+getArguments(method)+");\n"; 
		}
		
		return string ;
	}
	private static String getArguments(Method method) {
		String string =""; 
		Parameter parametors[] = method.getParameters();
		if(parametors.length != 0) {
			for(int i = 0 ; i < parametors.length-1 ; i++) {
				string += parametors[i].getType().getTypeName()+", ";
			}
			string += parametors[parametors.length - 1].getType().getTypeName();
			
		}
		return string;
	}
	private static String getArguments(Constructor<?> constructor) {
		String string =""; 
		Parameter parametors[] = constructor.getParameters();
		if(parametors.length != 0) {
			for(int i = 0 ; i < parametors.length-1 ; i++) {
				string += parametors[i].getType().getTypeName()+", ";
			}
			string += parametors[parametors.length - 1].getType().getTypeName();
			
		}
		return string;
	}
	/*
	 * 
	 
	public String getInterneClasses(Class<?> c) {
		
		Class<?> cls[] = c.getDeclaredClasses();
		String string = "";
		if(cls.length != 0) {
			for (Class<?> class1 : cls) {
				System.out.println(class1.getName());
				string += ClassParsser.parse(class1.getName());
			}
		}
		
		
		return string;
	}
*/
	

	
}
