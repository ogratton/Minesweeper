/**
 * A rough implementation of MineSweeper
 *
 */
public class MineSweeper {
	
	public static final int SAFE = 0;
	public static final int MINE = -1;
	
	private int difficulty;
	
	private int[][] board;
	private int[][] tracker;
	
	/**
	 * Constructor sets value for difficulty and makes a new board
	 * @param difficulty
	 */
	public MineSweeper(int difficulty)
	{
		this.difficulty = difficulty;
		
		newGame();
	}
	
	/**
	 * Generate new board with mines
	 */
	public void newGame()
	{
		this.tracker = new int[10][10];
		createBoard(tracker);
		this.board = new int[10][10];
		createBoard(board);
		
		// set how many mines we need to place
		int minesToPlace = difficulty;
		
		for (int i=0;i<minesToPlace;i++)
		{
			// two random numbers between board width and board height
			int randX = (int) (Math.random()*10);
			int randY = (int) (Math.random()*10);
			// set those points to have a mine if they don't already
			if (this.board[randX][randY] != MINE)
			{
				this.board[randX][randY] = MINE;
			}
			// if spot already has a mine, skip and try again
			else
			{
				i--;
			}
		}
		this.calcValues();
	}
	
	/**
	 * Counts number of mines surrounding every square that isn't a mine
	 */
	private void calcValues()
	{
		// make array of the surrounding blank squares
		int[] sur = new int[8];

		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				sur[0] = trySet(i-1,j+0);
				sur[1] = trySet(i+1,j+0);
				sur[2] = trySet(i+0,j-1);
				sur[3] = trySet(i+0,j+1);
				sur[4] = trySet(i-1,j-1);
				sur[5] = trySet(i-1,j+1);
				sur[6] = trySet(i+1,j-1);
				sur[7] = trySet(i+1,j+1);
				
				for (int s=0;s<sur.length;s++)
				{
					if (sur[s] == MINE && this.board[i][j] != MINE)
					{
						this.board[i][j]++;
					}
				}
			}
		}
	}
	
	/**
	 * Extension of newGame that catches IndexOutOfBounds exceptions
	 * @param x x coord
	 * @param y y coord
	 * @return actual value if exists else -99
	 */
	private int trySet(int x, int y)
	{
		try
		{
			return this.board[x][y];
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return -99;
		}
	}
	
	/**
	 * Creates an empty 2D int array
	 * @param grid 2D int array
	 */
	private void createBoard(int[][] grid)
	{
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				grid[i][j] = SAFE;
			}
		}
	}
	
	/**
	 * sets the value of the tracker grid to show if a square is explored
	 * @param i x coord
	 * @param j y coord
	 * @param e whether setting to 1 or 0
	 */
	public void updateTracker(int i, int j, boolean e)
	{
		this.tracker[i][j] = e ? 1 : 0; // shorthand if statement	
	}
	
	/**
	 * return whether or not a square has been explored
	 * @param i x coord
	 * @param j y coord
	 * @return whether or not a square has been explored
	 */
	public int getExplored(int i, int j)
	{
		return this.tracker[i][j];
	}
	
	/**
	 * Set every square to the same explored value
	 * @param mode set all to true or false (1 or 0)
	 */
	public void setAllExplored(int mode)
	{
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				this.tracker[i][j] = mode;
			}
		}
	}
	
	/**
	 * Get the value of a certain square
	 * @param i x coord
	 * @param j y coord
	 * @return value of square
	 */
	public int get(int i, int j)
	{
		return this.board[i][j];
	}
	
	/**
	 * Changes the difficulty
	 * @param dif the new value
	 */
	public void setDifficulty(int dif)
	{
		this.difficulty = dif;
	}
	
	/**
	 * Return the number of mines in the level
	 * @return number of mines
	 */
	public int getDifficulty()
	{
		return this.difficulty;
	}
	
	/**
	 * See if the player has won
	 * @return whether or not the player has won
	 */
	public boolean testWin()
	{
		boolean win = false;
		int unexploredCount = 0;

		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if (tracker[i][j] == 0)
				{
					unexploredCount++;
				}
			}
		}
		//System.out.println(unexploredCount);
		if(unexploredCount == this.difficulty)
		{
			win = true;
		}
		return win;
	}

	/**
	 * CHALLENGE 2:
	 * if someone clicks on a square with no neighbouring mines,
	 * the whole space around it is cleared automatically
	 */
	public void adjacentSpaces()
	{
		// do the following {10} times to make sure it gets them all (lazy way)
		for(int k = 0; k < 10; k++)
		{
			// make array of the surrounding blank squares
			for(int i = 0; i < 10; i++)
			{
				for(int j = 0; j < 10; j++)
				{
					if (this.board[i][j] == SAFE && this.tracker[i][j] == 1)
					{
						try2(i-1,j+0);
						try2(i+1,j+0);
						try2(i+0,j-1);
						try2(i+0,j+1);
						try2(i-1,j-1);
						try2(i-1,j+1);
						try2(i+1,j-1);
						try2(i+1,j+1);
					}
				}
			}
		}
	}
	
	
	/**
	 * Extension of adjacentSpaces that catches IndexOutOfBounds exceptions
	 * @param x x coord
	 * @param y y coord
	 */
	private void try2(int i, int j)
	{
		if(trySet(i,j) != -99)
		{
			this.updateTracker(i,j, true);
		}
	}
	
	/**
	 * Used for debugging/testing before GUI implemented
	 * Also can be used as a cheat sheet if lazy/terrible at MineSweeper
	 */
	public void printBoard()
	{
		for (int i=0;i<10;i++)
		{
			for (int j=0;j<10;j++)
			{
				System.out.print(this.board[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
