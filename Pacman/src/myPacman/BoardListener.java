package myPacman;

interface BoardListener {
	void enterTunnel(String from);
	void noFoodLeft();
	void addScore();
	void addBonus();
	void clashTreatment();
	void gameStarted();
	double getPacX();
	double getPacY();
	boolean checkAvailableMovement(double newXposition, double newYposition, boolean isPacman);
	String getLongestOpenPath (double x,double y);
	void stopRun();
	void becomeMighy();
	void becomeSuper();
	void returnRegularPacmanState();

}
