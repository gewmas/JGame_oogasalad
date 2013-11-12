package gameEngine.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import jgame.platform.JGEngine;
import gameEngine.model.Tile;
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
public class GridFactory implements FactoryInterface {
	private Parser parser;
	private ArrayList<ArrayList<Tile>> gridList;
        LinkedList<Tile> path;
	public GridFactory(Parser parser) {
	this.parser = parser;
	gridList = new ArrayList<ArrayList<Tile>>();
	path = new LinkedList<Tile>();
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
	    
	    //create a map of all of the path coordinates and null grid objects
	    HashMap<Coordinate, Tile> pathCoordinates = new HashMap<Coordinate, Tile>();
	    for(int k=0; k<pathList.length(); k++) {
	        JSONObject coord = pathList.getJSONObject(k);
	        pathCoordinates.put(new Coordinate(coord.getInt("x"), coord.getInt("y")), null);
	    }
	    
	    //create a 2D array of grid elements
	    
	    int currentXOffset = 0;
	    for(int k=0; k<tilesPerRow; k++) {
	        int currentYOffset = 0;  
	        gridList.add(new ArrayList<Tile>());
	        for(int m=0; m<tilesPerRow; m++) {
	            Tile tile = new Tile(currentXOffset, currentYOffset, (width / tilesPerRow) + currentXOffset, (height / tilesPerRow) + currentYOffset);
	            gridList.get(k).add(tile);
	            currentYOffset = currentYOffset + height / tilesPerRow;
	            if(pathCoordinates.keySet().contains(new Coordinate(k, m))) {
	                tile.setOnPath(pathImage);
	                pathCoordinates.put(new Coordinate(k, m), tile);
	            }
	        }
	        currentXOffset = currentXOffset + width / tilesPerRow;
	    }
	    
	   //generate path Linked List
	    for(int k=0; k<pathList.length(); k++) {
	        JSONObject coord = pathList.getJSONObject(k);
	        path.add(pathCoordinates.get(new Coordinate(coord.getInt("x"), coord.getInt("y"))));
	    }
	    	    
	}

        public ArrayList<ArrayList<Tile>> getGridList() {
            return gridList;
        }
        
        public LinkedList<Tile> getPathList() {
            return path;
        }
}
