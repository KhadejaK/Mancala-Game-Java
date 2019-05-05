/**
 * This class draw a stone icon which would be displayed in the Mancala Board pits
 * 
 * @author Yu Xiu
 * @version 1.0 5/4/2019
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SquareStone implements Icon {
    Color color;
    int size;
    int num_stones;

    /**
     * construct a stone given by color and size
     * @param size stone size
     * @param color stone color
     */
    public SquareStone(int size, Color color) {
        this.color = color;
        this.size = size;
    }

    /**
     * Set numbers of stones
     * @param num_stones
     */
    public void setNum_stones (int num_stones) {
        this.num_stones = num_stones;
    }

    /**
     * Get icon height
     * @return integer size
     */
    @Override
    public int getIconHeight() {
        return size;
    }

    /**
     * Get icon width
     * @return integer size
     */
    @Override
    public int getIconWidth() {
        return size;
    }

    /**
     * Paint a stone
     * @param c component
     * @param g graphics
     * @param x integer
     * @param y integer
     */
    public void paintIcon(Component c, Graphics g, int x, int y)  {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y,size + 1,size + 1);
        g2.setColor(color);
        g2.fill(rect);
    }
}
