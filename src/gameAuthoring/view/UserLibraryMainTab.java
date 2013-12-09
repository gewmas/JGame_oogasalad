package gameAuthoring.view;

import gameAuthoring.JSONObjects.GameData;
import java.awt.Dimension;
import javax.swing.JTabbedPane;


public class UserLibraryMainTab extends JTabbedPane {

    public UserLibraryMainTab (GameData gameData) {
        this.setPreferredSize(new Dimension(300, 600));
        UserImagesTab userImagesTab = new UserImagesTab(gameData);
        UserSoundsTab userSoundsTab = new UserSoundsTab();
        this.add("Image Library", userImagesTab.getTab());
        this.add("Sound Library", userSoundsTab.getTab());
        this.setFont(Constants.DEFAULT_BODY_FONT);
    }
}
