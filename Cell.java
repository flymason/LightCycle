import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel{

	public enum Status {BODY, HEAD, BLANK, FOOD};
	private Status status;
	private int _size;
	private Color _aliveColor;
	
	public Cell(int size)
	{
		_size = size;
		setSize(size, size);
		status = Status.BLANK;
	}
	public void makeFood()
	{
		status = Status.FOOD;
		repaint();
	}
	public void setColor(Color color)
	{
		_aliveColor = color;
	}
	
	public Status getStatus()
	{
		return status;
	}
	public void setStatus(Status s)
	{
		status = s;
		repaint();
	}
	
	public boolean isAlive()
	{
		if(status == Status.HEAD || status == Status.BODY)
			return true;
		else
			return false;
	}
	
	public void paintComponent(Graphics g)
	{
		if(status == Status.HEAD)
		{
			g.setColor(Color.YELLOW);
			g.fillRect(0, 0, _size-1 , _size-1);
		}
		else if(status == Status.BODY)
		{
			g.setColor(_aliveColor);
			g.fillRect(0, 0, _size-1 , _size-1);
		}
		else if(status == Status.FOOD)
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
