package myPacman;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/*
 * This class represents the board of the game which manage all the items on the board
 */
public class Board extends JPanel implements CharacterListener{

	// This takes care of the game board
	final int W=1; // Wall.
	final int F=2; // Crossroads with food 
	final int E=3; // Empty crossroads
	final int B=4; // Mighty
	final int S=5; //Super
	
	
	private int board2[][];
/* {		 0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7
		0	{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W},
		1	{W,B,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,B,W},
		2	{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
		3	{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
		4	{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
		5	{W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W},
		6	{W,F,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,F,W},
		7	{W,F,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,F,W},
		8	{W,F,F,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,F,F,W},
		9	{W,W,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,W,W},
		0	{E,E,E,E,E,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,E,E,E,E,E},
		1	{E,E,E,E,E,W,F,W,W,F,F,F,F,F,F,F,F,F,F,W,W,F,W,E,E,E,E,E},
		2	{E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
		3	{W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W},
		4	{F,F,F,F,F,F,F,F,F,F,W,E,E,E,E,E,E,W,F,F,F,F,F,F,F,F,F,F},
		5	{W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W},
		6	{E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
		7	{E,E,E,E,E,W,F,W,W,F,F,F,F,F,F,F,F,F,F,W,W,F,W,E,E,E,E,E},
		8	{E,E,E,E,E,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,E,E,E,E,E},
		9	{W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W},
		0	{W,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,W},
		1	{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
		2	{W,F,W,W,W,W,F,W,W,W,W,W,F,W,W,F,W,W,W,W,W,F,W,W,W,W,F,W},
		3	{W,F,F,F,W,W,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,W,W,F,F,F,W},
		4	{W,W,W,F,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,F,W,W,W},
		5	{W,W,W,F,W,W,F,W,W,F,W,W,W,W,W,W,W,W,F,W,W,F,W,W,F,W,W,W},
		6	{W,F,F,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,W,W,F,F,F,F,F,F,W},
		7	{W,F,W,W,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,W,W,F,W},
		8	{W,F,W,W,W,W,W,W,W,W,W,W,F,W,W,F,W,W,W,W,W,W,W,W,W,W,F,W},
		9	{W,B,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,B,W},
		0	{W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W}
	};*/
;
	// represents the board game
	public Cell board[][] = {
			//-----------------------X-----------------------------//
			{new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W)},
			{new Wall(W),new MightyFood(B),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new SuperFood(S),new Wall(W)},
			{new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W)},
			{new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W)},
			{new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W)},
			{new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W)},
			{new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W)},
			{new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W)},
			{new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W)},
			{new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W)},
			{new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E)},
			{new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E)},
			{new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E)},
			{new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W)},
			{new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F)},
			{new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W)},
			{new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E)},
			{new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E)},
			{new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Empty(E),new Empty(E),new Empty(E),new Empty(E),new Empty(E)},
			{new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W)},
			{new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W)},
			{new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W)},
			{new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W)},
			{new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W)},
			{new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W)},
			{new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W)},
			{new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new Wall(W)},
			{new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W)},
			{new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new RegularFood(F),new Wall(W)},
			{new Wall(W),new SuperFood(S),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new RegularFood(F),new MightyFood(B),new Wall(W)},
			{new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W),new Wall(W)}
	}; 
	
	
