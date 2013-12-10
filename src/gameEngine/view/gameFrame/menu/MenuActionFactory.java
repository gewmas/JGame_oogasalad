package gameEngine.view.gameFrame.menu;

/**
 * @author Lalita Maraj
 * Factory interface designed to create
 * MenuAction Objects for the different items on the Menu
 * 
 */
public interface MenuActionFactory {
    public static String END_GAME = "endGame";
    public static String SELECT_NEW_GAME ="selectNewGame";
    public static String MAIN_MENU = "mainMenu";
    
    /** Creates a MenuAction based on the string given to method
     * @param menuItem string of menu item to be created
     * @return MenuAction with executeAction method tailored to the given menuItem
     */
    public MenuAction createMenuAction(String menuItem);
}
