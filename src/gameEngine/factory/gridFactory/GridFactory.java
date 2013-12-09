package gameEngine.factory.gridFactory;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import jgame.platform.JGEngine;
import gameEngine.model.tile.Tile;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import helpers.Coordinate;
import java.util.ArrayList;
import java.util.HashSet;
import gameEngine.constant.*;


/**
 * 
 * @author Harris
 * 
 *         Constructs the grid
 * 
 */
public class GridFactory {

        private Parser parser;
        private ArrayList<ArrayList<Tile>> gridList;
        private LinkedList<Tile> path;
        private ArrayList<Tile> barriers;
        
        public GridFactory(Parser parser) {
        this.parser = parser;
        gridList = new ArrayList<ArrayList<Tile>>();
        path = new LinkedList<Tile>();
        barriers = new ArrayList<Tile>();
        }
        
        public void initialize() {

            //get all of the necessary values from the JSON
            int height = GameEngineConstant.GRID_HEIGHT;
            int width = GameEngineConstant.GRID_WIDTH;
            int tilesPerRow = GameEngineConstant.TILES_PER_ROW;
            JSONObject map = parser.getJSONObject("map");
            String pathImage = map.getString("pathImage");
            JSONArray pathList = map.getJSONArray("Path");
            JSONArray barrierList;
            try {
                //barriers may not be created by game designer
                barrierList = map.getJSONArray("Barriers");
            } catch (Exception e){
                barrierList = null;
            }

            //create a map of all of the path coordinates and null grid objects
            HashMap<Coordinate, Tile> pathCoordinates = new HashMap<Coordinate, Tile>();
            for(int k=0; k<pathList.length(); k++) {
                JSONObject coord = pathList.getJSONObject(k);
                pathCoordinates.put(new Coordinate(coord.getInt("x"), coord.getInt("y")), null);
            }
            
            //create a map of all the barrier coordinates and null grid objects
            HashMap<Coordinate, Tile> barrierCoordinates = new HashMap<Coordinate, Tile>();
            HashMap<Coordinate, String> barrierImages = new HashMap<Coordinate, String>();
            if(barrierList != null) {
                for(int k=0; k<barrierList.length(); k++) {
                    JSONObject coord = barrierList.getJSONObject(k);
                    barrierCoordinates.put(new Coordinate(coord.getInt("x"), coord.getInt("y")), null);
                    barrierImages.put(new Coordinate(coord.getInt("x"), coord.getInt("y")), coord.getString("image"));
                }
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
                    } else if(barrierCoordinates.keySet().contains(new Coordinate(k, m))) {
                        tile.setStaticBarrier(barrierImages.get(new Coordinate(k, m)));
                        barrierCoordinates.put(new Coordinate(k, m), tile);
                    }
                }
                currentXOffset = currentXOffset + width / tilesPerRow;
            }
            
           //generate path Linked List
            for(int k=0; k<pathList.length(); k++) {
                JSONObject coord = pathList.getJSONObject(k);
                path.add(pathCoordinates.get(new Coordinate(coord.getInt("x"), coord.getInt("y"))));
            }
            
            //generate barrier List
            if(barrierList != null) {
                for(int k=0; k<barrierList.length(); k++) {
                    JSONObject coord = barrierList.getJSONObject(k);
                    barriers.add(barrierCoordinates.get(new Coordinate(coord.getInt("x"), coord.getInt("y"))));
                }
            }
                        
        }

        public ArrayList<ArrayList<Tile>> getGridList() {
            return gridList;
        }
        
        public LinkedList<Tile> getPathList() {
            return path;
        }
        
        public ArrayList<Tile> getBarrierList() {
            return barriers;
        }

}