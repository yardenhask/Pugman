package myPacman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Login_Screen extends JFrame {

	
	JLayeredPane board;
	private JPanel background;
	
	public Login_Screen (String name) throws IOException 
	{
		super(name);
	
		board= new JLayeredPane();
		board.setPreferredSize(new Dimension(600,490));
		
		background = createBackground();
		background.setBounds(0, 0, 600, 490);
		board.add(background, new Integer(0));
	
		 JLabel borderBackground=new JLabel();
			borderBackground.setBackground(new Color(255,51,153,70));
			borderBackground.setVisible(true);	
			borderBackground.setBounds(55, 80, 450, 300);
			borderBackground.setOpaque(true);
			borderBackground.setBorder(new LineBorder(Color.pink, 2));
			board.add(borderBackground,new Integer(2));
		JLabel userName_label=new JLabel("Username");
			 userName_label.setLocation(80,120);
			 userName_label.setSize(200,35);
			 userName_label.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 35));
			 userName_label.setForeground(Color.white);
			 userName_label.setVisible(true);
			 board.add(userName_label,new Integer(3));
		 JLabel password_label=new JLabel("Password");
			 password_label.setLocation(80,170);
			 password_label.setSize(200, 35);	
			 password_label.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 35));
			 password_label.setForeground(Color.white);
			 password_label.setVisible(true);
			 board.add(password_label,new Integer(3));
		final JTextField usernameText = new JTextField(30);
			 usernameText.setLocation(270,130);
			 usernameText.setSize(200,24);
			 usernameText.setVisible(true);
			 board.add(usernameText,new Integer(3));
		final JTextField passwordText = new JTextField(30);
			 passwordText.setLocation(270,180);
		     passwordText.setSize(200,24);
			 passwordText.setVisible(true);
			 board.add(passwordText,new Integer(3));
			 
		JButton submit=new JButton("Submit");
			 submit.setLocation(270, 220);
			 submit.setSize(200, 50);
			 submit.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 25));
			 submit.setBackground(Color.pink);
			 submit.setVisible(true);
			 board.add(submit,new Integer(3));
		JLabel signUp_label=new JLabel("Sign up For Free");
			 signUp_label.setLocation(100,295);
			 signUp_label.setSize(150,24);
			 signUp_label.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 15));
			 signUp_label.setForeground(Color.LIGHT_GRAY);
			 signUp_label.setVisible(true);
			 board.add(signUp_label,new Integer(3));
	    JButton subscribe=new JButton("Sign Up");
			 subscribe.setLocation(70, 320);
			 subscribe.setSize(200, 50);
			 subscribe.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 25));
			 subscribe.setBackground(Color.pink);
			 subscribe.setVisible(true);
			board.add(subscribe,new Integer(3));
		JButton CompPlayer=new JButton("Comp Player");
			CompPlayer.setLocation(290,320);
			 CompPlayer.setSize(200, 50);
			 CompPlayer.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 25));
			 CompPlayer.setBackground(Color.pink);
			 CompPlayer.setVisible(true);
			 board.add(CompPlayer,new Integer(3));
			 
		board.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 490);
		add(board);
		setVisible(true);
		
		 submit.addActionListener(new ActionListener()
		 {
		   public void actionPerformed(ActionEvent e) 
		   {			   
			   boolean empty=false;
		     // display/center the jdialog when the button is pressed
			   FileReader fr;
			   String line=null;
			try {
				File f = new File("users.txt");
				if(f.exists()==false) { 
					PrintWriter writer = new PrintWriter("users.txt");
				}
				fr = new FileReader("users.txt");
				  BufferedReader br=new BufferedReader(fr);
				   
				   String UN=usernameText.getText();
				   String pass=passwordText.getText();
				   line=br.readLine();
					
				  if ( UN.equals("")==true  || pass.equals("")==true )
					 empty=true;
				   while ( line!=null && line.contains(UN+"_"+pass+"_")==false)
				   {
					   line=br.readLine();
				   }
				
				   br.close();
				   fr.close();
			}
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
	//		System.out.println("line: "+line);
			   if(line!=null && empty==false )
				try {
					new Configuration_Screen("Configurations",line.substring(0,line.indexOf('_')));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			else{
				   JOptionPane.showMessageDialog(null, "ho, don't cheat me, please insert username and password again");
				   
			   }
		   }
		 });
		 
		 CompPlayer.addActionListener(new ActionListener()
		 {
		   public void actionPerformed(ActionEvent e) 
		   {
			   try {
				updateCompBoards();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			   try {
				new Configuration_Screen("Configurations","Computer");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		   }
		 });
		 
		 subscribe.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent ae){
		    	   try {
					Subscription_Screen s=new Subscription_Screen("Sign Up");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		       } 
		    });
		
	}
	
	private JPanel createBackground () throws IOException{
		try {
			background = new JPanel(){
				private Image map = ImageIO.read(new File("img/chukie.png"));
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
	
	public void updateCompBoards() throws IOException
	{
		SexAndCompDistribution_Screen sex=new SexAndCompDistribution_Screen("");
		sex.addKind("C");
	}
	
	
}
