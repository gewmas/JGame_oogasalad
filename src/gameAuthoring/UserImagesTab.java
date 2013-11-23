package gameAuthoring;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JScrollPane;


public class UserImagesTab implements Observer {

    public JScrollPane getTab () {
        JScrollPane mainPanel = new JScrollPane();
        return mainPanel;
    }

    @Override
    public void update (Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

}
