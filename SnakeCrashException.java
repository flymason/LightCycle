
public class SnakeCrashException extends Exception{

	private Snake _snake;
	
	public SnakeCrashException(Snake snake)
	{
		_snake = snake;
	}
	public Snake getSnake()
	{
		return _snake;
	}

}
