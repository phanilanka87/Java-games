
package shoot;

public class Bullets 
{
    private int x_pos;
    private int y_pos;
    boolean visible = true;
    final int STEP = 5;
    
    public Bullets(int x , int y)
    {
       x_pos=x;
       y_pos=y;
    }
    
    public int getX()
    {
        return x_pos;
    }
    
    public int getY()
    {
        return y_pos;
    }
    
    
    public boolean isVisible()
    {
        return visible;
    }
    
    public void move()
    {
        x_pos -= STEP;
        
        
        if(x_pos<=0)
            visible = false;
    }

}
