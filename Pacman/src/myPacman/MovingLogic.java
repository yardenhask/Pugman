package myPacman;

public interface MovingLogic {

	public String calcNewDirection(double pacX,double pacY, double x,double y, BoardListener pacmanLocator);
}
