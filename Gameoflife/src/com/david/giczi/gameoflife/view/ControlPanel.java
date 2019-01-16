package com.david.giczi.gameoflife.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.david.giczi.gameoflife.controller.GameOfLifeController;
import com.david.giczi.gameoflife.model.PatternName;


public class ControlPanel implements PatternName {

	private JFrame frame;
	private JComboBox<String> patterns;
	private JButton start;
	private JButton stop;
	private JButton step;
	private JButton exit;
	
	public ControlPanel() {
		
		frame=new JFrame("Game of Life - Control Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 80);
		frame.setLocation(100, 100);
		frame.setLayout(new GridLayout(1, 6));
		frame.setAlwaysOnTop(true);
		
		JLabel label=new JLabel("Choose a pattern:");
		patterns=new JComboBox<>(PATTERNS);
		start=new JButton("Start");
		stop=new JButton("Stop");
		step=new JButton("Step");
		exit=new JButton("Exit");
		
		JPanel[] panels=new JPanel[6];
		
		for (int i = 0; i < panels.length; i++) {
			
			panels[i]=new JPanel();
			panels[i].setBackground(new Color(164,198,57));
		}	
		
		patterns.addActionListener(new GameOfLifeController());
		start.addActionListener(new GameOfLifeController());
		stop.addActionListener(new GameOfLifeController());
		step.addActionListener(new GameOfLifeController());
		exit.addActionListener(new GameOfLifeController());
		
		panels[0].add(label);
		panels[1].add(patterns);
		panels[2].add(start);
		panels[3].add(stop);
		panels[4].add(step);
		panels[5].add(exit);
		
		for (int i = 0; i < panels.length; i++) {
			
		frame.add(panels[i]);
	}	
	
			
		
		frame.setVisible(true);
	}
	
	
	
}
