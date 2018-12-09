
/*
 created by : phani
 mail:phani42@gmail.com
 */
package shoot;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.ArrayList;

public class Shooter extends Applet implements Runnable , ActionListener , KeyListener
{
    Panel p = new Panel();
    Button b=new Button("start");
    
    
    int box_y_speed;
   
    ArrayList bullets;
    
    Rectangle box;
    Rectangle gun;
    int bullet_count=15;
    
    int score = 0;
    int speed;
    int bullets_Left;
    
    boolean gameover;
    boolean started;
    
    Thread t;
    
    public void init()
    {
        bullets = new ArrayList();
        setBackground(Color.green);
        p.add(b);
        add(p);
        b.addActionListener(this);
        setSize(250,200);  
        addKeyListener(this);
        setFocusable(true);
    }

    public void run() 
    {
        box=new Rectangle(10,10);
        gun = new Rectangle(290,100,10,5);
        p.setVisible(false);
        bullets.clear();
        gameover=false;
        
        while(true)//----------gameloop
        {
         
             if(bullet_count <= 0)//---------keybord doesnot work if all bullets are fired
            {
               this.setFocusable(false);
			   
            }
             
            
            if(bullet_count <= 0 && bullets.size()==0)//---------keybord doesnot work if all bullets are fired
            {
               this.setFocusable(false);
               p.setVisible(true);
               started=false;
               gameover = true;
			   repaint();
               break;
            }
            
            
            if(started)     //--------- box moment
            {
                if(box.y >= getHeight()-15)
                    box_y_speed = -10;
                   
                else if(box.y <=0)
                    box_y_speed = 10;
                    
               
                box.y += box_y_speed;
            }
            
            
            for(int i=0;i<bullets.size();i++)//---------collision
            {
                Bullets b=(Bullets)bullets.get(i);
                 int temp_x = b.getX(); 
                 int temp_y = b.getY();
                
                if(temp_x <= 10 && (temp_y >=box.y && temp_y<= box.y+10))
                {
                    score++;
                    bullets.remove(i);
                }
               
            }
            
             
            
            for(int i=0 ; i<bullets.size() ; i++)  //-----bullets moment
            {
                Bullets b = (Bullets)bullets.get(i);
                if(b.isVisible())
                b.move();
                else bullets.remove(i);
            }
               
            repaint();
           
            
           try
           {
               Thread.sleep(speed);
           }
           catch(Exception e){}
        }
    }

   
    
    
    public void paint(Graphics g)
    {
        if(!started)
        {
        	g.drawString("use up and down arows to move the black block  ",5,50);
        	g.drawString("use space to fire bullets",5,80);
        }
    	g.drawRect(box.x, box.y, 15, 15);
        g.fillRect(gun.x, gun.y, 15, 10);
        
        
        for(int i=0;i<bullets.size();i++)  //-------display bullets
        {
            Bullets b=(Bullets)bullets.get(i);
           if(b.isVisible())
            g.fillRect(b.getX(), b.getY(), 6,2 );
        }
       
        g.drawString("score is"+score, 20,10);
        g.drawString("bullets remaining"+bullet_count, 100,10 );
        if(gameover)
          {
              g.setFont(new Font("Verdana", 1, 10));
              g.setColor(Color.red);
              g.drawString("TRY AGAIN "+"(phani42@gmail.com)", 60, 100);
          }
   
    }
    
    public void fire()
    {
       if(bullets.size()<15 )//------------limiting bullets
        bullets.add(new Bullets(gun.x,gun.y));
        bullet_count --;
    }
    
    
    
    public void actionPerformed(ActionEvent e) 
    {
        String s = e.getActionCommand();
        setFocusable(true);
        if(s.equals("start"))
        {
            
            bullet_count = 15;
            started = true;
            speed=100;
            bullet_count = 15;
            score=0;
            t=null;
            t=new Thread(this);
            t.start();
        }
        
    }

    public void keyTyped(KeyEvent e) 
    {
        
         
    }

    public void keyPressed(KeyEvent e)
    {

         int key = e.getKeyCode();
        if(key == e.VK_UP)
        {
            if(! (gun.y<=0) )
            gun.y -= 5;
        }
        else if(key == e.VK_DOWN)
        {
            if(! (gun.y>getHeight() -10) )
          gun.y += 5;
        }
        else if(key == e.VK_P)
            if(!started)started=true;
            else started = false;
           
        else if(key == e.VK_SPACE)
         fire();
       
    }

    public void keyReleased(KeyEvent e)
    {
       
    }

 
}
