package gameAuthoring.view;

import java.awt.geom.Point2D;
import java.util.Collection;


/**
 * @author Rebecca Lai & Susan Zhang
 *         MapDesignInformation is a data container that allows for use of Java's
 *         Observer/Observable interface. MapDesignInformation is passed from an Observable to an
 *         Observer. The Observer (most likely a controller) can then extract information from
 *         MapDesignInformation.
 */
public class MapDesignInformation {

    private String myBackgroundImage;
    private String myPathImage;
    private String myBackgroundImageKey;
    private Collection<Point2D> myPathCoordinates;

    /**
     * Creates new MapDesignInformation
     * 
     * @param backgroundImage is string path of map's background image
     * @param pathImage is string path of map's path image
     * @param backgroundImageKey is key of map's background image
     * @param pathCoordinates is collection of coordinates representing map path
     */
    public MapDesignInformation (String backgroundImage,
                                 String pathImage,
                                 String backgroundImageKey,
                                 Collection<Point2D> pathCoordinates) {
        myBackgroundImage = backgroundImage;
        myPathImage = pathImage;
        myBackgroundImageKey = backgroundImageKey;
        myPathCoordinates = pathCoordinates;
    }

    /**
     * @return string path of map background image
     */
    public String getBackgroundImage () {
        return myBackgroundImage;
    }

    /**
     * @return string path of map path image
     */
    public String getPathImage () {
        return myPathImage;
    }

    /**
     * @return key of map background image
     */
    public String getBackgroundImageKey () {
        return myBackgroundImageKey;
    }

    /**
     * @return collection of coordinates representing map path
     */
    public Collection<Point2D> getPathCoordinates () {
        return myPathCoordinates;
    }

}
