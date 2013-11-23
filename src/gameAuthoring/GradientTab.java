package gameAuthoring;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTabbedPane;


public class GradientTab extends JTabbedPane {

    @Override
    protected void paintComponent (Graphics grphcs) {
        Graphics2D g2d = (Graphics2D) grphcs;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp =
                new GradientPaint(0, 0,
                                  getBackground().brighter().brighter(), 0, getHeight(),
                                  getBackground().darker().darker());
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }
}
