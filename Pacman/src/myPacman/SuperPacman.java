package myPacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.ImageIcon;



public class SuperPacman extends Pacman{
	protected Timer ghostWaitInCageTimer1;
	protected Timer ghostWaitInCageTimer2;
	protected Timer ghostWaitInCageTimer3;
	protected Timer ghostWaitInCageTimer4;
	
	
	int counter=0;
	public SuperPacman(double startingPointX, double startingPointY, int speed,
			boolean isComp) {
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
	//	System.out.println("Super-Pac met Weak ghost");
		Sound sound=new Sound();
		sound.play("sound/sgrowl.au");
		ghost.stopRunning();
		double squareHeight=listener.getSquareHeight();
		double squareWidth=listener.getSquareWidth();
		ghost.initialPlace(14* squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
	//	ghost.setVisible(false);
		counter++;
		if (counter%4==0)
		{
		ghostWaitInCageTimer1=new Timer(2500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==ghostWaitInCageTimer1)
				{
					ghost.startRun();
				}
				repaint();
			}
		});
		ghostWaitInCageTimer1.setRepeats(false);
		ghostWaitInCageTimer1.start(); 
		}
		else if (counter%4==1)
		{
		ghostWaitInCageTimer2=new Timer(2500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==ghostWaitInCageTimer2)
				{
					ghost.startRun();
				}
				repaint();
			}
		});
		ghostWaitInCageTimer2.setRepeats(false);
		ghostWaitInCageTimer2.start(); 
		}
		else if (counter%4==2)
		{
		ghostWaitInCageTimer3=new Timer(2500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==ghostWaitInCageTimer3)
				{
					ghost.startRun();
				}
				repaint();
			}
		});
		ghostWaitInCageTimer3.setRepeats(false);
		ghostWaitInCageTimer3.start(); 
		}
		else if (counter%4==3)
		{
		ghostWaitInCageTimer4=new Timer(2500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==ghostWaitInCageTimer4)
				{
					ghost.startRun();
				}
				repaint();
			}
		});
		ghostWaitInCageTimer4.setRepeats(false);
		ghostWaitInCageTimer4.start(); 
		}
	}

	@Override
	public void visit(final MightyGhost ghost) {
		// TODO Auto-generated method stub
	//	System.out.println("Super-Pac met Strong ghost");
		Sound sound=new Sound();
		sound.play("sound/sgrowl.au");
		double squareHeight=listener.getSquareHeight();
		double squareWidth=listener.getSquareWidth();
		ghost.initialPlace(14* squareWidth+squareWidth/2,13.5*(squareHeight)+squareHeight/2);
		counter++;
	//	ghost.setVisible(false);
		if (counter%4==0)
		{
		ghostWaitInCageTimer1=new Timer(2500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==ghostWaitInCageTimer1)
				{
					ghost.startRun();
				}
				repaint();
			}
		});
		ghostWaitInCageTimer1.setRepeats(false);
		ghostWaitInCageTimer1.start(); 
		}
		else if (counter%4==1)
		{
		ghostWaitInCageTimer2=new Timer(2500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==ghostWaitInCageTimer2)
				{
					ghost.startRun();
				}
				repaint();
			}
		});
		ghostWaitInCageTimer2.setRepeats(false);
		ghostWaitInCageTimer2.start(); 
		}
		else if (counter%4==2)
		{
		ghostWaitInCageTimer3=new Timer(2500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==ghostWaitInCageTimer3)
				{
					ghost.startRun();
				}
				repaint();
			}
		});
		ghostWaitInCageTimer3.setRepeats(false);
		ghostWaitInCageTimer3.start(); 
		}
		else if (counter%4==3)
		{
		ghostWaitInCageTimer4=new Timer(2500,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==ghostWaitInCageTimer4)
				{
					ghost.startRun();
				}
				repaint();
			}
		});
		ghostWaitInCageTimer4.setRepeats(false);
		ghostWaitInCageTimer4.start(); 
		}
		
	}
	
	@Override
	public ImageIcon leftIcone() {
		return new ImageIcon("img/sLeft.png");
	}
	@Override
	public ImageIcon rightIcone() {
		return new ImageIcon("img/sRight.png");
	}
	@Override
	public ImageIcon upIcone() {
		return new ImageIcon("img/sUp.png");
	}
	@Override
	public ImageIcon downIcone() {
		return new ImageIcon("img/sDown.png");
	}
}
