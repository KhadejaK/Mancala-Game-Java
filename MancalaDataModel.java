package MancalaProject;

import java.util.ArrayList;
import javax.swing.event.*;

public class MancalaDataModel 
{
	private int[] data; 
	private int[] prevData; // Keeps data of previous 
	private ArrayList<ChangeListener> listeners;
	private static final int TOTAL_PITS = 14;
	
	//Constructor
	public MancalaDataModel(int initialStones)
	{
		for (int i=0; i<TOTAL_PITS; i++)
		{
			if((i != 6) || (i != 13))
			{
				data[i] = initialStones;
			}
			if( (i==6) || (i==13) )
			{
				data[i] = 0;
			}
		}
		
		listeners = new ArrayList<ChangeListener>();
	}
	
	public int[] getData()
	{
		int[] copyData = new int[TOTAL_PITS];
		for(int i=0; i<TOTAL_PITS; i++)
		{
			copyData[i] = data[i];
		}
		return copyData;
	}
	
	public void setData(int[] a)
	{
		for(int i=0; i<a.length; i++)
		{
			data[i] = a[i]; //should be same size
		}
	}
	
	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}
	
	public void updateStones(int initialPit)
	{
		// Starting at given pit, move stones 
		// for loop starting at initial pit 
		// If the last stone you drop is your own Mancala, you get a free turn
		// If the last stone you drop is in an empty pit on your side, 
		//   you get to take that stone and all of your opponents stones 
		//   that are in the opposite pit.
		//    - One stone, any empty pit on your side, take your stone and
		//       opponents stones in opposite side and put in your Mancala
		// Skip opponents Mancala
		// The game ends when all six pits on one side of the Mancala board are empty
		
		//Notify
		for (ChangeListener l : listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}

}
