import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.net.*;

public class SquareStone implements Icon {
    Color color;
    int size;
    int num_stones;

    // construct a stone given by color and size
    public SquareStone(int size, Color color) {
        this.color = color;
        this.size = size;
    }

    /*  public SquareStone() {
        this.color = color;
        this.size = size;
    }
*/

    public void setNum_stones (int num_stones) {
        this.num_stones = num_stones;
    }

    @Override
    public int getIconHeight() {
        return size;
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    public void paintIcon(Component c, Graphics g, int x, int y)  {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y,size + 1,size + 1);
        g2.setColor(color);
        g2.fill(rect);
    }

    // button image reference: https://www.coderanch.com/t/344149/java/JButton-Shape
   /* public JButton displayStone() {
        JButton b = new JButton();
        int i = 1;
        try {
            URL yellow = new URL("http://www.wpclipart.com/small_icons/buttons/.cache/button_yellow.png");
            URL orange = new URL("http://www.wpclipart.com/small_icons/buttons/.cache/button_orange.png");
            URL purple = new URL("http://www.wpclipart.com/small_icons/buttons/.cache/button_purple.png");

            b.setIcon(new ImageIcon(yellow));


        } catch (MalformedURLException e) {
            e.printStackTrace();

        }
        return b;

    }*/
}
