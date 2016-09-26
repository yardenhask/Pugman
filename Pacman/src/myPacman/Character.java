package myPacman;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Character extends JPanel{
 
	protected ImageIcon image;
	protected double x;//present position
	protected double y;//present position
	protected int deltaX=0;//future position 
	protected int deltaY=0;//future position	
	protected Timer timer; //for speed
	protected CharacterListener listener;
	protected int cSpeed;
	
	public Character(double startingPointX, double startingPointY, int speed){
		zeroDeltaXY();
		setXindex(startingPointX);
		setYindex(startingPointY);
		cSpeed=speed;
		timer=new Timer(speed,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource()==timer)
				{
					move();
					listener.clashTreatment();
				}
				repaint();
			}
		});
		this.setVisible(true);
	}
	
//	public void walk(){}
	
	/**
	 * add listener to character
	 */
	public void addListener(CharacterListener listenerToAdd) {
		listener=listenerToAdd;
    }
	/**
	 * initial the character direction
	 */	
	public void zeroDeltaXY(){
		this.deltaX=0;
		this.deltaY=0;
	}
	/**
	 * move the character to his new position
	 */
	protected void move(){
		if (listener.checkAvailableMovement(x+deltaX, y+deltaY, false)){
			this.x=x+deltaX;
			this.y=y+deltaY;
			
		}	
	}
	
	public void pacmanStart(){
		listener.pacmanStart();
	}
	
	public void tunnel(int dx){
		deltaX=dx;
		move();
		repaint();
		if (dx<0)
			setMoveRight();
		else
			setMoveLeft();
	}
	
	/**
	 * @param x = x index on the board
	 */	
	public void setXindex(double x){

		this.x=x;
	}
	/**
	 * @param y = y index on the board
	 */	
	public void setYindex(double y){
		this.y=y;
	}
	/**
	 * @return x = x index on the board
	 */	
	public double getXIndex(){
		return this.x;
	}
	
	/**
	 * @return y = y index on the board
	 */	
	public double getYIndex(){
		return this.y;
	}
	public void paint(Graphics g){
		super.paint(g);
		image.paintIcon(this, g, (int) x,(int) y);
	}
	/**
	 * This function sets the Character direction to be up 
	 */	
	public void setMoveUp(){
		deltaX=0; deltaY=-1;
	}
	/**
	 * This function sets the Character direction to be down 
	 */	
	public void setMoveDown(){
		deltaX=0; deltaY=1;
	}
	/**
	 * This function sets the Character direction to be right 
	 */	
	public void setMoveRight(){
		deltaX=1; deltaY=0;
	}
	/**
	 * This function sets the Character direction to be left 
	 */	
	public void setMoveLeft(){
		deltaX=-1; deltaY=0;
	}

public void stopRunning ()
{
	timer.stop();
}

public void startRunning ()
{
	setFocusable(true);
	timer.start();
}

public void initialPlace (double xx, double yy)
{
	deltaX=(int)xx;
	deltaY=(int)yy;
	setXindex(0);
	setYindex(0);
	move();
	repaint();
	zeroDeltaXY();
	timer.start();
}


}
