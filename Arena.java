import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Arena extends JPanel implements KeyListener{
	private Model _model;
	private Snake player1;
	private Snake player2;
	private boolean isRunning;
	
	public Arena() 
	{
		setLayout(null);
		Dimension preferredSize = new Dimension(GameManager.ARENA_WIDTH*GameManager.CELL_SIZE, GameManager.ARENA_HEIGHT*GameManager.CELL_SIZE);
		setPreferredSize(preferredSize);
		setFocusable(true);
		_model = new Model();
		
		setBackground(GameManager.BACKGROUND_COLOR);
		addCellsToArenaPanel();
		
		player1 = new Snake(_model, GameManager.PLAYER1_COLOR, Snake.DIR.LEFT, GameManager.ARENA_WIDTH-5);
		player2= new Snake(_model, GameManager.PLAYER2_COLOR, Snake.DIR.RIGHT, 5);		
		_model.placeRandomFood();
		
		Thread gameThread = new Thread() {
			public void run()
			{
				startSlither();
			}
		};
		isRunning = true;
		gameThread.start();
		
	}
	public void addCellsToArenaPanel()
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
		player1 = new Snake(_model, GameManager.PLAYER1_COLOR, Snake.DIR.LEFT, GameManager.ARENA_WIDTH-5);
		player2= new Snake(_model, GameManager.PLAYER2_COLOR, Snake.DIR.RIGHT, 5);
		_model.placeRandomFood();
		
		try {
            Thread.sleep(200);
         } catch (InterruptedException ex) {}
		
		Thread gameThread = new Thread() {
			public void run()
			{
				startSlither();
			}
		};
		isRunning = true;
		gameThread.start();
		
	}
	
	public void startSlither()
	{
		try{
			
			Thread.sleep(GameManager.INITIAL_DELAY);
		} catch (Exception e){e.printStackTrace();}
		
		addKeyListener(this);
		
		while(isRunning)
		{			
			try{
				player1.slither();
				player2.slither();
			} catch (SnakeCrashException exception) { 
				System.out.println("Game Over\nScore: " + player1.getLength());
				String winnerMessage = "";
				if(exception.getSnake() == player2)
					winnerMessage = GameManager.PLAYER1 + " Wins!";
				if(exception.getSnake() == player1)
					winnerMessage = GameManager.PLAYER2 + " Wins!";
				
				isRunning = false;
				Graphics g = this.getGraphics();
				Font medium = new Font("Helvetica", Font.BOLD, 24);
				FontMetrics metr_m = this.getFontMetrics(medium);
				g.setColor(Color.WHITE);
				g.setFont(medium);
				g.drawString(winnerMessage, (this.getWidth() - metr_m.stringWidth(winnerMessage)) / 2, this.getHeight() / 2);
				setBackground(GameManager.BACKGROUND_COLOR);
				
				
		    }			
			try {
                Thread.sleep(1000 / GameManager.UPDATE_RATE);
             } catch (InterruptedException ex) {}
		}	
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player1.requestChangeHeading(Snake.DIR.RIGHT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player1.requestChangeHeading(Snake.DIR.LEFT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player1.requestChangeHeading(Snake.DIR.DOWN);
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			player1.requestChangeHeading(Snake.DIR.UP);
		}
		else if (e.getKeyCode() == KeyEvent.VK_D) {
			player2.requestChangeHeading(Snake.DIR.RIGHT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_A) {
			player2.requestChangeHeading(Snake.DIR.LEFT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S) {
			player2.requestChangeHeading(Snake.DIR.DOWN);
		}
		else if (e.getKeyCode() == KeyEvent.VK_W) {
			player2.requestChangeHeading(Snake.DIR.UP);
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			isRunning = false;
			restartGame();
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
