package gameAuthoring;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;


public class UserImagesTab implements Observer {

    private JPanel myMainPanel = new GradientPanel(new MigLayout("wrap 1"));

    public JScrollPane getTab () {
        myMainPanel.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane(myMainPanel);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        return scrollPane;
    }

    @Override
    public void update (Observable arg0, Object arg1) {

    }

}
