package heaps;

import java.util.Random;
import java.util.UUID;

import javax.swing.JOptionPane;

import heaps.util.HeapToStrings;
import heaps.validate.MinHeapValidator;
import timing.Ticker;

public class MinHeap<T extends Comparable<T>> implements PriorityQueue<T> {

	private Decreaser<T>[] array;
	private int size;
	private final Ticker ticker;

	/**
	 * I've implemented this for you.  We create an array
	 *   with sufficient space to accommodate maxSize elements.
	 *   Remember that we are not using element 0, so the array has
	 *   to be one larger than usual.
	 * @param maxSize
	 */
	@SuppressWarnings("unchecked")
	public MinHeap(int maxSize, Ticker ticker) {
		this.array = new Decreaser[maxSize+1];
		this.size = 0;
		this.ticker = ticker;
		ticker.tick(3);
	}

	//
	// Here begin the methods described in lecture
	//
	
	/**
	 * Insert a new thing into the heap.  As discussed in lecture, it
	 *   belongs at the end of objects already in the array.  You can avoid
	 *   doing work in this method by observing, as in lecture, that
	 *   inserting into the heap is reducible to calling decrease on the
	 *   newly inserted element.
	 *   
	 *   This method returns a Decreaser instance, which for the inserted
	 *   thing, tracks the thing itself, the location where the thing lives
	 *   in the heap array, and a reference back to MinHeap so it can call
	 *   decrease(int loc) when necessary.
	 */
	public Decreaser<T> insert(T thing) {
		//
		// Below we create the "handle" through which the value of
		//    the contained item can be decreased.
		// VERY IMPORTANT!
		//    The Decreaser object contains the current location
		//    of the item in the heap array.  Initially it's ++size,
		//    as shown below.  The size is increased by 1, and that's
		//    were you should store ans in the heap array.
		//
		//    If and when the element there changes location in the heap
		//    array, the .loc field of the Decreaser must change to reflect
		//    that.
		//
		Decreaser<T> ans = new Decreaser<T>(thing, this, ++size);
		this.array[size] = ans;
		decrease(size);
		ticker.tick(3);
		return ans;
		
	}

	/**
	 * This method responds to an element in the heap decreasing in
	 * value.   As described in lecture, that element might have to swap
	 * its way up the tree so that the heap property is maintained.
	 * 
	 * This method can be called from within this class, in response
	 *   to an insert.  Or it can be called from a Decreaser.
	 *   The information needed to call this method is the current location
	 *   of the heap element (index into the array) whose value has decreased.
	 *   
	 * Really important!   If this method changes the location of elements in
	 *   the array, then the loc field within those elements must be modified 
	 *   too.  For example, if a Decreaser d is currently at location 100,
	 *   then d.loc == 100.  If this method moves that element d to
	 *   location 50, then this method must set d.loc = 50.
	 *   
	 * In my solution, I made sure the above happens by writing a method
	 *    moveItem(int from, int to)
	 * which moves the Decreaser from index "from" to index "to" and, when
	 * done, sets array[to].loc = to
	 *   
	 * This method is missing the "public" keyword so that it
	 *   is only callable within this package.
	 * @param loc position in the array where the element has been
	 *     decreased in value
	 */
	void decrease(int loc) {
		
		ticker.tick();
		// recursion base case
		if (loc <= 1) {
			return;
		} 
		
		// no greater than its parent base case
		else if(array[loc / 2].getValue().compareTo(array[loc].getValue()) <= 0){
			return;
		}
		else {
			
			// initialize parent index
			int pa_index = loc / 2;
				ticker.tick();
				
				// swap object the index point to
				Decreaser<T> temp = array[pa_index];
				array[pa_index] = array[loc];
				array[loc] = temp;
				
				//update each object's location
				array[pa_index].loc = pa_index;
				array[loc].loc = loc;
				
				decrease(loc / 2);
				ticker.tick();
			}
			
			return;
		}
	
	
	/**
	 * Described in lecture, this method will return a minimum element from
	 *    the heap.  The hole that is created is handled as described in
	 *    lecture.
	 *    This method should call heapify to make sure the heap property is
	 *    maintained at the root node (index 1 into the array).
	 */
	public T extractMin() {
		ticker.tick();
		T ans = array[1].getValue();
		
		// put the last element on the top, remove the element on the last
		array[1] = array[size];
		array[1].loc = 1;
		array[size] = null;
		size -= 1;
		
		// swift down
		heapify(1);
		return ans;
	}

