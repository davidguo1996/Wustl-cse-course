package lab3;

import cse131.ArgsProcessor;

public class Dice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);
		int d = ap.nextInt("How many dice?");
		int t = ap.nextInt("How many times will the dice be thrown?");
		int array[] = new int[d];
		boolean sameVa[] = new boolean[d - 1];
		int sum[] = new int[t];
		int sameNum = 0;
		for (int i = 0; i < t; ++i) {
			// refresh sameVa[]
			for (int m = 0; m < d - 1; ++m) {
				sameVa[m] = false;
			}
			// roll dice and compute their sum
			System.out.print("Array Index:    ");
			for (int j = 0; j < d; ++j) {
				array[j] = (int)(Math.random()*6 + 1);
				sum[i] = sum[i] + array[j];
				System.out.print(j + "  ");
			}
			// check if they are same
			int checkVa = 0;
			for (int l = 0; l < d - 1; ++l) {
				if(array[l] == array[l + 1]) {
					sameVa[l] = true;
					checkVa = checkVa + 1;
				}
			}
			// if same, store it
			if (checkVa == d - 1) {
				sameNum = sameNum + 1;
			}
			
			System.out.print("\n");
			System.out.print("Array Contents: ");
			for (int k = 0; k < d; ++k) {
				System.out.print(array[k] + "  ");
			}
			System.out.println("\n");
		}
		System.out.println("The fraction of times that all of the dice have the same value in a throw is: " + (double)sameNum / t);
		// check if there is the same sum and D dice have sum from D to 6*D
		int sumCheck[] = new int[5*d + 1];
		for (int n = d; n <= 6 * d; ++n) {
			//the amount of sum
			for (int f = 0; f < t; ++f) {
				if (sum[f] == n) {
					sumCheck[n - d] = sumCheck[n - d] + 1;
				}
			}
		}
		// go through the sumCheck[]
		System.out.println("Sum      Numbers");
		for (int c = 0; c <= 5*d; ++c) {
			if (sumCheck[c] != 0) {
				System.out.println(c+d + "       " + sumCheck[c]);
			}
		}
		
		
}

}
