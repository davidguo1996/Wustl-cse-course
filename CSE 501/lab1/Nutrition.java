package lab1;

import cse131.ArgsProcessor;

public class Nutrition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap =new ArgsProcessor(args);
		String name = ap.nextString("Name?");
		double carbs = ap.nextDouble("How much carbohydrates is in grams?");
		double fat = ap.nextDouble("How much fat is in grams?");
		double protein = ap.nextDouble("How much protein is in grams?");
		double statesCal = ap.nextDouble("What's the statesCals in grams?");
		
		//double test = 2.6456;
		//System.out.println(test + "\n\n");
		//test = Math.round(test);
		//System.out.println(test + "\n\n");
		
		double carCal = Math.round(carbs * 40)/10.0;
		double fatCal = Math.round(fat * 90)/10.0;
		double proCal = Math.round(protein * 40)/10.0;
		//System.out.println(carCal+"\n"
		//					+ fatCal + "\n"
		//					+ proCal + "\n");
		
		double avaCal = Math.round((carCal + fatCal + proCal)*10)/10.0;
		//System.out.println(avaCal);
		double unavaCal = Math.round((avaCal - statesCal)*10)/10.0;
		double dietfiber = unavaCal / 4;
		double carboP = (int)(carCal / statesCal * 1000)/10.0;
		double fatP = (int)(fatCal / statesCal * 1000)/10.0;
		double proP = (int)(proCal / statesCal * 1000)/10.0;
		boolean lowcarb = carboP < 25;
		boolean lowfat = fatP < 15;
		boolean coinflip = Math.random() < 0.5;
		System.out.println(name + " has \n"
				+ carbs + " grams of carbohydrates = " + carCal + " Calories \n"
				+ fat + " grams of fat = " + fatCal + " Calories \n"
				+ protein + " grams of protein = " + proCal + " Calories \n"
				+ "This food is said to have " + (int) statesCal + " (available) Calories. \n"
				+ "With " + unavaCal + " unavailable Calories, this food has " + dietfiber + " grams of fiber \n \n"
				+ "Approximately \n"
				+ carboP + "% of your food is carbohydrates \n"
				+ fatP + "% of your food is fat \n"
				+ proP + "% of your food is protein \n"
				+ "This food is acceptable for a low-carb diet?  " + lowcarb + "\n"
				+ "This food is acceptable for a low-fat diet?  " + lowfat + "\n"
				+ "By coin flip, you should eat this food?   " + coinflip + "\n");
		
				
	}

}
