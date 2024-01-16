package org.mql.java.lang.dom;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode {
	private Node node; 
	private static Document document ;
	private XMLNode children[];
	
	
	public XMLNode(Node node) {
		super();
		setNode(node);
	}
	
	public XMLNode(String source) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(source);
			
			node = document.getFirstChild();
			if(node.getNodeType() != Node.ELEMENT_NODE) {
				node = node.getNextSibling();
			}
			
			setNode(node);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public XMLNode[] getChildren() {
		return children; 
	}

	public void setNode(Node node) {
		this.node = node; 
		extractChildren(node);
	}
	private void extractChildren(Node node) {
		NodeList list = node.getChildNodes();
		List<XMLNode> nodes = new LinkedList<XMLNode>(); 
		
		for(int i = 0 ; i < list.getLength() ; i++ ) {
			if(list.item(i).getNodeType() == Node.ELEMENT_NODE) {
				nodes.add(new XMLNode(list.item(i)));
			}
		}
		children = new XMLNode[nodes.size()];
		nodes.toArray(children);
	}
	public String getName() {
		return node.getNodeName();
	}
	public String getValue() {
		Node child = node.getFirstChild();
		if(child != null && child.getNodeType() == Node.TEXT_NODE) {
			return child.getNodeValue();
		}
		return "";
	}
	//TODO : implementation of getAttributes
	
	public  XMLNode CreateElement(String name) {
		Node item = document.createElement(name);
		return new XMLNode(item);
	}
	
	public void setValue(String content) {
		Node contentItem = document.createTextNode(content);
		node.appendChild(contentItem);
	}
	
	public void add(XMLNode child) {
		//TODO: justify content
		
		node.appendChild(child.node);
		
		XMLNode data[] = new XMLNode[children.length + 1] ;
		for(int i = 0, j= 0 ; i < children.length ; i++ , j++) {
			data[j]= children[i];
		}
		data[children.length] = child; 
		children = data; 
	}
	public String getAttribute(String attName) {
		Node att =node.getAttributes().getNamedItem(attName);
		if(att == null)return null; 
		return att.getNodeValue();
	}
	public void setAttribute(String name, String value) {
		Attr att = document.createAttribute(name);
		att.setNodeValue(value);
		if(node.getNodeType() == Node.ELEMENT_NODE) {
			((Element)node).setAttributeNode(att);
		}
	}
	public void save() {
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File("resources/XMLfiles/racine2.xml"));
			transformer.transform(source, result);
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
		
	}
	
}
