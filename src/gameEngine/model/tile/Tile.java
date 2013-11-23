package gameEngine.model.tile;

import jgame.JGObject;


/**
 * 
 * @author Harris
 * 
 *         Each grid square will be a Tile object
 * 
 */
public class Tile {

    private boolean hasPath, hasTower, hasStaticBarrier, hasTemporaryBarrier;
    private double xPos, yPos, centerX, centerY, endX, endY;
    private String pathImage, barrierImage;

    public Tile (double x, double y, double endX, double endY) {
        hasPath = false;
        hasTower = false;
        hasStaticBarrier = false;
        hasTemporaryBarrier = false;
        xPos = x;
        yPos = y;
        this.endX = endX;
        this.endY = endY;
        pathImage = "";
        barrierImage = "";
        this.centerX = (x + endX) / 2;
        this.centerY = (y + endY) / 2;
    }

    public void setOnPath (String imgPath) {
        hasPath = true;
        this.pathImage = imgPath;
    }

    public void setTower () {
        hasTower = true;
    }
    
    public void removeTower() {
        hasTower = false;
    }

    public boolean hasPath () {
        return hasPath;
    }

    public boolean hasTower () {
        return hasTower;
    }
    
    public boolean hasStaticBarrier () {
        return hasStaticBarrier;
    }

    public boolean isEmpty () {
        return (!hasPath) && (!hasTower) && (!hasStaticBarrier) && (!hasTemporaryBarrier);
    }

    public double getX () {
        return xPos;
    }

    public double getY () {
        return yPos;
    }
    
    public double getEndX() {
        return endX;
    }
    
    public double getEndY() {
        return endY;
    }

    public double getCenterX () {
        return centerX;
    }

    public double getCenterY () {
        return centerY;
    }

    public String getPathImage () {
        return pathImage;
    }
    
    public String getBarrierImage() {
        return barrierImage;
    }
    
    public void setStaticBarrier (String img) {
        hasStaticBarrier = true;
        barrierImage = "";
    }
    
    public void setTemporaryBarrier() {
        hasTemporaryBarrier = true;
    }
    
    public void removeTemporaryBarrier () {
        hasTemporaryBarrier = false;
        barrierImage = "";
    }
}
