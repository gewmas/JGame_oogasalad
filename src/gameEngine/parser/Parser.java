package gameEngine.parser;

import java.util.List;
import java.util.Scanner;
import gameEngine.parser.JSONLibrary.*;


/**
 * 
 * @author Harris
 * 
 *         Parses a JSON file
 * 
 */
public class Parser {
    private Scanner scanner;
    private JSONObject jsonObject;

    public Parser (Scanner scanner) {
        this.scanner = scanner;
        jsonObject = new JSONObject(constructJSONString());
    }

    public String constructJSONString () {
        String returnString = "";
        while (scanner.hasNextLine()) {
            returnString = returnString + scanner.nextLine();
        }
        return returnString;
    }

    public String getString (String key) {
        return (String) jsonObject.get(key);
    }

    public int getInt (String key) {
        return jsonObject.getInt(key);
    }

    public JSONObject getJSONObject (String key) {
        return jsonObject.getJSONObject(key);
    }

    public JSONArray getJSONArray (String key) {
        return jsonObject.getJSONArray(key);
    }
    
    public double getDouble(String key){
        return (double) jsonObject.get(key);
    }
    

}
