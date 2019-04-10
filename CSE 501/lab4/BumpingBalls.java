package lab4;

import cse131.ArgsProcessor;
import sedgewick.StdDraw;

public class BumpingBalls {
	
	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		// set XY scale
		StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        
		//  initialize value
		int numBalls = ap.nextInt("How many balls do you want?");
		int pause = ap.nextInt("PAUSE");
		int numIter = ap.nextInt("How many Iterations?");
		int num = 0;
		double vxBalls[] = new double [numBalls];
		double vyBalls[] = new double [numBalls];
		double xBalls[] = new double [numBalls];
		double yBalls[] = new double [numBalls];
		double vx = 0;
		double vy = 0;
		double x = 0;
		double y = 0;
		double radius = 0.05;
		
	    //  initialize position and velocity
		for (int i = 0; i < numBalls; ++i) {
			
			// vx and vy
			vx = 0;
			while(Math.abs(vx) < 0.01 || Math.abs(vx) > 0.1) {
				vx = Math.random() * 2;
				if (vx < 1) vx = -vx;
				else vx = vx - 1;
			}
			vxBalls[i] = vx;
			
			vy = 0;
			while(Math.abs(vy) < 0.01 || Math.abs(vy) > 0.1) {
				vy = Math.random() * 2;
				if (vy < 1) vy = -vy;
				else vy = vy - 1;
			}
			vyBalls[i] = vy;
			
			// x and y
			x = 0;
			while(Math.abs(x) < radius || Math.abs(x) > 1 - radius) {
				x = Math.random() * 2;
				if (x < 1) x = -x;
				else x = x - 1;
			}
			xBalls[i] = x;
			
			y = 0;
			while(Math.abs(y) < radius || Math.abs(y) > 1 - radius) {
				y = Math.random() * 2;
				if (y < 1) y = -y;
				else y = y - 1;
			}
			yBalls[i] = y;
		}
//		for (int k = 0; k < numBalls; ++k) {
//			System.out.println("x" + k + " : " + vxBalls[k]);
//			System.out.println("y" + k + " : " + vyBalls[k]);
////			StdDraw.setPenColor(StdDraw.RED);
////			StdDraw.filledCircle(xBalls[k], yBalls[k], radius);
//		}
		
		while (num < numIter * 10) {
			num++;
			StdDraw.clear();
			boolean collision[] = new boolean[numBalls];
			for (int i = 0; i < numBalls; ++i) {
				
				// set pen color
				StdDraw.setPenColor(StdDraw.BLUE);
				
				// change velocity if over the border
				if (Math.abs(xBalls[i] + vxBalls[i]) > 1.0 - radius) vxBalls[i] = -vxBalls[i];
				if (Math.abs(yBalls[i] + vyBalls[i]) > 1.0 - radius) vyBalls[i] = -vyBalls[i];
				
				// change velocity if collision with another ball
				
				if (i < numBalls - 1) {
					for (int j = i+1; j < numBalls; j++) {
						if (Math.sqrt(Math.pow(xBalls[i]-xBalls[j], 2) + Math.pow(yBalls[i]-yBalls[j], 2)) <= radius * 2) {
							collision[i] = true;
							collision[j] = true;
						}
					}
				}
				
				if (collision[i]) {
					vxBalls[i] = -vxBalls[i];
					vyBalls[i] = -vyBalls[i];
				}
				
				// ball moves
				xBalls[i] = xBalls[i] + vxBalls[i];
				yBalls[i] = yBalls[i] + vyBalls[i];
				
				// set pen color by direction
				if (vxBalls[i] < 0 && vyBalls[i] < 0) StdDraw.setPenColor(StdDraw.RED);
				if (vxBalls[i] > 0 && vyBalls[i] < 0) StdDraw.setPenColor(StdDraw.GREEN);
				if (vxBalls[i] > 0 && vyBalls[i] > 0) StdDraw.setPenColor(StdDraw.PINK);
				// draw balls
				StdDraw.filledCircle(xBalls[i], yBalls[i], radius);
				
			}
			
			StdDraw.show(pause);
		}
	}

}
