package myPacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Configuration_Screen extends JFrame {

	JLayeredPane board;
	private JPanel background;
	Player player;
	
	public Configuration_Screen (String name,String userName) throws IOException
	{
		super(name);
		final String un=userName;
		board= new JLayeredPane();
		board.setPreferredSize(new Dimension(600,490));	
		background = createBackground();
		background.setBounds(0, 0, 600, 490);
		board.add(background, new Integer(0));
	JLabel borderBackground=new JLabel();
		borderBackground.setBackground(new Color(102,178,255,70));
		borderBackground.setVisible(true);	
		borderBackground.setBounds(35, 35, 500, 240);
		borderBackground.setOpaque(true);
		borderBackground.setBorder(new LineBorder(Color.blue, 2));
		board.add(borderBackground,new Integer(2));
	final JTextField userNameText=new JTextField(30);
		userNameText.setText(userName);	
	final JTextField ghostTimeText = new JTextField(30);
		ghostTimeText.setLocation(180,120);
		ghostTimeText.setSize(200,30);
		ghostTimeText.setVisible(true);
		board.add(ghostTimeText,new Integer(3));
	JLabel ghostTime_label=new JLabel("Ghost Cage Time [sec]");
		 ghostTime_label.setLocation(90,60);
		 ghostTime_label.setSize(400,50);
		 ghostTime_label.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 35));
		 ghostTime_label.setForeground(Color.white);
		 ghostTime_label.setVisible(true);
		 board.add(ghostTime_label,new Integer(3));
	JLabel gameLevel_label=new JLabel("Game Level");
		gameLevel_label.setLocation(175,170);
		gameLevel_label.setSize(250, 30);
		gameLevel_label.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 35));
		gameLevel_label.setForeground(Color.white);
		gameLevel_label.setVisible(true);
		board.add(gameLevel_label,new Integer(3));
	DefaultComboBoxModel model = new DefaultComboBoxModel(new String[]{"Easy","Difficult"});
	final JComboBox comboBox = new JComboBox(model);
			comboBox.setLocation(180, 220);
			comboBox.setSize(200, 30);
			comboBox.setVisible(true);
			board.add(comboBox,new Integer(3));
	JButton startGame=new JButton("Start Game");
		 startGame.setLocation(180, 350);
		 startGame.setSize(200, 50);
		 startGame.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 25));
		 startGame.setBackground(new Color(102,178,255));
		startGame.setVisible(true);
		board.add(startGame,new Integer(3));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 490);    
		 add(board);
		 setVisible(true);
	 
		 startGame.addActionListener(new ActionListener()
		 {
		   public void actionPerformed(ActionEvent e) 
		   {
		     // display/center the jdialog when the button is pressed
			 try {
			   int ghostTime=Integer.parseInt(ghostTimeText.getText());
			   String level=((String)(comboBox.getSelectedItem())).substring(0, 1);
			   if (ghostTime>=0) //correct configurations
				 //  new GameFrame(userNameText.getText(), ghostTime,level );	
				   player=new Player(un,ghostTime,level);
			   else
				   JOptionPane.showMessageDialog(null, "You should check your configurations again");
			 }
			 catch (NumberFormatException e1) {
				 JOptionPane.showMessageDialog(null, "You should check your configurations again");
				}
			  
				   
				   
					 
		   
		   }
		 });
		 
	}
	
	private JPanel createBackground () throws IOException{
		try {
			background = new JPanel(){
				private Image map = ImageIO.read(new File("img/configBachground.png"));
				public void paint( Graphics g ) { 
					super.paint(g);
					g.drawImage(map, 0,0 , null);
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
		background.setVisible(true);
		return background;
	}
	
}
