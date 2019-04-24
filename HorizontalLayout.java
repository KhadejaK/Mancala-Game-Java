package MancalaProject;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class HorizontalLayout extends JFrame implements BoardLayout
{
	private static final int BASE_HEIGHT =  500;
	private static final int BASE_WIDTH =  800;

	//private MancalaDataModel dataModel; // This is for control// This is for control
	private int[] data; // To get # of stones
	private static final int TOTAL_PITS = 14;
	
	public HorizontalLayout()
	{
		data = new int[TOTAL_PITS];
	}
	
	public void setData(int[] d)
	{
		for(int i=0; i<d.length; i++)
		{
			data[i] = d[i]; //should be same size
		}
	}
	
	public void drawBoard()
	{
		//setLayout(new BorderLayout());
		
		JPanel base = new JPanel();
		base.setLayout(new BorderLayout());
		
		//Mancala A
		//base.add
		
		//Mancala B
		
//		Icon base = new Icon()
//		{
//			public int getIconHeight() { return BASE_HEIGHT; }
//
//			public int getIconWidth() { return BASE_WIDTH; }
//
//			public void paintIcon(Component c, Graphics g, int x, int y) 
//			{
//				Graphics2D g2 = (Graphics2D) g;
//				
//			}
//		};
		
		//add(new JLabel(base));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setVisible(true);
	}
}
