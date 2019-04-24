import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.event.*;

public class Board extends JFrame implements ChangeListener
{
    public Board(MancalaDataModel m)
    {
        //GUI + ActionListers
        // Home page 1
        // Layout 1 or Layout 2

        //GUI + ActionListeners
        // Home page 2
        // 3 or 4 stones

        // After user decides, call LayoutManager
        // if ( choice = 1 )
        // { LayoutManager l  = new HorizontalLayout() }
    }

    @Override
    public void stateChanged(ChangeEvent e)
    {
        // TODO Auto-generated method stub

    }

}
