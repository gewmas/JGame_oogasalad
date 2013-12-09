package gameEngine.view.gameFrame.inputFrame;

/**
 * @author lalitamaraj
 *         An interface to be used with the InputAndDisplayFrame
 *         that executes behavior on the textInput a user enters
 *         in the textBox
 */
public interface InputSender {

    /**
     * Method to execute behavior on the text input from textbox
     * 
     * @param textInput input from textbox
     */
    public void submit (String textInput);

}
