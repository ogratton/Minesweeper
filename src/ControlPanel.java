import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel
{
	/**
	 * Contains all the buttons that will be on screen
	 * @param model MineModel object
	 */
	public ControlPanel(MineModel model)
	{
		super();
		
		JButton reset = new JButton("New Game");
		reset.addActionListener(e -> {model.newGame();model.setAllExplored(0);});
		
		JButton reveal = new JButton("Reveal Mines");
		reveal.addActionListener(e -> model.setAllExplored(1));
		
		JRadioButton easy = new JRadioButton("Easy");
		easy.addActionListener(e -> model.setDifficulty(5));
		JRadioButton medium = new JRadioButton("Medium");
		medium.addActionListener(e -> model.setDifficulty(10));
		medium.setSelected(true);
		JRadioButton hard = new JRadioButton("Hard");
		hard.addActionListener(e -> model.setDifficulty(15));
		ButtonGroup diff = new ButtonGroup();
		diff.add(easy);
		diff.add(medium);
		diff.add(hard);
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(e -> System.exit(0));
		
		
		add(easy); add(medium); add(hard);
		add(reset);
		add(reveal);
		add(exit);
	}
}
