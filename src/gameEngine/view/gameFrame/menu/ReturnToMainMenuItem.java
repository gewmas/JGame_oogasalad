package gameEngine.view.gameFrame.menu;
import gameEngine.view.ViewConstants;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author lalitamaraj
 *MenuItem that allows a user to return to the main menu
 */
@SuppressWarnings("serial")
public class ReturnToMainMenuItem extends AbstractAction{
    private MenuActions menuActions;
    public ReturnToMainMenuItem (MenuActions menuActions) {

        super(ViewConstants.resourceBundle.getString("ReturnToMainMenu"));

        this.menuActions = menuActions;
    }


    @Override
    public void actionPerformed (ActionEvent e) {
        menuActions.goToMainMenu();
        
    }

}
