package gameEngine.view.gameFrame.store;

/**
 * An enumerated class that contains the Fields of tower information
 * to be displayd in the infoPanel. The fields are returened with HTML formatting
 * 
 * @author Lalita Maraj
 * 
 */
public interface TowerInfoFields {

    static final String HTML_CLOSE = " :</font>";

    static final String HTML_FONT_OPENNING = "<li><font color=blue style=font-weight:bold>";

    static final String TOWER = HTML_FONT_OPENNING + "Tower" + HTML_CLOSE;
    static final String COST = HTML_FONT_OPENNING + "Cost" + HTML_CLOSE;
    static final String DESCRIPTION = HTML_FONT_OPENNING + "Description" + HTML_CLOSE;
    static final String ATTACKSPEED = HTML_FONT_OPENNING + "Attack Speed" + HTML_CLOSE;
    static final String ATTACKMODE = HTML_FONT_OPENNING + "Attack Mode" + HTML_CLOSE;

    static final String RANGE = HTML_FONT_OPENNING + "Range" + HTML_CLOSE;
    static final String RECYCLE = HTML_FONT_OPENNING + "Recycle Price" + HTML_CLOSE;

    static final String DAMAGE = HTML_FONT_OPENNING + "Damage" + HTML_CLOSE;

}
