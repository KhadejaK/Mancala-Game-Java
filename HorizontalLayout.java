
package MancalaProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class HorizontalLayout extends JFrame implements BoardLayout
{
	private static final int BASE_HEIGHT = 800;
	private static final int BASE_WIDTH =  1800;
	private static final int TOTAL_PITS = 14;

	private int[] data; // To get # of stones
	private MancalaDataModel model;
	private ArrayList<JButton> pits; 
	private int player;
	
	
	public HorizontalLayout(MancalaDataModel dm)
	{
		data = new int[TOTAL_PITS];
		model = dm;
		pits = new ArrayList<JButton>();
		for(int i=0; i<TOTAL_PITS; i++)
		{
			pits.add(i, new JButton());
		}
		player = 1;
	}
	
	public void setData(int[] d)
	{
		for(int i=0; i<d.length; i++)
		{
			data[i] = d[i]; //should be same size
		}
	}
	
	public void drawBoard()
	{
		Color darkBlue = new Color(59, 168, 226);
		
		JFrame frame = new JFrame("Horizontal Mancala");
		frame.setSize(BASE_WIDTH, BASE_HEIGHT);
		
		JPanel base = new JPanel();
		
		base.setBackground(darkBlue);
		base.setOpaque(true);
		
		base.setLayout(new BorderLayout());
		
		// Mancala B
		JPanel mancalaB = new JPanel();
		mancalaB.setLayout(new BorderLayout());
		mancalaB.setSize(200, 800);
		mancalaB.setBackground(darkBlue);
		base.setOpaque(true);
		JLabel labelB = new JLabel("Mancala B");
		labelB.setFont(new Font("Serif", Font.BOLD, 40));
		labelB.setHorizontalAlignment(SwingConstants.CENTER);
		labelB.setVerticalAlignment(SwingConstants.CENTER);
		mancalaB.add(labelB, BorderLayout.NORTH);

		JButton b = new MyRoundedButton(200, 800);
		b.setPreferredSize(new Dimension(200+35, 800));
		mancalaB.add(b, BorderLayout.CENTER);
		
		RoundStone stonesBM = new RoundStone();
		stonesBM.setNumStones(data[13]);
		stonesBM.mancalaPit(true);
		b.setIcon(stonesBM);
		
		pits.set(13, b);
		
		base.add(mancalaB, BorderLayout.WEST);
		
		//Board 
		JPanel board = new JPanel();
		board.setLayout(new GridLayout(4, 6));
		board.setBackground(darkBlue);
		board.setOpaque(true);
		
		// Add B Labels
		for(int i=6; i>0; i--)
		{
			JLabel bLabel = new JLabel("B" + i);
			bLabel.setFont(new Font("Serif", Font.CENTER_BASELINE, 70));
			bLabel.setHorizontalAlignment(SwingConstants.CENTER);
			bLabel.setVerticalAlignment(SwingConstants.CENTER);
			
			board.add(bLabel);
		}
		
		// Add B Pits
		for(int i=12; i>=7; i--)
		{
			JButton bPit = new MyRoundedButton(200, 200);
			bPit.setPreferredSize(new Dimension(200+30, 200+30));
			
			RoundStone stonesB = new RoundStone();
			stonesB.setNumStones(data[i]);
			stonesB.mancalaPit(false);
			bPit.setIcon(stonesB);
			
			pits.set(i, bPit);
			
			final int PIT = i;
			
//			if(player == 2)
//			{
//				bPit.addActionListener(new ActionListener(){
//					public void actionPerformed(ActionEvent e){
//						model.updateStones(PIT);
//					}
//				});
//			}
			
			board.add(bPit);
		}
		
		// Add A Pits
		for(int i=0; i<6; i++)
		{
			JButton aPit = new MyRoundedButton(200, 200);
			aPit.setPreferredSize(new Dimension(200+30, 200+30));
			
			pits.set(i, aPit);
			
			RoundStone stonesA = new RoundStone();
			stonesA.setNumStones(data[i]);
			stonesA.mancalaPit(false);
			aPit.setIcon(stonesA);
			
			final int PIT = i;
			
//			if(player == 1)
//			{
//				aPit.addActionListener(new ActionListener(){
//					public void actionPerformed(ActionEvent e){
//						model.updateStones(PIT);
//					}
//				});
//			}
			board.add(aPit);
		}
		
		// Add B Label
		for(int i=1; i<7; i++)
		{
			JLabel aLabel = new JLabel("A" + i);
			aLabel.setFont(new Font("Serif", Font.BOLD, 70));
			aLabel.setHorizontalAlignment(SwingConstants.CENTER);
			aLabel.setVerticalAlignment(SwingConstants.CENTER);
			
			board.add(aLabel);
		}
		
		base.add(board, BorderLayout.CENTER);
		
		// Mancala A
		JPanel mancalaA = new JPanel();
		mancalaA.setLayout(new BorderLayout());
		mancalaA.setSize(200, 800);
		mancalaA.setBackground(darkBlue);
		base.setOpaque(true);
		JLabel labelA = new JLabel("Mancala A");
		labelA.setFont(new Font("Serif", Font.BOLD, 40));
		labelA.setHorizontalAlignment(SwingConstants.CENTER);
		labelA.setVerticalAlignment(SwingConstants.CENTER);
		mancalaA.add(labelA, BorderLayout.NORTH);
		
		JButton a = new MyRoundedButton(200, 800);
		a.setPreferredSize(new Dimension(200+35, 800));
		mancalaA.add(a, BorderLayout.CENTER);
		
		pits.set(6, a);
		
		RoundStone stonesAM = new RoundStone();
		stonesAM.setNumStones(data[6]);
		stonesAM.mancalaPit(true);
		a.setIcon(stonesAM);
		
		base.add(mancalaA, BorderLayout.EAST);
		
		//Undo
		JButton undo = new JButton("Undo");
		//undo.setPreferredSize(new Dimension(400, 100));
		undo.setFont(new Font("Serif", Font.BOLD, 40));
		Color mattedBlue = new Color(19, 118, 181);
		undo.setBackground(mattedBlue);
		base.add(undo,BorderLayout.SOUTH);
		
		frame.add(base);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public void repaintStones()
	{
		for (int i=0; i<TOTAL_PITS; i++)
		{
			JButton pit = pits.get(i);
			
			RoundStone stone = new RoundStone();
			stone.setNumStones(data[i]);
			if ( (i==6) || (i==13))
				stone.mancalaPit(true);
			else
				stone.mancalaPit(false);
			pit.setIcon(stone);
			
			pit.repaint();
		}
	}

	public void whosTurn(int whosturn) 
	{
		player = whosturn;
		
		if(player == 1)
		{
			for (int i=0; i<=6; i++)
			{
				JButton aPit = pits.get(i);
				
				final int index = i;
				aPit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						model.updateStones(index);
					}
				});
			}
		}
		
		if(player == 2)
		{
			for (int i=7; i>=12; i++)
			{
				JButton bPit = pits.get(i);
				
				final int index = i;
				bPit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						model.updateStones(index);
					}
				});
			}
		}
	}
}

