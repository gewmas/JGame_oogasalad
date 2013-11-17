package gameEngine.view.gameFrame.store;

import gameEngine.view.StyleConstants;

/**
 * An interface that contains the Fields of tower information
 * to be displayd in the infoPanel. The fields are returened with HTML formatting
 * 
 * @author Lalita Maraj
 * 
 */
public interface TowerInfoFields {

    static final String HTML_CLOSE = " :</font>";

    static final String HTML_FONT_OPENNING = "<li><font color=blue style=font-weight:bold>";

    static final String TOWER = HTML_FONT_OPENNING + StyleConstants.resourceBundle.getString("Tower") + HTML_CLOSE;
    static final String COST = HTML_FONT_OPENNING +  StyleConstants.resourceBundle.getString("Cost") + HTML_CLOSE;
    static final String DESCRIPTION = HTML_FONT_OPENNING +  StyleConstants.resourceBundle.getString("Description") + HTML_CLOSE;
    static final String ATTACKSPEED = HTML_FONT_OPENNING +  StyleConstants.resourceBundle.getString("AttackSpeed") + HTML_CLOSE;
    static final String ATTACKMODE = HTML_FONT_OPENNING +  StyleConstants.resourceBundle.getString("AttackMode") + HTML_CLOSE;

    static final String RANGE = HTML_FONT_OPENNING +  StyleConstants.resourceBundle.getString("Range") + HTML_CLOSE;
    static final String RECYCLE = HTML_FONT_OPENNING +  StyleConstants.resourceBundle.getString("Recycle") + HTML_CLOSE;

    static final String DAMAGE = HTML_FONT_OPENNING +  StyleConstants.resourceBundle.getString("Damage") + HTML_CLOSE;

}
