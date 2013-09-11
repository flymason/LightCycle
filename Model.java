import java.util.Random;

public class Model {

	private Cell[][] _matrix;
	
	public Model()
	{
		_matrix = new Cell[GameManager.ARENA_WIDTH][GameManager.ARENA_HEIGHT];
		
		for(int i = 0; i < GameManager.ARENA_WIDTH; i++)
		{
			for(int j = 0; j < GameManager.ARENA_HEIGHT; j++)
			{
				_matrix[i][j] = new Cell(GameManager.CELL_SIZE);
			}
		}
	}
	
	public Cell getCell(int x, int y)
	{
		return _matrix[x][y];
	}
	
	public void plantSeed()
	{
		Random rand = new Random();
		
		while(true)
		{
			int randX = (rand.nextInt(Integer.MAX_VALUE)) % GameManager.ARENA_WIDTH;
			int randY = (rand.nextInt(Integer.MAX_VALUE)) % GameManager.ARENA_HEIGHT;
			Cell c = getCell(randX, randY);
			
			if(randX == 0 && randY == 0 || randX == GameManager.ARENA_WIDTH && randY == 0 || randX == 0 && randY == GameManager.ARENA_HEIGHT || randX == GameManager.ARENA_WIDTH && randY == GameManager.ARENA_HEIGHT)
				continue;
			
			if(!c.isAlive())
			{
				c.seed();
				return;
			}
		}
	}
	
	
}