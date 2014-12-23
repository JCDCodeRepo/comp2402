package comp2402a2;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class BulkArrayDeque<T> extends ArrayDeque<T> {
	public BulkArrayDeque(Class<T> clazz) {
		super(clazz);
	}
	
	/**
	 * Add all the elements of c to this array deque, starting
	 * at position i
	 * @param i
	 * @param c
	 */
	public boolean addAll(int i, Collection<? extends T> c) {
		// this implementation is too slow
		//  make it run in O(c.size()+n-i) time.
		
		Object [] d = c.toArray();
		int newNums = c.size();
		

		T[] tempArray = f.newArray(c.size() + a.length);
		System.arraycopy(a, 0, tempArray, 0, a.length);
		a = tempArray;
		
		
		int numMoved = n - i;
		if (numMoved > 0) {
			System.arraycopy(a, i, a, i + newNums, numMoved);
		}
		
		System.arraycopy(d, 0, a, i, newNums);
		n += newNums;

		return true;
	}
	
    public static void main(String[] args) {
        if (!Tester.testPart2(new BulkArrayDeque<Integer>(Integer.class))) {
            System.err.println("Test failed!");
            System.exit(-1);
        }
    }
}