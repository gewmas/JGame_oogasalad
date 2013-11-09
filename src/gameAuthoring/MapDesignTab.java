package gameAuthoring;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;


public class MapDesignTab extends Tab {

    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));
    private Grid myGrid;

    public MapDesignTab () {

    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel(new GridBagLayout());
        myGrid = new Grid(5, 5);
        GridBagConstraints c = new GridBagConstraints();
        JButton imageChooser = new JButton("Choose path image");
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    System.out.println(INPUT_CHOOSER.getSelectedFile());
                    File imgSource = INPUT_CHOOSER.getSelectedFile();
                    myGrid.setImageSource(imgSource);
                }
            }
        };
        imageChooser.addMouseListener(listener);
        c.gridwidth = 3;
        c.gridx = 2;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(myGrid, c);
        panel.add(imageChooser, c);
        return panel;
    }
}
