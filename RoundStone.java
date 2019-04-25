package MancalaProject;

import java.awt.*;

import javax.swing.Icon;

public class RoundStone implements Icon
{
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;
	private int stones;
	private boolean isMancala;
	
    public int getIconWidth() 
    {
        return WIDTH;
    }

    public int getIconHeight() 
    {
        return HEIGHT;
    }
    
    public void setNumStones(int numStones)
    {
    	stones = numStones;
    }
    
    public void mancalaPit(boolean value)
    {
    	isMancala = value;
    }

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