	/**
	 * As described in lecture, this method looks at a parent and its two 
	 *   children, imposing the heap property on them by perhaps swapping
	 *   the parent with the lesser of the two children.  The child thus
	 *   affected must be heapified itself by a recursive call.
	 * @param where the index into the array where the parent lives
	 */
	private void heapify(int where) {
		ticker.tick();
		int left_child = where * 2;
		int right_child = where * 2 + 1;
		
		// don't have left child base case
		if(left_child > size) {
			return;
		}
		
		// only have left child case
		else if(right_child > size){
			
			// parent bigger than its only left child, swap
			if (array[where].getValue().compareTo(array[left_child].getValue()) > 0) {
				ticker.tick();
				
				// swap
				Decreaser<T> temp = array[where];
				array[where] = array[left_child];
				array[left_child] = temp;
				
				// update objects' locations
				array[where].loc = where;
				array[left_child].loc = left_child;

				// recursively move down
				heapify(left_child);
				ticker.tick();

			}
		}

		// have both left and right child case
		else {
			

			// if bigger than at least one child, swap with the min child
			if ((array[where].getValue().compareTo(array[left_child].getValue()) > 0
					&& array[where].getValue().compareTo(array[right_child].getValue()) > 0)
					|| 
					(array[where].getValue().compareTo(array[left_child].getValue()) > 0
							|| array[where].getValue().compareTo(array[right_child].getValue()) > 0)) {
				ticker.tick();
				int min;

				// find the min child
				if (array[right_child].getValue().compareTo(array[left_child].getValue()) > 0) {
					min = left_child;
				} else {
					min = right_child;
				}

				// swap and move down
				Decreaser<T> temp = array[where];
				array[where] = array[min];
				array[min] = temp;
				array[where].loc = where;
				array[min].loc = min;
				heapify(min);
				ticker.tick();
			}
		}
		return;
	}
	
	/**
	 * Does the heap contain anything currently?
	 * I implemented this for you.  Really, no need to thank me!
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	//
	// End of methods described in lecture
	//
	
	//
	// The methods that follow are necessary for the debugging
	//   infrastructure.
	//
	/**
	 * This method would normally not be present, but it allows
	 *   our consistency checkers to see if your heap is in good shape.
	 * @param loc the location
	 * @return the value currently stored at the location
	 */
	public T peek(int loc) {
		if (array[loc] == null)
			return null;
		else return array[loc].getValue();
	}

	/**
	 * Return the loc information from the Decreaser stored at loc.  They
	 *   should agree.  This method is used by the heap validator.
	 * @param loc
	 * @return the Decreaser's view of where it is stored
	 */
	public int getLoc(int loc) {
		return array[loc].loc;
	}

	public int size() {
		return this.size;
	}
	
	public int capacity() {
		return this.array.length-1;
	}
	

	/**
	 * The commented out code shows you the contents of the array,
	 *   but the call to HeapToStrings.toTree(this) makes a much nicer
	 *   output.
	 */
	public String toString() {
//		String ans = "";
//		for (int i=1; i <= size; ++i) {
//			ans = ans + i + " " + array[i] + "\n";
//		}
//		return ans;
		return HeapToStrings.toTree(this);
	}

	/**
	 * This is not the unit test, but you can run this as a Java Application
	 * and it will insert and extract 100 elements into the heap, printing
	 * the heap each time it inserts.
	 * @param args
	 */
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "You are welcome to run this, but be sure also to run the TestMinHeap JUnit test");
		MinHeap<Integer> h = new MinHeap<Integer>(500, new Ticker());
		MinHeapValidator<Integer> v = new MinHeapValidator<Integer>(h);
		Random r = new Random();
		for (int i=0; i < 100; ++i) {
			v.check();
			h.insert(r.nextInt(1000));
			v.check();
			System.out.println(HeapToStrings.toTree(h));
			//System.out.println("heap is " + h);
		}
		while (!h.isEmpty()) {
			int next = h.extractMin();
			System.out.println("Got " + next);
		}
	}


}
