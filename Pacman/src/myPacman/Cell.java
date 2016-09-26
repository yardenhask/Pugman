package myPacman;

public class Cell {

	double x;
	double y;
	
	final int W=1; // Wall.
	final int F=2; // Crossroads with food 
	final int E=3; // Empty crossroads
	final int B=4; // Mighty
	final int S=5; //Super 
	public int value;
	
	public Cell (int value)
	{
		this.value=value;
	}

}
