package myPacman;

public class MightyGhost extends Ghost{

	public MightyGhost(double startingPointX, double startingPointY, int speed,
			BoardListener pacLocator) {
		super(startingPointX, startingPointY, speed, pacLocator,false);
		// TODO Auto-generated constructor stub
		calcSteps=new PacmanOrientedMovingLogic();
		pacmanLocator=pacLocator;
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
