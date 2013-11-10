package gameEngine.view.store;

/**
 * An enumerated class that contains the Fields of tower information
 * to be displayd in the infoPanel. The fields are returened with HTML formatting
 * 
 * @author Lalita Maraj
 * 
 */
public enum TowerInfoFields {
    NAME("Tower", "red"),
    COST("Cost", "green"),
    DESCRIPTION("Description", "blue");

    private static final String HTML_CLOSE = " :</font> ";
    private static final String HTML_STYLE_FONT = " style=font-weight:bold>";
    private static final String HTML_FONT_COLOR = "<html><font color=";
    private String field;
    private String color;

    /**
     * @param field name of field
     * @param color color text is to be displayed with
     */
    TowerInfoFields (String field, String color) {
        this.field = field;
        this.color = color;
    }

    @Override
    /**
     *Returns the field formatted with HTML styling
     */
    public String toString () {

        return HTML_FONT_COLOR + color + HTML_STYLE_FONT + field + HTML_CLOSE;
    }

}
