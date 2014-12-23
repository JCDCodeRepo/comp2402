package comp2402a3;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class implements the IntervalSet interface for storing a set of
 * intervals, which may or may not be disjoint.
 *
 * @author morin
 *
 * @param <K>
 */
public class OverlappingIntervalSet<K extends Comparable<K>> implements IntervalSet<K> {

    SortedSet<Interval<K>> intervals;

    public OverlappingIntervalSet() {
        intervals = new TreeSet<Interval<K>>();
    }

    @Override
    public boolean add(Interval<K> i) {

        //If it doesn't overlap
        SortedSet<Interval<K>> s = intervals.tailSet(i);
        if (s.isEmpty() || s.first().compareTo(i) != 0) {
            intervals.add(i);
            return true;
        }

        K at = i.getA();
        K bt = i.getB();

        //If it does overlap
        SortedSet<Interval<K>> hs = intervals.headSet(new Interval<K>(i.getA(), i.getA())); 
        SortedSet<Interval<K>> bts = intervals.tailSet(new Interval<K>(i.getB(), i.getB())); 
        SortedSet<Interval<K>> ats = intervals.tailSet(new Interval<K>(i.getA(), i.getA())); 
        
        if (!hs.isEmpty() && hs.last().contains(i.getA())) 
            at = hs.last().getB();
        else if (!ats.isEmpty() && ats.first().getA().compareTo(i.getA()) < 0) 
            at = ats.first().getA();
        
        if (!bts.isEmpty() && bts.first().contains(i.getB())) 
            bt  = bts.first().getA();
        else
            bt = i.getB();

        Interval<K> temp = new Interval<K>(at,bt);
        
        SortedSet<Interval<K>> newSet = new TreeSet<Interval<K>>();
        newSet.addAll(hs);
        newSet.addAll(bts);
        newSet.add(temp);
        
        intervals = newSet;
        
        return true;
        
    }

    @Override
    public void clear() {
        intervals.clear();
    }

    @Override
    public boolean contains(K x) {
        SortedSet<Interval<K>> ts =
                intervals.tailSet(new Interval<K>(x,x)); // Find stuff >= [i,i)
        if (!ts.isEmpty()) {
            
            SortedSet<Interval<K>> ts2 = ts.tailSet(new Interval<K>(ts.first().getB(), ts.first().getB()));
            
            Interval<K> u = ts.first();
            if (u.contains(x)) {
                return true;
            }
            
            if (!ts2.isEmpty() && ts2.first().getA().equals(x)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return intervals.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Tester.testPart2(new OverlappingIntervalSet<Integer>());
    }

}
