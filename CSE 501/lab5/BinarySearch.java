package lab5;

import cse131.NotYetImplementedException;

public class BinarySearch {
	/**
	 * Calculates the floored midpoint of two specified integer values.
	 * 
	 * For example: a: 300 and b: 400 returns 350. a: 300 and b: 401 also returns
	 * 350. a: 300 and b: 402 returns 351.
	 * 
	 * @param a a value
	 * @param b another value
	 * @return the midpoint of a and b
	 */
	public static int calculateMidPoint(int a, int b) {
		int mid = (a + b) / 2;
		return mid;
	}

	/**
	 * Searches the specified sorted array for the value specified by key. If the
	 * array contains key, then it returns an index which holds the value, otherwise
	 * it returns -1. If array contains multiple elements with the specified key
	 * value, there is no guarantee which index will be returned.
	 * 
	 * If the array is unsorted, then the results are undefined.
	 * 
	 * This method must not mutate (that is: change the contents of) the specified
	 * array, nor would it have any real reason to do so.
	 * 
	 * @param array the array to be searched
	 * @param key   the value to be searched for
	 * @return an index of an occurrence of key, if it is contained in the array;
	 *         otherwise -1.
	 */
	public static int findIndexInSorted(String[] array, String key) {
		int left = 0;
		int right = array.length - 1;
		while (left <= right) {
			int mid = calculateMidPoint(left, right);
			if (Strings.isGreaterThan(key, array[mid]) ) {
				left = mid + 1;
			}
			if (Strings.isLessThan(key, array[mid])) {
				right = mid - 1;
			}
			if (Strings.equals(key, array[mid])) {
				return mid;
			}
		}
		return -1;
		
		
	}
}
