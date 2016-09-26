package myPacman;

interface CharacterListener {
	boolean checkAvailableMovement(double newXPsition, double newYposition, boolean isPacman);
	void pacmanStart();
	void clashTreatment();
	//boolean isCrossRoads(double x, double y);
	boolean needChange(double xIndex, double yIndex, String went,boolean isWeak);
	void stopRun();
	double getSquareHeight();
	double getSquareWidth();
	void returnRegularPacmanState();

}
