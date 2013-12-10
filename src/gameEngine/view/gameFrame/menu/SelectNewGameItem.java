package gameEngine.view.gameFrame.menu;

import java.awt.event.ActionEvent;


/**
 * Menu specific to selecting a new game
 * 
 * @author Lalita Maraj
 * 
 */
@SuppressWarnings("serial")
class SelectNewGameItem extends MenuItem {


    public SelectNewGameItem (MenuActions menuActions,String menuName) {
        super(menuActions,menuName);
       
    }

    @Override
    public void actionPerformed (ActionEvent e) {

        menuActions.selectNewGame();

    }

}
