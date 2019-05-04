/**
 * This MancalaDataModel class works as a model in MVC pattern
 * It contains all the number of stones data that would be updated by the users interactions and notify the change to View
 */

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.event.*;

public class MancalaDataModel
{
    private static final int TOTAL_PITS = 14;

    private int[] data;
    private int[] prevData; // Keeps data of previous
    private ArrayList<ChangeListener> listeners;
    private boolean isGameOver;
    private boolean extraTurnA;
    private boolean extraTurnB;

    private int undoNumA;
    private int undoNumB;
    private boolean isUndo;

    private static final int PLAYER_A = 1;
    //private static final int PLAYER_B = 2;
    private int player = PLAYER_A;

    /**
     * Construct model
     */
    public MancalaDataModel()
    {
        // current data
        data = new int[TOTAL_PITS];
        for (int i=0; i<TOTAL_PITS; i++)
        {
            data[i] = 0;
        }

        // previous data
        prevData = new int[TOTAL_PITS];
        for (int i=0; i<TOTAL_PITS; i++)
        {
            prevData[i] = 0;
        }

        listeners = new ArrayList<ChangeListener>();

        // default game over is false
        isGameOver = false;

        // default extra turns are false
        extraTurnA = false;
        extraTurnB = false;

        // initial undo numbers are 0
        undoNumA = 0;
        undoNumB = 0;

        // default undo is false
        isUndo = false;

        // default player is A
        player = PLAYER_A;

    }

    /**
     * Get data
     * @return integer array of data
     */
    public int[] getData()
    {
        int[] copyData = new int[TOTAL_PITS];
        for(int i=0; i<TOTAL_PITS; i++)
        {
            copyData[i] = data[i];
        }
        return copyData;
    }

    /**
     * Get previous data
     * @return integer array of previous data
     */
    public int[] getPrevData()
    {
        int[] copyPrevData = new int[TOTAL_PITS];
        for(int i=0; i<TOTAL_PITS; i++)
        {
            copyPrevData[i] = prevData[i];
        }
        return copyPrevData;
    }

    /**
     * Initially display all the stones to pits
     * @param initialValue
     */
    public void initialStones(int initialValue)
    {
        for (int i=0; i<TOTAL_PITS; i++)
        {
            // don't put pits in players A and B mancalas
            if((i != 6) || (i != 13))
            {
                data[i] = initialValue;
            }
            // set 0 stones in A and B mancala
            if( (i==6) || (i==13) )
            {
                data[i] = 0;
            }
        }

        for (int i=0; i<TOTAL_PITS; i++)
        {
            // don't put pits in players A and B mancalas for previous data as well
            if((i != 6) || (i != 13))
            {
                prevData[i] = initialValue;
            }
            // set 0 stones in A and B mancala for previous data as well
            if( (i==6) || (i==13) )
            {
                prevData[i] = 0;
            }
        }
    }

    /**
     * set data and update pits
     * @param a
     */
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

    /**
     * Attach actionListeners
     * @param c
     */
    public void attach(ChangeListener c)
    {
        listeners.add(c);
    }

    /**
     * Update stones in pit A
     * @param initialPit
     */
    public void updateStonesA(int initialPit)
    {
        // replace prevData[] with data[] before updating
        for(int i=0; i<TOTAL_PITS; i++)
        {
            prevData[i] = data[i];
        }

        // initial numbers of stones
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
                if (numStones == 0)
                {
                    // lands in your own pit
                    if (i == 6)
                    {
                        // implement the free turn
                        extraTurnA = true;
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

        // if one side row is empty, game over
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

    /**
     * Update stones in pits B
     * @param initialPit
     */
    public void updateStonesB(int initialPit)
    {
        // replace prevData[] with data[] before updating
        for(int i=0; i<TOTAL_PITS; i++)
        {
            prevData[i] = data[i];
        }

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

            // reset to beginning
            if (index == 13){
                index = -1;
            }

            // Last Stone
            if (stones == 1)
            {
                // lands in your own pit
                if (index == -1)
                {
                    // implement the free turn
                    extraTurnB = true;
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

    /**
     * Determines extra turn for A
     * @return boolean
     */
    public boolean isExtraTurnA()
    {
        return extraTurnA;
    }

    /**
     * Determines extra turn for B
     * @return boolean
     */
    public boolean isExtraTurnB()
    {
        return extraTurnB;
    }

    /**
     * Reset extra turn for A
     */
    public void resetExtraTurnA()
    {
        extraTurnA = false;
    }

    /**
     * Reset extra turn for B
     */
    public void resetExtraTurnB()
    {
        extraTurnB = false;
    }

    /**
     * Check if one side of row is completely empty
     * @return
     */
    public boolean checkForEmptyRow()
    {
        boolean emptyRow = false;
        if (data[0] == 0 && data[1] == 0 && data[2] == 0 && data[3] == 0 && data[4] == 0 && data[5] == 0)
            emptyRow = true;
        if (data[7] == 0 && data[8] == 0 && data[9] == 0 && data[10] == 0 && data[11] == 0 && data[12] == 0)
            emptyRow = true;
        return emptyRow;
    }

    /**
     * Check if game is over
     * @return boolean
     */
    public boolean isGameOver()
    {
        return isGameOver;
    }

    /**
     * Check who is the winner
     * @return integer winner
     */
    public int getWinner()
    {
        // Default: Initialize Player A as winner
        int winner = 1;

        // Calculate Player A Total Stones
        int player1 = 0;
        for(int i=0; i<=6; i++)
        {
            player1 += data[i];
        }

        // Calculate Player B Total Stones
        int player2 = 0;
        for(int j=7; j<=13; j++)
        {
            player2 += data[j];
        }

        // If Player A has less stones than Player B, winner is Player B
        if (player1 < player2)
            winner = 2;

        return winner;
    }

    /**
     * Compare to current board and previous board
     * @return
     */
    public boolean compareBoard()
    {
        return Arrays.equals(data, prevData);
    }

    /**
     * Get undo numbers of A
     * @return integer numbers of undo of A
     */
    public int getUndoNumA()
    {
        return undoNumA;
    }

    /**
     * Ger undo numbers of B
     * @return integer numbers of undo of B
     */
    public int getUndoNumB()
    {
        return undoNumB;
    }

    /**
     * Reset undo number of A to 0
     */
    public void resetUndoNumA()
    {
        undoNumA = 0;
    }
    /**
     * Reset undo number of Bto 0
     */
    public void resetUndoNumB()
    {
        undoNumB = 0;
    }

    /**
     * Increment undo number of A by 1 each time
     */
    public void incUndoNumA()
    {
        undoNumA++;
    }
    /**
     * Increment undo number of B by 1 each time
     */
    public void incUndoNumB()
    {
        undoNumB++;
    }

    /**
     * Check if undo
     * @return boolean
     */
    public boolean isUndo()
    {
        return isUndo;
    }

    /**
     * Set undo
     * @param isUnd boolean
     */
    public void setUndo(boolean isUnd)
    {
        isUndo = isUnd;
    }

    /**
     * Get a player
     * @return integer of player
     */
    public int getPlayer()
    {
        return player;
    }

    /**
     * Set player
     * @param turn player
     */
    public void setPlayer(int turn)
    {
        player = turn;
    }
}