package gameEngine.model.effect;

public class WordEffect {
   
    public WordEffect(double x , double y, String str,int width){
        double currentX=x-str.length()*width/2;
        double currentY=y;
        for(int i=0; i<str.length();i++){
            
            Character c=Character.valueOf((str.charAt(i)));
            if(!Character.isLetterOrDigit(c)){
                return;
            }
            String letter= "W"+c.toString();
            new FadeEffect(currentX,currentY,letter,null);
            currentX+=width;
        }
        
    }

}
