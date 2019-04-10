package lab6;

import sedgewick.StdDraw;

public class Triangles {

	public static void Triangles_draw(double xu, double yu, double xl, double yl, double xr, double yr) {
		if ((xr-xl)/2 < 0.01) {
			return;
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.line((xu+xl)/2, (yu+yl)/2, (xl+xr)/2, yl);
		StdDraw.line((xl+xr)/2, yl, (xu+xr)/2, (yu+yl)/2);
		StdDraw.line((xu+xr)/2, (yu+yl)/2, (xu+xl)/2, (yu+yl)/2);
		Triangles_draw((xu+xl)/2, (yu+yl)/2, xl, yl, (xl+xr)/2, yl);
		Triangles_draw(xu, yu, (xu+xl)/2, (yu+yl)/2, (xu+xr)/2, (yu+yl)/2);
		Triangles_draw((xu+xr)/2, (yu+yl)/2, (xl+xr)/2, yl, xr, yr);
	}
	
	public static void main(String[] args) {
		StdDraw.setXscale(0, 1.0);
		StdDraw.setYscale(0, 1.0);
		StdDraw.line(0.0, 0.0, 1.0, 0.0);
		StdDraw.line(0.0, 0.0, 0.5, Math.sqrt(3.0)/2);
		StdDraw.line(1.0, 0.0, 0.5, Math.sqrt(3.0)/2);
		Triangles_draw(0.5, Math.sqrt(3.0)/2, 0.0, 0.0, 1.0, 0.0);
	}
}
