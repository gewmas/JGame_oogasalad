package gameEngine.view.gameFrame.menu;


import java.awt.event.ActionEvent;


/**
 * @author lalitamaraj
 *         MenuItem that allows a user to return to the main menu
 */
@SuppressWarnings("serial")
public class ReturnToMainMenuItem extends MenuItem {

    public ReturnToMainMenuItem (MenuActions menuActions, String menuName) {

        super(menuActions, menuName);

    }

    @Override
    public void actionPerformed (ActionEvent e) {
        menuActions.goToMainMenu();

    }

}
