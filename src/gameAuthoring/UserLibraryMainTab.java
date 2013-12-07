package gameAuthoring;

import java.awt.Dimension;
import javax.swing.JTabbedPane;


public class UserLibraryMainTab extends JTabbedPane {

    public UserLibraryMainTab () {
        this.setPreferredSize(new Dimension(300, 650));
        UserImagesTab userImagesTab = new UserImagesTab();
        UserSoundsTab userSoundsTab = new UserSoundsTab();
        this.add("Image Library", userImagesTab.getTab());
        this.add("Sound Library", userSoundsTab.getTab());
        this.setFont(Constants.DEFAULT_BODY_FONT);
    }
}
