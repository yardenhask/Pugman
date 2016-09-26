package myPacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.transaction.xa.Xid;

public abstract class Ghost extends Character implements Visited {

	static int color=0; 
	protected MovingLogic calcSteps;
	protected BoardListener pacmanLocator;
	
	//Constructor
	public Ghost(double startingPointX, double startingPointY,int speed,final BoardListener pacLocator,final boolean isWeak){
		super(startingPointX, startingPointY, speed);
		pacmanLocator=pacLocator;
		calcSteps=new RandomGhostMovingLogic();
		if(color<5)/// max ghost number
			color++;
		this.image = spiritIcon();
		this.setVisible(true);
		if (isWeak) deltaX=-1;
		timer=new Timer(speed,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==timer)
				{
					String went;
					if (deltaX>0)
						went="right";
					else if (deltaX<0)
						went="left";
					else if (deltaY>0)
						went="down";
					else went="up";
					
					
					
					if (listener.needChange(getXIndex(),getYIndex(),went,isWeak))
					{
						if (isWeak  ){
							walk(0,0,0,0);
						}
						else{
					/*		int ix=(int) (getXIndex()/listener.getSquareWidth());
							int iy=(int) (getYIndex()/listener.getSquareHeight());
							
							if (iy==14 && (ix==27 || ix==0)){
								
							}
							else*/ walk(pacLocator.getPacX(),pacLocator.getPacY(),x,y);
						}
					}
					move();
				}
				repaint();
			}
		});
		timer.start(); 
	}
		
	public ImageIcon spiritIcon() {
		return new ImageIcon("img/spirit"+color+".png");
	}
	
	public void startRun(){
		setYindex(200);
		setXindex(280);
		repaint();
	}
	
	public void walk(double pacX,double pacY,double xx, double yy){
		
		String direction=calcSteps.calcNewDirection(pacX,pacY,x,y,pacmanLocator);

		if (direction.equals("up"))
			setMoveUp();
		else if (direction.equals("down"))
			setMoveDown();
		else if (direction.equals("left"))
			setMoveLeft();
		else setMoveRight();
	}


	public abstract void accept (Visitor v);
	
	public void clash(Pacman pac){
		this.accept(pac);
	}
}
