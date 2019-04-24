package MancalaProject;

//import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class Board extends JFrame implements ChangeListener//, MouseListener
{
	private int[] data;
	private MancalaDataModel dataModel;
	private static final int TOTAL_PITS = 14;
	
	public Board(MancalaDataModel dm)
	{
		data = new int[TOTAL_PITS];
		for(int i=0; i<dm.getData().length; i++)
		{
			data[i] = dm.getData()[i]; //should be same size
		}
		//data = dm.getData(); //shallow vs deep copy
		dataModel = dm;
		
		//GUI + ActionListers
		// Home page 1
			// Layout 1 or Layout 2
		
		//GUI + ActionListeners
		// Home page 2
			// 3 or 4 stones
		
		// After user decides, call LayoutManager 
		// LayoutManager l;
		// if ( choice = 1 )
		// { l  = new HorizontalLayout() }
		// else
		// { l  = new VerticalLayout() }
	}

	public void stateChanged(ChangeEvent e) 
	{
		for(int i=0; i<dataModel.getData().length; i++)
		{
			data[i] = dataModel.getData()[i]; //should be same size
		}
		repaint();
	}
}
