package finalproject;
import java.util.HashMap;
import cse131.ArgsProcessor;

public class HumanPlayer implements Player {
	
	private final ArgsProcessor ap; // Don't change this!
	private final String name;
	private final int height, width;
	private final int[][]board;
	private final int[][]record;
	private HashMap<Ship, Integer> map = new HashMap <Ship, Integer> ();
	/**
	 * Creates an instance of the HumanPlayer class
	 * Note that we already dealt with taking in an ArgsProcessor object
	 * 		We know you've never seen this before, which is why it's given to you
	 * 		You can treat this ArgsProcessor (ap) throughout the HumanPlayer class
	 * 			like any other ArgsProcessor you've used in 131
	 * We leave the rest of the constructor to you
	 * 
	 * @param name the name of the player
	 * @param height the height of the board
	 * @param width the width of the board
	 * @param ap the ArgsProcessor object
	 */
	
	/**
	 * board[x][y] = 0 means nothing on board[x][y], board[x][y] = 1 means ship on x,y
	 * @param name
	 * @param height
	 * @param width
	 * @param ap
	 */
	public HumanPlayer(String name, int height, int width, ArgsProcessor ap) {
		this.ap = ap;
		this.name = name;
		this.height = height;
		this.width = width;
		this.board = new int[height][width];
		this.record = new int[height][width];
		for(int r = 0; r < height; ++r) {
			for(int c = 0; c < width; ++c) {
				this.board[r][c] = 0;
				this.record[r][c] = 0;
			}
		}
	}
	/**
	 * add a ship
	 */
	@Override
	public boolean addShip(Ship s) {
		for (int i = 0; i < s.getLength(); ++i) {
			if((s.getOccupy()[i][0] < this.height && s.getOccupy()[i][0] >= 0) && (s.getOccupy()[i][1] < this.width && s.getOccupy()[i][1] >= 0)) {
				if(this.board[s.getOccupy()[i][0]][s.getOccupy()[i][1]] == 1) {
					return false;
				}
			}
			else {
				return false;
			}
		}
		this.map.put(s, 0);
		for(int i = 0; i < s.getLength(); ++i) {
			this.board[s.getOccupy()[i][0]][s.getOccupy()[i][1]] = 1;
		}
		return true;
	}
	/**
	 * get target location
	 */
	@Override
	public int[] getTargetLocation() {
		int[] res = new int[2];
		while(true) {
			res[0] = this.ap.nextInt("Enter x coordinate");
			res[1] = this.ap.nextInt("Enter y coordinate");
			if ((res[0] >= 0 && res[0] < this.width) && (res[1] >= 0 && res[1] < this.height)) {
				break;
			}
		}
		return res;
	}

	@Override
	/**
	 * record[x][y] = 1 means hit, -1 means missed.
	 */
	public void recordHitOrMiss(int x, int y, boolean isHit) {
		if((x >= 0 && x < this.width) && (y >= 0 && y < this.height)) {
			if(isHit) {
				this.record[y][x] = 1;
			}
			else {
				this.record[y][x] = -1;
			}
		}
	}

	@Override
	/**
	 * decide where to place ship and add it
	 */
	public Ship decideShipPlacement(int length) {
		int tLX = -1;
		int tLY = -1;
		boolean isH = false;
		while(true) {
			tLX = this.ap.nextInt("Enter the top left x");
			tLY = this.ap.nextInt("Enter the top left y");
			isH = this.ap.nextBoolean("Enter if horizontal or not");
			if(this.isValidShipToAdd(new Ship(tLX, tLY, length, isH))) {
				break;
			}
		}
		Ship res = new Ship(tLX, tLY, length, isH);
		return res;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	/**
	 * return ture if hit a ship
	 */
	public boolean respondToFire(int x, int y) {
		if(x >= 0 && x < this.width && y >=0 && y < this.height) {
			if (this.board[y][x] == 1 && this.record[y][x] == 0) {
				this.record[y][x] = 1;
				findShip: for(Ship key : this.map.keySet()) {
					for(int i =0; i < key.getLength(); ++i) {
						if(key.getOccupy()[i][1] == x && key.getOccupy()[i][0] == y) {
							key.isHit(x, y);
							// store the hit in valueSet of map
							this.map.put(key, this.map.get(key)+1);
							break findShip;
						}
					}
				}
				return true;
			}
			else if(this.board[y][x] == 1 && this.record[y][x] == 1) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	@Override
	/**
	 * return the number of ships still afloat
	 */
	public int numShipsStillAfloat() {
		int num = 0;
		for(Ship key : this.map.keySet()) {
			if (key.getLength() > this.map.get(key)) {
				num += 1;
			}
		}
		return num;
	}

	@Override
	/**
	 * add a ship in random position
	 */
	public boolean addRandomShip(int length) {
		
		while(true) {
			int y = (int) (this.height * Math.random());
			int x = (int) (this.width * Math.random());
			if(this.isValidShipToAdd(new Ship (x, y, length, true))) {
				this.addShip(new Ship(x, y, length, true));
				break;
			}
			if(this.isValidShipToAdd(new Ship (x, y, length, false))) {
				this.addShip(new Ship(x, y, length, false));
				break;
			}
		}
		
		return true;
	}

	@Override
	/**
	 * test if it is valid to add
	 */
	public boolean isValidShipToAdd(Ship s) {
		for(Ship key : this.map.keySet()) {
			if(key.equals(s)) {
				return false;
			}
		}
		int tLX = s.gettLX();
		int tLY = s.gettLY();
		int length = s.getLength();
		boolean isH = s.getisH();
		if((tLX >= 0 && tLX < this.width) && (tLY >= 0 && tLY < this.height)) {
			if((isH && tLX + length-1 < this.width) || (!isH && tLY + length-1 < this.height)){
			for(int i = 0; i < length; ++i) {
				if(isH) {
					if(this.board[tLY][tLX+i] == 1) {
						return false;
					}
				}
				else {
					if(this.board[tLY+i][tLX] == 1) {
						return false;
					}
				}
				if(i == length - 1) {
					if(isH ) {
						if(this.board[tLY][tLX+i] != 1) {
							return true;
						}
					}
					else { 
						if(this.board[tLY+i][tLX] != 1) {
							return true;
						}
					}
				}
			}
			}
		}
		return false;
	}

	@Override
	/**
	 * O is hit, X is missed, . is not been hit yet
	 */
	public void printRadar() {
		for(int r = 0; r < this.height; ++r) {
			for(int c = 0; c < this.width; ++c) {
				if(this.record[r][c] == 0) {
					System.out.print(". ");
				}
				else if(this.record[r][c] == 1) {
					System.out.print("O ");
				}
				else {
					System.out.print("X ");
				}
				
			}
			System.out.println(" ");
		}
	}


}
