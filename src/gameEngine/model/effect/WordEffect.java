package gameEngine.model.effect;

public class WordEffect {
    private final int WIDTH=30;
    public WordEffect(double x , double y, String str,int width){
        double currentX=x;
        double currentY=y;
        for(int i=0; i<str.length();i++){
            
            String letter= "W"+Character.valueOf((str.charAt(i))).toString();
            new FadeEffect(currentX,currentY,letter,null);
            currentX+=WIDTH;
        }
        
    }

}
