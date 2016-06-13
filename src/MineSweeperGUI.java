import javax.swing.JFrame;

/**
 * Make the frame
 *
 */
public class MineSweeperGUI
{
	public static void main(String[] args)
	{
		int dif = 10;
		MineSweeper m = new MineSweeper(dif);
		MineComponent comp = new MineComponent(m);
		
		JFrame frame = new JFrame("MineSweeper");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(comp);
		
		frame.setVisible(true);
	}
}