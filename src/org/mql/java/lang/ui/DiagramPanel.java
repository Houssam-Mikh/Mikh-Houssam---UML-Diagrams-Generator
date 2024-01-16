package org.mql.java.lang.ui;

import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JPanel;

import org.mql.java.lang.dom.XMLNode;


public class DiagramPanel extends JPanel{
	
	private XMLNode children[];
	private static final long serialVersionUID = 1L;
	public DiagramPanel(String src) {
		setBackground(Color.lightGray);
		XMLNode root = new XMLNode(src);
		children = root.getChildren();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int x = 10, y= 10 , width = 130 , height = 20,heightField,heightMethod; 
		
		for (int i = 0; i < children.length; i++) {
			XMLNode fields[] = children[i].getChildren()[3].getChildren();
			XMLNode methods[] = children[i].getChildren()[5].getChildren();
			
			
			x+= 150 ; 
			if(x >= 900) {
				x = 10; 
				y += 300;
			}
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);
			
			 
			g.drawString(children[i].getChildren()[1].getValue(),x + 25, y + 15);
			
			if(fields.length != 0) heightField = fields.length*13;
			else heightField = 10;
			g.drawRect(x, y+20 ,width,heightField);
				int m = x+5 , n = y+20; 
				
				for (XMLNode field : fields) {
					n+=12;
					switch(field.getAttribute("scope")) {
					case "public ": g.drawString("+ "+field.getValue(), m, n);break;
					case "private ": g.drawString("- "+field.getValue(), m, n);break;
					case "protected ":g.drawString("# "+field.getValue(), m, n);break;
					default : g.drawString(field.getValue(), x + 5, n);break;
					}
					
				}
				
				if(methods.length != 0) heightMethod = methods.length*13;
				else heightMethod = 10;
				g.drawRect(x, y+20+heightField ,width,heightMethod);
				n += 15;	
				
				for (XMLNode method : methods) {
					switch(method.getAttribute("scope")) {
					case "public ": g.drawString("+ "+method.getValue()+"()", m, n);break;
					case "private ": g.drawString("- "+method.getValue()+"()", m, n);break;
					case "protected ":g.drawString("# "+method.getValue()+"()", m, n);break;
					default : g.drawString(method.getValue()+"()", m, n);break;
					}
					n+=12;
				}
			
		}
	
		
	}
	
	

}
