package MancalaProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import javax.swing.event.*;

public class MancalaDataModel 
{
	private static final int TOTAL_PITS = 14;
	
	private int[] data; 
	private int[] prevData; // Keeps data of previous 
	private ArrayList<ChangeListener> listeners;
	private boolean isGameOver;
	private boolean extraTurn;
	private Stack<int[]> undoStack;
	private int undoNum;
	private boolean isUndo;
	
	//Constructor
	public MancalaDataModel()
	{
		data = new int[TOTAL_PITS];
		for (int i=0; i<TOTAL_PITS; i++)
		{
			data[i] = 0;
		}
		
		prevData = new int[TOTAL_PITS];
		for (int i=0; i<TOTAL_PITS; i++)
		{
			prevData[i] = 0;
		}
		
		listeners = new ArrayList<ChangeListener>();
		
		isGameOver = false;
		extraTurn = false;
		
		undoStack = new Stack<int[]>();
		undoNum = 0;
		isUndo = false;
		
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
	
	public int[] getPrevData()
	{
//		int[] copyPrevData = new int[TOTAL_PITS];
//		for(int i=0; i<TOTAL_PITS; i++)
//		{
//			copyPrevData[i] = prevData[i];
//		}
//		return copyPrevData;
		
		if (undoStack.size() != 0)
			return undoStack.pop();
		else
			return getData();
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
	
	public void setDataAndUpdate(int[] a)
	{
		for(int i=0; i<a.length; i++)
		{
			data[i] = a[i]; //should be same size
		}
		
		// Notify Action Listeners
		for (ChangeListener l : listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}

	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}
	
	public void updateStonesA(int initialPit)
	{
		// replace prevData[] with data[] before updating
		for(int i=0; i<TOTAL_PITS; i++)
		{
			prevData[i] = data[i];
		}
		undoStack.push(prevData);

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
						extraTurn = true;
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
		
		if(checkForEmptyRow())
			isGameOver = true;
		
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
	
	public void updateStonesB(int initialPit)
	{
		// replace prevData[] with data[] before updating
		for(int i=0; i<TOTAL_PITS; i++)
		{
			prevData[i] = data[i];
		}
		undoStack.push(prevData);

		int numStones = data[initialPit];
		int stones = numStones;
		
		// remove all the stones in the pit
		data[initialPit] = 0;

		// update required pits 
		int index = initialPit+1;
		for (int i = 0; i < numStones; i++) 
		{
			// skips opponent A's pit
			if (index == 6) {
				index = 7;
			}
			
			data[index]++;
			//numStones--; //Handled with for loop
			
			// reset to beginning
			if (index == 13){
				index = -1;
			}
			
			// Last Stone
			if (stones == 1) 
			{
				// lands in your own pit 
				if (index == -1) {
					// implement the free turn
					extraTurn = true;	
				}
				// pit on your side is empty
				if (index >= 7 && index < 13 && data[index] == 1) 
				{
					data[index] = 0;
					int opponentPit = 0;
					switch (index) {
						case 7: 
							opponentPit = data[5];
							data[5] = 0; break;
						case 8: 
							opponentPit = data[4];
							data[4] = 0; break;
						case 9: 
							opponentPit = data[3];
							data[3] = 0; break; 
						case 10: 
							opponentPit = data[2];
							data[2] = 0; break;
						case 11: 
							opponentPit = data[1];
							data[1] = 0; break;
						case 12: 
							opponentPit = data[0];
							data[0] = 0; break;
						default:
					}
					data[13]+= opponentPit + 1;
				}
			}
			index++;
			stones--;
		}
		
		if(checkForEmptyRow())
			isGameOver = true;
		
		//Notify
		for (ChangeListener l : listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	}
	
	public boolean isExtraTurn()
	{
		return extraTurn;
	}
	
	public void resetExtraTurn()
	{
		extraTurn = false;
	}
	
	public boolean checkForEmptyRow() 
	{
		boolean emptyRow = false;
		if (data[0] == 0 && data[1] == 0 && data[2] == 0 && data[3] == 0 && data[4] == 0 && data[5] == 0)
			emptyRow = true;
		if (data[7] == 0 && data[8] == 0 && data[9] == 0 && data[10] == 0 && data[11] == 0 && data[12] == 0)
			emptyRow = true;
		return emptyRow;
	}
	
	public boolean isGameOver()
	{
		return isGameOver;
	}
	
	public int getWinner()
	{
		int winner = 1;
		
		if (data[6] < data[13])
			winner = 2;
		
		return winner;
	}
	
	public boolean compareBoard()
	{
		if(undoStack.size() != 0)
		{
			return Arrays.equals(data, undoStack.peek());
		}
		else
			return false;
	}
	
	public int getUndoNum()
	{
		return undoNum;
	}
	
	public void resetUndoNum()
	{
		undoNum = 0;
	}
	
	public void incUndoNum()
	{
		undoNum++;
	}
	
	public boolean isUndo()
	{
		return isUndo;
	}
	public void setUndo(boolean isUnd)
	{
		isUndo = isUnd;
	}
}
