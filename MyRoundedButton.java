
import java.awt.*;
import javax.swing.JButton;

public class MyRoundedButton extends JButton {
    private int height;
    private int width;

    public MyRoundedButton(int w, int h) {
        height = h;
        width = w;
        setContentAreaFilled(false);
    }

    public void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color darkBlue = new Color(0, 41, 63);
        g2.setColor(darkBlue);
        g2.drawRoundRect(10, 10, width + 10, height + 10, 40, 40);
        g2.drawRoundRect(11, 11, width + 10, height + 10, 40, 40);
        g2.drawRoundRect(12, 12, width + 10, height + 10, 40, 40);
        g2.drawRoundRect(12, 12, width + 11, height + 11, 40, 40);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color lightBlue = new Color(145, 208, 242);
        Color lighterBlue = new Color(186, 230, 255);

        if (getModel().isArmed() && (height <= 200))
            g2.setColor(lighterBlue);
        else
            g2.setColor(lightBlue);

        g2.fillRoundRect(10, 10, width + 10, height + 10, 40, 40);

        super.paintComponent(g2);

    }
}