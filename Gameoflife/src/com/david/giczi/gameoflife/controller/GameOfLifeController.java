package com.david.giczi.gameoflife.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.david.giczi.gameoflife.exception.UnknownFileFormatException;
import com.david.giczi.gameoflife.exception.NonExistentFunctionException;
import com.david.giczi.gameoflife.model.Cell;
import com.david.giczi.gameoflife.model.FileFormat;
import com.david.giczi.gameoflife.model.GameOfLifeLogic;
import com.david.giczi.gameoflife.model.InputFileReader;
import com.david.giczi.gameoflife.view.UniverseDisplayer;


public class GameOfLifeController implements ActionListener, FileFormat, Runnable {
	
	
	private InputFileReader input;
	private static GameOfLifeLogic logic;
	private static UniverseDisplayer displayer;
	private static boolean isEnabled=false;
	private static int generationCounter=0;
	private static boolean run=false;
	private static boolean canGrow=true;
	private static Thread runner;
	private static JComboBox<String> combo;
	private static boolean canStep=true;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		String name="";
		
		if(e.getSource() instanceof JComboBox) {
			
			if(isEnabled) 
			 displayer.destroyTable();
				
			init();
			
			combo=(JComboBox<String>)e.getSource();
			String item=((JComboBox<String>)e.getSource()).getSelectedItem().toString();
			
			
			if("OwnPattern".equals(item)) {
				createEmptytableForOwnPattern();
			}
			else {
				createTabel(item.toLowerCase());
			}
			
			isEnabled=true;
			combo.setEnabled(false);
		}
		else if(e.getSource() instanceof JButton && !isEnabled) {
			
			name=((JButton)e.getSource()).getText();
			
			if("Exit".equals(name)) {
				System.exit(0);
			}
			
			
		}
		else if(e.getSource() instanceof JButton && isEnabled) {
			
			
			name=((JButton)e.getSource()).getText();
			
			if("Exit".equals(name)) {
				System.exit(0);
			}
			else if("Start".equals(name)){
				
				if(canStep) {
				run=true;
				running();
				}
				
				combo.setEnabled(false);
				canStep=false;
			}
			else if("Stop".equals(name)) {
				
				run=false;
				combo.setEnabled(true);
				canStep=true;
			}
			else if("Step".equals(name)) {
				
				if(canStep)
				growing();
				
			}
			else {
				
				List<Cell>table=displayer.ownPatternDisplayer((JButton)e.getSource(), logic.getUniverseOne());
				logic.setUniverseOne(table);
				
			}
				
			
				
			}
	
		else
			new NonExistentFunctionException("This function isn't existed!");
			
			
		
		
	}
	
	
	private void init() {
		
		run=false;
		canGrow=true;
		canStep=true;
		generationCounter=0;
		
		
	}
	
	private void createTabel(String patternName) {
		
		input=new InputFileReader(patternName);
		List<Cell> table=new ArrayList<>();
		
		if(FileFormat.FORMATS105.equals(input.getFileFormat())) {
			
			logic=new GameOfLifeLogic(input.getNeedfulRowsNumberFor1_05FileFormat(), input.getNeedfulColsNumberFor1_05FileFormat(),
													input.getOrigoXFor1_05FileFormat(),input.getOrigoYFor1_05FileFormat());
			
			 table=logic.life1_05FileProcessing(input.getInputData());
			
			
		}
		else if(FileFormat.FORMATS106.equals(input.getFileFormat())) {
			
			
			logic=new GameOfLifeLogic(input.getNeedfulRowsNumberFor1_06FileFormat(), input.getNeedfulColsNumberFor1_06FileFormat(),
					input.getOrigoXFor1_06FileFormat(),input.getOrigoYFor1_06FileFormat());

			 table=logic.life1_06FileProcessing(input.getInputData());
			
		}
		else
			new UnknownFileFormatException("Unknown format: \'"+input.getFileFormat()+"\'!");
		
		
		displayer=new UniverseDisplayer(input.getNameOfThePattern());
		
		displayer.createUniverseTableWindow(table, logic.getRows(), logic.getCols());
		
	}
	
	
	private void createEmptytableForOwnPattern() {
		
		
		logic=new GameOfLifeLogic(0, 0, 0, 0);
		
		displayer=new UniverseDisplayer("own pattern");
		
		displayer.createUniverseTableWindowForOwnPattern();
		
	}
	
	private void growing() {
		
		
			
			try {
			
		if(canGrow) {
		
			List<Cell> table=logic.cellsInTheUniverseAreAliveOrDead();
			Thread.sleep(100);
			displayer.populationDisplayer(table);
			displayer.setTitle(displayer.getNameOfThePattern(), ++generationCounter);
			canGrow=logic.tableChanging();
			
		}
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
	}
	
	private void running()  {
		
		runner=new Thread(this);
		runner.start();
			
	}


	@Override
	public void run() {
		
		
		while(run && canGrow) {
			
			growing();
			
		}
		
		if(!canGrow)
		combo.setEnabled(true);
	}
	
}
