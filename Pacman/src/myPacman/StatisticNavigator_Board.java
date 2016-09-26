package myPacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

public class StatisticNavigator_Board extends JFrame {

	private JLayeredPane board=new JLayeredPane();

	
	public StatisticNavigator_Board(String name)
	{
		super(name);
	
		board.setPreferredSize(new Dimension(600,490));
		
		JButton winnersBoardButton=new JButton("Winners Board");
			winnersBoardButton.setSize(400,50);
			winnersBoardButton.setLocation(100, 50);
			winnersBoardButton.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 25));
			winnersBoardButton.setBackground(new Color(255, 178, 102));
		JButton longestTimeFirstDeathBoardButton=new JButton("Longest Time Till First Death Board");
			longestTimeFirstDeathBoardButton.setSize(400,50);
			longestTimeFirstDeathBoardButton.setLocation(100, 125);
			longestTimeFirstDeathBoardButton.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 20));
			longestTimeFirstDeathBoardButton.setBackground(new Color(255, 178, 102));
		JButton longestTimeGameOverBoardButton=new JButton("Longest Time Till Game Over Board");
			longestTimeGameOverBoardButton.setSize(400,30);
			longestTimeGameOverBoardButton.setLocation(100, 200);
			longestTimeGameOverBoardButton.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 20));
			longestTimeGameOverBoardButton.setBackground(new Color(255, 178, 102));
		JButton ageDistributionBoardButton=new JButton("Age Distribution Board");
			ageDistributionBoardButton.setSize(400,30);
			ageDistributionBoardButton.setLocation(100, 275);	
			ageDistributionBoardButton.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 25));
			ageDistributionBoardButton.setBackground(new Color(255, 178, 102));
		JButton kindDistributionBoardButton=new JButton("Kind Distribution Board");
			kindDistributionBoardButton.setSize(400,30);
			kindDistributionBoardButton.setLocation(100, 350);
			kindDistributionBoardButton.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 25));
			kindDistributionBoardButton.setBackground(new Color(255, 178, 102));
		
		
		winnersBoardButton.setVisible(true);
		longestTimeFirstDeathBoardButton.setVisible(true);
		longestTimeGameOverBoardButton.setVisible(true);
		kindDistributionBoardButton.setVisible(true);
		ageDistributionBoardButton.setVisible(true);
		board.add(winnersBoardButton);
		board.add(longestTimeFirstDeathBoardButton);
		board.add(longestTimeGameOverBoardButton);
		board.add(ageDistributionBoardButton);
		board.add(kindDistributionBoardButton);
		
		board.setVisible(true);
		
		getContentPane().setBackground(new Color(255, 204, 153));
		add(board);
		setVisible(true);
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(600,490);
			
		
		 ageDistributionBoardButton.addActionListener(new ActionListener()
		 {
			 
		   public void actionPerformed(ActionEvent e) 
		   {
			 JFrame age;
			try {
				age = new ageDistributionBoard("Age Distribution Board");
				age.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			   
		   }
		 });
		 
		 kindDistributionBoardButton.addActionListener(new ActionListener()
		 {
		   public void actionPerformed(ActionEvent e) 
		   {
			 JFrame sex;
			try {
				sex = new SexAndCompDistribution_Screen("Kind Distribution Board");
				sex.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		   }
		 });
		 
		 longestTimeFirstDeathBoardButton.addActionListener(new ActionListener()
		 {
		   public void actionPerformed(ActionEvent e) 
		   {
			  JFrame firstDeath;
			try {
				firstDeath = new LongestTimeTillFirstDeath_Screen("Longest Time First Death Board");
				 firstDeath.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		   }
		 });
		 
		longestTimeGameOverBoardButton.addActionListener(new ActionListener()
		 {
		   public void actionPerformed(ActionEvent e) 
		   {
			  JFrame gameOver;
			try {
				gameOver = new LongestTimeTillGameOver_Screen("Longest Time Till Game Over Board");
				gameOver.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
		   }
		 });
		
		 
		winnersBoardButton.addActionListener(new ActionListener()
		 {
		   public void actionPerformed(ActionEvent e) 
		   {
			 JFrame winners;
			try {
				winners = new Winners_Statistic_Screen("Winners Board");
				winners.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		   }
		 });
		
		

	}
}
