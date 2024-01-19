package org.mql.java.lang.ui;



import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Frame extends JFrame{
	private JPanel contentPanel;
	private static final long serialVersionUID = 1L;
	
	public Frame() {
		contentPanel = new DiagramPanel("D:\\Data\\MQL\\2023_2025\\MyWorkSpace\\Mikh Houssam-UML Diagrams Generator\\resources\\XMLfiles\\racine2.xml");
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
        JScrollPane scrollPane = new JScrollPane(contentPanel);
       // scrollPane.setPreferredSize(new java.awt.Dimension(1200, 700));
        getContentPane().add(scrollPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
		
	}
	
	
	
	
}
