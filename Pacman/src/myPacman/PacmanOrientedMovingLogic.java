package myPacman;

import java.awt.List;
import java.util.Set;

public class PacmanOrientedMovingLogic implements MovingLogic{
	int counter=0;
	boolean inOppositeDirection=false;
	
	@Override
	public String calcNewDirection(double pacX,double pacY, double x,double y, BoardListener pacmanLocator) {
		// TODO Auto-generated method stub
		
		int delX=(int)pacX-(int)x;
		int delY=(int)pacY-(int)y;
		
		String value="";
		if (delX==0){
			if (delY>0){
				if(pacmanLocator.checkAvailableMovement(x, y+1, false)){
					value= "down";
				}
				else
					inOppositeDirection=true;
			}
			else{
				if(pacmanLocator.checkAvailableMovement(x, y-1, false)){
					value= "up";
				}
				else
					inOppositeDirection=true;
			}
		}
		else if (delY==0){
			if (delX>0){
				if(pacmanLocator.checkAvailableMovement(x+1, y, false)){
					value= "right";
				}
				else
					inOppositeDirection=true;
			}
			else{
				if(pacmanLocator.checkAvailableMovement(x-1, y, false)){
					value= "left";
				}
				else
					inOppositeDirection=true;
			}
		}
		else if(!inOppositeDirection){	
			if ( delX>0 ){
				 if ( pacmanLocator.checkAvailableMovement(x+1, y, false))
					 value= "right";
				 else if (delY>0){
					 if (pacmanLocator.checkAvailableMovement(x, y+1, false)){
						 value= "down";
					 }
					 else{
						 inOppositeDirection=true;
					 }
				 }
				 else if (pacmanLocator.checkAvailableMovement(x, y-1, false)){
					 value="up";
				 }
				 else{
					 inOppositeDirection=true;
				 }
			 }
			 else if (delY>0){
				 if ( pacmanLocator.checkAvailableMovement(x, y+1, false))
					 value= "down";
				 else if (delX>0){
					 if ( pacmanLocator.checkAvailableMovement(x+1, y, false))
						 value= "right";
					 else{
						 inOppositeDirection=true;
					 }
				 }
				 else if (pacmanLocator.checkAvailableMovement(x-1, y, false))
					 value="left";
				 else
					 inOppositeDirection=true;
			 }
			 else if (delY<0){
				 if ( pacmanLocator.checkAvailableMovement(x, y-1, false))
					 value= "up";
				 else if (delX>0){
					 if (pacmanLocator.checkAvailableMovement(x+1, y, false))
						 value= "right";
					 else
						 inOppositeDirection=true; 
				 }
				 else if (pacmanLocator.checkAvailableMovement(x-1, y, false))
					 value="left";
				 else
					 inOppositeDirection=true; 
			 }
			 else if ( delX<0){
				 if ( pacmanLocator.checkAvailableMovement(x-1, y, false))
					 value= "left";
				 else if (delY>0){
					 if (pacmanLocator.checkAvailableMovement(x, y+1, false))
							value= "down";
					 else
						 inOppositeDirection=true; 
				 }
				 else if (pacmanLocator.checkAvailableMovement(x, y-1, false))
						value="up";
				else
					inOppositeDirection=true; 
			 }
		}
		else{
			counter--;
			
			if (delX==0 && delY>0 && pacmanLocator.checkAvailableMovement(x, y-1, false) ){
				value= "up";
			}
			else if (delX==0 && delY>0 && pacmanLocator.checkAvailableMovement(x, y+1, false)){
				value= "down";
			}
			else if (delY==0 && delX>0 && pacmanLocator.checkAvailableMovement(x-1, y, false) ){
				value= "left";
			}
			else if (delY==0 && delX>0 && pacmanLocator.checkAvailableMovement(x+1, y, false) ){
				value= "right";
			}
			else if ( delX>0 ){
				if (pacmanLocator.checkAvailableMovement(x, y-1, false)){
					value= "up";
				}
				else if ( pacmanLocator.checkAvailableMovement(x-1, y, false))
					value= "left";
				 }
			else if (delY>0){
				if( pacmanLocator.checkAvailableMovement(x-1, y, false))
					value= "left";
				else if ( pacmanLocator.checkAvailableMovement(x, y-1, false))
					value= "up";
			}
			else if (delY<0){
				if ( pacmanLocator.checkAvailableMovement(x-1, y, false))
					value= "left";
				else if (pacmanLocator.checkAvailableMovement(x, y+1, false))
					value= "down";
			}
			else {
				if ( pacmanLocator.checkAvailableMovement(x, y-1, false))
					value= "up";
				else if (pacmanLocator.checkAvailableMovement(x+1, y, false))
					value= "right";
			}
		
			if (counter==0)
				inOppositeDirection=false;
		}		
		if (inOppositeDirection && counter==0){
				
			counter=50;
			if (delX==0 && delY>0 && pacmanLocator.checkAvailableMovement(x, y-1, false) ){
				value= "up";
			}
			else if (delX==0 && delY>0 && pacmanLocator.checkAvailableMovement(x, y+1, false)){
				value= "down";
			}
			else if (delY==0 && delX>0 && pacmanLocator.checkAvailableMovement(x-1, y, false) ){
				value= "left";
			}
			else if (delY==0 && delX>0 && pacmanLocator.checkAvailableMovement(x+1, y, false) ){
				value= "right";
			}
			else if ( delX>0 ){
				if (pacmanLocator.checkAvailableMovement(x, y-1, false)){
					value= "up";
				}
				else if ( pacmanLocator.checkAvailableMovement(x-1, y, false))
					value= "left";
				 }
			else if (delY>0){
				if( pacmanLocator.checkAvailableMovement(x-1, y, false))
					value= "left";
				else if ( pacmanLocator.checkAvailableMovement(x, y-1, false))
					value= "up";
			}
			else if (delY<0){
				if ( pacmanLocator.checkAvailableMovement(x-1, y, false))
					value= "left";
				else if (pacmanLocator.checkAvailableMovement(x, y+1, false))
					value= "down";
			}
			else {
				if ( pacmanLocator.checkAvailableMovement(x, y-1, false))
					value= "up";
				else if (pacmanLocator.checkAvailableMovement(x+1, y, false))
					value= "right";
			}
				 
		}
		return value;
	}


	private String longestOpenDirection (double x,double y, BoardListener pacmanLocator)
	{
		
	return	pacmanLocator.getLongestOpenPath(x, y);
		
	}


}
