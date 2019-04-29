package MancalaProject;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VerticalLayout extends JComponent implements BoardLayout
{
    // Methods shared by both layout1 and layout2

    //private MancalaDataModel model;
    //private static final int STONE_HEIGHT = 2;
    //private static final int STONE_WIDTH = 2;

    SquareStone stone;
    int stone_count = 4;
    int x;
    int y;

    private int[] data;
    static final int NUM_PITS = 14;


    public VerticalLayout() {
        //reference : https://stackoverflow.com/questions/1065691/how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace();
        }

        data = new int[NUM_PITS];
    }



    public void setData(int[] data) {
        for (int i = 0; i < NUM_PITS; i++) {
            this.data[i] = data[i];
        }
    }

    public void drawBoard() {

        // create new buttons A1-A6, B1-B6
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        for (int i = 1; i <= 6; i++) {
            JButton button = new JButton();
            buttons.add(button);
            button.setBackground(Color.PINK);
            button.setOpaque(true);
            button.setLayout(new FlowLayout());
        }
        for (int i = 1; i <= 6; i++) {
            JButton button = new JButton();
            buttons.add(button);
            button.setBackground(Color.YELLOW);
            button.setOpaque(true);
            button.setLayout(new FlowLayout());
        }


        // the panel holds all the buttons
        JPanel pits_panel = new JPanel();
        //pits_panel.setPreferredSize(new Dimension(100, 100));
        // set pits panel as boarder Layout 6 rows and 2 columns
        pits_panel.setLayout(new GridLayout(6,2));
        for (int i = 0; i <= 5; i++) {
            pits_panel.add(buttons.get(i));
            pits_panel.add(buttons.get(i + 6));
        }


        // a panel with A1-B6
        JPanel names_panel_A = new JPanel();
        names_panel_A.setPreferredSize(new Dimension(30,10));
        names_panel_A.setLayout(new GridLayout(6,1));
        for (int i = 1; i <= 6; i++) {
            JLabel label = new JLabel("A" + i);
            label.setSize(new Dimension(50,30));
            names_panel_A.add(label);
        }
        JPanel names_panel_B = new JPanel();
        names_panel_B.setPreferredSize(new Dimension(30,10));
        names_panel_B.setLayout(new GridLayout(6,1));
        for (int i = 1; i <= 6; i++) {
            JLabel label = new JLabel("B" + i);
            label.setSize(new Dimension(50,30));

            names_panel_B.add(label);
        }


        // a base to put pits panel
        JPanel pits_and_names_panel = new JPanel();
        pits_and_names_panel.setLayout(new BorderLayout());
        //pits_and_names_panel.setSize(new Dimension(300,200));
        pits_and_names_panel.setPreferredSize(new Dimension(300,300));

        // add pits
        pits_and_names_panel.add(pits_panel, BorderLayout.CENTER);
        pits_and_names_panel.add(names_panel_A, BorderLayout.WEST);
        pits_and_names_panel.add(names_panel_B, BorderLayout.EAST);




        JButton mancala_A = new JButton("Mancala A");
        JButton mancala_B = new JButton("Mancala B");
        //mancala_B.setPreferredSize(new Dimension(20,20));

        // set pits background color
        mancala_A.setBackground(Color.green);
        mancala_A.setOpaque(true);
        mancala_A.setPreferredSize(new Dimension(50, 50));
        mancala_A.setLayout(new FlowLayout());
        mancala_B.setBackground(Color.green);
        mancala_B.setOpaque(true);
        mancala_B.setPreferredSize(new Dimension(50, 50));
        mancala_B.setLayout(new FlowLayout());


        // the second largest box in the design
        JPanel board_panel = new JPanel();
        // set the base of vertical Mancala board as BoxLayout
        board_panel.setLayout(new BorderLayout());

        // add mancala B on the top and mancala A on the bottom of the board base
        board_panel.add(mancala_A, BorderLayout.SOUTH);
        board_panel.add(mancala_B, BorderLayout.NORTH);
        board_panel.add(pits_and_names_panel, BorderLayout.CENTER);



        JButton undo = new JButton("Undo");
        //undo.setPreferredSize(new Dimension(20,20));


        // the most outside box in the design
        JPanel layout_panel = new JPanel();
        // set the outer layout panel as box layout
        layout_panel.setLayout(new BoxLayout(layout_panel, BoxLayout.Y_AXIS));
        // add pits_panel and undo button to board base
        layout_panel.add(board_panel);
        layout_panel.add(undo);


        // undo button actionListener
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do something
            }
        });

        // add actionListeners to all the pits buttons
        for (int i = 0; i <= 11; i++) {
            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    x = getX();
                    y = getY();

                    for (int i = 0; i <= stone_count; i ++ ) {
                        y = y + buttons.get(i).getHeight();
                    }
                    repaint();
                }
            });

        }


        Color stone_color = Color.BLUE;
        int stone_size = 10;

        int stone_in_A = data[6];
        for (int i = 0; i < stone_in_A; i++) {
            mancala_A.add(new JLabel(new SquareStone(stone_size, stone_color)));
        }
        int stone_in_B = data[13];
        for (int i = 0; i < stone_in_B; i++) {
            mancala_B.add(new JLabel(new SquareStone(stone_size, stone_color)));
        }

        // add stones to A's pits
        for (int i = 0; i < 6; i++) {
            JButton button = buttons.get(i);
            int num_stone = data[i];
            for (int j = 0; j < num_stone; j++) {
                button.add(new JLabel(new SquareStone(stone_size, stone_color)));
            }
        }

        // add stones to B's pits
        for (int i = 7; i < 13; i++) {
            JButton button = buttons.get(i - 1);
            int num_stone = data[i];
            for (int j = 0; j < num_stone; j++) {
                button.add(new JLabel(new SquareStone(stone_size, stone_color)));
            }
        }



        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(1000,800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(layout_panel);
        frame.pack();
        frame.setVisible(true);

    }


    public void stateChanged(ChangeEvent e) {

    }

	public void repaintStones() {
		// TODO Auto-generated method stub
		
	}

	public void whosTurn(int player) {
		// TODO Auto-generated method stub
		
	}

}