//	private int board[][] =	
		
	private Sound sound;
	private int mapHeight;
	private int mapWidth;
	private double squareHeight;
	private double squareWidth;
	private JLayeredPane multiBoard;
	private JPanel background;
	private JPanel itemsBoard;
	protected BoardListener listener;
	private int countFood;
	
	//Constructor
		public Board(){
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			sound=new Sound();
			mapHeight=580;
			mapWidth=580;
			
			squareHeight=mapHeight/31;
			squareWidth=mapWidth/28;
			// Creates and set-up the layered pane.
			multiBoard = new JLayeredPane();
			multiBoard.setPreferredSize(new Dimension(mapHeight, mapWidth));
			// Creates the background.
			JPanel background = createBackground();
			background.setBounds(0, 0, mapWidth, mapHeight);
			multiBoard.add(background, new Integer(0));
			// Creates the items board - Placing food in proper places
			CreateItemBoard();
			itemsBoard.setOpaque(false);
			itemsBoard.setSize(mapWidth, mapHeight);
			itemsBoard.setBackground(null);
			multiBoard.add(itemsBoard,new Integer (1));
			
			// Final stuff
			multiBoard.setVisible(true);
			this.add(multiBoard);
			countFood=300;
			saveAnotherBoard();
		}

	public void addListener(BoardListener listenerToAdd){
		this.listener=listenerToAdd;
	}
	/**
	 * This function draws the background of the board (the map image)
	 * @return
	 */
	private JPanel createBackground(){
		try {
			background = new JPanel(){
				private Image map = ImageIO.read(new File("img/Map2.bmp"));
				public void paint( Graphics g ) { 
					super.paint(g);
					g.drawImage(map, 0, 0, null);
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
		background.setVisible(true);
		
		return background;
	}
	/**
	 * This function draws the food on the board
	 */
	private void CreateItemBoard(){
		try {
			itemsBoard = new JPanel(){
				private Image food = ImageIO.read(new File("img/NormalPoint.png"));
				private Image bonus = ImageIO.read(new File("img/BigPoint.png"));
				private Image Super=ImageIO.read(new File("img/super.png"));
				public void paint (Graphics g){
					super.paint(g);
					for (int i=0; i<board.length; i++)
						for (int j=0; j<board[i].length; j++) {
							
							if(board[i][j].value==F)
							
								g.drawImage(food, (int)(j*(squareWidth)+squareWidth/2), (int)(i*(squareHeight)+squareHeight/2), null);
							
							if(board[i][j].value==B)
								g.drawImage(bonus, (int)(j*(squareWidth)+squareWidth/2-5), (int)(i*(squareHeight)+squareHeight/2-5), null);
							if(board[i][j].value==S)
								g.drawImage(Super, (int)(j*(squareWidth)+squareWidth/2-5), (int)(i*(squareHeight)+squareHeight/2-5), null);
						}
				}
			};
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getMapWidth(){
		return mapWidth;
	}
	public int getMapHeight(){
		return mapHeight;
	}
	public double getSquareWidth(){
		return squareWidth;
	}
	public double getSquareHeight(){
		return squareHeight;
	}
	
	public void addItemToMultiBoard(Component comp , Integer num){
		multiBoard.add(comp,num);
	}
	
	public void pacmanStart(){
		//openCage();
		listener.gameStarted();
	}
	
	/*public void openCage(){
		board[12][14]=E;
		board[12][15]=E;
	}
	public void closeCage(){
		board[12][14]=W;
		board[12][15]=W;
	}*/
	
	public void clashTreatment() {
		listener.clashTreatment();		
	}
	
	public boolean checkAvailableMovement(double newXposition, double newYposition, boolean isPacman){
		int x=(int)newXposition;
		int y=(int)newYposition;
		
		
		if ( (y>=0 && y<mapHeight) && (x>=0 && x<mapWidth)){
			x=x/(int)squareWidth;
			y=y/(int)squareHeight;

			
			if (x<28 && y<31 && board[y][x].value!=W){
				if (isPacman)
				{
					if (board[y][x].value==B){
						countFood--; 
						sound.play("sound/b.au");
						listener.becomeMighy();
					}
					if (board[y][x].value==F) 
					{
						sound.play("sound/e.au");
						countFood--; 
						listener.addScore();
					}
					if (board[y][x].value==S){
						countFood--; 
						sound.play("sound/b.au");
						listener.becomeSuper();
					}
					
					board[y][x].value=E;
					if (countFood==0) listener.noFoodLeft();
				}
				if ( y==14 &&( x==27 || x==0) ){
					if (x==27){
						if (isPacman)
						{ 
							if (board[14][0].value==F)
							{
								countFood--; 
								listener.addScore();
								board[14][0].value=E;
							}		
							if (countFood==0) listener.noFoodLeft();
						}
						listener.enterTunnel("right");
					}
					else {
						listener.enterTunnel("left");
						if (isPacman) 
						{
							if (board[14][27].value==F)
							{
								countFood--; 
								listener.addScore();
								board[14][27].value=E; 
							}
							
							 if (countFood==0) listener.noFoodLeft(); 
						}
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean needChange(double xIndex, double yIndex, String went,boolean isWeak) 
	{

		int x=(int)xIndex;
		int y=(int)yIndex;
		x=x/(int)squareWidth;
		y=y/(int)squareHeight;	
		
		
		if (isWeak)
		{
			if (went.equals("up") &&  board[y-1][x].value==W)
				return true;
			if (went.equals("down") && board[y+1][x].value==W)
				return true;
			if (went.equals("left") && board[y][x-1].value==W)
				return true;
			if (went.equals("right")&& board[y][x+1].value==W)
				return true;
		}
		else
		{

			boolean up=false;
			boolean down=false;
			boolean left=false;
			boolean right=false;
	
			if (y-1 >0 )
				up=(board[y-1][x].value!=W);
			if (y+1 < board.length)
				down=(board[y+1][x].value!=W);
			if (x-1 >0)
				left=(board[y][x-1].value!=W);
			if  (x+1 < board[0].length)
				right=(board[y][x+1].value!=W);
			
			if (down && left)
				return true;
			if (down && right)
				return true;
			if (up && left )
				return true;
			if (up && right )
				return true;

			if (y==14 && (x==27 || x==0)){
				
			}
			else{
				if (went.equals("up") && board[y-1][x].value==W)
					return true;
				if (went.equals("down") && board[y+1][x].value==W)
					return true;
				if (went.equals("left") && board[y][x-1].value==W)
					return true;
				if (went.equals("right")&& board[y][x+1].value==W)
					return true;
			}
		}
		
		return false;
	}
	
	public void saveAnotherBoard(){
		board2=new int [board.length][board[0].length];
		for (int i=0; i<board.length; i++) 
			for (int j=0; j<board[0].length; j++)
				board2[i][j]=board[i][j].value;
	}
	
	public void setInit() {
	//	board=new Cell[board2.length][board2[0].length];
		for (int i=0; i<board.length; i++) 
			for (int j=0; j<board[0].length; j++)
				board[i][j].value=board2[i][j];
	}
	public void stopRun()
	{
		listener.stopRun();
	}


	@Override
	public void returnRegularPacmanState() {
		// TODO Auto-generated method stub
		listener.returnRegularPacmanState();
	}


}
