package MancalaProject;

/**
 * Creates and displays a round stone, with the given number of stones 
 * 
 * @author Khadeja Khalid
 * @version 1.0 5/4/2019
 */

import java.awt.*;
import javax.swing.Icon;

public class RoundStone implements Icon
{
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;
	private int stones;
	private boolean isMancala;
	
	/**
	 * Get the width of the stone
	 */
    public int getIconWidth() 
    {
        return WIDTH;
    }

    /**
     * Get the height of the stone
     */
    public int getIconHeight() 
    {
        return HEIGHT;
    }
    
    /**
     * Set the number of stones to display
     * @param numStones : number of stones
     */
    public void setNumStones(int numStones)
    {
    	stones = numStones;
    }
    
    /**
     * Check if the given pit is a Mancala
     * @param value : boolean value if it  is a mancala pit or not
     */
    public void mancalaPit(boolean value)
    {
    	isMancala = value;
    }

    /**
     * Displays the stones with the given x and y positions 
     * Displays in a row of 5 for each pit,
     * and in a row of 3 for each mancala
     * 
     * @param c : Component
     * @param g : Graphics
     * @param x : int
     * @param y : int
     */
	public void paintIcon(Component c, Graphics g, int x, int y) 
	{
		Graphics2D g2 = (Graphics2D) g;
		Color myBlue = new Color(39, 58, 86);
		g2.setColor(myBlue);
		
		if(!isMancala)
		{
			int xPos = 20;
			int yPos = 20;
			for(int i=0; i<stones; i++)
			{
				if ( (i%5 != 0) && (i != 0) )
					xPos += 40;
	
				else if (i != 0)
				{
					xPos = 20;
					yPos += 40;
				}
				
				g2.fillOval(xPos, yPos, WIDTH, HEIGHT);
			}
		}
		else
		{
			int xPos = 40;
			int yPos = 50;
			for(int i=0; i<stones; i++)
			{
				if ( (i%3 != 0) && (i != 0) )
					xPos += 60;
	
				else if (i != 0)
				{
					xPos = 40;
					yPos += 60;
				}
				
				g2.fillOval(xPos, yPos, WIDTH, HEIGHT);
			}
		}
	}
}
