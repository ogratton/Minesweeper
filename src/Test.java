
public class Test {

	public static void main(String[] args) {
		
		/* Testing the board is set up by printing it (inspecting manually) */
		
		MineSweeper m = new MineSweeper(10);
		m.printBoard();
		System.out.println();
		MineModel model = new MineModel(m);
		model.printBoard();
		/* Testing the winning mechanic */
		int w = 10;
		int h = 10;
		
		int[][] tracker = new int[w][h];
		for(int i = 0; i < w; i++)
		{
			for(int j = 0; j < h; j++)
			{
				tracker[i][j] = 1;
			}
		}
		tracker[1][1] = 0;
		tracker[2][2] = 0;
		tracker[1][3] = 0;
		
		int difficulty = 3;
		
		boolean win = false;
		int unexploredCount = 0;

		for(int i = 0; i < w; i++)
		{
			for(int j = 0; j < h; j++)
			{
				if (tracker[i][j] == 0)
				{
					unexploredCount++;
				}
			}
		}
		if(unexploredCount == difficulty)
		{
			win = true;
		}
		
		System.out.println("Win:" + win);

	}

}
