package gameEngine.view.gameFrame.tools;

/**
 * @author Lalita Maraj
 *         Data Structure that encapsulates the formatting of information 
 *         that is displayed on the InfoDisplay Panel
 */
public class DisplayValue {
    private static final String CLOSING_COLOR_HTML = ">";
    private static final String CLOSING_HTML = "</font><br>";
    private static final String COLOR_HTML = ":</b></font>  <font color=";
    private static final String OPENING_HTML = "<font color=blue> <b>";
    private String field;
    private String value;
    private String color;

    public DisplayValue (String field, String value, String color) {
        this.field = field;
        this.value = value;
        this.color = color;
    }

    public String toString () {
        return OPENING_HTML + field + COLOR_HTML +
               color + CLOSING_COLOR_HTML + value + CLOSING_HTML;
    }
}
