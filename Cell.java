import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel{

	private boolean alive;
	private boolean seed;
	private int _size;
	
	public Cell(int size)
	{
		_size = size;
		setSize(size, size);
		alive = false;
		seed = false;
	}
	public void seed()
	{
		seed = true;
		alive = false;
		repaint();
	}
	public void spawn()
	{
		seed = false;
		alive = true;
		repaint();
	}
	public void kill()
	{
		alive = false;
		seed = false;
		repaint();
	}
	public boolean isAlive()
	{
		return alive;
	}
	public boolean isSeed()
	{
		return seed;
	}
	public void paintComponent(Graphics g)
	{
		if(alive)
		{
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, _size , _size);
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, _size, _size);
		}
		else if(seed)
		{
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, _size, _size);
		}
		else
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, _size, _size);
		}
		
		
	}
}
