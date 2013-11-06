package gameEngine.factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	public GridFactory(Parser parser) {
		this.parser = parser;
		gridList = new ArrayList<ArrayList<Grid>>();
	}
	
	@Override
	public void initialize() {
	    int height = Integer.parseInt(parser.getValue("heightOfWindow"));
	    int width = Integer.parseInt(parser.getValue("widthOfWindow"));
	    int tilesPerRow = Integer.parseInt(parser.getValue("tilesPerRow"));
	    JSONObject map = parser.getJSONObject("map");
	    String pathImage = map.getString("pathImage");
	    JSONArray pathList = map.getJSONArray("path");
	    HashSet<Coordinate> pathCoordinates = new HashSet<Coordinate>();
	    for(int k=0; k<pathList.length(); k++) {
	        JSONObject coord = pathList.getJSONObject(k);
	        pathCoordinates.add(new Coordinate(coord.getInt("x"), coord.getInt("y")));
	    }
	   
	    int currentYOffset = 0; 
	    for(int k=0; k<15; k++) {
	        int currentXOffset = 0;
	        gridList.add(new ArrayList<Grid>());
	        for(int m=0; m<15; m++) {
	            Grid grid = new Grid("grid", false, currentXOffset, currentYOffset, 8, "tile", currentXOffset, currentYOffset, (int)(width / 15), (int)(height / 15));
	            gridList.get(k).add(grid);
	            currentXOffset = currentXOffset + width / 15;
	            if(pathCoordinates.contains(new Coordinate(k, m))) {
	                grid.setImage(pathImage);
	                grid.setOnPath();
	            }

	        }
	        currentYOffset = currentYOffset + height / 15;
	    }	    
	}
	
        public ArrayList<ArrayList<Grid>> getGridList() {
            return gridList;
        }
}
