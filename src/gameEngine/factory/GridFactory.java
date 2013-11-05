package gameEngine.factory;

import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;

public class GridFactory implements FactoryInterface{
	private JSONObject jsonObject;
	public GridFactory(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}
	
	@Override
	public void initialize() {
	    JSONArray pathList = jsonObject.getJSONArray("path");
	    //assuming that the grid is 10 x 10...WHAT IS THE GRID SIZE??
		
	}
	
	

}
