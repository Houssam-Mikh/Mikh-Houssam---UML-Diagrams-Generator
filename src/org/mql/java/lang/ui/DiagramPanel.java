package org.mql.java.lang.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.util.Vector;

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
		ClassPosition classPositions [] = new ClassPosition[children.length];
		int x = 15, y= 200 , width = 130 , height = 20,heightField,heightMethod; 
		
		for (int i = 0; i < children.length; i++) {
			XMLNode fields[] = children[i].getChildren()[3].getChildren();
			XMLNode methods[] = children[i].getChildren()[5].getChildren();
			
			
			x+= 150 ; 
			if(x >= 900) {
				x = 10; 
				y += 320;
			}
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);
			classPositions[i] = new ClassPosition(children[i].getChildren()[1].getValue(),x,y);
			 
			g.drawString(children[i].getChildren()[1].getValue(),x + 25, y + 15);
			
			if(fields.length != 0) heightField = fields.length*13;
			else heightField = 10;
			g.drawRect(x, y+20 ,width,heightField);
				int m = x+5 , n = y+20; 
				
				for (XMLNode field : fields) {
					n+=12;
					switch(field.getAttribute("scope")) {
					case "public": g.drawString("+ "+field.getValue()+":"+field.getAttribute("type"), m, n);break;
					case "private": g.drawString("- "+field.getValue()+":"+field.getAttribute("type"), m, n);break;
					case "protected":g.drawString("# "+field.getValue()+":"+field.getAttribute("type"), m, n);break;
					default : g.drawString(field.getValue()+":"+field.getAttribute("type"), x + 5, n);break;
					}
					
				}
				
				if(methods.length != 0) heightMethod = methods.length*13;
				else heightMethod = 10;
				g.drawRect(x, y+20+heightField ,width,heightMethod);
				n += 15;	
				
				for (XMLNode method : methods) {
					switch(method.getAttribute("scope").split(" ")[0]) {
					case "public": g.drawString("+ "+method.getValue()+"()", m, n);break;
					case "private": g.drawString("- "+method.getValue()+"()", m, n);break;
					case "protected":g.drawString("# "+method.getValue()+"()", m, n);break;
					default : g.drawString(method.getValue()+"()", m, n);break;
					}
					n+=12;
				}
				
			
		}
		for(int i = 0 ; i < children.length;i++) {
			String name = children[i].getChildren()[1].getValue();
			String nameMother = children[i].getChildren()[2].getValue();
			
			ClassPosition childPosition = findPosition(name, classPositions);
			ClassPosition motherPosition = findPosition(nameMother, classPositions);
			if(childPosition != null && motherPosition != null) {
				//déssiner une fléche d'éhritage : 
				g.drawLine(childPosition.getX(), childPosition.getY(), motherPosition.getX(), motherPosition.getY());
				int[]xPoint = {motherPosition.getX(),motherPosition.getX(),motherPosition.getX()+10};
				int[]yPoint = {motherPosition.getY(),motherPosition.getY()-10,motherPosition.getY()};
				g.fillPolygon(xPoint, yPoint, 3);
				
			}
			
		}
		
		//dessiner les associations entres les classes 
		for (int i = 0; i < children.length; i++) {
			// on va dessiner pour chaque element ces associations
			//récupérer les associations tous d'abord : 
			XMLNode associations []= children[i].getChildren()[6].getChildren();
			List<String> assocationsName = new Vector<String>(); 
			for (XMLNode xmlNode : associations) {
				assocationsName.add(xmlNode.getValue());
			}
			//draw line for each association 
			ClassPosition startPosition , endPosition ; 
			startPosition = findPosition(children[i].getChildren()[1].getValue(), classPositions);
			for (String name : assocationsName) {
				endPosition = findPosition(name, classPositions);
				if(endPosition != null) {
					if(endPosition.getX() == startPosition.getX() && endPosition.getY() == startPosition.getY()) {
						ClassPosition p1 = new ClassPosition("",startPosition.getX()+50,startPosition.getY());
						ClassPosition p2 = new ClassPosition("",startPosition.getX()+50,startPosition.getY()-50);
						ClassPosition p3 = new ClassPosition("",startPosition.getX(),startPosition.getY()-50);
						g.drawLine(startPosition.getX(), startPosition.getY(), p1.getX(), p1.getY());
						g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
						g.drawLine(p2.getX(), p2.getY(), p3.getX(), p3.getY());
						g.drawLine(p3.getX(), p3.getY(), endPosition.getX(), endPosition.getY());
						
					}
					else
						g.drawLine(startPosition.getX(), startPosition.getY(),endPosition.getX(),endPosition.getY()-10 );
					int[]xPoints = {endPosition.getX(),endPosition.getX()+10,endPosition.getX(),endPosition.getX()-10};
					int[]yPoints = {endPosition.getY(),endPosition.getY()-5,endPosition.getY() - 10 , endPosition.getY() - 5};
						g.fillPolygon(xPoints, yPoints, 4);
				
				
				
				
				}
				
			}
		}
	
}
	
	@Override
	public Dimension preferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(1500,1200);
	}
	private ClassPosition findPosition(String name , ClassPosition positions[]) {
			for(int i=0; i<positions.length ; i++) {
				if(name.equals(positions[i].getName())) {
					return positions[i];
				}
			}
		return null ;
	}

}
