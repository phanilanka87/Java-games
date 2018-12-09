
/*
 creted by :phani
 email:phani42@gmail.com
 */
package pong;

import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;



public class Pong extends Applet implements KeyListener, ActionListener, Runnable 
{

	Panel p;
	Button b1;
	Button b2;
	Button b3;
	
	
	Ball ball;
	ComputerPlayer cpu;
	HumanPlayer player;
	
	Rectangle cpurect;
	Rectangle playerrect;
	Rectangle ballrect;
	
	boolean started;
	boolean gameover;
	
	Thread t = null;
	
	int gamespeed;
	private final int WIDTH = 300;
	private final int HEIGHT = 200; 
	
	int playerscore;
	int cpuscore;
	int playerlives;
	int cpulives;
	
	int ballSpeed;
	int cpuSpeed;
	private int UP; 
	private int DOWN;
	
	
	public void init()
	{
		
		
		
		p=new Panel();
		
		b1=new Button("level 1");
		b2=new Button("level 2");
		b3=new Button("level 3");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		p.add(b1);
		p.add(b2);
		p.add(b3);
		
		add(p);
		setSize(WIDTH , HEIGHT);
		
		ball = new Ball(WIDTH/2 , HEIGHT/2);
		cpu = new ComputerPlayer(WIDTH-10 ,HEIGHT/2-20);
		player = new HumanPlayer(5 , HEIGHT/2-20);
		
		ComputerPlayer cpu;
		HumanPlayer player;
		
		addKeyListener(this);
		requestFocus();
		setFocusable(true);
		
	}
	
	
	public void run() 
	{
		
		cpurect = new Rectangle();
		playerrect=new Rectangle();
		ballrect =new Rectangle();
		
		gameover = false;
		started = true;
		
		while(true)
		{
				
			if(gameover)
			{
				started = false;
				p.setVisible(true);
				repaint();
				break;
			}
			
			if(started)
			{	
				collisionCheck();
				moveBall();
				moveCPU();
			}
			
			
			repaint();
		try
		{
			Thread.sleep(gamespeed);
		}
		catch(Exception e){}
		}
		
	}
	
	private void moveBall()
	{
	 
		if(ball.getX() >= WIDTH-10)
	 {
		ball.setX(WIDTH-10);	
		ball.setXSpeed(-ballSpeed);
	    cpulives--;
	    playerscore += 5;
	 }
	 
	 if(ball.getX()<= 0)
	 {
	 	ball.setX(0);
	 	ball.setXSpeed(ballSpeed); 
	 	playerlives--;
	 	cpuscore += 5;
	 }
	 
	 if(ball.getY() >= HEIGHT-10 )ball.setYSpeed(-ballSpeed);
	 if(ball.getY() <=0)ball.setYSpeed(ballSpeed);
		
	 if(playerlives > 0 && cpulives > 0)
	 ball.move();
	 
	}
	
	private void moveCPU()
	{
		if(ball.getXSpeed()>0 )//&& ball.getX()>WIDTH/3)
		{
			if(ball.getYSpeed()>0 )//&& cpu.getY()>0)
			{
				if(ball.getY() >= cpu.getY()+20)
				{
					cpu.setSpeed(cpuSpeed);
					cpu.move();
				}
				else if(ball.getY()<= cpu.getY()+20)
				{
					cpu.setSpeed(-cpuSpeed);
					cpu.move();
				}
			}
			
			else if(ball.getYSpeed()<0 && cpu.getY()<=WIDTH-40)
			{
				if(ball.getY()<=cpu.getY())
				{
					cpu.setSpeed(-cpuSpeed);
					cpu.move();
				}
				else if(ball.getY() >= cpu.getY())
				{
					cpu.setSpeed(cpuSpeed);
					cpu.move();
				}
				if(cpu.getY()<0 )cpu.setY(0);
				if(cpu.getY()>HEIGHT-40)cpu.setY(HEIGHT-40);
			}
		}
		
	}
	
