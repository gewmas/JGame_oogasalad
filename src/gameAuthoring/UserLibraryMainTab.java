package gameAuthoring;

import java.awt.Dimension;
import javax.swing.JTabbedPane;


public class UserLibraryMainTab extends JTabbedPane {

    public UserLibraryMainTab () {
        this.setPreferredSize(new Dimension(300, 650));
        this.add("Image Library", new UserImagesTab().getTab());
        this.add("Sound Library", new UserSoundsTab().getTab());
    }
}
