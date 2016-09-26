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
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class SexAndCompDistribution_Screen extends JFrame {

	private Map<String, Integer> kindMap = new HashMap<String, Integer>();
	JLayeredPane board;
	private JPanel background;
	
	public SexAndCompDistribution_Screen(String name) throws IOException
	{
		super(name);
		board= new JLayeredPane();
		board.setPreferredSize(new Dimension(700,500));	 
		background = createBackground();
		background.setBounds(0, 0, 700, 500);
		board.add(background, new Integer(0));
		
		loadFromFile();
		
		 JLabel borderBackground=new JLabel();
			borderBackground.setBackground(new Color(255,255,255,80));
			borderBackground.setVisible(true);	
			borderBackground.setBounds(10,70, 310, 200);
			borderBackground.setOpaque(true);
			borderBackground.setBorder(new LineBorder(Color.white, 2));
			board.add(borderBackground,new Integer(2));
		
		int total=kindMap.get("M")+kindMap.get("F")+kindMap.get("C");
		JLabel range1=new JLabel("Male");
		range1.setLocation(20,90);
		range1.setSize(150,30);
		range1.setFont(new Font("Narkisim", Font.BOLD, 20));
		String MLabel=((new Double((((double)kindMap.get("M"))/(double)total)*100))).toString();
		JLabel range1Count=new JLabel (MLabel.substring(0, MLabel.indexOf('.')+2)+"%");
		range1Count.setLocation(120,90);
		range1Count.setSize(100,30);
		range1Count.setFont(new Font("Narkisim", Font.BOLD, 20));
			
		JLabel range2=new JLabel("Female");
		range2.setLocation(20,140);
		range2.setSize(150, 30);
		range2.setFont(new Font("Narkisim", Font.BOLD, 20));
		String FLabel=((new Double((((double)kindMap.get("F"))/(double)total)*100))).toString();
		JLabel range2Count=new JLabel (FLabel.substring(0, FLabel.indexOf('.')+2)+"%");
		range2Count.setLocation(120,140);
		range2Count.setSize(100,30);
		range2Count.setFont(new Font("Narkisim", Font.BOLD, 20));
			 
		JLabel range3=new JLabel("Computer");
		range3.setLocation(20,190);
		range3.setSize(150, 30);
		range3.setFont(new Font("Narkisim", Font.BOLD, 20));
				
		String CLabel=((new Double((((double)kindMap.get("C"))/(double)total)*100))).toString();
		JLabel range3Count=new JLabel (CLabel.substring(0, CLabel.indexOf('.')+2)+"%");
		range3Count.setLocation(150,190);
		range3Count.setSize(100,30);
		range3Count.setFont(new Font("Narkisim", Font.BOLD, 20));
				
			
		
		range1.setVisible(true);
		range2.setVisible(true);
		range3.setVisible(true);
		
		
		range1Count.setVisible(true);  
		range2Count.setVisible(true);
		range3Count.setVisible(true);
	
		board.add(range1,new Integer(3));
		board.add(range2,new Integer(3));
		board.add(range3,new Integer(3));
		
		board.add(range1Count,new Integer(3));
		board.add(range2Count,new Integer(3));
		board.add(range3Count,new Integer(3));
		board.setVisible(true);
		
		add(board);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(700, 500);
		this.setVisible(false);
		this.setTitle("Kind Distribution Board");
		
	}
	
	public void addKind ( String kind)
	{
	
			if (kind.equals("M"))
				kindMap.put("M", kindMap.get("M")+1);
			else if (kind.equals("F"))
				kindMap.put("F", kindMap.get("F")+1);
			
			else if (kind.equals("C"))
				kindMap.put("C", kindMap.get("C")+1);
			
			
			
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(600, 600);
			this.setVisible(false);
			this.setTitle("Kind Distribution Board");
		  saveToFile();
		
	}
	
	public void saveToFile ()
	{
		  String line=null;
			try {
				File f = new File("kindBoard.txt");
				if(f.exists()==false) { 
					PrintWriter writer = new PrintWriter("kindBoard.txt");
				}
				FileWriter fw=new FileWriter("kindBoard.txt",false);
				PrintWriter pw=new PrintWriter(fw);

		    	pw.println(kindMap.get("M"));
		    	pw.println(kindMap.get("F"));
		    	pw.println(kindMap.get("C"));
		    
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
			File f = new File("kindBoard.txt");
			if(f.exists()==false) { 
				PrintWriter writer = new PrintWriter("kindBoard.txt");
			}
			fr = new FileReader("kindBoard.txt");
			
			  BufferedReader br=new BufferedReader(fr);
			   
			kindMap.put("M",Integer.parseInt( br.readLine()));
			kindMap.put("F",Integer.parseInt( br.readLine()));
			kindMap.put("C",Integer.parseInt( br.readLine()));
			
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


