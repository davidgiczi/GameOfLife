package com.david.giczi.gameoflife.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFileReader   {

	
	private List<String> inputData;
	private String nameOfThePattern;
	private String fileFormat;
	private BufferedReader in;
	
	
	public InputFileReader(String fileName) {
		
		inputData=new ArrayList<>();
		nameOfThePattern=fileName;
			
			try {
				
				in=new BufferedReader(new FileReader(new File("./files/"+fileName+".lif")));
				
				fileFormat=in.readLine().substring(1);
				
				String row;
				
				while((row=in.readLine())!=null) {
					inputData.add(row);
				}
				
			} catch (IOException e) {
				System.out.println("The lif file with \'"+fileName+"\' name does not exist!");
				e.printStackTrace();
			}
			finally {
				
				try {
					in.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
			
	
	}
	
			
	
	public List<String> getInputData() {
		return inputData;
	}

	
	

	public String getNameOfThePattern() {
		return nameOfThePattern;
	}

	

	public String getFileFormat() {
		return fileFormat;
	}



	public int getNeedfulColsNumberFor1_05FileFormat() {
		
		
			Cell upperLeftCorner=upperLeftHandCornerFinderFor1_05FileFormat();
			Cell lowerRightCorner=lowerRightHandCornerFinderFor1_05FileFormat();
			
		
			if(upperLeftCorner.equals(lowerRightCorner))
			return theLongestRowInThe1_05FileFormat();
			
			
			return Math.abs(lowerRightCorner.getX()-upperLeftCorner.getX())+theLongestRowInThe1_05FileFormat();
			
		
	}
	
	
	public int getNeedfulRowsNumberFor1_05FileFormat() {
		

			Cell upperLeftCorner=upperLeftHandCornerFinderFor1_05FileFormat();
			Cell lowerRightCorner=lowerRightHandCornerFinderFor1_05FileFormat();
			
			
			if(upperLeftCorner.equals(lowerRightCorner))
			return numberOftheMostRowInThe1_05FileFormat();
			
			
			return Math.abs(lowerRightCorner.getY()-upperLeftCorner.getY())+numberOftheMostRowInThe1_05FileFormat();
	
		
	}
	
	
	public int getNeedfulColsNumberFor1_06FileFormat() {
		
	
		return lowerRightHandCornerFinderFor1_06FileFormat().getX();
		
	
	}


	public int getNeedfulRowsNumberFor1_06FileFormat() {
	
	
		
		return lowerRightHandCornerFinderFor1_06FileFormat().getY();

	
	}
	
	
	
	
	
	public int getOrigoXFor1_05FileFormat() {
		
		
		return upperLeftHandCornerFinderFor1_05FileFormat().getX();
		
	}
	
	
	
	
	public int getOrigoYFor1_05FileFormat() {
		
		
		return upperLeftHandCornerFinderFor1_05FileFormat().getY();
			
		
	}
	
	public int getOrigoXFor1_06FileFormat() {
		
		
		return 0;
			
		
	}
	
	public int getOrigoYFor1_06FileFormat() {
		
		
		return 0;
			
		
	}
	
	private Cell upperLeftHandCornerFinderFor1_05FileFormat(){
		
		Cell upperLeftHand=new Cell(Integer.MAX_VALUE, Integer.MAX_VALUE);
		
		
		for (String row : inputData) {
			
		
			if(row.startsWith("#P")) {
				
			String[] parts=row.split(" ");
			
			int x=Integer.parseInt(parts[1]);
			int y=Integer.parseInt(parts[2]);
			
			
			if(upperLeftHand.getX()>x) {
				
				upperLeftHand.setX(x);
				
			}
			
			if(upperLeftHand.getY()>y) {
				upperLeftHand.setY(y);
			}
			
		}
			
	}
			
			
				
		return upperLeftHand;
		
	}
	
	
	private Cell lowerRightHandCornerFinderFor1_05FileFormat() {
		
		
		Cell lowerRightHand=new Cell(Integer.MIN_VALUE, Integer.MIN_VALUE);
		
		
		for (String row : inputData) {
			
			
			if(row.startsWith("#P")) {
				
			String[] parts=row.split(" ");
			
			int x=Integer.parseInt(parts[1]);
			int y=Integer.parseInt(parts[2]);
			
			
			if(lowerRightHand.getX()<x) {
				
				lowerRightHand.setX(x);
				
			}
			
			if(lowerRightHand.getY()<y) {
				lowerRightHand.setY(y);
			}
			
		}
			
	}
		
		
		return lowerRightHand;
	}
	
	
	
	private int theLongestRowInThe1_05FileFormat() {
		
		int theLongest=0;
		
		for (String row : inputData) {
			
			if(row.startsWith(".") || row.startsWith("*")) {
				
				if(row.length()>theLongest) {
					theLongest=row.length();
				}
				
			}
			
		}
		
		
		return theLongest;
	}
	
	
	private int numberOftheMostRowInThe1_05FileFormat() {
		
		int numberOfTheMostRow=0;
		int pcs=0;
		boolean letsCount=false;
		
		
		
		for (String row : inputData) {
			
			
			if(row.startsWith("#P") && !letsCount) {
				
				letsCount=true;
				
				if(pcs>numberOfTheMostRow)
				numberOfTheMostRow=pcs;
				
				pcs=0;
				continue;
			}
				
			
			if(row.startsWith(".") || row.startsWith("*")) {
				pcs++;
			}
			
			
			if(row.startsWith("#P") && letsCount) {
				
				letsCount=false;
				
				if(pcs>numberOfTheMostRow)
				numberOfTheMostRow=pcs;
				
				pcs=0;
			}
			
			
		}
		
		if(pcs>numberOfTheMostRow)
			numberOfTheMostRow=pcs;
		
		
		return numberOfTheMostRow;
	}
	
	private Cell lowerRightHandCornerFinderFor1_06FileFormat() {
		
		
		Cell lowerRightHand=new Cell(Integer.MIN_VALUE, Integer.MIN_VALUE);
		
		
		for (int i=1; i<inputData.size(); i++) {
			
			String[] coords=inputData.get(i).split(" ");
			
			int x=Integer.parseInt(coords[0]);
			int y=Integer.parseInt(coords[1]);
			
			if(lowerRightHand.getX()<x) {
				lowerRightHand.setX(x);
			}
			
			if(lowerRightHand.getY()<y) {
				lowerRightHand.setY(y);
			}
			
		}
		
		
		return lowerRightHand;
	}
	
}
