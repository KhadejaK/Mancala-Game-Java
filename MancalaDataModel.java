package MancalaProject;

import java.util.ArrayList;
import javax.swing.event.*;

public class MancalaDataModel 
{
	private int[] data; 
	private ArrayList<ChangeListener> listeners;
	private static final int TOTAL_PITS = 14;
	
	public MancalaDataModel(int initialStones)
	{
		for (int i=0; i<TOTAL_PITS; i++)
		{
			if((i != 6) && (i != 13))
			{
				data[i] = initialStones;
			}
		}
		
		listeners = new ArrayList<ChangeListener>();
	}
	
	public int[] getData()
	{
		return data;
	}
	
	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}
	
	public void updateStones(int initialPit)
	{
		// Starting at given pit, move stones 
		// for loop starting at initial pit 
		
		//Notify
		for (ChangeListener l : listeners)
      {
         l.stateChanged(new ChangeEvent(this));
      }
	}

}
