package myPacman;

public class Player  implements PlayerListener{

	private int life;
	private int score;
	private String level;
	private String name;
	
	
	
	public Player (String playerName, int ghostTime, String lvl)
	{
		life=3;
		score=0;
		level=lvl;
		name=playerName;
		GameFrame.getInstance(playerName, ghostTime, level , this);	
	}
	public String getUsername () {return name;}
	
	
	public void  setLife (int addition)
	{
		life+=addition;
	
	}
	
	public void setScore (int addition)
	{
		score+=addition;
	}
	
	public int getLife (){ return life;}
	public int getScore () {return score;}
	public String getName() {return name;}
}
