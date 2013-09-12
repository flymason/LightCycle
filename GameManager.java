import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameManager extends JFrame {

	public static final int INITIAL_DELAY = 200;
	public static final int SLITHER_DELAY = 90;
	public static final int CELL_SIZE = 10;
	public static final int ARENA_HEIGHT = 60;
	public static final int ARENA_WIDTH = 60;
	public static final int FOOD_VALUE = 20;
	
	public static final Color BACKGROUND_COLOR = Color.GRAY;	// color of grid lines
	public static final Color INACTIVE_CELL_COLOR = Color.BLACK;
	public static final Color PLAYER1_COLOR = Color.RED;
	public static final Color PLAYER2_COLOR = Color.MAGENTA;
	public static final Color FOOD_COLOR = Color.GREEN;
	
	
	
	
	private Arena _arena;
	
	public GameManager() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Snake");
		setLayout(new BorderLayout());
		
		_arena = new Arena();
		
		add(_arena, BorderLayout.CENTER);
		pack();	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2 );
		setVisible(true);
		_arena.startSlither();
	}	


public static void main(String[] args) {
	new GameManager();
}
}
