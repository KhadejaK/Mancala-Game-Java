import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerticalLayout extends JComponent implements LayoutManager
{
    // Methods shared by both layout1 and layout2

    private MancalaDataModel model;
    private static final int base_Height = 800;
    private static final int base_Width = 500;


    public VerticalLayout() {
        // the most outside box in the design
        JPanel layout_panel = new JPanel();
        // set the outer layout panel as box layout
        layout_panel.setLayout(new  BoxLayout(layout_panel, BoxLayout.Y_AXIS));

        // the second largest box in the design
        JPanel board_base = new JPanel();
        // set the base of vertical Mancala board as BoxLayout
        board_base.setLayout(new BorderLayout());

        // the panel holds all the buttons
        JPanel pits_panel = new JPanel();
        // set pits panel as boarder Layout 6 rows and 2 columns
        pits_panel.setLayout(new GridLayout(6,2));


        JButton undo = new JButton("Undo");
        JButton mancala_B = new JButton("Mancala B");
        JButton mancal_A = new JButton("Mancala A");
        JButton A1 = new JButton("A1");
        JButton B6 = new JButton("B6");
        JButton A2 = new JButton("A2");
        JButton B5 = new JButton("B5");
        JButton A3 = new JButton("A3");
        JButton B4 = new JButton("B4");
        JButton A4 = new JButton("A4");
        JButton B3 = new JButton("B3");
        JButton A5 = new JButton("A5");
        JButton B2 = new JButton("B2");
        JButton A6 = new JButton("A6");
        JButton B1 = new JButton("B1");

        pits_panel.add(A1);
        pits_panel.add(A2);
        pits_panel.add(A3);
        pits_panel.add(A4);
        pits_panel.add(A5);
        pits_panel.add(A6);
        pits_panel.add(B1);
        pits_panel.add(B2);
        pits_panel.add(B3);
        pits_panel.add(B4);
        pits_panel.add(B5);
        pits_panel.add(B6);



        // add pits_panel and undo button to board base
        layout_panel.add(board_base);
        layout_panel.add(undo);

        board_base.add(pits_panel, BorderLayout.CENTER);
        // add mancala B on the top and mancala A on the bottom of the board base
        board_base.add(mancal_A,BorderLayout.SOUTH);
        board_base.add(mancala_B, BorderLayout.NORTH);

        // undo button actionListener
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do something
            }
        });

        // add actionListeners to all buttons


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(layout_panel);
        frame.pack();
        frame.setVisible(true);

    }

/**    public int set_base_size(int base_Height, int base_Width) {
        int x = base_Height;
        int y = base_Width;

    }
*/


    public void temp(){

    }

    public void stateChanged(ChangeEvent e) {

    }





























    /**
     * determine the minimum size of container
     * @param parent the container needs to set min size
     * @return
     */
 /*   public Dimension minimumLayoutSize(Container parent){
        int x;
        int y;

    }
*/
    /**
     * determine the preferred size for container
     * @param parent the container
     * @return
     */
 /*   public Dimension preferredLayoutSize(Container parent) {

    }
*/
    /**
     * lays out the components in container
     * @param parent the container
     */
 /*   public void layoutComponent(Container parent){

    }

    // do-nothing method
    public void addLayoutComponent(String name, Component comp){

    }
    // do-nothing method
    public void removeLayoutComponent(Component comp) {

    }
*/
}
