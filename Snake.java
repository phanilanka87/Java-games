/*
created by :phani
email: phani42@gmail.com
*/

import java.awt.*;
import java.applet.*;
import java.awt.event.*;


public class Snake extends Applet implements KeyListener,ActionListener,Runnable
{
    Rectangle[] r=new Rectangle[300];
    Panel p=new Panel();
    Button b1=new Button("level 1");
    Button b2=new Button("level 2");
    Button b3=new Button("level 3");
    Button b4=new Button("level 4");
    Button b5=new Button("level 5");
    
    boolean left=false;
    boolean right=false;
    boolean up=false;
    boolean down=false;
    
    int tail;
    int speed;
    int score;
    int newpos;
    
    Thread t;
    
    int x_speed=0;
    int y_speed=0;
    
    boolean gameover=false;
    boolean touched;
    boolean started=false;
    
    
    public void init()
    {
                
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(b5);
        add(p);
        setBackground(Color.green);
        addKeyListener(this);
        
    }
  
    public void run() 
    {
        p.setVisible(false);
           tail=3;
           touched=true;
           gameover=false;
           x_speed=0;
           y_speed=0;
           
        for(int i=0; i<tail; i++)
            {
                r[i] = new Rectangle(150,150,10,10);
            }
            
            for(int i=tail; i<300 ;i++)
            {
                r[i] = new Rectangle(-10,-10,10,10);
            }
        
           
           while(true)
           {
              if(gameover)
              {
              p.setVisible(true);
		  setFocusable(false);
              break ;
                      
              }
               if(started && !gameover)//game loop
               {
               
               if(touched)
               {
                newpos=tail;
                r[newpos].x = nextDot();
                r[newpos].y = nextDot();
                touched=false;                
               }
             
               for(int i=tail-1 ; i>=1 ; i--)
               {
                 r[i].x = r[i-1].x;
                 r[i].y = r[i-1].y;
               }
               r[0].x += x_speed;
               r[0].y += y_speed;               
               
               if(r[0].x<0 || r[0].y<0 || r[0].x>getSize().width-10 || r[0].y>getSize().height-10)
               {
                   started=false;
                   gameover=true;
                  
               }
               for(int i=2;i<tail-1;i++)
               {
                   if(r[0].x==r[i].x && r[0].y==r[i].y)
                   {
                       gameover=true;
                       started=false;
                      
                   }
               }
               
               if(r[0].x==r[newpos].x && r[0].y==r[newpos].y)
               {
                   tail++;
                   touched=true;
                   
               }
                 
               repaint();
             try
              {
               t.sleep(speed);
              }
             catch(InterruptedException e){}
          
           
           }
              
                
         }
    }
    
    public void update(Graphics g)
    {
        g.setColor(getBackground());
        g.clearRect(0, 0, getSize().width, getSize().height);
        paint(g);
    }
   
    public void paint(Graphics g)
    {
        setBackground(Color.green);
        g.setColor(Color.red);
        for(int i=0 ; i<=tail ; i++)
        {
	    g.fillRect(r[i].x, r[i].y, 10, 10);
        }
        if(gameover)
        {
            g.setFont(new Font("Verdana", 1, 20));
		g.drawString("GAMEOVER",80,100);
		g.setFont(new Font("Verdana", 1, 10));
		g.drawString("your score is"+((tail-3)*10), 120, 130);
		g.setColor(Color.black);
		g.drawString("Created by phani :-)  phani42@gmail.com",20,150);
        }
    }
   public int nextDot()
   {
       int temp =(int)(Math.random()*27);
       return temp*10;       
   }
   public void doFalse()
   {
       if(p.isVisible())p.setVisible(false);
       left=right=up=down=false;
       
   }
    
    public void keyTyped(KeyEvent e) 
    {
        int key=e.getKeyCode();
        if(! started) started=true;
        if(key == e.VK_LEFT)
        {
            if(! right)
            {
                doFalse();
                left = true;
                x_speed = -10;
                y_speed = 0;                
            }
        }
        
        else if(key == e.VK_RIGHT)
        {
            if(! left)
            {
                doFalse();
                x_speed = 10;
                y_speed = 0;
                right = true;
            }
        }
        
        else if(key == e.VK_UP)
        {
            if(! down)
            {
                doFalse();
                x_speed = 0;
                y_speed = -10;
                up=true;
            }
        }
        
        else if(key == e.VK_DOWN)
        {
            if(! up)
            {
                doFalse();
                x_speed = 0;
                y_speed = 10;
                down=true;
            }
        }
        
    }

    public void keyPressed(KeyEvent e) 
    {
        keyTyped(e);
    }

    public void keyReleased(KeyEvent e) 
    {
        
    }

    public void actionPerformed(ActionEvent e) 
    {
        String s = e.getActionCommand();
        setFocusable(true);

        if(s.equals("level 1"))
        {
           doFalse();
           started=true;
           gameover=false;
           speed=150;
           if(t!=null)t=null;
           t=new Thread(this);
           t.start();
        }
        
        else if(s.equals("level 2"))
        {
           doFalse();
           started=true;
           gameover=false;
           speed=100;
           if(t!=null)t=null;
           t=new Thread(this);
           t.start();
        }
        
        else if(s.equals("level 3"))
        {
           doFalse();
           started=true;
           gameover=false;
           speed=80;
           if(t!=null)t=null;
           t=new Thread(this);
           t.start();
        }
        
        else if(s.equals("level 4"))
        {
           doFalse();
           started=true;
           gameover=false;
           speed=60;
           if(t!=null)t=null;
           t=new Thread(this);
           t.start();
        }
        else if(s.equals("level 5"))
        {
           doFalse();
           started=true;
           gameover=false;
           speed=40;
           if(t!=null)t=null;
           t=new Thread(this);
           t.start();
        }
    }

    

}
