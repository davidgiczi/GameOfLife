package com.david.giczi.gameoflife.model;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeLogic  {

	private List<Cell> universeOne;
	private List<Cell> universeTwo;
	private int rows;
	private int cols;
	private int origoX;
	private int origoY;
	
	
	public GameOfLifeLogic(int rows, int cols, int origoX, int origoY) {
		
		this.universeOne = new ArrayList<>();
		this.universeTwo=new ArrayList<>();
		setRows(rows);
		setCols(cols);
		setOrigoX(origoX);
		setOrigoY(origoY);
		
		for (int i = 0; i < this.rows; i++) {
			
			for (int j = 0; j < this.cols; j++) {
				
				universeOne.add(new Cell(i,j));
				universeTwo.add(new Cell(i,j));
			}
		}
		

	}

	
	


	public List<Cell> getUniverseOne() {
		return universeOne;
	}


	public int getRows() {
		return rows;
	}


	public int getCols() {
		return cols;
	}
	
	

	public int getOrigoX() {
		return origoX;
	}





	public int getOrigoY() {
		return origoY;
	}



	public void setRows(int rows) {
		
		if(rows<100)
		this.rows=100;
		else
		this.rows = rows;
	}


	public void setCols(int cols) {
		
		if(cols<100)
		this.cols=100;
		else
		this.cols = cols;
	}

	

	public void setOrigoX(int origoX) {
		
		if(cols<=100)
		this.origoX = 50;
		else
		this.origoX=1+origoX*-1;
		
	}


	public void setOrigoY(int origoY) {
		
		if(rows<=100)
		this.origoY=50;
		else 
		this.origoY = 1+origoY*-1;
		
	}

	

	public void setUniverseOne(List<Cell> universeOne) {
		this.universeOne = universeOne;
	}


	public List<Cell> life1_05FileProcessing(List<String> inputData) {
		
		int x=origoX;
		int y=origoY;
		int rowCounter=0;
		int pX=0;
		int pY=0;
		
		
		for (String row : inputData) {
			
			
			if(row.startsWith("#P")) {
				
				String[] origo=row.split(" ");
				x=origoX;
				y=origoY;
				rowCounter=0;
				pX=Integer.parseInt(origo[1]);
				pY=Integer.parseInt(origo[2]);
				x+=pX;
				y+=pY;
			}
			
			
			if(row.startsWith(".") || row.startsWith("*")) {
					
			
				for (int i = 0; i < row.length(); i++) {

					x=origoX+pX;
					
					if(row.charAt(i)=='*') {
						
						x+=i;
						y+=rowCounter;
						
						universeOne.get(y*cols+x).setAlive(true);
						
						y=origoY+pY;
					}
					
					
				}

				
				rowCounter++;
			}
			
			
			
		}
		
		
		
		return universeOne;
		
	}
	
	
	public List<Cell> life1_06FileProcessing(List<String> inputData) {
		
		
		
		for(int i=0; i<inputData.size(); i++) {
			
			if(!inputData.get(i).startsWith("#")) {
				
			String[] coords=inputData.get(i).split(" ");
			
			int x=Integer.parseInt(coords[0])+origoX;
			int y=Integer.parseInt(coords[1])+origoY;
			
		
			universeOne.get(x*cols+y).setAlive(true);
	}	
			
		}
		
		
		
		return universeOne;
		
	}
	
	
	public List<Cell> cellsInTheUniverseAreAliveOrDead() {
		
		
		for (int i=0; i<universeOne.size(); i++) {
			
			if(universeOne.get(i).isAlive() && cellNeighborhoodCounter(universeOne.get(i))<2)
				universeTwo.get(i).setAlive(false);
			else if((universeOne.get(i).isAlive() && cellNeighborhoodCounter(universeOne.get(i))==2) || (universeOne.get(i).isAlive() && cellNeighborhoodCounter(universeOne.get(i))==3))
				universeTwo.get(i).setAlive(true);
			else if(universeOne.get(i).isAlive() && cellNeighborhoodCounter(universeOne.get(i))>3)
				universeTwo.get(i).setAlive(false);
			else if(!universeOne.get(i).isAlive() && cellNeighborhoodCounter(universeOne.get(i))==3)
				universeTwo.get(i).setAlive(true);
			else;
			
		}
		
				
		return universeTwo;
	
	}
	
	
	public boolean tableChanging() {
		
		
		if(canGrowth()) {
			
			
			for (int i = 0; i < universeTwo.size(); i++) {
				
				universeOne.get(i).setAlive(universeTwo.get(i).isAlive());
				
			}
			
			return true;
		}
		
		
		return false;
	}
	
	
	
	
	private boolean canGrowth() {
		
		
		for (int i = 0; i < universeOne.size(); i++) {
			
			if(universeOne.get(i).isAlive()!=universeTwo.get(i).isAlive())
			return true;
		}
		
		
		
		return false;
	}
	
	
	private int cellNeighborhoodCounter(Cell cell){
		
		int cellNeighbors=0;
		
		int x=cell.getX();
		int y=cell.getY();
		
		if(x-1>=0 && universeOne.get((x-1)*cols+y).isAlive())
			cellNeighbors++;
		if(x-1>=0 && y+1<cols && universeOne.get((x-1)*cols+y+1).isAlive())
			cellNeighbors++;
		if(y+1<cols && universeOne.get(x*cols+y+1).isAlive())
			cellNeighbors++;
		if(x+1<rows && y+1<cols && universeOne.get((x+1)*cols+y+1).isAlive())
			cellNeighbors++;
		if(x+1<rows && universeOne.get((x+1)*cols+y).isAlive())
			cellNeighbors++;
		if(x+1<rows && y-1>=0 && universeOne.get((x+1)*cols+y-1).isAlive())
			cellNeighbors++;
		if(y-1>=0 && universeOne.get(x*cols+y-1).isAlive())
			cellNeighbors++;
		if(x-1>=0 && y-1>=0 && universeOne.get((x-1)*cols+y-1).isAlive())
			cellNeighbors++;
		
		return cellNeighbors;
	}
	
}
