package myPacman;

public class RegularPacman extends Pacman{

	public RegularPacman(double startingPointX, double startingPointY,
			int speed, boolean isComp) {
		super(startingPointX, startingPointY, speed, isComp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visit(WeakGhost ghost) {
		// TODO Auto-generated method stub
	//	System.out.println("Reular pac met Weak Ghost");
		Sound sound=new Sound();
		sound.play("sound/c.au");
		listener.stopRun();
	}

	@Override
	public void visit(MightyGhost ghost) {
		// TODO Auto-generated method stub
	//	System.out.println("Reular pac met Strong Ghost");
		Sound sound=new Sound();
		sound.play("sound/c.au");
		listener.stopRun();
	}

}
