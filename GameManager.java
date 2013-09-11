import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameManager extends JFrame implements MouseListener {

	public static final int INITIAL_DELAY = 200;
	public static final int SLITHER_DELAY = 70;
	public static final int CELL_SIZE = 15;
	public static final int ARENA_HEIGHT = 35;
	public static final int ARENA_WIDTH = 33;
	public static final int FOOD_VALUE = 5;
	public static final Snake.DIR STARTING_HEADING = Snake.DIR.UP;
	
	
	
	private Arena _arena;
	private JPanel smilyBar;
	public SmileyButton smileyButton;
	private boolean mousePressed = false;
	
	public GameManager() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Snake");
		setLayout(new BorderLayout());
		
		_arena = new Arena();
		
		addSmileyBar();
		add(_arena, BorderLayout.CENTER);
		pack();	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2 );
		setVisible(true);
		_arena.startSlither();
	}
	public void addSmileyBar()
	{
		smileyButton = new SmileyButton();
		smileyButton.addMouseListener(this);
		
		smilyBar = new JPanel();
		smilyBar.add(Box.createHorizontalGlue());
		smilyBar.add(smileyButton);
		smilyBar.setBorder(BorderFactory.createRaisedBevelBorder());
		
		add(smilyBar, BorderLayout.NORTH);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
	}
	public void mousePressed(MouseEvent e)
	{
		smileyButton.setPressed(true);
		mousePressed = true;
		repaint();
	}

	public void mouseReleased(MouseEvent e)
	{
		if (smileyButton.isPressed())
		{
			mousePressed = false;
			smileyButton.setPressed(false);
			_arena.restartGame();
			_arena.startSlither();
		}
		
	}
	public void mouseEntered(MouseEvent e){
		if(mousePressed)
		{
			smileyButton.setPressed(true);
		}
	}
	public void mouseExited(MouseEvent e) {
			smileyButton.setPressed(false);
	} 


public static void main(String[] args) {
	new GameManager();
}
}
