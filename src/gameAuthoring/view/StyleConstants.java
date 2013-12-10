package gameAuthoring.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


/**
 * @author Rebecca Lai & Susan Zhang
 *         Constants that apply for ALL Game Authoring classes
 */
public final class StyleConstants {
    public static final String DEFAULT_RESOURCE_PACKAGE = "gameAuthoring/resources.";
    public static final ResourceBundle resourceBundle = ResourceBundle
            .getBundle(DEFAULT_RESOURCE_PACKAGE +
                       "Labels");
    public static final Font DEFAULT_TITLE_FONT = new Font("Calibri", Font.PLAIN, 30);
    public static final Font TITLE_FONT_2 = new Font("Calibri", Font.PLAIN, 20);
    public static final Font DEFAULT_BODY_FONT = new Font("Calibri", Font.PLAIN, 14);
    public static final Font MAP_FONT = new Font("Calibri", Font.PLAIN, 12);
    public static final Font MAIN_PANEL_BUTTON_FONT = new Font("Helvetica", Font.PLAIN, 40);
    public static final String PATH_SELECTION_MODE = "path";
    public static final String BARRIER_SELECTION_MODE = "barrier";
    public static final String DEFAULT_WRAP_MODE = "wrap 2";
    public static final String DEFAULT_SPAN_MODE = "span 2";
    public static final Dimension DEFAULT_PANEL_SIZE = new Dimension(500, 500);
    public static final Border DEFAULT_PANEL_BORDER = BorderFactory
            .createLineBorder(Color.black, 1);
    public static final String NULL_STRING = "";

}
