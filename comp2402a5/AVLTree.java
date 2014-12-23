package comp2402a5;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An unfinished implementation of an AVL tree (for exercises)
 * @author morin
 *
 * @param <T>
 */
public class AVLTree<T> extends
		BinarySearchTree<AVLTree.Node<T>, T> implements SSet<T> {
	/**
	 * A random number source
	 */
	Random rand;

	public static class Node<T> extends BSTNode<Node<T>,T> {
		int h;  // the height of the node
	}
	
	public AVLTree() {
		sampleNode = new Node<T>();
		rand = new Random();
		c = new DefaultComparator<T>();
	}

	public int height(Node<T> u) {
		return (u == null) ? 0 : u.h;
	}
	
	public boolean add(T x) {
		Node<T> u = new Node<T>();
		u.x = x;
		if (super.add(u)) {
			for (Node<T> w = u; w != nil; w = w.parent) {
				// walk back up to the root adjusting heights
				w.h = Math.max(height(w.left), height(w.right)) + 1;
			}
			fixup(u);
			return true;
		}
		return false;
	}
	
	public void splice(Node<T> u) {
		Node<T> w = u.parent;
		super.splice(u);
		for (Node<T> z = u; z != nil; z = z.parent)
			z.h = Math.max(height(z.left), height(z.right)) + 1;			
		fixup(w);
	}
	
	public void checkHeights(Node<T> u) {
		if (u == nil) return;
		checkHeights(u.left);
		checkHeights(u.right);
		if (height(u) != 1 + Math.max(height(u.left), height(u.right))) 
			throw new RuntimeException("Check heights shows incorrect heights");
		int dif = height(u.left) - height(u.right);
		if (dif < -1 || dif > 1)
			throw new RuntimeException("Check heights found height difference of " + dif);
	}
	
	/**
	 * TODO: finish writing this method
	 * @param u
	 */
	public void fixup(Node<T> u) {
		while (u != nil) {
			int dif = height(u.left) - height(u.right);
			if (dif > 1) {
				// TODO: add code here to fix AVL condition on the path from u to the root, if necessary
				if(height(u.left.left) - height(u.left.right) < 0) 	
					rotateLeft(u.left);
				rotateRight(u);
			} else if (dif < -1) {
				// TODO: add code here to fix AVL condition on the path from u to the root, if necessary
				if(height(u.right.right) - height(u.right.left) < 0) 
					rotateRight(u.right);
				rotateLeft(u);			
			}
			u = u.parent;
		}
	}
	
	public void rotateLeft(Node<T> u) {
		super.rotateLeft(u);
		// TODO: Recompute height values at u and u.parent
		Node<T> v;
		for (v = u; v != nil; v = v.parent) {
			v.h = 1+Math.max(height(v.left), height(v.right));
		}
	}
	
	public void rotateRight(Node<T> u) {
		super.rotateRight(u);
		// TODO: Recompute height values at u and u.parent
		Node<T> v;
		for (v = u; v != nil; v = v.parent) {
			v.h = 1+Math.max(height(v.left), height(v.right));
		}
	}

	public static <T> T find(SortedSet<T> ss, T x) {
		SortedSet<T> ts = ss.tailSet(x);
		if (!ts.isEmpty()) {
			return ts.first(); 
		}
		return null;
	}
}
