import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.util.Observer;
import java.util.Observable;
import java.awt.Container;
import java.awt.GridLayout;

/**
 * Contains the actual game screen elements
 *
 */
public class BoardView extends JPanel implements Observer
{
	private MineModel model;
	private JButton[][] grid;
	
	/**
	 * Constructor
	 * @param model MineModel object
	 */
	public BoardView(MineModel model)
	{
		super();
		
		// initialise model
		this.model = model;
		//create array of buttons
		grid = new JButton[10][10];
		//set layout of panel
		setLayout(new GridLayout(10, 10));
		//for each square in grid:create a button; place on panel
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				grid[i][j] = new JButton("");
				final int x = i;final int y = j;
				grid[i][j].addActionListener(e->this.label(x, y)); // should be a function that sets the text of the current button and disables it
				add(grid[i][j]);
			}
		}
	}
	
	/**
	 * Called when a square is clicked.
	 * If a mine is clicked, end the game
	 * If a safe square is clicked, reveal its value and disable it
	 * @param i x coord of button clicked
	 * @param j y coord of button clicked
	 */
	public void label(int i, int j)
	{
		if(model.get(i, j) == MineSweeper.MINE)
		{
			grid[i][j].setText("B");
			model.setAllExplored(1); // disables all buttons and reveals all square values and ends the game
			Container frame = this.getParent();
			JOptionPane.showMessageDialog(frame,"You Lose!"); 
		}
		else
		{
			if(model.get(i, j) != MineSweeper.SAFE)
			{
				grid[i][j].setText(""+model.get(i, j));
			}
			else
			{
				grid[i][j].setText(""); // a 0 value square gets no text
			}
			
			grid[i][j].setEnabled(false);
			model.updateTracker(i, j, true);
			model.adjacentSpaces();
			
			// Check to see if they have explored every square but the mines
			if (model.testWin())
			{
				Container frame = this.getParent();
				JOptionPane.showMessageDialog(frame,"You Win!");
				//model.newGame(); // makes a new game as soon as the dialog box is closed. Disabled to allow player to change difficulty or view board afterwards to show their friends how good they are
			}
		}
	}
	
	/**
	 * Called when a the screen is updated. Very similar to label but not identical
	 * If a mine is clicked, end the game
	 * If a safe square is clicked, reveal its value and disable it
	 * @param i x coord of button clicked
	 * @param j y coord of button clicked
	 */
	public void label2(int i, int j)
	{
		if(model.get(i, j) == MineSweeper.MINE)
		{
			grid[i][j].setText("B");
			grid[i][j].setEnabled(false);
		}
		else
		{
			if(model.get(i, j) != MineSweeper.SAFE)
			{
				grid[i][j].setText(""+model.get(i, j));
			}
			else
			{
				grid[i][j].setText("");
			}
			grid[i][j].setEnabled(false);
		}
	}
	
	/**
	 * What to do when something happens. Triggered by methods in MineModel
	 */
	public void update(Observable obs, Object obj)
	{		
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(model.getExplored(i, j) == 1)
				{
					this.label2(i,j);
				}
				
				else if (model.getExplored(i, j) == 0)
				{
					grid[i][j].setText("");
					grid[i][j].setEnabled(true);
				}
			}
		}
		repaint();
	}
}	
					
		
