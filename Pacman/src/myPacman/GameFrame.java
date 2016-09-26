package myPacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.TimerTask;
import javax.swing.Timer;



import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JOptionPane;
import javax.naming.event.NamespaceChangeListener;
import javax.security.auth.callback.NameCallback;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;



import javax.swing.plaf.multi.MultiButtonUI;

import sun.audio.*;

public final class GameFrame extends JFrame implements BoardListener {

	private JLayeredPane multiGameBoard;
	private Board gameBoard;
	private Pacman gamePacman;
	private Ghost[] gameGhost;
	private Player playerListener;
	//private final int MAXGHOST=4;
	private JLabel[] heartLabel;
	private JLabel scoreLable;
	private JPanel title;
	private int pacSpeed;
	private int ghostTime;
	private Timer ghostTimer;
	private boolean oneTime;
	
	private int numOfGhosts;
	private double timeFirstDeath;
	private double timeGameOver;
	private double startTime;
	private int MAXLIFE=3;
	private boolean isComp=false;
	private Sound sound;
	final int W=1; // Wall.
	final int F=2; // Crossroads with food 
	final int E=3; // Empty crossroads
	final int B=4; // Mighty
	final int S=5; //Super
	boolean evenStrongGhost=true;
	boolean evenWeakGhost=true;
	boolean inCage;
	
	private static GameFrame gameFrame;
	//constructor
	//public GameFrame(String userName){ 
	
	public static GameFrame getInstance(String userName ,int ghostTime, String level, Player player)
	{
		if (gameFrame==null)
			gameFrame=new GameFrame(userName ,ghostTime,  level,  player);
		
			return gameFrame;
	}
	private GameFrame(String userName ,int ghostTime, String level, Player player ){ 
		
		super("The Pac-Man Game");
		sound=new Sound();
		playerListener=player;
		startTime=System.currentTimeMillis();
		isComp=false;
		inCage=true;
		if (userName.equals("Computer"))
		{
			isComp=true;
		}
		numOfGhosts=0;
		if (level.equals("E"))
			numOfGhosts=2;
		else if (level.equals("D"))
			numOfGhosts=4;
		
		this.ghostTime=ghostTime;
		//player=new Player(1,userName);
		
	//	System.out.println("UN: "+userName);
		multiGameBoard= new JLayeredPane();
		multiGameBoard.setPreferredSize(new Dimension(650,800));	
	/*	charactersSpeed=new int[numOfGhosts+1];
		charactersSpeed[0]=3;//pacman
		charactersSpeed[1]=2;
		charactersSpeed[2]=15; */
		pacSpeed=8;
	/*	if (numOfGhosts==4)
		{
		charactersSpeed[3]=8;
		charactersSpeed[4]=3;
		}*/
		// Create the content pane
		gameBoard=CreateBoard();
		
		
		//create ghosts
		gameGhost=new Ghost[numOfGhosts];
		for (int i=0; i<numOfGhosts; i++){
			if (i%2==0)
			gameGhost[i]=CreateGhost("weak");
			else
				gameGhost[i]=CreateGhost("strong");
		}
		//create pacman
		gamePacman = CreatePacman();
		if (isComp==true)
		{
			gamePacman.setRandomPolicy();
		}
		// Create and set up the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//score labele
		scoreLable=new JLabel(Integer.toString(playerListener.getScore()));
		scoreLable.setLocation(350, 590);
		scoreLable.setSize(200,100);
		scoreLable.setFont(new Font("Guttman Hatzvi", Font.BOLD, 62));
		scoreLable.setForeground(Color.white);
		multiGameBoard.add(scoreLable);
		//heart labels
		ImageIcon heart=new ImageIcon("img/heart.png");
		heartLabel = new JLabel[playerListener.getLife()];
		for(int i=0; i<3; i++){
			heartLabel[i] = new JLabel(heart); 
			multiGameBoard.add(heartLabel[i]);
			heartLabel[i].setLocation(100*i, 585);
			heartLabel[i].setSize(100, 100);
		}
		title=new JPanel();
		title.setSize(80,570);
		title.setBackground(Color.black);
		setTitle();
		multiGameBoard.add(title);
		title.setLocation(590, 15);
		// Display the window
		this.setSize(new Dimension(700,735));
		multiGameBoard.setVisible(true);
		this.setVisible(true);
		this.add(multiGameBoard);
		this.getContentPane().setBackground(Color.BLACK);
		
		gamePacman.setFocusable(true);
		gamePacman.requestFocus();

	}

