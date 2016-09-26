package myPacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LongestTimeTillGameOver_Screen extends JFrame {

	
	private JTable table;
	private Object[][] tableData;
	private String[]columns;
	private int minScore;
	private JPanel background;
	JLayeredPane board;
		
		public LongestTimeTillGameOver_Screen(String name) throws IOException
		{
			super(name);
			board= new JLayeredPane();
			board.setPreferredSize(new Dimension(700,500));	 
			background = createBackground();
			background.setBounds(0, 0, 700, 500);
			board.add(background, new Integer(0));
			
			//this.setLayout(new FlowLayout());
			minScore=0;
			String[]columnsNames={"UserName","time"};
			columns=columnsNames;
			
		
			loadFromFile();

			table.setPreferredScrollableViewportSize(new Dimension(500,350));
			table.setFillsViewportHeight(true);
			JScrollPane scrollPane=new JScrollPane(table);
			scrollPane.setLocation(90, 50);
			scrollPane.setSize(500,350);
			scrollPane.setBackground(new Color(0,0,0,60));
			scrollPane.setVisible(true);
			board.add(scrollPane,new Integer(3));
			//add(scrollPane);
			
			board.setVisible(true);
			this.add(board);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setSize(700, 500);
			this.setVisible(false);
			this.setTitle("Longest Time Until Game Over Board");
			
		}
		
		public void addTime (String UN, double time)
		{
			boolean found=false;
			for (int i=0;i<tableData.length;i++)
			{
				if (tableData[i][0].equals(UN))
					found=true;
					if (found==true && Double.parseDouble(tableData[i][1].toString())<time)
					tableData[i][1]=time;
			}
			
			if (found==false)
			{
				int rows=tableData.length+1;
				int cols=2;
				Object[][] temp=tableData;
				tableData=new Object[rows][cols];
				
				for (int i=0;i<rows-1;i++)
					for (int j=0;j<cols;j++)
						tableData[i][j]=temp[i][j];
				tableData[tableData.length-1][0]=UN;
				tableData[tableData.length-1][1]=time;
			}
			
			
		
			
			  
			  getContentPane().removeAll();
			  this.setLayout(new FlowLayout());
			  table=new JTable(tableData,columns);
			  table.setPreferredScrollableViewportSize(new Dimension(500,300));
				table.setFillsViewportHeight(true);
				
				JScrollPane scrollPane=new JScrollPane(table);
				add(scrollPane);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setSize(600, 600);
				this.setVisible(false);
				this.setTitle("Longest Time Until Game Over Board");
			  saveToFile();
			
		}
		
		public void saveToFile ()
		{
			  String line=null;
				try {
					File f = new File("LongestTimeTillGameOver.txt");
					if(f.exists()==false) { 
						PrintWriter writer = new PrintWriter("LongestTimeTillGameOver.txt");
					}
					FileWriter fw=new FileWriter("LongestTimeTillGameOver.txt",false);
					PrintWriter pw=new PrintWriter(fw);

			    	 for (int i=0;i<tableData.length;i++)
			    	   pw.println(tableData[i][0]+"_"+tableData[i][1]);
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
				File f = new File("LongestTimeTillGameOver.txt");
				if(f.exists()==false) { 
					PrintWriter writer = new PrintWriter("LongestTimeTillGameOver.txt");
				}
				fr = new FileReader("LongestTimeTillGameOver.txt");
				
				  BufferedReader br=new BufferedReader(fr);
				   
				  int lines = 0;
				  while (br.readLine() != null) lines++;
				 // br.close();
				   tableData=new Object[lines][2];
				   br.close();
				   fr.close();
				   fr = new FileReader("LongestTimeTillGameOver.txt");
					
					   br=new BufferedReader(fr);
				  
				  line=br.readLine();
				  int count=0;
				   while ( line!=null )
				   {
					   tableData[count][0]=line.substring(0, line.indexOf('_'));
					   tableData[count][1]=line.substring(line.indexOf('_')+1);
					   count++;
					   line=br.readLine();
				   }
				   table=new JTable(tableData,columns);
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
