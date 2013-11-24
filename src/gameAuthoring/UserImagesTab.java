package gameAuthoring;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;


public class UserImagesTab implements Observer {

    private JPanel myMainPanel = new JPanel(new MigLayout("wrap 1")) {
        @Override
        protected void paintComponent (Graphics grphcs) {
            super.paintComponent(grphcs);
            grphcs.setColor(Color.red);
            Graphics2D g2d = (Graphics2D) grphcs;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);

            GradientPaint gp =
                    new GradientPaint(0, 0,
                                      getBackground().brighter().brighter(), 0, getHeight(),
                                      getBackground().darker().darker().darker());

            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(grphcs);
        }
    };

    public JScrollPane getTab () {
        myMainPanel.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane(myMainPanel);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        return scrollPane;
    }

    @Override
    public void update (Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

}
