package gameEngine.factory;

import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;

public class GridFactory implements FactoryInterface{
	private JSONObject jsonObject;
	public GridFactory(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
		initialize();
	}
	
	@Override
	public void initialize() {
//	    String pathImage = jsonObject.getString("pathImage");
//	    JSONArray pathList = jsonObject.getJSONArray("path");
	    //assuming that the grid is 15 x 15...WHAT IS THE GRID SIZE??
	    for(int k=0; k<15; k++) {
	        for(int m=0; m<15; m++) {
	           
	        }
	    }
		
	}
	
	

}
