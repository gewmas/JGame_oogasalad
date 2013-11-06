package gameEngine.factory;

import java.util.ArrayList;
import java.util.HashSet;
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
	public GridFactory(Parser parser) {
		this.parser = parser;
		initialize();
	}
	
	@Override
	public void initialize() {
	    int height = Integer.parseInt(parser.getValue("height"));
	    int width = Integer.parseInt(parser.getValue("width"));
	    JSONObject map = parser.getJSONObject("map");
	    String pathImage = map.getString("pathImage");
	    JSONArray pathList = map.getJSONArray("path");
	    HashSet<Coordinate> pathCoordinates = new HashSet<Coordinate>();
	    for(int k=0; k<pathList.length(); k++) {
	        JSONObject coord = pathList.getJSONObject(k);
	        pathCoordinates.add(new Coordinate(coord.getInt("x"), coord.getInt("y")));
	    }
	   
	    ArrayList<ArrayList<Grid>> gridList = new ArrayList<ArrayList<Grid>>();
	    //assuming that the grid is 15 x 15...WHAT IS THE GRID SIZE??
	    int currentYOffset = 0;
	    
	    for(int k=0; k<15; k++) {
	        int currentXOffset = 0;
	        gridList.add(new ArrayList<Grid>());
	        for(int m=0; m<15; m++) {
	            gridList.get(k).add(new Grid("grid", false, currentXOffset, currentYOffset, 8, "tile", currentXOffset, currentYOffset, (int)(width / 15), (int)(height / 15)));
	            currentXOffset = currentXOffset + width / 15;
	            if(pathCoordinates.contains(new Coordinate(k, m))) {
	                
	            }

	        }
	        currentYOffset = currentYOffset + height / 15;
	    }
		
	}
	
	

}
