package com.david.giczi.gameoflife.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.david.giczi.gameoflife.controller.GameOfLifeController;
import com.david.giczi.gameoflife.model.Cell;

public class UniverseDisplayer {

	private JFrame frame;
	private List<JButton> cellButtonList;
	private String nameOfThePattern;
	
	public UniverseDisplayer(String nameOfThePattern) {
		
		frame=new JFrame("Game of Life - "+nameOfThePattern.toUpperCase());
		cellButtonList=new ArrayList<>();
		this.nameOfThePattern=nameOfThePattern;
	}
	
	
	
	public String getNameOfThePattern() {
		return nameOfThePattern;
	}



	public void createUniverseTableWindow(List<Cell> table, int rows, int cols) {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		frame.setLayout(new GridLayout(rows, cols));
		
		
		
		for (Cell cell : table) {
			
			JButton cellButton=new JButton();
			
			if(cell.isAlive())
				cellButton.setBackground(new Color(127,63,79));
			else
				cellButton.setBackground(Color.WHITE);
			
			frame.add(cellButton);
			
			cellButtonList.add(cellButton);
			
		}
			
		
		frame.setVisible(true);
	}

		
	public void createUniverseTableWindowForOwnPattern() {
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		frame.setLayout(new GridLayout(100, 100));
		
		for (int i = 0; i < 10000; i++) {
				
		JButton  cellButton=new JButton();
			
		cellButton.setBackground(Color.WHITE);
		
		cellButton.addActionListener(new GameOfLifeController());
			
		frame.add(cellButton);
			
		cellButtonList.add(cellButton);
	}
		
		frame.setVisible(true);
	}
	
	
	public List<Cell> ownPatternDisplayer(JButton clickedbButton, List<Cell> table){
	
			
			
			for (int i=0; i<cellButtonList.size(); i++) {
				
				if(cellButtonList.get(i)==clickedbButton) {
				
				if(table.get(i).isAlive()) {
					
					table.get(i).setAlive(false);
					clickedbButton.setBackground(Color.WHITE);
				}
				else {
					
					table.get(i).setAlive(true);
					clickedbButton.setBackground(new Color(127,63,79));
				}
					
			}
		}
			
			return table;
	}
	
	
	public void populationDisplayer(List<Cell> table) {
		
		
		for (int i=0; i<table.size(); i++) {
			
			if(table.get(i).isAlive()) 
				cellButtonList.get(i).setBackground(new Color(127,63,79));
			else
				cellButtonList.get(i).setBackground(Color.WHITE);
			
			
		}
		
	}
	
	public void setTitle(String nameOfPattern, int generationCounter) {
		
		frame.setTitle("Game of Life - "+nameOfPattern.toUpperCase()+"  "+generationCounter);
	}
	
	public void destroyTable(){
		
		frame.setVisible(false);
		frame=null;
		
	}
	
	
	public JFrame getFrame() {
		return frame;
	}

	

	
}
