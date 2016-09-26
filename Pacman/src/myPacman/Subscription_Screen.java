package myPacman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Subscription_Screen extends JFrame {

	JLayeredPane board;
	private JPanel background;
	private final JCheckBox sexMale_CheckBox=new JCheckBox();
	private final JCheckBox sexFemale_CheckBox=new JCheckBox();
	
	public Subscription_Screen(String privateName) throws IOException 
	{
		super(privateName);
		
		board= new JLayeredPane();
		board.setPreferredSize(new Dimension(600,490));	
		background = createBackground();
		background.setBounds(0, 0, 600, 490);
		board.add(background, new Integer(0));
		
	 JLabel borderBackground=new JLabel();
			borderBackground.setBackground(new Color(105,255,102,70));
			borderBackground.setVisible(true);	
			borderBackground.setBounds(15, 15, 300, 420);
			borderBackground.setOpaque(true);
			borderBackground.setBorder(new LineBorder(Color.green, 2));
			board.add(borderBackground,new Integer(2));	
	final JTextField usernameText = new JTextField(30);
		usernameText.setLocation(50,70);
		usernameText.setSize(200,30);
		 usernameText.setVisible(true);
		 board.add(usernameText,new Integer(3));	
	final JTextField passwordText = new JTextField(30);
		passwordText.setLocation(50,150);
		passwordText.setSize(200,30);
		passwordText.setVisible(true);
		 board.add(passwordText,new Integer(3));
	final JTextField nameText = new JTextField(30);
		nameText.setLocation(50,220);
		nameText.setSize(200,30);
		nameText.setVisible(true);
		 board.add(nameText,new Integer(3));
	final JTextField ageText = new JTextField(30);
		ageText.setLocation(80,270);
		ageText.setSize(80,30);
		ageText.setVisible(true);
		 board.add(ageText,new Integer(3));
	final JCheckBox sexMale_CheckBox=new JCheckBox();
		sexMale_CheckBox.setLocation(120, 320);
		sexMale_CheckBox.setSize(20,20);
		sexMale_CheckBox.setVisible(true);
		board.add(sexMale_CheckBox,new Integer(3));
	final JCheckBox sexFemale_CheckBox=new JCheckBox();
		sexFemale_CheckBox.setLocation(250, 320);
		sexFemale_CheckBox.setSize(20,20);
		sexFemale_CheckBox.setVisible(true); 
		board.add(sexFemale_CheckBox,new Integer(3));
	JLabel sexMale_label=new JLabel("Male");
		sexMale_label.setSize(250,50);
		sexMale_label.setLocation(40, 310);
		sexMale_label.setFont(new Font("Narkisim", Font.BOLD, 30));
		sexMale_label.setForeground(Color.white);
	    sexMale_label.setVisible(true);
        board.add(sexMale_label,new Integer(3));
	JLabel sexFemale_label=new JLabel("Female");
		sexFemale_label.setSize(250,50);
		sexFemale_label.setLocation(150, 310);
		sexFemale_label.setFont(new Font("Narkisim", Font.BOLD, 30));
		sexFemale_label.setForeground(Color.white);
		sexFemale_label.setVisible(true);
		board.add(sexFemale_label,new Integer(3));
   JLabel userName_label=new JLabel("Username");
		 userName_label.setLocation(25,20);
		 userName_label.setSize(250,50);
		 userName_label.setFont(new Font("Narkisim", Font.BOLD, 30));
		 userName_label.setForeground(Color.white);
		 board.add(userName_label,new Integer(3));
   JLabel password_label=new JLabel("Password");
			 password_label.setLocation(20,100);
			 password_label.setSize(250,50);
			 password_label.setFont(new Font("Narkisim", Font.BOLD, 30));
			 password_label.setForeground(Color.white);
			 board.add(password_label,new Integer(3));
   JLabel name_label=new JLabel("Name");
			 name_label.setLocation(20,180);
			 name_label.setSize(250,50);
			 name_label.setFont(new Font("Narkisim", Font.BOLD, 30));
			 name_label.setForeground(Color.white);
			 board.add(name_label,new Integer(3));
	JLabel age_label=new JLabel("Age");
			 age_label.setLocation(20,260);
			 age_label.setSize(250,50);
			 age_label.setFont(new Font("Narkisim", Font.BOLD, 30));
			 age_label.setForeground(Color.white);
			 board.add(age_label,new Integer(3));
			 
   JButton subscribe=new JButton("Sign Up");
		 subscribe.setLocation(60, 370);
		 subscribe.setSize(200, 50);
		 subscribe.setFont(new Font("cooper black", Font.ROMAN_BASELINE, 25));
		 subscribe.setBackground(Color.green);
		 subscribe.setVisible(true);
		 board.add(subscribe,new Integer(3));
		 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 500);
		 add(board);
		 setVisible(true);
			 
		 subscribe.addActionListener(new ActionListener()
		 {
		   public void actionPerformed(ActionEvent e) 
		   {
		     // display/center the jdialog when the button is pressed
			   
			   String line=null;
			try {
				File f = new File("users.txt");
				if(f.exists()==false) { 
					PrintWriter writer = new PrintWriter("users.txt");
				}
				FileWriter fw=new FileWriter("users.txt",true);
				PrintWriter pw=new PrintWriter(fw);
				
  				boolean isMale=sexMale_CheckBox.isSelected();
		    	   boolean isFemale=sexFemale_CheckBox.isSelected();
		    	   String sex="";
		    	   if (isMale==true && isFemale==true){
		    		   JOptionPane.showMessageDialog(null, "ho, don't cheat me, please select only one gender");
		    	   }
		    	   else{
		    		   if (isMale==true && isFemale==false)
			    		   sex="Male";
			    	   else if (isMale==false && isFemale==true)
			    		   sex="Female";
		    	   }
		    	   if (usernameText.getText().length()==0 || passwordText.getText().length()==0  || nameText.getText().length()==0||ageText.getText().length()==0 ){
			 			JOptionPane.showMessageDialog(null, "ho, don't cheat me, all fields must be filed out");
	            	 }
				 else{
					 pw.println(usernameText.getText()+"_"+passwordText.getText()+"_"+ageText.getText()+"_"+sex);
			    	   pw.close();
			    	   fw.close();
			    	   updateCompBoards(sex.substring(0, 1));
			    	   updateAgeBoard(Integer.parseInt( ageText.getText()));
			    	   Configuration_Screen configuration_screen=new Configuration_Screen("Configurations",usernameText.getText());
				
				 }
		    	   
		    	}
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	  
		   }
		 });
		
	}
	
	private JPanel createBackground () throws IOException{
		try {
			background = new JPanel(){
				private Image map = ImageIO.read(new File("img/registerBackground.png"));
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
	
	public void updateCompBoards(String sexString)
	{
		SexAndCompDistribution_Screen sex;
		try {
			sex = new SexAndCompDistribution_Screen("");
			sex.addKind(sexString.substring(0,1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void updateAgeBoard(int ageInt)
	{
		ageDistributionBoard age;
		try {
			age = new ageDistributionBoard("");
			age.addAge(ageInt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
