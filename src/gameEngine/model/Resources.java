package gameEngine.model;

import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.HashMap;
import java.util.Map;
import jgame.impl.JGEngineInterface;

/**
 * @author Jiaran
 *
 */
public class Resources {
    
    JGEngineInterface myEngine;
    private Map<String ,String > myImages= new HashMap<String,String>();
    public Resources (JGEngineInterface eng){
        myEngine=eng;
    }
    public void register(Parser parser){
        register(parser,"image");
        register(parser,"audio");
        register(parser,"animation");
        myEngine.playAudio("alarm");
    }
    
    private void register(Parser parser, String category){
        JSONArray items = parser.getJSONObject("resources").getJSONArray(category);
        for (int i = 0; i < items.length(); i++) {
            JSONObject oneItem = items.getJSONObject(i);
            String id = oneItem.getString("id");
            
            if(category.equals("image")){
                String url = oneItem.getString("url");
                System.out.println(url);
                myEngine.defineImage(id, "-", 256, url, "-");
                myImages.put(id, url);
            }
            else if(category.equals("audio")){
                String url = oneItem.getString("url");
                myEngine.defineAudioClip(id, url);
            }
            else{
                String[] frames= makeFrames(oneItem.getJSONArray("sheet"));
                myEngine.defineAnimation(id, frames, 0.2);
                System.out.print("done with frames");
            }
            
        }
    }
   
   
    private String[] makeFrames (JSONArray jsonArray) {
        int size = jsonArray.length();
        String[] result= new String[size];
        for(int i=0; i <jsonArray.length();i++){
            result[i]= jsonArray.getJSONObject(i).getString("id");
        }
        return result;
        
    }
    
    
   

}
