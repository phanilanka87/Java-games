package pong;

public class Ball 
{
	private int x_pos;
	private int y_pos;
	
	private int x_speed ;
	private int y_speed ;
	
	
	public Ball(int x , int y)
	{
		x_pos = x;
		y_pos = y;
	}
	
	public void move()
	{
		x_pos += x_speed;
		y_pos += y_speed;
	}
	
	public void setSpeed(int x_speed , int y_speed)
	{
		this.x_speed = x_speed;
		this.y_speed = y_speed;		
	}
	
	public void setXSpeed(int speed)
	{
		x_speed = speed;
	}
	
	public void setYSpeed(int speed)
	{
		y_speed = speed;
	}
	
	public void setX(int x)
	{
		x_pos = x;
	}
	public void setY(int y)
	{
		y_pos = y;
	}
	public int getX()
	{
		return x_pos;
	}
	
	public int getY()
	{
		return y_pos;
	}

	public int getXSpeed()
	{
		return x_speed;		
	}
	
	public int getYSpeed()
	{
		return y_speed;
	}
}
