package myPacman;

public class WeakGhost extends Ghost  {

	public WeakGhost(double startingPointX, double startingPointY, int speed,
			BoardListener pacLocator) {
		super(startingPointX, startingPointY, speed, pacLocator,true);
		// TODO Auto-generated constructor stub
		calcSteps=new RandomGhostMovingLogic();
		pacmanLocator=null;
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
	
		v.visit(this);
	}

	@Override
	public void clash(Pacman pac){
		this.accept(pac);
	}
}
