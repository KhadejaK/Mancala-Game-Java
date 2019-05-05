package MancalaProject;

/**
 * This class is the control/view of the MVC Pattern, as well as the context of the Strategy Pattern
 * It contains a pop-up home page frame to let users to choose layout and number of stones (control/view)
 * It contains the gameOver screen function which would display the winner and gameOver screen (view)
 * It contains displayBoard function to display both vertical and horizontal layout Mancala boards (control/view/context)
 * It states changed to both layout and model then repaint the pits in the board
 * 
 * @author Khadeja Khalid, Yu Xiu
 * @version 1.0 5/4/2019
 * 
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class Board extends JFrame implements ChangeListener
{
    private int[] data;
    private MancalaDataModel dataModel;
    private BoardLayout layout;
    private static final int TOTAL_PITS = 14;
    private static final int PLAYER_A = 1;
    private static final int PLAYER_B = 2;

    /**
     * Constructs the Board with dafault values
     * @param dm
     */
    public Board(MancalaDataModel dm)
    {
        dataModel = dm;

        //Initial Values if they weren't selected (# stones & layout)
        dataModel.initialStones(3);
        layout = new HorizontalLayout(dataModel);

        //Initialize data
        data = new int[TOTAL_PITS];
        for(int i=0; i<dm.getData().length; i++)
        {
            data[i] = dm.getData()[i]; //should be same size
        }
    }

    /**
     * stateChanged
     * Get's the updated data from the DataModel class and repaints the stones
     * Decides who's turn it is by checking if there was an extra turn or undo
     * @param e
     */
    public void stateChanged(ChangeEvent e)
    {
        for(int i=0; i<dataModel.getData().length; i++)
        {
            data[i] = dataModel.getData()[i]; //should be same size
        }

        layout.setData(data);
        layout.repaintStones();

        // Switch the players
        if (dataModel.getPlayer() == PLAYER_A)
        {
            // if player is A and A has free turn, don't switch player
            if (dataModel.isExtraTurnA())
            {
                if (!dataModel.isUndo())
                {
                    // reset undo number is B
                    dataModel.resetUndoNumB();
                }

            }
            // if A doesn't have extra turn and not in undo then player goes to B
            else if(!dataModel.isExtraTurnA())
            {
                if (!dataModel.isUndo())
                {
                    dataModel.setPlayer(PLAYER_B);
                    dataModel.resetUndoNumB();
                }
            }
        }
        // if player is B and B has extra turn and B does not undo, player is still B
        else if (dataModel.getPlayer() == PLAYER_B)
        {
            if (dataModel.isExtraTurnB()) // Don't switch player
            {
                if (!dataModel.isUndo())
                {
                    // reset undo number of A
                    dataModel.resetUndoNumA();
                }
            }
            // otherwise, B is doesn't have extra turn and not undo, player goes to A
            else if(!dataModel.isExtraTurnB())
            {
                if (!dataModel.isUndo())
                {
                    dataModel.setPlayer(PLAYER_A);
                    dataModel.resetUndoNumA();
                }
            }
        }

        // If the pits on one side is empty, end the game
        if (dataModel.isGameOver())
            gameOverScreen(dataModel.getWinner());

        // get whose turn
        layout.whosTurn(dataModel.getPlayer());
    }

    /**
     * Display a Mancala Game board with certain layout
     */
    public void displayBoard()
    {
        layout.setData(data);
        layout.drawBoard();
    }

    /**
     * When one side of pits is empty, a game over screen would be popped up with the player who has most stones
     * in his mancala
     * @param winner : player A or B, as 1 or 2
     */
    public void gameOverScreen(int winner)
    {
        // a game over frame
        JFrame frame = new JFrame("Game Over");
        frame.setSize(600, 300);
        frame.setLayout(new BorderLayout());

        JTextArea gameOver = new JTextArea();
        gameOver.setEditable(false);

        // if winner is A
        if(winner == 1)
            gameOver.setText("Game Over! \nWinner: Player A");
        // winner is B
        else if (winner == 2)
            gameOver.setText("Game Over! \nWinner: Player B");

        gameOver.setFont(new Font("Serif", Font.BOLD, 50));

        frame.add(gameOver, BorderLayout.CENTER);

        // a close button
        JButton close = new JButton("Close");
        close.setFont(new Font("Serif", Font.BOLD, 30));
        close.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
                frame.dispose();
            }
        });
        frame.add(close, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * When the game begins, a homepage would pop up and provide layouts and chooses of number of stones
     * let users to choose, and it also add actionListeners for those choices buttons
     */
    public void homepage()
    {
        Color boardColor = new Color(66, 244, 179);
        Color buttonColor = new Color(212, 252, 237);

        // home page frame
        JFrame frame = new JFrame("Mancala Homepage");
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());

        // a base panel
        JPanel base = new JPanel();
        base.setLayout(new BorderLayout());
        base.setSize(500, 600);

        // a top panel
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.setSize(500, 200);
        top.setBackground(boardColor);
        top.setOpaque(true);

        // 3 stones choice
        JButton threeStones = new JButton("3 Stones");
        threeStones.setFont(new Font("Serif", Font.BOLD, 70));
        threeStones.setHorizontalAlignment(SwingConstants.CENTER);
        threeStones.setVerticalAlignment(SwingConstants.CENTER);
        threeStones.setBackground(buttonColor);

        threeStones.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dataModel.initialStones(3);
                data = dataModel.getData();
            }
        });
        top.add(threeStones);

        // 4 stones choice
        JButton fourStones = new JButton("4 Stones");
        fourStones.setFont(new Font("Serif", Font.BOLD, 70));
        fourStones.setHorizontalAlignment(SwingConstants.CENTER);
        fourStones.setVerticalAlignment(SwingConstants.CENTER);
        fourStones.setBackground(buttonColor);

        fourStones.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dataModel.initialStones(4);
                data = dataModel.getData();
            }
        });
        top.add(fourStones);

        // add top to base
        base.add(top, BorderLayout.NORTH);

        // a mid panel
        JPanel mid = new JPanel();
        mid.setLayout(new FlowLayout());
        mid.setSize(500, 200);
        mid.setBackground(boardColor);
        mid.setOpaque(true);

        // horizontal board choice
        JButton horiLayout = new JButton("Horizontal");
        horiLayout.setFont(new Font("Serif", Font.BOLD, 70));
        horiLayout.setHorizontalAlignment(SwingConstants.CENTER);
        horiLayout.setVerticalAlignment(SwingConstants.CENTER);
        horiLayout.setBackground(buttonColor);

        horiLayout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                layout = new HorizontalLayout(dataModel);
            }
        });
        mid.add(horiLayout);

        // vertical board choice
        JButton vertLayout = new JButton("Vertical");
        vertLayout.setFont(new Font("Serif", Font.BOLD, 70));
        vertLayout.setHorizontalAlignment(SwingConstants.CENTER);
        vertLayout.setVerticalAlignment(SwingConstants.CENTER);
        vertLayout.setBackground(buttonColor);

        vertLayout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                layout = new VerticalLayout(dataModel);
            }
        });
        mid.add(vertLayout);

        base.add(mid, BorderLayout.CENTER);

        // a bot panel
        JPanel bot = new JPanel();
        bot.setLayout(new FlowLayout());
        bot.setSize(400, 200);
        bot.setBackground(boardColor);
        bot.setOpaque(true);

        // a done button
        JButton done = new JButton("Done!");
        done.setFont(new Font("Serif", Font.BOLD, 40));
        done.setHorizontalAlignment(SwingConstants.CENTER);
        done.setVerticalAlignment(SwingConstants.CENTER);
        done.setBackground(buttonColor);

        // add actionListener to done button
        done.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayBoard();
            }
        });
        bot.add(done);

        // add bot to base
        base.add(bot, BorderLayout.SOUTH);

        frame.add(base, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}