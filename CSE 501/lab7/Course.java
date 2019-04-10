package lab7;

public class Course {
	private String name;
	private int seatsRemaining, seatsTaken, credits, capacity, enrolledPointer;
	private Student enrolled[];
	/**
	 * a
	 * @param name
	 * @param credits
	 * @param capacity
	 */
	public Course(String name, int credits,int capacity){
		this.name = name;
		this.credits = credits;
		this.capacity = capacity;
		this.seatsRemaining = capacity;
		this.seatsTaken = 0;
		this.enrolledPointer = 0;
		this.enrolled = new Student [capacity];
	}
	
	
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @return credits
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * 
	 * @return capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * 
	 * @return seatsTaken
	 */
	public int getSeatsTaken() {
		return seatsTaken;
	}
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @param credits
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}
	/**
	 * 
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	/**
	 * 
	 * @return seats remaining
	 */
	public int getSeatsRemaining() {
		return seatsRemaining;
	}
	/**
	 * 
	 * @param s
	 * @return if is successful of adding
	 */
	public boolean addStudent(Student s) {
		if(this.seatsRemaining < 1) {
			return false;
		}
		
		for(int i = 0; i < this.enrolledPointer; ++i) {
			if(this.enrolled[i] == s) {
				return false;
			}
		}

		this.seatsRemaining -= 1;
		this.seatsTaken += 1;
		this.enrolled[this.enrolledPointer] = s;
		this.enrolledPointer += 1;
		return true;	
	}
	/**
	 * 
	 * @return students roster
	 */
	public String generateRoster() {
		String students = "";
		for(int i = 0; i < this.enrolledPointer; ++i) {
			students += this.enrolled[i].getFullName() + ", ";
		}
		return students;
	}
	/**
	 * a
	 * @return average GPA
	 */
	public double calculateAverageGPA() {
		double totalGPA = 0;
		for(int i = 0; i < this.enrolledPointer; ++i) {
			totalGPA += this.enrolled[i].calculateGradePointAverage();
		}
		return totalGPA / this.enrolledPointer;
	}
	/**
	 * toString
	 */
	public String toString() {
		return this.getName() + ", " + this.getCredits();
	}
}
