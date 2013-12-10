package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.ResourceJSONObject;
import gameAuthoring.model.GameData;
import java.util.Observable;


/**
 * @author Rebecca Lai & Susan Zhang
 *         UserImagesController acts as a controller for UserImagesTab. When an image is uploaded to
 *         UserImagesTab, UserImagesController is notified so that it can uploaded the image to
 *         GameData as a ResourceJSONObject
 */
public class UserImagesController extends DesignController {

    /**
     * Creates new UserImagesController
     * 
     * @param gameData is GameData to which information is written
     */
    public UserImagesController (GameData gameData) {
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
        ResourceJSONObject image = (ResourceJSONObject) arg;
        myGameData.addImage(image);
    }
}