	public void setTitle(){
		JLabel [] pugman=new JLabel[6];
		pugman[0]=new JLabel("P");
		pugman[1]=new JLabel("U");
		pugman[2]=new JLabel("G");
		pugman[3]=new JLabel("M");
		pugman[4]=new JLabel("A");
		pugman[5]=new JLabel("N");
		for(int i=0; i<6; i++){
			pugman[i].setSize(50,50);
			pugman[i].setLocation(500, 20+i*50);
			pugman[i].setFont(new Font("cooper black", Font.ROMAN_BASELINE, 70));
			pugman[i].setForeground(Color.yellow);
			title.add(pugman[i]);
		}
		
	}
	/**
	 * This function return a new pucman with his initial place
	 * @return Pacman = the initial pacman
	 */
	
	/**
	 * this function return a new ghost with initial place
	 */
	
	public void enterTunnel(String from){
		double squareHeight=gameBoard.getSquareHeight();
		double squareWidth=gameBoard.getSquareWidth();
		if (from.equals("right")){
			if ((int)(gamePacman.getXIndex()/squareWidth)==27 && (int)(gamePacman.getYIndex()/squareHeight)==14){
				gamePacman.tunnel(-(int)(27*squareWidth) + 20);				
			}
			else {
				for (int i=0; i<numOfGhosts; i++){
					if ((int)(gameGhost[i].getXIndex()/squareWidth)==27 && (int)(gameGhost[i].getYIndex()/squareHeight)==14)
						gameGhost[i].tunnel(-(int)(27*squareWidth) + 20);
				}
			}
		}	
		else {
	//		System.out.println(gamePacman.getXIndex()/squareWidth);
			if ((int)(gamePacman.getXIndex()/squareWidth)==0 && (int)(gamePacman.getYIndex()/squareHeight)==14)
				gamePacman.tunnel((int)(27*squareWidth) - 20);
			else {
				for (int i=0; i<numOfGhosts; i++){
					if  ((int)(gameGhost[i].getXIndex()/squareWidth)==0 && (int)(gameGhost[i].getYIndex()/squareHeight)==14)
						gameGhost[i].tunnel((int)(27*squareWidth) - 20);
				}
			}
		}
	}

	public void clashTreatment(){
		double xPac=gamePacman.getXIndex();
		double yPac=gamePacman.getYIndex();
		double squareHeight=gameBoard.getSquareHeight();
		double squareWidth=gameBoard.getSquareWidth();
		
		xPac=xPac/squareWidth;
		yPac=yPac/squareHeight;
		
		for (int i=0; i<numOfGhosts; i++){
			double xGhost=gameGhost[i].getXIndex();
			double yGhost=gameGhost[i].getYIndex();
			xGhost=xGhost/squareWidth;
			yGhost=yGhost/squareHeight;
			if ((int)(xGhost)==(int)(xPac) && (int)(yGhost)==(int)(yPac)){
				{
					//System.out.println("clashed!");
					gameGhost[i].clash(gamePacman);
				}
			}
		}
	}

	private void decreaseLife(){
		
		if (playerListener.getLife()==MAXLIFE)
			timeFirstDeath=(System.currentTimeMillis()-startTime)/1000;
		if (playerListener.getLife()==1)
			timeGameOver=(System.currentTimeMillis()-startTime)/1000;
		
	//	System.out.println("time first "+timeFirstDeath);
		//System.out.println("time for GO "+timeGameOver);
	//	System.out.println("start time "+startTime);
		//System.out.println(System.currentTimeMillis()-startTime);
		heartLabel[playerListener.getLife()-1].setVisible(false);
		playerListener.setLife(-1);
	}
	
