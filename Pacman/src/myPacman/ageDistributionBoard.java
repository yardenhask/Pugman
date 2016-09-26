package myPacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class ageDistributionBoard extends JFrame {

	
	private Map<String, Integer> ageMap = new HashMap<String, Integer>();
	JLayeredPane board;
	private JPanel background;
	
	public ageDistributionBoard(String name) throws IOException
	{
		super(name);
		board= new JLayeredPane();
		board.setPreferredSize(new Dimension(700,500));	 
		background = createBackground();
		background.setBounds(0, 0, 700, 500);
		board.add(background, new Integer(0));
		
		loadFromFile();
		
		JLabel borderBackground=new JLabel();
		borderBackground.setBackground(new Color(255,255,255,90));
		borderBackground.setVisible(true);	
		borderBackground.setBounds(10,70, 310, 350);
		borderBackground.setOpaque(true);
		borderBackground.setBorder(new LineBorder(Color.white, 2));
		board.add(borderBackground,new Integer(2));
	
		JLabel range1=new JLabel("5-10 years old");
		range1.setLocation(20,90);
		range1.setSize(150,30);
		range1.setFont(new Font("Narkisim", Font.BOLD, 20));
		
		JLabel range1Count=new JLabel(ageMap.get("10").toString());
		range1Count.setLocation(200,90);
		range1Count.setSize(100,30);
		range1Count.setFont(new Font("Narkisim", Font.BOLD, 20));
			
		JLabel range2=new JLabel("10-15 years old");
		range2.setLocation(20,140);
		range2.setSize(150, 30);
		range2.setFont(new Font("Narkisim", Font.BOLD, 20));
		
		JLabel range2Count=new JLabel(ageMap.get("15").toString());
		range2Count.setLocation(200,140);
		range2Count.setSize(100,30);
		range2Count.setFont(new Font("Narkisim", Font.BOLD, 20));
		
			 
		JLabel range3=new JLabel("15-20 years old");
		range3.setLocation(20,190);
		range3.setSize(150, 30);
		range3.setFont(new Font("Narkisim", Font.BOLD, 20));
				
		JLabel range3Count=new JLabel(ageMap.get("20").toString());
		range3Count.setLocation(200,190);
		range3Count.setSize(100,30);
		range3Count.setFont(new Font("Narkisim", Font.BOLD, 20));
		
		 JLabel range4=new JLabel("20-25 years old");
		 range4.setLocation(20,240);
			range4.setSize(150, 30);
			range4.setFont(new Font("Narkisim", Font.BOLD, 20));
		 
		JLabel range4Count=new JLabel(ageMap.get("25").toString());
		range4Count.setLocation(200,240);
		range4Count.setSize(100,30);
		range4Count.setFont(new Font("Narkisim", Font.BOLD, 20));
					
				 
		JLabel range5=new JLabel("25-30 years old");
		range5.setLocation(20,290);
		range5.setSize(150, 30);
		range5.setFont(new Font("Narkisim", Font.BOLD, 20));
			 
		JLabel range5Count=new JLabel(ageMap.get("30").toString());
		range5Count.setLocation(200,290);
		range5Count.setSize(100,30);
		range5Count.setFont(new Font("Narkisim", Font.BOLD, 20));
				 
		 JLabel range6=new JLabel("Above 30 years old");
		 range6.setLocation(20,340);
			range6.setSize(250, 30);
			range6.setFont(new Font("Narkisim", Font.BOLD, 20));
				 
		 JLabel range6Count=new JLabel(ageMap.get("100").toString());
		 range6Count.setLocation(200,340);
			range6Count.setSize(100,30);
			range6Count.setFont(new Font("Narkisim", Font.BOLD, 20));
		
		
		range1.setVisible(true);
		range2.setVisible(true);
		range3.setVisible(true);
		range4.setVisible(true);
		range5.setVisible(true);
		range6.setVisible(true);
		
		range1Count.setVisible(true);  
		range2Count.setVisible(true);
		range3Count.setVisible(true);
		range4Count.setVisible(true);
		range5Count.setVisible(true);
		range6Count.setVisible(true);
		board.add(range1,new Integer(3));
		board.add(range2,new Integer(3));
		board.add(range3,new Integer(3));
		board.add(range4,new Integer(3));
		board.add(range5,new Integer(3));
		board.add(range6,new Integer(3));
		board.add(range1Count,new Integer(3));
		board.add(range2Count,new Integer(3));
		board.add(range3Count,new Integer(3));
		board.add(range4Count,new Integer(3));
		board.add(range5Count,new Integer(3));
		board.add(range6Count,new Integer(3));
		
		add(board);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(700, 500);
		this.setVisible(false);
		this.setTitle("Age Distribution Board");
		
	}
	
	public void addAge ( int age)
	{
	
			if (age>=5 && age<=10)
				ageMap.put("10", ageMap.get("10")+1);
			else if (age>10 && age<=15)
				ageMap.put("15", ageMap.get("15")+1);
			
			else if (age>15 && age<=20)
				ageMap.put("20", ageMap.get("20")+1);
			
			else if (age>20 && age<=25)
				ageMap.put("25", ageMap.get("25")+1);
			else if (age>25 && age<=30)
				ageMap.put("30", ageMap.get("30")+1);
			else if (age>30)
				ageMap.put("100", ageMap.get("100")+1);
			
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(600, 600);
			this.setVisible(false);
			this.setTitle("Age Distribution Board");
		  saveToFile();
		
	}
	
	public void saveToFile ()
	{
		  String line=null;
			try {
				File f = new File("ageBoard.txt");
				if(f.exists()==false) { 
					PrintWriter writer = new PrintWriter("ageBoard.txt");
				}
				FileWriter fw=new FileWriter("ageBoard.txt",false);
				PrintWriter pw=new PrintWriter(fw);

		    	pw.println(ageMap.get("10"));
		    	pw.println(ageMap.get("15"));
		    	pw.println(ageMap.get("20"));
		    	pw.println(ageMap.get("25"));
		    	pw.println(ageMap.get("30"));
		    	pw.println(ageMap.get("100"));
		    	   pw.close();
		    	   fw.close();
		    	   
			}
			catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	public void loadFromFile()
	{
		
		   FileReader fr;
		   String line=null;
		try {
			File f = new File("ageBoard.txt");
			if(f.exists()==false) { 
				PrintWriter writer = new PrintWriter("ageBoard.txt");
			}
			fr = new FileReader("ageBoard.txt");
			
			  BufferedReader br=new BufferedReader(fr);
			   
			ageMap.put("10",Integer.parseInt( br.readLine()));
			ageMap.put("15",Integer.parseInt( br.readLine()));
			ageMap.put("20",Integer.parseInt( br.readLine()));
			ageMap.put("25",Integer.parseInt( br.readLine()));
			ageMap.put("30",Integer.parseInt( br.readLine()));
			ageMap.put("100",Integer.parseInt( br.readLine()));
			   br.close();
			   fr.close();
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			
			e1.printStackTrace();
			
		}
		 
	}
	
	private JPanel createBackground () throws IOException{
		try {
			background = new JPanel(){
				private Image map = ImageIO.read(new File("img/statictics.png"));
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
