package gameEngine.view;

import java.awt.Font;
import java.util.ResourceBundle;


/**
 * Constants that are paramters for the look and feel
 * of the GUI
 * 
 * @author Lalita Maraj
 * 
 */
public class StyleConstants {
    public static final String BUTTON_FONT = "Helvetica";
    public static final String BUTTON_FONT_KEY = "Button.font";
    public static final int BUTTON_FONT_SIZE = 12;
    public static final int BUTTON_FONT_STYLE = Font.CENTER_BASELINE;

    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    public static final String USER_DIR = "user.dir";

    public static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE +
                                                                        "Labels");

}
