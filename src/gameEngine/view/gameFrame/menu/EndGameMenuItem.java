package gameEngine.view.gameFrame.menu;


import java.awt.event.ActionEvent;



/**
 * @author Lalita Maraj
 * Menu item to end game
 */
@SuppressWarnings("serial")
public class EndGameMenuItem extends MenuItem {
   

    public EndGameMenuItem (MenuActions menuActions,String menuName) {

        super(menuActions, menuName);

      
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        menuActions.endGame();

    }

}
