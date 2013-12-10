package gameAuthoring.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import javax.swing.JPanel;


/**
 * @author Rebecca Lai
 *         GradientPanel is a modified version of a JPanel that has a gradiated background
 */
@SuppressWarnings("serial")
public class GradientPanel extends JPanel {

    /**
     * Creates new GradientPanel
     */
    public GradientPanel () {
        this.setOpaque(false);
    }

    /**
     * Sets layoutmanager for new GradientPanel
     * 
     * @param layoutManager is layout manager for panel
     */
    public GradientPanel (LayoutManager layoutManager) {
        super(layoutManager);
        this.setOpaque(false);
    }

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
}
