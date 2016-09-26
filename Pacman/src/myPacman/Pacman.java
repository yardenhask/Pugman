package myPacman;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public abstract class Pacman extends Character implements KeyListener,Visitor {
	
	boolean start;

	private RandomGhostMovingLogic calcSteps;
	boolean isComp=false;
	 protected Timer tM;
	//Constructor
	public Pacman(double startingPointX, double startingPointY,int speed, boolean isComp){
		super(startingPointX, startingPointY, speed);
		
		this.isComp=isComp;
		this.image = leftIcone();
		this.setVisible(true);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus(true);
		start=false;
		if (isComp==true)
		{
			
			timer=new Timer(speed,new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getSource()==timer)
					{
						if (!start)
						{
						pacmanStart();
						start=true;
						}
						String went;
						if (deltaX>0)
						{
							image = rightIcone();
							went="right";
						}
						else if (deltaX<0)
						{
							image = leftIcone();
							went="left";
						}
						else if (deltaY>0)
						{
							image=downIcone();
							went="down";
						}
						else {went="up"; image=upIcone();}
						if (listener.needChange(getXIndex(),getYIndex(),went,true) )
							walk();
						move();
						listener.clashTreatment();
					}
					repaint();
				}
			});
		}
		//timer.start();
		timer.start();
	}
	
	//images
	public ImageIcon leftIcone() {
		return new ImageIcon("img/PacmanLeft.png");
	}
	public ImageIcon rightIcone() {
		return new ImageIcon("img/PacmanRight.png");
	}
	public ImageIcon upIcone() {
		return new ImageIcon("img/PacmanUp.png");
	}
	public ImageIcon downIcone() {
		return new ImageIcon("img/PacmanDown.png");
	}
	/**
	 * move the character to his new position
	 */
	protected void move(){
		if (listener.checkAvailableMovement(getXIndex()+deltaX, getYIndex()+deltaY, true)){
			setXindex(getXIndex()+deltaX);
			setYindex(getYIndex()+deltaY);
		}	
	}
	public void setStopToPlay()
	{
		start=false;
	}
	
	
	/**
	 * key presses
	 */
	public void keyPressed(KeyEvent e) {
		
		if (!start){
			pacmanStart();
			start=true;
		}
		if (isComp==false)
		{
			if (e.getKeyCode()==KeyEvent.VK_LEFT){
				image = leftIcone();
				setMoveLeft();
			}
			if (e.getKeyCode()==KeyEvent.VK_RIGHT){
				image = rightIcone();
				setMoveRight();
			}
			if (e.getKeyCode()==KeyEvent.VK_UP){
				image = upIcone();
				setMoveUp();
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN){
				image = downIcone();
				setMoveDown();
			}
		}
	}
	
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
	}
	public boolean getStart(){
		return start;
	}

	public void setRandomPolicy() {
		// TODO Auto-generated method stub
		calcSteps=new RandomGhostMovingLogic();
	}
	public void walk(){


		String direction=calcSteps.calcNewDirection(0,0,0,0,null);
		if (direction.equals("up"))
			setMoveUp();
		else if (direction.equals("down"))
			setMoveDown();
		else if (direction.equals("left"))
			setMoveLeft();
		else setMoveRight();
	}
	
	
}

