package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.view.MapDesignInformation;
import java.util.Observable;


public class MapDesignController extends DesignController {

    public MapDesignController (GameData gameData) {
        super(gameData);
    }

    @Override
    public void update (Observable o, Object arg) {
        MapDesignInformation mapDesignInformation = (MapDesignInformation) arg;
        System.out.println("MapDesignController received updates from MapDesignController");
        myGameData.setBackgroundImage(mapDesignInformation.getBackgroundImageKey());
        myGameData.addImage(mapDesignInformation.getBackgroundImageKey(),
                            mapDesignInformation.getBackgroundImage());
        myGameData.setMap(mapDesignInformation.getPathImage(),
                          mapDesignInformation.getPathCoordinates());
    }
}
