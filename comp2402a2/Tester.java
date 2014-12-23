package comp2402a2;

import java.util.AbstractQueue;
import java.util.List;
import java.util.Queue;

public class Tester{

    public static boolean testPart1(Queue<Integer> q) {
    	Stopwatch timer = new Stopwatch();

		//if (q.size() == 0) return false;

		int test;

		while (q.size()!=0){
			test = q.peek();
			if (q.poll() != test) return false;
		}

		int i;
		int n = 1000;
		int a = 1;
		int b = 2;

		while (a!=b){
			for (i = 0; i < n; i++) {q.add(i);}
			for (i = 0; i < n-1; i++) {q.poll();}
			
			a = q.poll();

			for (i = 0; i < n; i++) {q.add(i);}
			for (i = 0; i < n-1; i++) {q.poll();}

			b = q.poll();
		}

		double timeSmall = 2; 
		double timeBig = 5;

		while (timeBig > timeSmall){
			timer.start();
			n = 10000;
			for (i=0; i<n;i++){
				q.add(i);
				q.poll();
			}
			timer.stop();

			timeSmall = timer.elapsedSeconds()/n;

			timer.start();
			n = 100000;
			for (i=0; i<n;i++){
				q.add(i);
				q.poll();
			}
			timer.stop();

			timeBig = timer.elapsedSeconds()/n;
		}
		return true;
    }

    public static boolean testPart2(List<Integer> ad) {
        // Your code goes here
        return false;
    }

    public static boolean testPart3(List<Integer> ad) {

    	/*
    	[Exercise 2.9 in the textbook] The ArrayDeque implementation given in the archive is pretty good, but it suffers a little from its use of modular arithmetic. Complete the ArrayDeque2 class that works like an ArrayDeque, but all the data always occupies positions a[j],a[j+1],...,a[j+n-1](notice that there is no modular arithmetic being done).
If ever the data runs off either end of a then the resize() operation is called to resize a to size 2*n and place the data at locations a[n/2],...,a[n/2+n-1].

Like an ArrayDeque, the add(i,x), and remove(i) operations should run in O(1+min{i,n-i}) amortized time.
*/
        // Correctness Tests
        return false;
    }

    public static boolean testPart5(List<Integer> ras) {
        // Your code goes here
        return false;
    }

}

