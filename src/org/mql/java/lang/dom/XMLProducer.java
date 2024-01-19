package org.mql.java.lang.dom;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;



import org.mql.java.lang.Scanners.ClassData;
import org.mql.java.lang.Scanners.Outils;

public class XMLProducer {
	
	private XMLNode root ;
	@SuppressWarnings("unused")
	private List<String> qNameClasses ;
	private ClassData cd; 
	
	public XMLProducer(String src , List<String> qNameClasses) {
		this.qNameClasses= qNameClasses;
		root = new XMLNode(src); 
		
		for(int i = 0 ; i < qNameClasses.size() ; i++) {
			cd = new ClassData(qNameClasses.get(i)); 
			XMLNode classNode = root.CreateElement("class"); 
			classNode.setAttribute("scope", cd.getScope());
			
			XMLNode typeNode = root.CreateElement("type");
			typeNode.setValue(cd.getType());
			classNode.add(typeNode);
			
			XMLNode	nameNode  = root.CreateElement("name");  
			nameNode.setValue(cd.getName());
			classNode.add(nameNode);
			
			XMLNode motherNode = root.CreateElement("mother-class"); 
			motherNode.setValue(cd.getMother());
			classNode.add(motherNode);
			
			
			List<Field> listFields = cd.getFields();
			XMLNode fieldsNode = root.CreateElement("fields");
			if(listFields != null) { 
				XMLNode fieldNode; 
				for (Field field : listFields) {
					if(field != null) {

						fieldNode = root.CreateElement("field"); 
						fieldNode.setValue(field.getName());
						fieldNode.setAttribute("scope",Outils.getScope(field.getModifiers()));
						fieldNode.setAttribute("type", field.getType().getSimpleName());
						fieldsNode.add(fieldNode);
					
					}
				}
				
			}
			classNode.add(fieldsNode);
			
			
			
			
			
			
			
			
			XMLNode interfacesNode = root.CreateElement("interfaces"); 
			List<String> listInterfaces = cd.getInterfaces(); 
			XMLNode interfaceNode ;
			for (String interfaceItem : listInterfaces) {
				interfaceNode = root.CreateElement("interface");
				interfaceNode.setValue(interfaceItem);
				interfacesNode.add(interfaceNode);
			}
			classNode.add(interfacesNode);
			
			
			
			List<Method> listMethods = cd.getMethods();
			XMLNode methodsNode = root.CreateElement("Methods"); 
			if(listMethods != null) {
				XMLNode methodNode; 
				for (Method method : listMethods) {
					if(method != null) {
						methodNode = root.CreateElement("method"); 
						methodNode.setValue(method.getName());
						methodNode.setAttribute("scope",Outils.getScope(method.getModifiers()));
						methodsNode.add(methodNode);
					}
				}
			}
			classNode.add(methodsNode);
			
			List<Class<?>> associatedClasses = cd.getAssociatedClass();
			XMLNode associationsNode = root.CreateElement("compositions");
			
			if(associatedClasses != null) {
				XMLNode associationNode; 
				for (Class<?> class1 : associatedClasses) {
					associationNode= root.CreateElement("composition");
					String name = class1.getSimpleName();
					if(name.contains("\\[")) {
						name = name.split("\\[")[0];
					}
					associationNode.setValue(name);
					associationsNode.add(associationNode);
				}
			}
			
			
			classNode.add(associationsNode);
			root.add(classNode);
			root.save();
		}
	}
}
