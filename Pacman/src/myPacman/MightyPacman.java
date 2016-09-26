package myPacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


import javax.swing.ImageIcon;

public class MightyPacman extends Pacman {
protected Timer ghostWaitInCageTimer;
	public MightyPacman(double startingPointX, double startingPointY,
			int speed, boolean isComp) {
		super(startingPointX, startingPointY, speed, isComp);
		// TODO Auto-generated constructor stub
		
		tM=new Timer(10000,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.returnRegularPacmanState();	
			}
		});
		tM.setRepeats(false);
		tM.start();
	}

	@Override
	public void visit(final WeakGhost ghost) {
		
		// TODO Auto-generated method stub
	//	System.out.println("Mighty pac met Weak Ghost");
		Sound sound=new Sound();
		sound.play("sound/mgrowl.au");
		double squareHeight=listener.getSquareHeight();
		double squareWidth=listener.getSquareWidth();
		ghost.initialPlace(14* squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
		ghostWaitInCageTimer=new Timer(3000,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==ghostWaitInCageTimer)
				{
					ghost.startRun();
		//			ghost.timer.start();
				}
				repaint();
			}
		});
		ghostWaitInCageTimer.setRepeats(false);
		ghostWaitInCageTimer.start(); 

	}

	@Override
	public void visit(MightyGhost ghost) {
	//	System.out.println("Mighty pac met Mighty Ghost");
		// TODO Auto-generated method stub
		Sound sound=new Sound();
		sound.play("sound/c.au");
		listener.stopRun();
	}
		@Override
	public ImageIcon leftIcone() {
		return new ImageIcon("img/mLeft.png");
	}
	@Override
	public ImageIcon rightIcone() {
		return new ImageIcon("img/mRight.png");
	}
	@Override
	public ImageIcon upIcone() {
		return new ImageIcon("img/mUp.png");
	}
	@Override
	public ImageIcon downIcone() {
		return new ImageIcon("img/mDown.png");
	}
}


