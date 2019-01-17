package com.david.giczi.gameoflife.model;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InputFileReader   {

	
	private List<String> inputData;
	private String nameOfThePattern;
	private String fileFormat;
	
	
	public InputFileReader(String fileName) {
		
		inputData=new ArrayList<>();
		nameOfThePattern=fileName;
			
		try {
			
			inputData=Files.lines(Paths.get("./files/"+fileName+".lif")).collect(Collectors.toList());
			fileFormat=inputData.get(0).substring(1);
		
		} catch (IOException e) {
			
			e.printStackTrace();
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
		
		Cell upperLeftHand=new Cell(0, 0);
		
		
		int xMax=inputData.stream().filter(x->x.startsWith("#P")).map(x->x.split(" ")).map(x->Integer.parseInt(x[1])).mapToInt(x->x).max().getAsInt();
		
		int yMax=inputData.stream().filter(x->x.startsWith("#P")).map(x->x.split(" ")).map(x->Integer.parseInt(x[2])).mapToInt(x->x).max().getAsInt();	
		
		
		upperLeftHand.setX(xMax);
		upperLeftHand.setY(yMax);
			
			
		return upperLeftHand;
		
	}
	
	
	private Cell lowerRightHandCornerFinderFor1_05FileFormat() {
		
		
		Cell lowerRightHand=new Cell(0, 0);
		
		
		int xMin=inputData.stream().filter(x->x.startsWith("#P")).map(x->x.split(" ")).map(x->Integer.parseInt(x[1])).mapToInt(x->x).min().getAsInt();
		
		int yMin=inputData.stream().filter(x->x.startsWith("#P")).map(x->x.split(" ")).map(x->Integer.parseInt(x[2])).mapToInt(x->x).min().getAsInt();	
		
		
		lowerRightHand.setX(xMin);
		lowerRightHand.setY(yMin);
		
		
		return lowerRightHand;
	}
	
	
	
	private int theLongestRowInThe1_05FileFormat() {
		
		return inputData.stream().filter(x->x.startsWith(".") || x.startsWith("*")).map(x->x.length()).mapToInt(x->x).max().getAsInt();
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
		
		
		Cell lowerRightHand=new Cell(0, 0);
		
		
		int xMax=inputData.stream().filter(x->!x.startsWith("#")).map(x->x.split(" ")).map(x->Integer.parseInt(x[0])).mapToInt(x->x).max().getAsInt();
		int yMax=inputData.stream().filter(x->!x.startsWith("#")).map(x->x.split(" ")).map(x->Integer.parseInt(x[1])).mapToInt(x->x).max().getAsInt();
		
		lowerRightHand.setX(xMax);
		lowerRightHand.setY(yMax);
		
		
		return lowerRightHand;
	}
	
	
}
