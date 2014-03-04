import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Random;

public class MovingWindowMain {

	public static void main(String args[])throws Exception
	{	
		//Find the width and height
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		Point p=new Point(d.width/2,d.height/2);
		int numWin=2;
		int range=20; // Range of the velocities
		if(args.length>0)
			numWin=Integer.parseInt(args[0]);

		Multi window[] =new Multi[numWin];

		for(int win=0;win<numWin;win++)
		{
			//Assign a random velocity to this tiny window
			Random r=new Random();
			double velx=r.nextGaussian()*range;
			double vely=r.nextGaussian()*range;

			System.out.println(velx+" "+vely);

			window[win]=new Multi(velx,vely,d);
			window[win].setLocation(p);
			window[win].setVisible(true);	
		}

		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e)
		{}

		for(int win=0;win<numWin;win++)
			window[win].runAnimation();
		

	}
}
