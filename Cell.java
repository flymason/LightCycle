import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel{

	private boolean _alive;
	private boolean _food;
	private int _size;
	private Color _spawnColor;
	
	public Cell(int size)
	{
		_size = size;
		setSize(size, size);
		_alive = false;
		_food = false;
	}
	public void makeFood()
	{
		_food = true;
		_alive = false;
		repaint();
	}
	public void spawn(Color color)
	{
		_spawnColor = color;
		_food = false;
		_alive = true;
		repaint();
	}
	public void kill()
	{
		_alive = false;
		_food = false;
		repaint();
	}
	public boolean isAlive()
	{
		return _alive;
	}
	public boolean isFood()
	{
		return _food;
	}
	public void paintComponent(Graphics g)
	{
		if(_alive)
		{
			g.setColor(_spawnColor);
			g.fillRect(0, 0, _size-1 , _size-1);
		}
		else if(_food)
		{
			g.setColor(GameManager.FOOD_COLOR);
			g.fillRect(0, 0, _size-1, _size-1);
		}
		else
		{
			g.setColor(GameManager.BACKGROUND_COLOR);
			g.fillRect(0, 0, _size, _size);
			g.setColor(GameManager.INACTIVE_CELL_COLOR);
			g.fillRect(0, 0, _size-1, _size-1);
		}
		
		
	}
}
