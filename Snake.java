import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

public class Snake {

	public enum DIR {UP, DOWN, RIGHT, LEFT};
	private Model _model;
	private DIR _heading;
	private LinkedList<DIR> _newHeadings;
	private LinkedList<Cell> _body;
	private Point _headLocation;
	private int food;
	private int length;
	private Color _color;
	
	public Snake(Model model, Color color, DIR heading, int startingX)
	{
		
		_model = model;
		_color = color;
		_heading = heading;
		_headLocation = new Point(startingX, GameManager.ARENA_HEIGHT/2);
		
		length = 0;
		food = 0;
		_body = new LinkedList<Cell>();
		_newHeadings = new LinkedList<DIR>();		
		
		try {
			spawnCell((int)_headLocation.getX(), (int) _headLocation.getY());
		} catch (Exception e) {}		
	}
	
	private void spawnCell(int x, int y) throws SnakeCrashException, TieException
	{
		x = x < 0 ? GameManager.ARENA_WIDTH - 1 : x;
		y = y < 0 ? GameManager.ARENA_HEIGHT - 1 : y;
		x = x % GameManager.ARENA_WIDTH;
		y = y % GameManager.ARENA_HEIGHT;
		
		Cell c;
		
		try {
			c = _model.getCell(x,  y);
		} catch (ArrayIndexOutOfBoundsException e) { throw new SnakeCrashException(this); }
		
		if (c.getStatus() == Cell.Status.BODY)
			{ throw new SnakeCrashException(this); }
		
		if (c.getStatus() == Cell.Status.HEAD)
		{ throw new TieException(this); }
		
		if(c.getStatus() == Cell.Status.FOOD)
		{
			food += GameManager.FOOD_VALUE;
			length += food;
			_model.placeRandomFood();
		}
		
		if(!_body.isEmpty())
			_body.getFirst().setStatus(Cell.Status.BODY);	//change head to body
		
		
		_body.addFirst(c);   
		_headLocation = new Point(x, y);
		c.setColor(_color);
		c.setStatus(Cell.Status.HEAD);					//set up new head cell
	}
	
	public void requestChangeHeading(DIR newHeading)
	{
		_newHeadings.addLast(newHeading);
	}
	
	public void slither() throws SnakeCrashException, TieException
	{
		moveHead();
		if(food < 1)
		{
			moveTail();
		}
		else
		{
			food--;
		}
	}
	
	private void moveHead() throws SnakeCrashException, TieException
	{
		
		updateHeading();
		
		switch(_heading){
			case UP:
				spawnCell((int)_headLocation.getX(),(int) _headLocation.getY() - 1);
				break;
			case DOWN:
				spawnCell((int)_headLocation.getX(),(int) _headLocation.getY() + 1);
				break;
			case RIGHT:
				spawnCell((int)_headLocation.getX() + 1,(int) _headLocation.getY());
				break;
			case LEFT:
				spawnCell((int)_headLocation.getX() - 1,(int) _headLocation.getY());
				break;
				
		}
	}
	
	private void updateHeading()
	{
		while(true)
		{
			if(!_newHeadings.isEmpty())
			{
				DIR newHeading = _newHeadings.getFirst();
				_newHeadings.removeFirst();
				
				if(headingIsValid(newHeading))
				{
						_heading = newHeading;
						break;
				}
				
			}
			else
				break;
		}
	}
	
	private boolean headingIsValid(DIR newHeading)
	{
		if(newHeading.equals(_heading))
			return false;
		if(newHeading == DIR.RIGHT && _heading == DIR.LEFT)
			return false;
		if(newHeading == DIR.LEFT && _heading == DIR.RIGHT)
			return false;
		if(newHeading == DIR.UP && _heading == DIR.DOWN)
			return false;
		if(newHeading == DIR.DOWN && _heading == DIR.UP)
			return false;
		
		return true;
	}
	
	private void moveTail()
	{
			Cell tail = _body.getLast();
			tail.setStatus(Cell.Status.BLANK);
			_body.remove(tail);
	}
	
	public int getLength()
	{
		return length;
	}
}