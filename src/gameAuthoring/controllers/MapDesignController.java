package gameAuthoring.controllers;

import gameAuthoring.model.GameData;
import gameAuthoring.view.MapDesignInformation;
import java.util.Observable;


/**
 * @author Rebecca Lai & Susan Zhang
 *         MapDesignController acts as a controller for MapDesignTab. MapDesignController observes
 *         MapDesignTab such that when MapDesignTab is changed, MapDesignController can send
 *         appropriate map information to GameData.
 */
public class MapDesignController extends DesignController {

    /**
     * Creates new MapDesignController
     * 
     * @param gameData is GameData to which information is written
     */
    public MapDesignController (GameData gameData) {
        super(gameData);
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameAuthoring.controllers.DesignController#update(java.util.Observable,
     * java.lang.Object)
     */
    @Override
    public void update (Observable o, Object arg) {
        MapDesignInformation mapDesignInformation = (MapDesignInformation) arg;
        myGameData.setBackgroundImage(mapDesignInformation.getBackgroundImageKey());
        myGameData.addImage(mapDesignInformation.getBackgroundImageKey(),
                            mapDesignInformation.getBackgroundImage());
        myGameData.setMap(mapDesignInformation.getPathImage(),
                          mapDesignInformation.getPathCoordinates());
    }
}
