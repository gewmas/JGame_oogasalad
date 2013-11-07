package gameEngine.factory;

import java.util.ArrayList;
import java.util.HashSet;
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
	private Coordinate pathStart;
	private Coordinate pathEnd;
	private ArrayList<Grid> path;
	private int nextGridOnPathX;
	private int nextGridOnPathY;
	public GridFactory(Parser parser) {
		this.parser = parser;
		gridList = new ArrayList<ArrayList<Grid>>();
		path = new ArrayList<Grid>();
		nextGridOnPathX = 0;
		nextGridOnPathY = 0;
	}
	
	@Override
	public void initialize() {
	    //get all of the necessary values from the JSON
	    int height = parser.getInt("heightOfWindow");
	    int width = parser.getInt("widthOfWindow");
	    int tilesPerRow = parser.getInt("tilesPerRow");
	    JSONObject map = parser.getJSONObject("map");
	    String pathImage = map.getString("pathImage");
	    JSONArray pathList = map.getJSONArray("Path");
	    pathStart = new Coordinate(map.getJSONObject("startPath").getInt("x"), map.getJSONObject("startPath").getInt("y"));
	    pathEnd = new Coordinate(map.getJSONObject("endPath").getInt("x"), map.getJSONObject("endPath").getInt("y"));
	    
	    //create a set of all of the path coordinates
	    HashSet<Coordinate> pathCoordinates = new HashSet<Coordinate>();
	    for(int k=0; k<pathList.length(); k++) {
	        JSONObject coord = pathList.getJSONObject(k);
	        pathCoordinates.add(new Coordinate(coord.getInt("x"), coord.getInt("y")));
	    }
	   
	    //create a 2D array of grid elements
	    int currentYOffset = 0; 
	    for(int k=0; k<15; k++) {
	        int currentXOffset = 0;
	        gridList.add(new ArrayList<Grid>());
	        for(int m=0; m<15; m++) {
	            Grid grid = new Grid("grid", false, currentXOffset, currentYOffset, 8, "block", currentXOffset, currentYOffset, (int)(width / 15), (int)(height / 15));
	            gridList.get(k).add(grid);
	            currentXOffset = currentXOffset + width / 15;
	            if(pathCoordinates.contains(new Coordinate(k, m))) {
	                grid.setImage(pathImage);
	                grid.setOnPath();
	            }
	        }
	        currentYOffset = currentYOffset + height / 15;
	    }
	    
	    //figure out direction that player travels (assuming path is straight)
	    if(pathEnd.getX() - pathStart.getX() != 0) {
	        if(pathEnd.getX() - pathStart.getX() < 0) {
	            nextGridOnPathX = -1;
	        } else {
	            nextGridOnPathX = 1;
	        }
	    }
	    if(pathEnd.getY() - pathStart.getY() != 0) {
	        if(pathEnd.getY() - pathStart.getY() < 0) {
	            nextGridOnPathY = -1;
	        } else {
	            nextGridOnPathY = 1;
	        }
	    }
	    
	}
	

        public ArrayList<ArrayList<Grid>> getGridList() {
            return gridList;
        }
}
