package comp2402a4;

import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

public class GeometricTree extends BinaryTree<GeometricTreeNode> {

	public GeometricTree() {
		super (new GeometricTreeNode());
	}
	
	public void inorderDraw() {
		assignLevels();
		GeometricTreeNode w = firstNode();
		int i;
		for (i = 0; w != nil; i++) {
			w.position.x = i;
			w = nextNode(w);
		}
	}
	
	
	protected void randomX(GeometricTreeNode u, Random r) {
		if (u == null) return;
		u.position.x = r.nextInt(60);
		randomX(u.left, r);
		randomX(u.right, r);
	}
	
	
	/**
	 * Draw each node so that it's x-coordinate is as small
	 * as possible without intersecting any other node at the same level 
	 * the same as its parent's
	 */
	public void leftistDraw() {
		assignLevels();
		assignLevels();
		
		GeometricTreeNode d = r;
		d.position.x = 0;
		
		Queue<GeometricTreeNode> q = new LinkedList<GeometricTreeNode>();
		q.add(r);
		while (!q.isEmpty()) {
			GeometricTreeNode u = q.remove();
			
			if (d.position.y == u.position.y) { //if nodes on same level
				u.position.x = d.position.x + 1;	//put node next to previous node
				d = u;
			} 
			else {
				u.position.x = 0; // first on this level
				d = u;
			}
			//	Add Left Child
			if (u.left != nil)	q.add(u.left);
			// Add Right Child
			if (u.right != nil) q.add(u.right);
		}
		// Root at leftmost
		r.position.x = 0;
	}
	
	
public void balancedDraw() {
		
		int x,y;
		x = 0;
		y = 0;

		GeometricTreeNode currNode = firstNode();

		moveLeft(r);

		while (currNode != nil) {
			if (currNode.left != nil && currNode.right != nil) currNode.size = currNode.left.size + currNode.right.size + 1;
			else if (currNode.left != nil) currNode.size = currNode.left.size + 1;
			else if (currNode.right != nil) currNode.size = currNode.right.size + 1;
			else currNode.size = 1;
			currNode = next(currNode);
		}

		currNode = r;

		while(true){

			while (currNode.left != nil || currNode.right != nil) {
				// Go to smaller of the two children if two children exist
				if (currNode.right != nil && currNode.left != nil) {
					currNode = smallChild(currNode);
					y++;
				} 
				// Go to the only remaining child if only one child exists
				else {
					if (currNode.left == nil)	currNode = currNode.right;
					else currNode = currNode.left;
					x++;	
				}
				currNode.position.x = x;
				currNode.position.y = y;
			}

			// Move to parent when no children exist
			currNode = currNode.parent;

			// Keep moving up as long as we're done with the subtree we're moving up from
			while (currNode != nil && ((currNode.left == nil || currNode.right == nil) || bigChild(currNode).position.x > 0))
				currNode = currNode.parent;

			// Check if fully done
			if (currNode == nil) break;

			y = currNode.position.y;
			x++;

			currNode = bigChild(currNode);

			currNode.position.y = y;
			currNode.position.x = x;
		}
		
	}

	private void moveLeft(GeometricTreeNode a) {
		if (a == null) return;
		a.position.x = 0;
		moveLeft(a.left);
		moveLeft(a.right);
	}

	private GeometricTreeNode next(GeometricTreeNode a) {
		// Post-Order Traversal
		if (a.parent != nil && a.parent.left == a) {
			a = a.parent;
			if (a.right != nil) {
				a = a.right;
				while (a.left != nil || a.right != nil) {
					while (a.left != nil) a = a.left;
					if (a.right != nil) a = a.right;
				}
			}
		} 
		else a = a.parent;
		return a;
	}

	private GeometricTreeNode smallChild(GeometricTreeNode u) {
		if (u.left.size < u.right.size) return u.left;
		else return u.right;
	}
	
	private GeometricTreeNode bigChild(GeometricTreeNode u) {
		if (u.right.size > u.left.size) return u.right;
		else return u.left;
	}

	protected void assignLevels() {
		assignLevels(r, 0);
	}
	
	protected void assignLevels(GeometricTreeNode u, int i) {
		if (u == null) return;
		u.position.y = i;
		assignLevels(u.left, i+1);
		assignLevels(u.right, i+1);
	}
	
	public static void main(String[] args) {
		GeometricTree t = new GeometricTree();
		galtonWatsonTree(t, 100);
		System.out.println(t);
		t.inorderDraw();
		System.out.println(t);
	}
	
}
