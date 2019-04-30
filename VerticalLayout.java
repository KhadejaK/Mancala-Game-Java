
/**
 * This is a concrete vertical layout out of the layout manger pattern
 * It displays a vertical layout of the mancala game with the JComponent
 * and implements the actionListener and repaint stones in each pit afer
 * stones numbers are changed, and determines who is current and next
 * player.
 */
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class VerticalLayout extends JComponent implements BoardLayout
{
    private MancalaDataModel data_model;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private JButton mancala_A;
    private JButton mancala_B;
    private JLabel message;

    private int[] data;
    private Color black = Color.BLACK;
    private Color pink = Color.PINK;
    private Color orange = Color.ORANGE;
    private Color white = Color.yellow;
    static final int NUM_PITS = 14;
    private int player;

    /**
     * Construct a Vertical Layout object
     * @param d data model object
     */
    public VerticalLayout(MancalaDataModel d) {
        this.data_model = d;
        //reference : https://stackoverflow.com/questions/1065691/how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace();
        }
        data = new int[NUM_PITS];
    }

    /**
     * set data for vertical layout
     * @param data int[] of data
     */
    public void setData(int[] data) {
        for (int i = 0; i < NUM_PITS; i++) {
            this.data[i] = data[i];
        }
    }

    /**
     * draw mancala board
     */
    public void drawBoard() {

        // create new buttons A1-A6, B1-B6
        // create A pits; player 1
        for (int i = 0; i <= 5; i++) {
            JButton button = new JButton();
            buttons.add(button);
            button.setBackground(pink);
            button.setOpaque(true);
            button.setLayout(new FlowLayout());
            button.setBorder(new LineBorder(black));

            // vertical layout button array index
            final int indexOfPitInButtons = i;
            // data model array index
            final int indexOfPitInDataModel = i;

            // add actionListener to all the A pits
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // if player A, works; otherwise doesn't work
                    if (player == 1) {
                        System.out.println("ActionListener of A: button = " + indexOfPitInButtons + ", update data = "
                                + indexOfPitInDataModel);
                        // update stones in pit
                        data_model.updateStonesA(indexOfPitInDataModel);
                    }
                }
            });

        }

        // create B pits; player 2
        for (int i = 0; i <= 5; i++) {
            JButton button = new JButton();
            buttons.add(button);
            button.setBackground(orange);
            button.setOpaque(true);
            button.setLayout(new FlowLayout());
            button.setBorder(new LineBorder(black));

            // map vertical layout pit index to data model index
            final int indexOfPitInButtons = i + 6;
            // 18-(i + 6) = 12 - i
            final int indexOfPitInDataModel = 12 - i;

            // add actionListener to all the Bs
            // when action event occurs, update stones in B pits
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //button.repaint();
                    // if user B, it works, otherwise, not working
                    if (player == 2) {
                        System.out.println("ActionListener of B: button = " + indexOfPitInButtons + ", update data = "
                                + indexOfPitInDataModel);
                        data_model.updateStonesB(indexOfPitInDataModel);
                    }
                }
            });
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


        // a panel display A1-B6 characters
        JPanel names_panel_A = new JPanel();
        names_panel_A.setPreferredSize(new Dimension(100,100));
        names_panel_A.setLayout(new GridLayout(6,1));
        for (int i = 1; i <= 6; i++) {
            JLabel label = new JLabel("A" + i, SwingConstants.CENTER);
            label.setFont(new Font(label.getName(), Font.PLAIN, Math.min(20, 20)));

            names_panel_A.add(label);
        }
        JPanel names_panel_B = new JPanel();
        names_panel_B.setPreferredSize(new Dimension(100,100));
        names_panel_B.setLayout(new GridLayout(6,1));
        for (int i = 1; i <= 6; i++) {
            JLabel label = new JLabel("B" + i,SwingConstants.CENTER);
            label.setFont(new Font(label.getName(), Font.PLAIN, Math.min(20, 20)));

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

        // mancala pits A and B
        mancala_A = new JButton("Mancala A");
        mancala_A.setFont(new Font("Arial", Font.ITALIC, 30));
        mancala_A.setBackground(white);
        mancala_A.setOpaque(true);
        mancala_A.setPreferredSize(new Dimension(20, 70));
        mancala_A.setLayout(new FlowLayout());

        mancala_B = new JButton("Mancala B");
        mancala_B.setFont(new Font("Arial", Font.ITALIC, 30));
        mancala_B.setBackground(white);
        mancala_B.setOpaque(true);
        mancala_B.setPreferredSize(new Dimension(20, 70));
        mancala_B.setLayout(new FlowLayout());

        // the second largest box in the design
        JPanel board_panel = new JPanel();
        // set the base of vertical Mancala board as BoxLayout
        board_panel.setLayout(new BorderLayout());

        // add mancala B on the top and mancala A on the bottom of the board base
        board_panel.add(mancala_A, BorderLayout.SOUTH);
        board_panel.add(mancala_B, BorderLayout.NORTH);
        board_panel.add(pits_and_names_panel, BorderLayout.CENTER);


        // undo button
        JButton undo = new JButton("Undo");
        undo.setPreferredSize(new Dimension(50,30));
        undo.setFont(new Font("Lucida", Font.TYPE1_FONT, 20));

        // TODO: undo button
        // undo button actionListener
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // dataModel.update();
            }
        });

        // display a label on the to to declare player's turn
        message = new JLabel();
        // player 1 is default player
        whosTurn(1);

        // the most outside box in the design
        JPanel layout_panel = new JPanel();
        // set the outer layout panel as box layout
        layout_panel.setLayout(new BorderLayout());
        // add pits_panel and undo button to board base
        layout_panel.add(message, BorderLayout.NORTH);
        layout_panel.add(board_panel, BorderLayout.CENTER);
        layout_panel.add(undo, BorderLayout.SOUTH);

        // draw all stones
        repaintStones();

        // frame
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(1000,800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(layout_panel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * repaint stones in pits
     */
    public void repaintStones() {
        System.out.println("V layout repaint stones: " + Arrays.toString(data));

        // stones
        Color stone_color = Color.GRAY;
        int stone_size = 12;

        // repaint mancala A, index 6
        int stone_in_A = data[6];
        // avoid repeatedly add stones, so remove all the previous added stones, then add new stones
        mancala_A.removeAll();
        for (int i = 0; i < stone_in_A; i++) {
            mancala_A.add(new JLabel(new SquareStone(stone_size, stone_color)));
        }

        // repaint mancala B, index 13
        int stone_in_B = data[13];
        // avoid repeatedly add stones, so remove all the previous added stones, then add new stones
        mancala_B.removeAll();
        for (int i = 0; i < stone_in_B; i++) {
            mancala_B.add(new JLabel(new SquareStone(stone_size, stone_color)));
        }

        // add stones to A's pits
        for (int i = 0; i < 6; i++) {
            JButton button = buttons.get(i);
            int num_stone = data[i];
            // avoid repeatedly add stones, so remove all the previous added stones, then add new stones
            button.removeAll();
            for (int j = 0; j < num_stone; j++) {
                button.add(new JLabel(new SquareStone(stone_size, stone_color)));

            }
        }

        // add stones to B's pits
        for (int i = 7; i < 13; i++) {
            JButton button = buttons.get(18 - i);
            int num_stone = data[i];
            // avoid repeatedly add stones, so remove all the previous added stones, then add new stones
            button.removeAll();
            for (int j = 0; j < num_stone; j++) {
                button.add(new JLabel(new SquareStone(stone_size, stone_color)));
            }
        }
    }

    // player A is 1, and player B is 2

    /**
     * determine who is the current player
     * @param a_player int 1 or 2
     */
    public void whosTurn(int a_player) {
        this.player = a_player;
        message.setText("Now is Player " + player + "'s turn!");
        message.setFont(new Font("Arial", Font.PLAIN, Math.min(25, 25)));
    }
}