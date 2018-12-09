
package pong;


public class HumanPlayer 
{
	private int x_pos;
	private int y_pos;
	
	private int y_speed;
	
	
	HumanPlayer(int x ,int y)
	{
		x_pos = x;
		y_pos = y;
	}
	
	public void move()
	{
		y_pos += y_speed;
	}
	
	public void setSpeed(int speed)
	{
		y_speed = speed;
	}
	

	public void setY(int y)
	{
		y_pos = y;
	}
	
	public int getY()
	{
		return y_pos;
	}
	
	public int getX()
	{
		return x_pos;
	}
	
}
