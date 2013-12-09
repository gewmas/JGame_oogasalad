package gameEngine.view.gameFrame.tools;

public class DisplayValue {
    private String field;
    private String value;
    private String color;

    public DisplayValue (String field, String value, String color) {
        this.field = field;
        this.value = value;
        this.color = color;
    }

    public String toString () {
        return "<font color=blue> <b>" + field + ":</b></font>  <font color=" +
                color + ">" + value + "</font><br>";
    }
}
