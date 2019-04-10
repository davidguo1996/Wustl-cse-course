package lab2;

import cse131.ArgsProcessor;

public class Nim {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);
		int sticks = ap.nextInt("How many sticks?");
		boolean firstMove = ap.nextBoolean("Do you want to play first?");
		int round = 0;
		int comMove = 0;
		if (firstMove) {
			while(sticks > 0) {
				
				int huMove = ap.nextInt("How many moves?");
				
				// human move must less than 2
				
				if (huMove <= 2) {
					
					// human move must less than sticks remain
					
					if (huMove <= sticks) {
						
						round = round + 1;
						System.out.print("Round " + round + ", " + sticks + " sticks at start, ");
						sticks = sticks - huMove;
						System.out.print("human took " + huMove + ", so " + sticks + " sticks remain\n");
						if (sticks == 0) {
							System.out.println("Human wins");
							break;
						}
						//decide computer move
						
						if (sticks > 1) {
							comMove = (int)(Math.random() * 2 + 1);
						}
						else {
							comMove = 1;
						}
						
						//continue play
						
						round = round + 1;
						System.out.print("Round " + round + ", " + sticks + " sticks at start, ");
						sticks = sticks - comMove;
						System.out.print("computer took " + comMove + ", so " + sticks + " sticks remain\n");
						if (sticks == 0) {
							System.out.println("Computer wins");
							break;
						}
					}
					
					else {
						System.out.println("Error! Moves should be less than sticks remain");
					}
				}		
					
				else {		
					System.out.println("Error! Moves should be less than 2");
				}
			 
				
			}
			
		}
		else {
			while (sticks > 0) {

				// decide computer move

				if (sticks > 1) {
					comMove = (int) (Math.random() * 2 + 1);
				} else {
					comMove = 1;
				}

				// continue play

				round = round + 1;
				System.out.print("Round " + round + ", " + sticks + " sticks at start, ");
				sticks = sticks - comMove;
				System.out.print("computer took " + comMove + ", so " + sticks + " sticks remain\n");
				int veristicks = sticks;
				if (sticks == 0) {
					System.out.println("Computer wins");
					break;
				}
				
				// loop for human decision verify
				
				while (veristicks == sticks) {
					int huMove = ap.nextInt("How many moves?");
					
					// human move must less than 2
					
					if (huMove <= 2) {
						
						// human move must less than sticks remain
						
						if (huMove <= sticks) {
							
							round = round + 1;
							System.out.print("Round " + round + ", " + sticks + " sticks at start, ");
							sticks = sticks - huMove;
							System.out.print("human took " + huMove + ", so " + sticks + " sticks remain\n");
							if (sticks == 0) {
								System.out.println("Human wins");
								break;
							}
							
						}
						
						else {
							System.out.println("Error! Moves should be less than sticks remain");
						}
					}		
						
					else {		
						System.out.println("Error! Moves should be less than 2");
					}
				}
				
			 
				
			}
			
		}
		}
	}

