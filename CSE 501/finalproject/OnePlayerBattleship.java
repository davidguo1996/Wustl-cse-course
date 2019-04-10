package finalproject;

import cse131.ArgsProcessor;
import cse131.NotYetImplementedException;

public class OnePlayerBattleship implements Battleship {

	private ArgsProcessor ap;
	private Player p1;
	
	/**
	 * The main method that gets the starting parameters for a game,
	 * creates an instance of the OnePlayerBattleship class,
	 * and plays the game!
	 * @param args
	 */
	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		
		String name = ap.nextString("What is the player's name?");
		int length = ap.nextInt("What is the length of the board?");
		int width = ap.nextInt("What is the width of the board?");
		int numShips = ap.nextInt("How many ships should each player have?");
		boolean randomShips = ap.nextBoolean("Should the ships be placed randomly? Type true or false");
		Battleship bs = new OnePlayerBattleship(length, width, randomShips, numShips, name, ap);
		Player winner = bs.play();
		System.out.println(winner.getName() + " won!");
	}
	
	/**
	 * Create an instance of the OnePlayerBattleship class
	 * Create a player with the given name
	 * Place all ships, randomly or manually
	 * 
	 * NOTE: We've created the player for you, in order to deal with the ArgsProcessor that needs to be passed
	 * 
	 * @param width width of the board (# cols)
	 * @param height height of the board (# rows)
	 * @param randomShips whether or not the ships should be placed randomly
	 * @param playerName the name of the Player who will be playing the game
	 */
	public OnePlayerBattleship(int width, int height, boolean randomShips, int numShips, String playerName, ArgsProcessor ap) {
		this.p1 = new HumanPlayer(playerName, height, width, ap); // DON'T CHANGE THIS
		this.ap = ap;
		if(randomShips) {
			for(int i = 0; i < numShips; ++i) {
				int lenShip = (int) (width * Math.random());
				this.p1.addRandomShip(lenShip);
			}
		}
		else {
			for(int i = 0; i < numShips; ++i) {
				int lenShip = (int) (width * Math.random());
				this.p1.addShip(this.p1.decideShipPlacement(lenShip));
			}
		}
	}

	@Override
	public Player play() {
		while(true) {
			if(this.turn(this.getPlayerOne())) {
				return this.getPlayerOne();
			}
		}
	}

	@Override
	public boolean turn(Player p) {
		int[] target = new int[2];
		System.out.println("Now:");
		p.printRadar();
		int num = p.numShipsStillAfloat();
		target = p.getTargetLocation();
		p.recordHitOrMiss(target[0], target[1], p.respondToFire(target[0], target[1]));
		System.out.println("Hit:");
		p.printRadar();
		if(num == p.numShipsStillAfloat() + 1) {
			System.out.println("You sunk my battleship!");
		}
		if(p.numShipsStillAfloat() == 0) {
			return true;
		}
		else {
			
			return false;
		}
		
		
	}
	
	/**
	 * We've implemented this for you since there's only one player, you can leave this alone!
	 */
	@Override
	public Player getPlayerOne() {
		return p1;
	}

	/**
	 * We've implemented this for you since there's only one player, you can leave this alone!
	 */
	@Override
	public Player getPlayerTwo() {
		return null;
	}

}
