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
	
	public Snake(Model model)
	{
		
		_model = model;
		_heading = GameManager.STARTING_HEADING;
		_headLocation = new Point(GameManager.ARENA_WIDTH/2, GameManager.ARENA_HEIGHT-1);
		
		length = 0;
		food = 0;
		_body = new LinkedList<Cell>();
		_newHeadings = new LinkedList<DIR>();		
		
		try {
			spawnCell((int)_headLocation.getX(), (int) _headLocation.getY());
		} catch (SnakeCrashException e) {}		
	}
	
	private void spawnCell(int x, int y) throws SnakeCrashException
	{
		x = x < 0 ? GameManager.ARENA_WIDTH - 1 : x;
		y = y < 0 ? GameManager.ARENA_HEIGHT - 1 : y;
		x = x % GameManager.ARENA_WIDTH;
		y = y % GameManager.ARENA_HEIGHT;
		
		Cell c;
		
		try {
			c = _model.getCell(x,  y);
		} catch (ArrayIndexOutOfBoundsException e) { throw new SnakeCrashException(); }
		
		if (c.isAlive())
			{ throw new SnakeCrashException(); }
		
		if(c.isSeed())
		{
			food += GameManager.FOOD_VALUE;
			length += food;
			_model.plantSeed();
		}
		
		_body.addFirst(c);   
		_headLocation = new Point(x, y);
		c.spawn();
	}
	
	public void requestChangeHeading(DIR newHeading)
	{
		if(_newHeadings.size() < 3)
			_newHeadings.addLast(newHeading);
	}
	
	public void slither() throws SnakeCrashException
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
	
	private void moveHead() throws SnakeCrashException
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
			tail.kill();
			_body.remove(tail);
	}
	
	public int getLength()
	{
		return length;
	}
}

















