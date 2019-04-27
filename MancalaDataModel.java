

import java.util.ArrayList;
import javax.swing.event.*;

public class MancalaDataModel 
{
	private int[] data; 
	//private int[] prevData = new int[data.length]; // Keeps data of previous 
	private ArrayList<ChangeListener> listeners;
	private static final int TOTAL_PITS = 14;
	
	//Constructor
	public MancalaDataModel()
	{
		data = new int[TOTAL_PITS];
		for (int i=0; i<TOTAL_PITS; i++)
		{
			data[i] = 0;
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
	
	public void initialStones(int initialValue)
	{
		for (int i=0; i<TOTAL_PITS; i++)
		{
			if((i != 6) || (i != 13))
			{
				data[i] = initialValue;
			}
			if( (i==6) || (i==13) )
			{
				data[i] = 0;
			}
		}
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
		// replace prevData[] with data[] before updating

		int numStones = data[initialPit];
		int stones = numStones;
		// remove all the stones in the pit
		data[initialPit] = 0;

		// update required pits 
		for (int i = initialPit+1; i < stones+initialPit+1; i++) {
			if (numStones != 0) {
				// skips opponents pit
				if (i == 13) {
					i = -1;
					continue;
				}
				
				data[i]++;
				numStones--;
	
				// Last Stone
				if (numStones == 0) {
					// lands in your own pit 
					if (i == 6) {
						// implement the free turn
					}
					// pit on your side is empty
					if (i >= 0 && i < 6 && data[i] == 1) {
						data[i] = 0;
						int opponentPit = 0;
						switch (i) {
							case 0: opponentPit = data[12];
								data[12] = 0; break;
							case 1: opponentPit = data[11];
								data[11] = 0; break;
							case 2: opponentPit = data[10];
								data[10] = 0; break; 
							case 3: opponentPit = data[9];
								data[9] = 0; break;
							case 4: opponentPit = data[8];
								data[8] = 0; break;
							case 5: opponentPit = data[7];
								data[7] = 0; break;
							default:
						}
						data[6]+= opponentPit + 1;
					}
				}
			}
		}
		
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
	
	public boolean checkForEmptyRow() {
		boolean emptyRow = false;
		if (data[0] == 0 && data[1] == 0 && data[2] == 0 && data[3] == 0 && data[4] == 0 && data[5] == 0)
			emptyRow = true;
		if (data[7] == 0 && data[8] == 0 && data[9] == 0 && data[10] == 0 && data[11] == 0 && data[12] == 0)
			emptyRow = true;
		return emptyRow;
	}

}
