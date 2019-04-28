import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class VerticalLayout extends JComponent implements BoardLayout
{
    // Methods shared by both layout1 and layout2

    MancalaDataModel data_model;
    private int[] data;
    private Color black = Color.BLACK;
    private Color pink = Color.PINK;
    private Color orange = Color.ORANGE;
    private Color white = Color.yellow;
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

        // create A pits
        for (int i = 0; i <= 5; i++) {
            JButton button = new JButton();
            buttons.add(button);
            button.setBackground(pink);
            button.setOpaque(true);
            button.setLayout(new FlowLayout());
            button.setBorder(new LineBorder(black));
           // button.darkShadow();

            final int pit = i;
            // add actionListener to all the A pits
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("ActionListener of As.");
                    // update stone at a pit
                    System.out.println(pit);
                    data_model = new MancalaDataModel();
                    data_model.updateStones(pit);
                    //button.repaint();


                    // if player A ,works otherwise don't work
                }
            });

        }
        // create B pits
        for (int i = 0; i <= 5; i++) {
            JButton button = new JButton();
            buttons.add(button);
            button.setBackground(orange);
            button.setOpaque(true);
            button.setLayout(new FlowLayout());
            button.setBorder(new LineBorder(black));

            final int pit = i + 6;
            // add actionListener to all the Bs
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("ActionListener of Bs");
                    System.out.println(pit);
                    data_model = new MancalaDataModel();
                    data_model.updateStones(pit);
                    //button.repaint();

                    // if user B, it works, otherwise, not working

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


        // a panel with A1-B6
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
        JButton mancala_A = new JButton("Mancala A");
        mancala_A.setFont(new Font("Arial", Font.ITALIC, 30));
        JButton mancala_B = new JButton("Mancala B");
        mancala_B.setFont(new Font("Arial", Font.ITALIC, 30));
        mancala_A.setBackground(white);
        mancala_A.setOpaque(true);
        mancala_A.setPreferredSize(new Dimension(20, 70));
        mancala_A.setLayout(new FlowLayout());
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
        // undo button actionListener
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // dataModel.update();
            }
        });


        // the most outside box in the design
        JPanel layout_panel = new JPanel();
        // set the outer layout panel as box layout
        layout_panel.setLayout(new BorderLayout());
        // add pits_panel and undo button to board base
        layout_panel.add(board_panel, BorderLayout.CENTER);
        layout_panel.add(undo, BorderLayout.SOUTH);


        // new stones
        Color stone_color = Color.GRAY;
        int stone_size = 12;

        int stone_in_A = data[6];
        for (int i = 0; i < stone_in_A; i++) {
           mancala_A.add(new JLabel(new SquareStone(stone_size, stone_color)));
//         mancala_A.add(new SquareStone().displayStone());
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

    }

    // player 1 is 1, and player 2 is 2
}