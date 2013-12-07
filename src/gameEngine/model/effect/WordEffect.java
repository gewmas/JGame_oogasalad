package gameEngine.model.effect;

import jgame.JGObject;

public class WordEffect {
    
    public WordEffect(double x , double y, String str,int width){
        double currentX=x-str.length()*width/2;
        double currentY=y;
        for(int i=0; i<str.length();i++){
            
            Character c=Character.valueOf((str.charAt(i)));
            
            System.out.println(c.charValue());
            if(c.charValue()==' '){
                currentX+=width;
                continue;
            }
          
            String letter= "Letter"+c.toString();
            JGObject oneLetter= new JGObject(null, true,0, 0, 0, letter);
            new FadeEffect(currentX,currentY,oneLetter);
            currentX+=width;
        }
        
    }

}
