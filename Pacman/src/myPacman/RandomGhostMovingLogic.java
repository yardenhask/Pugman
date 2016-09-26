package myPacman;

import java.awt.List;

public class RandomGhostMovingLogic implements MovingLogic {


	public RandomGhostMovingLogic(){}
	@Override
	public String calcNewDirection(double pacX, double pacY, double x, double y, BoardListener pacmanLocator){
		int f = (int) ((Math.random()*4)+1);  
		if (f==1)
			return "up";
		else if (f==2)
			return "right";
		else if(f==3)
			return "down";
		else return "left";	
	}



	

}
