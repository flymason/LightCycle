
public class TieException extends Exception{

	private Snake _snake;
	
	public TieException(Snake snake)
	{
		_snake = snake;
	}
	public Snake getSnake()
	{
		return _snake;
	}

}
