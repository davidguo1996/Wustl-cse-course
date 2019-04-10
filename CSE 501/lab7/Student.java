package lab7;
/**
 * 
 * @author david
 *
 */
public class Student {
	private String firstName, lastName;
	private final int id;
	private int attemptedCredits, passingCredits;
	private double totalGradeQualityPoints, bearBucksBalance;
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param id
	 */
	public Student(String firstName, String lastName, int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}
	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * 
	 * @return
	 */
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * caculate submitGrade
	 * @param grade
	 * @param credits
	 */
	public void submitGrade(double grade, int credits) {
		this.totalGradeQualityPoints += grade * credits;
		this.attemptedCredits += credits;
		if (grade >= 1.7 ) {
			this.passingCredits += credits;
		}
	}
	/**
	 * 
	 * @return
	 */
	public int getTotalAttemptedCredtis(){
		return this.attemptedCredits;
	}
	/**
	 * a
	 * @return
	 */
	public int getTotalPassingCredits() {
		return this.passingCredits;
	}
	/**
	 * calculateGPA
	 * @return GPA
	 */
	public double calculateGradePointAverage() {
		return this.totalGradeQualityPoints / this.attemptedCredits;
	}
	/**
	 * 
	 * @return ClassStanding
	 */
	public String getClassStanding() {
		if(passingCredits < 30) {
			return "FirstYear";
		}
		else if(passingCredits < 60) {
			return "Sophomore";
		}
		else if(passingCredits < 90) {
			return "Junior";
		}
		else {
			return "Senior";
		}
	}
	/**
	 * 
	 * @return isEligible for PhiBetaKappa
	 */
	public boolean isEligibleForPhiBetaKappa() {
		if ((this.attemptedCredits >= 98 && this.calculateGradePointAverage() >= 3.6) 
				||(this.attemptedCredits >= 75 && this.calculateGradePointAverage() >= 3.8)) {
			return true;
		}
		return false;
	}
	/**
	 * add funds
	 * @param amount
	 * 
	 */
	public void depositBearBucks(double amount) {
		this.bearBucksBalance += amount;
	}
	/**
	 * withdraw funds
	 * @param amount
	 */
	public void deductBearBucks(double amount) {
		this.bearBucksBalance -= amount;
	}
	/**
	 * 
	 * @return balance
	 */
	public double getBearBucksBalance() {
		return this.bearBucksBalance;
	}
	/**
	 * 
	 * @return cash out money
	 */
	public double cashOutBearBucks() {
		if (this.bearBucksBalance <= 10) {
			this.bearBucksBalance = 0.0;
			
		}
		else {
			this.bearBucksBalance -=10;
		}
		double cashOut = this.bearBucksBalance;
		this.bearBucksBalance = 0;
		return cashOut;
	}
	/**
	 * 
	 * @param firstName
	 * @param otherParent
	 * @param isHyphenated
	 * @param id a
	 * @return the child student 
	 */
	public Student createLegacy(String firstName, Student otherParent, boolean isHyphenated, int id) {
		String lastName = "";
		if (isHyphenated) {
			lastName = this.lastName + "-" + otherParent.lastName;
		}
		else {
			lastName = this.lastName;
		}
		Student child = new Student(firstName, lastName, id);
		child.bearBucksBalance = this.cashOutBearBucks() + otherParent.cashOutBearBucks();
		return child;
	}
	/**
	 * toString
	 */
	public String toString() {
		return this.firstName + " " + this.lastName + " " + String.valueOf(this.id);
	}
	
}
