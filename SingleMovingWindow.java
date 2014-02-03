import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SingleMovingWindow extends JFrame implements Runnable
{
	
	double velx,vely;
	int delay=50;
	Dimension d;
	int wid,ht;
	public SingleMovingWindow(double velx,double vely,Dimension d)
	{
		super("Dancing Window");
		this.velx=velx;
		this.vely=vely;
		this.d=d;
		wid=d.width;
		ht=d.height;

		setSize(100,100);
	}

	public void run()
	{
		while(true)	
		{
			Point p=getLocationOnScreen();
			p.setLocation(p.x+velx,p.y+vely);
				
			//Check if touched the wall
			if(p.x<0 || p.x>wid-100)
			{
				System.out.println("Hit side wall "+p.x+ " "+wid);
				p.setLocation(p.x-2*velx,p.y);
				velx*=-1;
			}

			if(p.y<20 || p.y>ht-100)
			{
				System.out.println("Hit horizontal wall "+p.y+ " "+ht);
				p.setLocation(p.x,p.y-2*vely);
				vely*=-1;
			}

			this.setLocation(p);

			try
			{
				Thread.sleep(delay);
			}
			catch(Exception e)
			{}
		}


	}

	public void runAnimation()
	{
		(new Thread(this)).start();
	}
		
	public static void main(String args[])throws Exception
	{
		
		int range=10; // Range of the velocities
		if(args.length>0)
			range=Integer.parseInt(args[0]);
			

		//Assign a random velocity to this tiny window
		Random r=new Random();
		double velx=r.nextGaussian()*range;
		double vely=r.nextGaussian()*range;
		
		//Find the width and height
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		Point p=new Point(d.width/2,d.height/2);


		System.out.println(velx+" "+vely);

		SingleMovingWindow window=new SingleMovingWindow(velx,vely,d);
		window.setLocation(p);
		window.setVisible(true);	
		try
		{
			Thread.sleep(2000);
		}
		catch(Exception e)
		{}

		window.runAnimation();
		

	}
}
