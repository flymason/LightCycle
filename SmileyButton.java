import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class SmileyButton extends JPanel{
	
	private boolean pressed;
	private boolean gameOver;
	private boolean gameWon;
	private boolean mouthOpen;
	
	public SmileyButton()
	{		
		setSize(new Dimension(30, 30));
		setPreferredSize(new Dimension(30, 30));
		setOpaque(false);
		pressed = false;
		gameOver = false;
		gameWon = false;
		mouthOpen = false;
		
		repaint();
	}	
	
	public void paintComponent(Graphics g)
	{
		if(pressed)
		{
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, 30, 30);
			g.setColor(Color.GRAY);
			g.drawRect(0, 0, 30, 30);
		}	
		else	//if not pressed
		{
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, 30, 30);
			
			g.setColor(Color.WHITE);
			int[] x = {0, 30, 27, 0};
			int[] y = {0, 0, 2, 2};
			g.fillPolygon(x, y, 4);
			
			int[] x2 = {0, 0, 2, 2};
			int[] y2 = {0, 30, 27, 0};
			g.fillPolygon(x2, y2, 4);
			
			g.setColor(Color.GRAY);
			int[] x3 = {27, 30, 30, 27};
			int[] y3 = {2, 0, 30, 30};
			g.fillPolygon(x3, y3, 4);
			
			int[] x4 = {0, 2, 30, 30};
			int[] y4 = {30, 27, 27, 30};
			g.fillPolygon(x4, y4, 4);
		}
		g.setColor(Color.YELLOW);
		g.fillOval(4, 4, 21, 21);
		g.setColor(Color.BLACK);
		g.drawOval(4, 4, 21, 21);
		
		if(mouthOpen)
		{
			g.fillOval(10, 10, 4, 4);
			g.fillOval(16, 10, 4, 4);
			g.drawOval(12, 16, 5, 5);
		}
		else if(gameOver)
		{
			g.drawLine(10, 10, 14, 14);
			g.drawLine(14, 10, 10, 14);
			g.drawLine(17, 10, 21, 14);
			g.drawLine(21, 10, 17, 14);
			g.drawArc(9, 17, 12, 8, 15, 140);
		}
		else if(gameWon)
		{
			g.fillRect(10, 11, 11, 2);
			g.fillOval(8, 10, 7, 7);
			g.fillOval(15, 10, 7, 7);
			g.drawLine(10, 11, 5, 14);
			g.drawLine(21, 11, 25, 15);
			
			g.drawArc(9, 12, 12, 8, 195, 140);
		}
		else
		{
			g.fillOval(11, 10, 3, 3);
			g.fillOval(16, 10, 3, 3);
			g.drawArc(9, 12, 12, 8, 195, 140);
		}
		
	}
	public void putGlassesOn()
	{
		gameWon = true;
		repaint();
	}
	public void killSmiley()
	{
		gameOver = true;
		repaint();
	}
	public void setPressed(boolean b)
	{
		pressed = b;
		repaint();
	}
	public boolean isPressed()
	{
		return pressed;
	}
	public void setMouthOpen(boolean m)
	{
		mouthOpen = m;
		repaint();
	}
	public void reset()
	{
		pressed = false;
		gameOver = false;
		gameWon = false;
		mouthOpen = false;
		repaint();
	}
}