	public void stopRun ()
	{
		returnRegularPacmanState();
	//	gameBoard.closeCage();
		gamePacman.stopRunning();
		if (gamePacman.tM!=null)
			gamePacman.tM.stop();
		gamePacman.setStopToPlay();
		for (int i=0;i<numOfGhosts;i++)
			gameGhost[i].stopRunning();
		decreaseLife(); 
		if (playerListener.getLife()>0){	
			initiat();
			
		}
		else {
			initiat();
			gameOverLose();
			newGame();
			
		}
	}
	
	public void newGame(){
		startTime=System.currentTimeMillis();
		gameBoard.setInit();
		gameBoard.repaint();
		playerListener.setScore(-playerListener.getScore());
		playerListener.setLife(3-playerListener.getLife());
		for (int i=0; i<playerListener.getLife();i++)
			heartLabel[i].setVisible(true);
		
		gamePacman.setVisible(true);
		gamePacman.timer.start();
		
		for (int i=0;i< numOfGhosts; i++){
			gameGhost[i].setVisible(true);
			gameGhost[i].timer.start();
		}
	}
	
	public void initiat(){
		inCage=true;
		double squareHeight=gameBoard.getSquareHeight();
		double squareWidth=gameBoard.getSquareWidth();
		gamePacman.initialPlace(14* squareWidth+squareWidth/2, 22.5*(squareHeight)+squareHeight/2);
		
		
		for (int i=0; i<numOfGhosts;i++)
			gameGhost[i].initialPlace(14* squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
		repaint();
		
	}
	
	public void noFoodLeft(){
		gamePacman.stopRunning();
		gamePacman.setStopToPlay();
		for (int i=0;i<numOfGhosts;i++)
			gameGhost[i].stopRunning();
		gameOverWin();
	//	System.exit(0);
	//	initiat();
	//	newGame();
	}

	public void addScore(){
		playerListener.setScore(1);
		scoreLable.setText(Integer.toString(playerListener.getScore()));
	}
	
	
	public void addBonus(){
		playerListener.setScore(5);
		scoreLable.setText(Integer.toString(playerListener.getScore()));
	}
	

	public void gameOverWin(){
		gamePacman.timer.stop();
		for (int i=0; i<numOfGhosts;i++){
			gameGhost[i].timer.stop();
			gameGhost[i].setVisible(false);
		}
		gamePacman.setVisible(false);
		playerListener.setScore(playerListener.getLife()*20);
		sound.play("sound/w.au");
		JOptionPane.showMessageDialog(null, "congratulation "+ playerListener.getName() +", you WON!", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		updateBoards ();
		this.setVisible(false);
	}
	

	public void gameOverLose(){
		gamePacman.timer.stop();
		for (int i=0; i<numOfGhosts;i++){
			gameGhost[i].timer.stop();
			gameGhost[i].setVisible(false);
		}
		gamePacman.setVisible(false);
		JOptionPane.showMessageDialog(null,"sorry "+playerListener.getName()+ ", you LOST!", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		updateBoards ();
		this.setVisible(false);
	}
	
	public void updateBoards ()
	{
		Winners_Statistic_Screen winners;
		LongestTimeTillFirstDeath_Screen first;
		LongestTimeTillGameOver_Screen gameOver;
		try {
			winners = new Winners_Statistic_Screen("");
			first=new LongestTimeTillFirstDeath_Screen("");
			gameOver=new LongestTimeTillGameOver_Screen("");
		//	System.out.println(player.getUsername());
			winners.addScore(playerListener.getUsername(), playerListener.getScore());
			first.addTime(playerListener.getUsername(), timeFirstDeath);
			gameOver.addTime(playerListener.getUsername(), timeGameOver);
			StatisticNavigator_Board statisticBoard=new StatisticNavigator_Board("Statistic Navigator Board");
			sound.play("sound/who.au");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	public void gameStarted()  {
		//Easy","Difficult		
		ghostTimer=new Timer(ghostTime*1000,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==ghostTimer)
				{
					if (inCage){
						for (int i=0; i<numOfGhosts; i++)
							gameGhost[i].startRun();
						inCage=false;
					}
				}
				repaint();
			}
		});
		ghostTimer.setRepeats(false);
        ghostTimer.start(); 
	}

	
public static void main(String args[]) throws Exception {
		//String name = JOptionPane.showInputDialog("What is your name?", null);	
	Login_Screen login_screen=new Login_Screen("Login");
}

@Override
public double getPacX() {
	// TODO Auto-generated method stub
	return gamePacman.getXIndex();
}

@Override
public double getPacY() {
	// TODO Auto-generated method stub
	return gamePacman.getYIndex();
}

@Override
public boolean checkAvailableMovement(double newXposition, double newYposition,
		boolean isPacman) {
	// TODO Auto-generated method stub
	return gameBoard.checkAvailableMovement(newXposition, newYposition, isPacman);
}

@Override
public String getLongestOpenPath(double oldX, double oldY) {
	// TODO Auto-generated method stub
	int up=0,down=0,left=0,right=0;
	boolean more=true;
	boolean[]checks=new boolean[4];
	checks[0]=true;checks[1]=true;checks[2]=true;checks[3]=true;
	int x=(int)oldX/28;
	int y=(int)oldY/31;
	
	while (more)
	{
		if (checks[0] && y-up>=0 && this.gameBoard.board[x][y-up].value!=W)
			up++;
		else
			checks[0]=false;
		if (checks[1] && y+down<this.gameBoard.board[0].length && this.gameBoard.board[x][y+down].value!=W)
			down++;
		else
			checks[1]=false;
		if (checks[2] && x+right<this.gameBoard.board.length && this.gameBoard.board[x+right][y].value!=W)
			right++;
		else
			checks[2]=false;
		if (checks[3] && x-left>=0 && this.gameBoard.board[x-left][y].value!=W)
			left++;
		else
			checks[3]=false;
		more=checks[0]||checks[1]||checks[2]||checks[3];
		
	}
	String maxS="up";
	int maxI=up;
	if (down>maxI)
	{
		maxS="down";
		maxI=down;
	}
	if (right>maxI)
	{
		maxS="right";
		maxI=right;
	}
	if (left>maxI)
	{
		maxI=left;
		maxS="left";
	}
	return maxS;
}


public Pacman CreatePacman() {
	// TODO Auto-generated method stub
	
	
	double squareHeight=gameBoard.getSquareHeight();
	double squareWidth=gameBoard.getSquareWidth();
	Pacman gamePacman=new RegularPacman(14* squareWidth+squareWidth/2,22.5*(squareHeight)+squareHeight/2,pacSpeed, isComp);
	gamePacman.setOpaque(false);
	gamePacman.setSize(gameBoard.getMapWidth(), gameBoard.getMapHeight());
	multiGameBoard.add(gamePacman,new Integer(2));
	
	gamePacman.addListener(gameBoard);
	
	return gamePacman;
}




public Ghost CreateGhost(String strength) {
	double squareHeight=gameBoard.getSquareHeight();
	double squareWidth=gameBoard.getSquareWidth();
	Ghost ghost;
	if (strength.equals("weak")==true)
	{
		if (evenWeakGhost==true)
		{
		ghost=new WeakGhost(14* squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2,pacSpeed-1,this);
		evenWeakGhost=!evenWeakGhost;
		}
		else
		{
			ghost=new WeakGhost(14* squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2,pacSpeed-6,this);
			evenWeakGhost=!evenWeakGhost;
		}
	}
	else
	{
		if (evenStrongGhost==true)
		{
		ghost=new MightyGhost(14* squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2,pacSpeed+1,this);
		evenStrongGhost=!evenStrongGhost;
		}
		else
		{
			ghost=new MightyGhost(14* squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2,pacSpeed+6,this);
			evenStrongGhost=!evenStrongGhost;
		}
	}
	ghost.addListener(gameBoard);
	ghost.setOpaque(false);
	ghost.setSize(gameBoard.getMapWidth(), gameBoard.getMapHeight());
	ghost.setVisible(true);
	multiGameBoard.add(ghost, 0);
	
	return ghost;
	
}



public Board CreateBoard() {

	// TODO Auto-generated method stub
	Board board = new Board();
	board.setSize(board.getMapWidth(), board.getMapHeight());
	board.addListener(this);
	multiGameBoard.add(board,0);		
	board.setBorder(new LineBorder(Color.orange, 0));
	
	return board;
}
@Override
public void becomeMighy() {
	//System.out.println("became mighty");
	int dx=gamePacman.deltaX;
	int dy=gamePacman.deltaY;
	sound.play("sound/powerup.au");
	if (gamePacman.tM!=null)
		gamePacman.tM.stop();
	Pacman p=new MightyPacman(gamePacman.x,gamePacman.y,pacSpeed, isComp);
	p.setVisible(true);
	p.setSize(gameBoard.getMapWidth(),gameBoard.getMapHeight());
	p.setLocation(0,0);
	p.setLayout(null);
	p.setOpaque(false);
	p.addListener(gameBoard);
	gamePacman.stopRunning();
	gamePacman.timer.stop();
	multiGameBoard.remove(gamePacman);
	multiGameBoard.add(p,new Integer(2));
	p.requestFocusInWindow();
	if (gamePacman.isComp)
		p.setRandomPolicy();
	gamePacman=p;
	gamePacman.startRunning();
	gamePacman.timer.start();
	repaint();
if (dx==1)
	dx=-1;
else if (dx==-1)
	dx=1;
if (dy==1)
	dy=-1;
else if (dy==-1)
	dy=1;
	gamePacman.deltaX=dx;
	gamePacman.deltaY=dy;
	gamePacman.tM.start();	
}

@Override
public void becomeSuper() {
//	System.out.println("became super ");
	sound.play("sound/powerup.au");
	int dx=gamePacman.deltaX;
	int dy=gamePacman.deltaY;
	if (gamePacman.tM!=null)
		gamePacman.tM.stop();
	Pacman p=new SuperPacman(gamePacman.x,gamePacman.y,pacSpeed, isComp);
	p.setVisible(true);
	p.setSize(gameBoard.getMapWidth(),gameBoard.getMapHeight());
	p.setLocation(0,0);
	p.setLayout(null);
	p.setOpaque(false);
	p.addListener(gameBoard);
	gamePacman.stopRunning();
	gamePacman.timer.stop();
	multiGameBoard.remove(gamePacman);
	multiGameBoard.add(p,new Integer(2));
	p.requestFocusInWindow();
	if (gamePacman.isComp)
		p.setRandomPolicy();
	gamePacman=p;
	gamePacman.startRunning();
	gamePacman.timer.start();
	repaint();
	if (dx==1)
		dx=-1;
	else if (dx==-1)
		dx=1;
	if (dy==1)
		dy=-1;
	else if (dy==-1)
		dy=1;
	gamePacman.deltaX=dx;
	gamePacman.deltaY=dy;
	gamePacman.tM.start();
	
}

public void returnRegularPacmanState ()
{
//	System.out.println("returned to be Regular");
	sound.play("sound/powerdown.au");
	int dx=gamePacman.deltaX;
	int dy=gamePacman.deltaY;
	if (gamePacman.tM!=null)
		gamePacman.tM.stop();
	Pacman p=new RegularPacman(gamePacman.x,gamePacman.y,pacSpeed, isComp);
	p.setVisible(true);
	p.setSize(gameBoard.getMapWidth(),gameBoard.getMapHeight());
	p.setLocation(0,0);
	p.setLayout(null);
	p.setOpaque(false);
	p.addListener(gameBoard);
	gamePacman.stopRunning();
	gamePacman.timer.stop();
	multiGameBoard.remove(gamePacman);
	multiGameBoard.add(p,new Integer(2));
	p.requestFocusInWindow();
	if (gamePacman.isComp)
		p.setRandomPolicy();
	gamePacman=p;
	gamePacman.startRunning();
	gamePacman.timer.start();
	repaint();
	if (dx==1)
		dx=-1;
	else if (dx==-1)
		dx=1;
	if (dy==1)
		dy=-1;
	else if (dy==-1)
		dy=1;
	gamePacman.deltaX=dx;
	gamePacman.deltaY=dy;

}

}
