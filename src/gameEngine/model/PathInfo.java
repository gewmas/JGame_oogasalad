package gameEngine.model;
/*
 * wenxin shi
 */
public class PathInfo {
    private int myX;
    private int myY;
    private String myImage;
    
    public PathInfo(int x,int y,String image){
        myX=x;
        myY=y;
        myImage=image;
    }
    
    public int getX(){
        return myX;
    }
    
    public int getY(){
        return myY;
    }
    
    public String getImage(){
        return myImage;
    }

}
