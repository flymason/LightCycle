import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Arena extends JPanel implements KeyListener{
	private Model _model;
	private Snake snake;
	
	public Arena() 
	{
		setLayout(null);
		Dimension preferredSize = new Dimension(GameManager.ARENA_WIDTH*GameManager.CELL_SIZE, GameManager.ARENA_HEIGHT*GameManager.CELL_SIZE);
		setPreferredSize(preferredSize);
		setFocusable(true);
		_model = new Model();
		addCellsToPanel();
		
		snake = new Snake(_model);
		_model.plantSeed();
		
	}
	public void addCellsToPanel()
	{
		Cell c;
		for(int x = 0; x < GameManager.ARENA_WIDTH; x++)
		{
			for(int y = 0; y < GameManager.ARENA_HEIGHT; y++)
			{
				c = _model.getCell(x, y);
				c.setLocation(x * GameManager.CELL_SIZE, y * GameManager.CELL_SIZE);
				add(c);
			}
		}
	}
	public void restartGame()
	{
		Cell c;
		for(int x = 0; x < GameManager.ARENA_WIDTH; x++)
		{
			for(int y = 0; y < GameManager.ARENA_HEIGHT; y++)
			{
				c = _model.getCell(x, y);
				c.kill();
			}
		}
		snake = new Snake(_model);
		_model.plantSeed();
	}
	
	public void startSlither()
	{
		try{
			
			Thread.sleep(GameManager.INITIAL_DELAY);
		} catch (Exception e){e.printStackTrace();}
		
		addKeyListener(this);
		
		while(true)
		{
			try{
				Thread.sleep(GameManager.SLITHER_DELAY);
			} catch (Exception e){e.printStackTrace();}
			
			try{
				snake.slither();
			} catch (SnakeCrashException e) { 
				System.out.println("Game Over\nScore: " + snake.getLength());
				return;
		    }
		}	
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			snake.requestChangeHeading(Snake.DIR.RIGHT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			snake.requestChangeHeading(Snake.DIR.LEFT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			snake.requestChangeHeading(Snake.DIR.DOWN);
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			snake.requestChangeHeading(Snake.DIR.UP);
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
