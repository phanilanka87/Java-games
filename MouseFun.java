//package mousefun;
/*
 created by phani
 email phani42@gmail.com
*/
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
public class MouseFun extends Applet implements MouseListener,ActionListener
{
    Button b1,b2;
    int x=90;
    int y=110;
    String s="DO YOU THINK YOU R GREAT ?";
    boolean giveup=false;
    boolean started=false;
    
    public void init()
    {
        b1=new Button("yes");
        b2=new Button("no");
        b1.addMouseListener(this);
        b2.addActionListener(this);
        add(b1);
        add(b2);
        this.setSize(300, 300);
        this.setBackground(Color.darkGray);
    }
    public void move()
    {
       x=(int)((Math.random()*27)*10);
       y=(int)((Math.random()*27)*10);
       
       if(x==120 && y== 100)
       {
           move();
       }
    }
   
    public void paint(Graphics g)
    {
    	b1.setBounds(x,y,30,30);
    	b2.setBounds(130,110,30 ,30);
        g.drawString(s, 10, 80);
        
        if(giveup)
        {
            b1.setVisible(false);
        	g.setColor(Color.red);
            g.drawString(" :-) oook....ITS JUST A GAME",20 , 150);
            
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e){}
            g.setColor(Color.black);
            g.drawString("phani :-) phani42@gmail.com", 25, 200);
            g.drawString("refresh ur browser to play again", 25, 230);
             
        }
    }

    public void mouseClicked(MouseEvent e) {
        
    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
       
    }

    public void mouseEntered(MouseEvent e) 
    {
        move();
      repaint();   
    }

    public void mouseExited(MouseEvent e) {
        
    }

    public void actionPerformed(ActionEvent e) {
        giveup=true;
        repaint();
    }

    
        
}