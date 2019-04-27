package MancalaProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class Board extends JFrame implements ChangeListener//, MouseListener
{
	private int[] data;
	private MancalaDataModel dataModel;
	private BoardLayout layout;
	private static final int TOTAL_PITS = 14;
	private static final int PLAYER_A = 1;
	private static final int PLAYER_B = 2;
	
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

	public void stateChanged(ChangeEvent e) 
	{
		for(int i=0; i<dataModel.getData().length; i++)
		{
			data[i] = dataModel.getData()[i]; //should be same size
		}
		
		layout.setData(data);
		layout.repaintStones();
	}
	
	public void displayBoard()
	{
		layout.setData(data);
		layout.drawBoard();
		playGame();
	}
	
	public void playGame()
	{
		layout.whosTurn(PLAYER_A);
	}
	
	public void homepage()
	{
		Color boardColor = new Color(66, 244, 179);
		Color buttonColor = new Color(212, 252, 237);
		
		JFrame frame = new JFrame("Mancala Homepage");
		frame.setSize(500, 600);
		frame.setLayout(new BorderLayout());
		
		JPanel base = new JPanel();
		base.setLayout(new BorderLayout());
		base.setSize(500, 600);
		
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		top.setSize(500, 200);
		top.setBackground(boardColor);
		top.setOpaque(true);
		
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
		
		base.add(top, BorderLayout.NORTH);
		
		JPanel mid = new JPanel();
		mid.setLayout(new FlowLayout());
		mid.setSize(500, 200);
		mid.setBackground(boardColor);
		mid.setOpaque(true);
		
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
		
		JButton vertLayout = new JButton("Verticle");
		vertLayout.setFont(new Font("Serif", Font.BOLD, 70));
		vertLayout.setHorizontalAlignment(SwingConstants.CENTER);
		vertLayout.setVerticalAlignment(SwingConstants.CENTER);
		vertLayout.setBackground(buttonColor);
		
		vertLayout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				layout = new VerticalLayout();
			}
		});
		mid.add(vertLayout);
		
		base.add(mid, BorderLayout.CENTER);
		
		JPanel bot = new JPanel();
		bot.setLayout(new FlowLayout());
		bot.setSize(400, 200);
		bot.setBackground(boardColor);
		bot.setOpaque(true);
		
		JButton done = new JButton("Done!");
		done.setFont(new Font("Serif", Font.BOLD, 40));
		done.setHorizontalAlignment(SwingConstants.CENTER);
		done.setVerticalAlignment(SwingConstants.CENTER);
		done.setBackground(buttonColor);
		
		done.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				displayBoard();
			}
		});
		bot.add(done);
		
		base.add(bot, BorderLayout.SOUTH);
		
		frame.add(base, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}
}
