package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.view.AudioLabel;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class SkillsDesignController implements Observer {

    private GameData myGameData;

    public SkillsDesignController (GameData gameData) {
        myGameData = gameData;
    }

    @Override
    public void update (Observable arg0, Object arg1) {
        System.out.println("Skills Design Controller received notification from Skills Design Tab");
        List<AudioLabel> skillsAudioList = (ArrayList<AudioLabel>) arg1;
        for (AudioLabel audio : skillsAudioList) {
            myGameData.addAudio(audio.getID(), audio.getAudioFile().getName());
        }
    }

}
