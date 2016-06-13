import java.util.Observable;

/**
 * A model of a MineSweeper game containing lots of wrapper functions
 *
 */
public class MineModel extends Observable
{
	private MineSweeper m;
	
	/**
	 * Constructor
	 * @param m MineSweeper object
	 */
	public MineModel(MineSweeper m)
	{
		this.m = m;
	}
	
	/**
	 * Generate new board
	 */
	public void newGame()
	{
		m.newGame();
		setChanged();
		notifyObservers();
	}

	/**
	 * return whether or not a square has been explored
	 * @param i x coord
	 * @param j y coord
	 * @return whether or not a square has been explored
	 */
	public int getExplored(int i, int j)
	{
		return m.getExplored(i, j);
	}
	
	/**
	 * sets the value of the tracker grid to show if a square is explored
	 * @param i x coord
	 * @param j y coord
	 * @param e whether setting to 1 or 0
	 */
	public void updateTracker(int i, int j, boolean e)
	{
		m.updateTracker(i, j, e);
	}
	
	/**
	 * Set every square to the same explored value
	 * @param mode set all to true or false (1 or 0)
	 */
	public void setAllExplored(int mode)
	{
		m.setAllExplored(mode);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Get the status of a certain square
	 * @param i x coord
	 * @param j y coord
	 * @return value of square
	 */
	public int get(int i, int j)
	{
		return m.get(i,j);
	}
	
	/**
	 * Changes the difficulty
	 * @param dif the new value
	 */
	public void setDifficulty(int dif)
	{
		m.setDifficulty(dif);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Return the number of mines in the level
	 * @return number of mines
	 */
	public int getDifficulty()
	{
		return m.getDifficulty();
	}

	/**
	 * See if the player has won
	 * @return whether or not the player has won
	 */
	public boolean testWin()
	{
		return m.testWin();
	}
	
	/**
	 * CHALLENGE 2:
	 * if someone clicks on a square with no neighbouring mines,
	 * the whole space around it is cleared automatically
	 */
	public void adjacentSpaces()
	{
		m.adjacentSpaces();
		setChanged();
		notifyObservers();
	}
	
	public void printBoard()
	{
		m.printBoard();
	}
	
}