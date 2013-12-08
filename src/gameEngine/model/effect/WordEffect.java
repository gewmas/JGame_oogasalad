package gameEngine.model.effect;

import jgame.JGObject;

/**
 * By application of FadeEffect, we can create WordEffect to print messages.
 * @author Jiaran
 *
 */
public class WordEffect {
    
    /**
     * @param x: the x coordinate where to print these messages.
     * @param y: y coordinate
     * @param str: the message to be printed. Only support letters and digit or space
     * @param width: the interval between each letters.
     */
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
            new FadeEffect("WordEffect",currentX,currentY,oneLetter);
            currentX+=width;
        }
        
    }

}
