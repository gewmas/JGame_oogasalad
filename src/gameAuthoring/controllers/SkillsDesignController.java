package gameAuthoring.controllers;

import gameAuthoring.model.GameData;
import gameAuthoring.view.AudioLabel;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


/**
 * @author Rebecca Lai & Susan Zhang
 *         SkillsDesignController acts as a controller for SkillsDesignTab. When all fields in
 *         SkillsDesignTab are set, it notifies SkillsDesignController so that new resources can be
 *         sent to GameData.
 */
public class SkillsDesignController extends DesignController {

    /**
     * Creates new SkillsDesignController
     * 
     * @param gameData is GameData to which information is being written
     */
    public SkillsDesignController (GameData gameData) {
        super(gameData);
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameAuthoring.controllers.DesignController#update(java.util.Observable,
     * java.lang.Object)
     */
    @Override
    public void update (Observable arg0, Object arg1) {
        List<AudioLabel> skillsAudioList = (ArrayList<AudioLabel>) arg1;
        for (AudioLabel audio : skillsAudioList) {
            myGameData.addAudio(audio.getID(), audio.getAudioFile().getName());
        }
    }

}
