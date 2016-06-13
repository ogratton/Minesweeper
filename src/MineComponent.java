import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Combines the ControlPanel and the BoardView
 *
 */
public class MineComponent extends JPanel
{
	/**
	 * Combine the views
	 * @param m MineSweeper object
	 */
	public MineComponent(MineSweeper m)
	{
		super();
		
		// make model
		MineModel model = new MineModel(m);
		
		// make views
		BoardView board = new BoardView(model);
		ControlPanel controls = new ControlPanel(model);
		
		// make model observe view
		model.addObserver(board);
		
		setLayout(new BorderLayout());
		
		add(board, BorderLayout.CENTER);
		add(controls, BorderLayout.SOUTH);
	}
}
