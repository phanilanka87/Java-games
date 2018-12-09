package pong;

public class ComputerPlayer 
{
	private int x_pos;
	private int y_pos;
	
	private int y_speed;
	
	public ComputerPlayer(int x , int y)
	{
		x_pos = x;
		y_pos = y;
	}
	public void move()
	{
		y_pos += y_speed;
	}
	
	public void setSpeed(int y)
	{
		y_speed = y;
	}
	
	public int getYSpeed()
	{
		return y_speed;
	}
	
	public int getY()
	{
		return y_pos;
	}
	
	public int getX()
	{
		return x_pos;
	}
	public void setY(int y)
			{
				y_pos = y;
			}
}
