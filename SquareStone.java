package MancalaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SquareStone implements Icon 
{
    Color color;
    int size;
    int width;

    /**
     * Construct a stone Icon
     * @param size
     * @param color
     */
    public SquareStone(int size, Color color) {
        this.color = color;
        this.size = size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y,size + 1,size + 1);
        g2.setColor(color);
        g2.fill(rect);
    }

}