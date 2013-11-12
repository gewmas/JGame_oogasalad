package gameEngine.model;

import jgame.JGObject;
/**
 * 
 * @author Harris
 * 
 * Each grid square will be a Tile object
 *
 */
public class Tile {
    
    private boolean hasPath, hasTower;
    private double xPos, yPos, centerX, centerY;
    private String imgPath;
        
    public Tile(double x, double y, double endX, double endY) {
        hasPath = false;
        hasTower = false;
        xPos = x;
        yPos = y;
        imgPath = "";
        this.centerX = (x + endX) / 2;
        this.centerY = (y + endY) / 2;
    }
    
    public void setOnPath(String imgPath) {
        hasPath = true;
        this.imgPath = imgPath;
    }
    
    public void setTower() {
        hasTower = true;
    }
    
    public boolean hasPath() {
        return hasPath;
    }
    
    public boolean hasTower() {
        return hasTower;
    }
    
    public boolean isEmpty() {
        return (!hasPath) && (!hasTower);
    }
    
    public double getX() {
        return xPos;
    }
    
    public double getY() {
        return yPos;
    }
    
    public double getCenterX() {
        return centerX;
    }
    
    public double getCenterY() {
        return centerY;
    }
    
    public String getPathImage() {
        return imgPath;
    }
    
}
