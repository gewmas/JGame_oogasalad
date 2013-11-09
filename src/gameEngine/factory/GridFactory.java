package gameEngine.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import jgame.platform.JGEngine;
import gameEngine.model.Grid;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import helpers.Coordinate;
import jgame.platform.*;
/**
 * 
 * @author Harris
 * 
 * Constructs the grid
 *
 */
public class GridFactory implements FactoryInterface{
	private Parser parser;
	private ArrayList<ArrayList<Grid>> gridList;
        LinkedList<Grid> path;
	public GridFactory(Parser parser) {
		this.parser = parser;
		gridList = new ArrayList<ArrayList<Grid>>();
		path = new LinkedList<Grid>();
	}
	
	@Override
	public void initialize() {
	    //testing the coordinate .equals method
	    System.out.println("using the .equals method in initialize");
	    System.out.println(new Coordinate(3, 4).equals(new Coordinate(3, 4)));
	    
	    
	    
	    //get all of the necessary values from the JSON
	    int height = parser.getInt("heightOfWindow");
	    int width = parser.getInt("widthOfWindow");
	    int tilesPerRow = parser.getInt("tilesPerRow");
	    JSONObject map = parser.getJSONObject("map");
	    String pathImage = map.getString("pathImage");
	    JSONArray pathList = map.getJSONArray("Path");
	    
	    //create a map of all of the path coordinates and null grid objects
	    HashMap<Coordinate, Grid> pathCoordinates = new HashMap<Coordinate, Grid>();
	    for(int k=0; k<pathList.length(); k++) {
	        JSONObject coord = pathList.getJSONObject(k);
	        pathCoordinates.put(new Coordinate(coord.getInt("x"), coord.getInt("y")), null);
	    }
	   	  
	    System.out.println("======================");
	    //create a 2D array of grid elements
	    int currentYOffset = 0; 
	    for(int k=0; k<tilesPerRow; k++) {
	        int currentXOffset = 0;
	        gridList.add(new ArrayList<Grid>());
	        for(int m=0; m<tilesPerRow; m++) {
//	            Grid grid = new Grid("grid", false, currentXOffset, currentYOffset, 8, "block1", currentXOffset, currentYOffset, (int)(width / 15), (int)(height / 15));
//	            Grid grid = new Grid("grid", false, currentXOffset, currentYOffset, 8, "block1");
//	            grid.setPos(currentXOffset, currentYOffset);
//	            System.out.println("creating new grid square at x=" + currentXOffset + " and y=" + currentYOffset);
//	            gridList.get(k).add(grid);
	            currentXOffset = currentXOffset + width / 15;
	            if(pathCoordinates.keySet().contains(new Coordinate(k, m))) {
//	                grid.setImage(pathImage);
//	                grid.setOnPath();
	                pathCoordinates.put(new Coordinate(k, m), grid);
	                System.out.println("path coordinate found");
	            }
	        }
	        currentYOffset = currentYOffset + height / 15;
	    }
	    

	   //generate path Linked List
	    for(int k=0; k<pathList.length(); k++) {
	        JSONObject coord = pathList.getJSONObject(k);
	        path.add(pathCoordinates.get(new Coordinate(coord.getInt("x"), coord.getInt("y"))));
	    }
	    	    
	}
	

        public ArrayList<ArrayList<Grid>> getGridList() {
            return gridList;
        }
        
        public LinkedList<Grid> getPathList() {
            return path;
        }
}
