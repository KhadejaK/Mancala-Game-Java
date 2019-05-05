package MancalaProject;

/**
 * Creates and displays a rounded rectangle for a JButton 
 * 
 * @author Khadeja Khalid
 * @version 1.0 5/4/2019
 */

import java.awt.*;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MyRoundedButton extends JButton
{
	private int height;
	private int width;
	
	/**
	 * Constructs a MyRoundedButton with the given width and height
	 * 
	 * @param w : width
	 * @param h : height
	 */
	public MyRoundedButton(int w, int h)
	{
		height = h;
		width = w;
		setContentAreaFilled(false);
	}
	
	/**
	 * Paints the border of a rounded Jbutton
	 * The color is a dark blue
	 */
	public void paintBorder(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		Color darkBlue = new Color(0, 41, 63);
		g2.setColor(darkBlue);
		g2.drawRoundRect(10, 10, width+10, height+10, 40, 40);
		g2.drawRoundRect(11, 11, width+10, height+10, 40, 40);
		g2.drawRoundRect(12, 12, width+10, height+10, 40, 40);
		g2.drawRoundRect(12, 12, width+11, height+11, 40, 40);
	}
	
	/**
	 * Paints a rounded JButton
	 * The color is a light blue originally
	 * The color is a lighter blue when pressed
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		Color lightBlue = new Color(145, 208, 242);
		Color lighterBlue = new Color(186, 230, 255);
		
		if(getModel().isArmed() && (height <= 200))
			g2.setColor(lighterBlue);
		else
			g2.setColor(lightBlue);
		
		g2.fillRoundRect(10, 10, width+10, height+10, 40, 40);
		
		super.paintComponent(g2);
	}
}
