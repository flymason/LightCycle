import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel{

	private boolean _alive;
	private boolean _seed;
	private int _size;
	private Color _color;
	
	public Cell(int size)
	{
		_size = size;
		setSize(size, size);
		_alive = false;
		_seed = false;
	}
	public void seed()
	{
		_seed = true;
		_alive = false;
		repaint();
	}
	public void spawn(Color color)
	{
		_color = color;
		_seed = false;
		_alive = true;
		repaint();
	}
	public void kill()
	{
		_alive = false;
		_seed = false;
		repaint();
	}
	public boolean isAlive()
	{
		return _alive;
	}
	public boolean isSeed()
	{
		return _seed;
	}
	public void paintComponent(Graphics g)
	{
		if(_alive)
		{
			g.setColor(_color);
			g.fillRect(0, 0, _size-1 , _size-1);
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, _size , _size);
		}
		else if(_seed)
		{
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, _size-1, _size-1);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, _size-1, _size-1);
		}
		
		
	}
}
