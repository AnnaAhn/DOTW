import javax.swing.*;

import java.awt.*;

public class Multi extends JFrame implements Runnable {
	private static Point psion = new Point();
	double velx,vely;
	int delay=50;
	Dimension d;
	int wid,ht;
	public Multi(double velx,double vely,Dimension d) {
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
			input(p);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
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
			
			//Check if bumped each other
			if(isBumped(p)){
				System.out.println("Bumped!");
			}

			this.setLocation(p);
		}


	}
	private synchronized void input(Point p){
		psion = p;
	}
	private synchronized boolean isBumped (Point p) {
		
		if((psion.x < p.x && p.x < psion.x + 100 )&& (psion.y < p.y && p.y < psion.y + 100)){
			return true;
		}
		return false;
	}
	public void runAnimation() {
		(new Thread(this)).start();
	}

	public static Point getPsion() {
		return psion;
	}

	public static void setPsion(Point psion) {
		Multi.psion = psion;
	}
}