	private void collisionCheck()
	{
		cpurect.setBounds(cpu.getX(),cpu.getY(),15,40);
		playerrect.setBounds(player.getX(),player.getY(),15,40);
		ballrect.setBounds(ball.getX(),ball.getY(),10,10);
		
		int i;
		do
		{
			i =(int)(Math.random()*(ballSpeed*2));
		}
		while(i<=ballSpeed );
		
		if(ballrect.intersects(playerrect))
		{
			ball.setXSpeed(i);
		
		}
		else  if(ballrect.intersects(cpurect))
		{
			ball.setXSpeed(-i);
		}
		
		if(playerlives ==0 || cpulives == 0)
		{
			gameover=true;
		}
	}
	
	public void update(Graphics g)
	{
		g.setColor(getBackground());
		g.fillRect(0,0,WIDTH+10,HEIGHT+10);
		paint(g);
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(cpu.getX(),cpu.getY(),15,40);
		g.fillOval(ball.getX(),ball.getY(),10,10);
		g.fillRect(player.getX(),player.getY(),15,40);
		
		g.setColor(Color.blue);
		g.drawString("score :"+playerscore ,10,10 );
		g.drawString("cpu score:"+cpuscore,130,10);
		g.drawString("lives:"+playerlives,60,10);
		g.drawString("cpulives"+cpulives,200,10);
		g.drawRect(0,0,WIDTH+10,HEIGHT+10);
	if(! started)
	{
		g.setFont(new Font("Arial",Font.BOLD,15));
		g.setColor(Color.blue);
		g.drawString("select your level",20,80);
	    g.setColor(Color.orange);
		g.drawString("use up and down arrows to play",20,100);
	}
	
	if(gameover)
	{
		g.setFont(new Font("verdana",Font.BOLD,20));
		g.setColor(Color.red);
		if(playerlives == 0 )
		{
			g.drawString("COMPUTER WINS" ,60,160);
			g.drawString("GAME  OVER",60,130);
		}
		else
			g.drawString("PLAYER WINS" ,60,160);
	}
	}
	
	 public void reset()
	{
	 	ball = new Ball(WIDTH/2 , HEIGHT/2);
		cpu = new ComputerPlayer(WIDTH-10 ,HEIGHT/2-20);
		player = new HumanPlayer(5 , HEIGHT/2-20);
		ball.setSpeed(0,0);
		cpu.setSpeed(0);
		cpu.setY(HEIGHT/2 -20);
		gamespeed = 0;
		t=null;
	}
	

	
	public void keyTyped(KeyEvent e) 
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP) 
			{
			if( (player.getY() >= 0))
			   {
				player.setSpeed(UP);
				player.move();
			    }
			if(player.getY()<=0)player.setY(0);
			}
		else if(key == KeyEvent.VK_DOWN)
			{
			if((player.getY() <= HEIGHT-40 ))
			{
				player.setSpeed(DOWN);
				player.move();
			}
			}
	}

	
	public void keyPressed(KeyEvent e)
	{
		keyTyped(e);
	}

	
	public void keyReleased(KeyEvent e) 
	{
		keyPressed(e);
	}

	
	public void actionPerformed(ActionEvent e) 
	{   
		reset();
		String s = e.getActionCommand();
		if(s.equals("level 1"))
		{
			t=new Thread(this);			
			UP = -5;
			DOWN = 5;
			playerlives = 15;
			cpulives = 15;
			playerscore=0;
			cpuscore=0;
			gamespeed=120;
			ballSpeed=4;
			cpuSpeed=4;
			p.setVisible(false);
			ball.setSpeed(-4,4);
			t.start();
						
		}
		
		
		else if(s.equals("level 2"))
		{
			t=new Thread(this);			
			UP = -5;
			DOWN = 5;
			playerlives = 15;
			cpulives = 15;
			playerscore=0;
			cpuscore=0;
			gamespeed=80;
			ballSpeed=5;
			cpuSpeed=4;
			p.setVisible(false);
			ball.setSpeed(-4,4);
			t.start();
						
		}
		
		else if(s.equals("level 3"))
		{
			t=new Thread(this);			
			UP = -5;
			DOWN = 5;
			playerlives = 15;
			cpulives = 15;
			playerscore=0;
			cpuscore=0;
			gamespeed=40;
			ballSpeed=6;
			cpuSpeed=5;
			p.setVisible(false);
			ball.setSpeed(-5,5);
			t.start();			
		}
		
	}

	

}
