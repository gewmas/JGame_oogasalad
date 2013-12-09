package gameAuthoring.view;

import java.awt.geom.Point2D;
import java.util.Collection;


public class MapDesignInformation {

    private String myBackgroundImage;
    private String myPathImage;
    private String myBackgroundImageKey;
    private Collection<Point2D> myPathCoordinates;

    public MapDesignInformation (String backgroundImage,
                                 String pathImage,
                                 String backgroundImageKey,
                                 Collection<Point2D> pathCoordinates) {
        myBackgroundImage = backgroundImage;
        myPathImage = pathImage;
        myBackgroundImageKey = backgroundImageKey;
        myPathCoordinates = pathCoordinates;
    }

    public String getBackgroundImage () {
        return myBackgroundImage;
    }

    public String getPathImage () {
        return myPathImage;
    }

    public String getBackgroundImageKey () {
        return myBackgroundImageKey;
    }

    public Collection<Point2D> getPathCoordinates () {
        return myPathCoordinates;
    }

}
