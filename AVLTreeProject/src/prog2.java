/************************************************************************************************************************
* @author Brandon Cobb
* Comp 282 Monday/Wednesday 2pm
* Assignment 2
* October 8, 2018
* This class defines what a node is and contains all of the methods that act upon a single node
************************************************************************************************************************/

class StringAVLNode {
	
	// Attributes
	private int balance;
	private String item;
	private StringAVLNode left, right;
	
	// Constructor
	StringAVLNode (String str){
		balance = 0;
		item = str;
		left = null; right = null;
	}
	
	
	
	// Get the balance of the current node
	public int getBalance() {
		return balance;
	}
	
	// Set the balance for the current node
	public void setBalance(int bal) {
		if (bal == 0)
			balance = bal;
		else
			balance += bal;
	}
	
	// Get the information in the current node
	public String getItem() {
		return item;
	}
	
	// Get the left child of the current node
	public StringAVLNode getLeft() {
		return left;
	}
	
	// Set the left child of the current node
	public void setLeft(StringAVLNode t) {
		left = t;
	}
	
	// Get the right child of the current node
	public StringAVLNode getRight() {
		return right;
	}

	// Set the right child of the current node
	public void setRight(StringAVLNode t) {
		right = t;
	}
}

/************************************************************************************************************************
* @author Brandon Cobb
* Comp 282 Monday/Wednesday 2pm
* Assignment 2
* October 8, 2018
* This class contains a binary tree data struct with all the methods the act upon the tree
************************************************************************************************************************/

class StringAVLTree {
	
	StringAVLNode root;
	
	StringAVLTree() {
		root = null;
	}
	
	// Rotate the top node down and to the right
	private static StringAVLNode rotateRight(StringAVLNode n1) {
		System.out.println("Rotate " + n1.getItem() + " to the right");
		StringAVLNode n2 = n1.getLeft();
		n1.setLeft(n2.getRight());								// n2's right child becomes n1's left child
		n2.setRight(n1);										// Move top node down
		n1.setBalance(0);										// Set new balances
		n2.setBalance(0);
		return n2;
	}
	
	// Rotate the top node down and to the left
	private static StringAVLNode rotateLeft(StringAVLNode n1) {
		System.out.println("Rotate " + n1.getItem() + " to the left");
		StringAVLNode n2 = n1.getRight();
		n1.setRight(n2.getLeft());								// n2's left child becomes n1's right child
		n2.setLeft(n1);											// Move top node down
		n1.setBalance(0);										// Set new balances
		n2.setBalance(0);
		return n2;
	}
	
	// Straighten the 3 nodes so it becomes a left left. Then perform "rotateRight"
	private static StringAVLNode doubleRotateRight(StringAVLNode n1) {
		System.out.println("Double rotate " + n1.getItem() + " with left child");
		n1.setRight(rotateRight(n1.getRight()));
		return rotateLeft(n1);
	}
	
	// Straighten the 3 nodes so it becomes a right right. Then perform "rotateLeft"
	private static StringAVLNode doubleRotateLeft(StringAVLNode n1) {
		System.out.println("Double rotate " + n1.getItem() + " with right child");
		n1.setLeft(rotateLeft(n1.getLeft()));
		return rotateRight(n1);
	}
	
	// TODO: Make height recursive
	// Return the height of the tree, using balance factor to traverse the tree
	// A tree tree with 1 node returns 1
	public int height() {
		StringAVLNode t = root;
		int treeHeight = 1;
		if (t == null)
			treeHeight = 0;

		while (t != null) {
			if (t.getBalance() < 0) {				// If the left branch is bigger
				t = t.getLeft();
				treeHeight++;
			} else if (t.getBalance() > 0) {		// If the right branch is bigger
				t = t.getRight();
				treeHeight++;
			} else {								// If branches are equal height
				if (t.getLeft() != null) {				// And if there are children present
					t = t.getLeft();					// Then the default will be go left
					treeHeight++;
				} else									// Otherwise both children and null and we've found the height
					t = null;
				}
			}
		return treeHeight;
	}
	
	// Calls recursive leafCt()
	public int leafCt() {
		return leafCt(root);
	}

	// Return the number of leaves in the tree
	private int leafCt(StringAVLNode t) {		
		int numOfLeaves = 0;
		if (t != null) {
			if (t.getRight() == null && t.getLeft() == null) {
				numOfLeaves++;
			} else {
				numOfLeaves += leafCt(t.getLeft());
				numOfLeaves += leafCt(t.getRight());
			}
		}
		return numOfLeaves;
	}
	
	// Calls recursive balanced()
	public int balanced() {
		return balanced(root);
	}
	
	// Return the number of perfectly balanced AVL nodes
	private int balanced(StringAVLNode t) {
		int balancedNodes = 0;
		if (t != null) {
			if (t.getBalance() == 0) {
				balancedNodes++;
			}
			balancedNodes += balanced(t.getLeft());
			balancedNodes += balanced(t.getRight());
		}
		return balancedNodes;
	}
	
	public String successor(String str) {
		return successor(str, root);
	}
	
	// Return the in-order successor OR return null if there isnt a successor or if str isnt in tree
	/***** SUCCESSOR WORKS IN EVERY CASE EXCEPT WHEN FINDING THE SUCCESSOR OF THE LARGEST NODE IN THE TREE******
	****** THE SUCCESSOR OF THE LARGEST NODE IN THE TREE RETURNS THE CONTENT OF THE NODE AND NOT NULL.*********/
	private String successor(String str, StringAVLNode t) {
		String sucsr = null;
		if (t != null) {									// If tree isnt null then continue
			if (str.compareTo(t.getItem()) < 0) {			// Find node that matches str in left tree
				sucsr = str;
				str = successor(str, t.getLeft());
				if (sucsr == str || sucsr == null) {
					str = t.getItem();
				}
			} else if( str.compareTo(t.getItem()) > 0) {	// Find node that matches str in right tree
				str = successor(str, t.getRight());
			} else {										// Node matching str is found
				if (t.getRight() != null) {					// If a successor exists in right child, then continue
					t = t.getRight();
					while (t.getLeft() != null) {			// Keep going left until the "very next" successor is found
						t = t.getLeft();
					}
					str = t.getItem();						// When left child == null, then successor is found
				}
			}
		} else {
			str = null;
		}
		return str;
	}

	// Calls recursive insert Method
	public void insert(String str) {
		System.out.println("INSERT " + str);
		root = insert(str, root);
	}
	
	// Recursive insert method
	private StringAVLNode insert(String str, StringAVLNode t) {
		int originalBal, newBal;
		
		// Insert new node if tree is empty
		if (t == null)
			t = new StringAVLNode(str);
		
		// str is already in tree, do nothing
		else if (str.compareTo(t.getItem()) == 0) {}	
		
		// str < current node t, insert into left subtree
		else if (str.compareTo(t.getItem()) < 0) {		
			if (t.getLeft() == null) {						// If left child doesnt exist then...
				t.setLeft(insert(str, t.getLeft()));		// Do the insertion
				t.setBalance(-1);							// Offset balance to the left
			} else {
				originalBal = t.getLeft().getBalance();		// Track the original bal of left child
				t.setLeft(insert(str, t.getLeft()));		// Do the insertion
				newBal = t.getLeft().getBalance();			// Track the new bal of the left child
				if (originalBal == 0 && newBal != 0) {		// If the insertion added height then...
					t.setBalance(-1);						// Offset balance to the left
				}
			}
			if (t.getBalance() == -2) {								// If t is out of balance
				if (str.compareTo(t.getLeft().getItem()) < 0 ) {	// If its a left left rotation
					t = rotateRight(t);
				} else {												// If its a left right rotation
					originalBal = t.getLeft().getRight().getBalance();	// Bottom node of the 3
					t = doubleRotateLeft(t);
					if (originalBal == -1) {				// Correct balances
						t.getLeft().setBalance(0);			// Left child bal has to be 0 after double rotation
						t.getRight().setBalance(1);			// Right child bal has to be 1
					} else if (originalBal == 0) {
						t.getLeft().setBalance(0);			// Both children bal have to be 0 after double rotation
						t.getRight().setBalance(0);
					} else {
						t.getLeft().setBalance(-1);			// Left child bal has to be 0 after double rotation
						t.getRight().setBalance(0);			// Right child bal has to be 0 after double rotation
					}
				}
			}
			
		// str > current node t, insert into right subtree
		} else {
			if (t.getRight() == null ) {					// If right child doesnt exist then...
				t.setRight(insert(str, t.getRight()));		// Do the insertion
				t.setBalance(1);							// Offset balance to the right
			} else {
				originalBal = t.getRight().getBalance();	// Track the original bal of right child
				t.setRight(insert(str, t.getRight()));		// Do the insertion
				newBal = t.getRight().getBalance();			// Track the new bal of the right child
				if (originalBal == 0 && newBal != 0) {		// If the insertion added height then...
					t.setBalance(1);						// Offset balance to the right
				}
			}
			if (t.getBalance() == 2) {								// If t's out of balance, then rotate
				if (str.compareTo(t.getRight().getItem()) > 0 ) {	// If its a right right rotation
					t = rotateLeft(t);
				} else {												// If its a right left rotation
					originalBal = t.getRight().getLeft().getBalance();	// Bottom node of the 3
					t = doubleRotateRight(t);
					if (originalBal == -1) {				// Correct balances
						t.getLeft().setBalance(0);			// Left child bal has to be 0 after double rotation
						t.getRight().setBalance(1);			// Right child bal has to be 1
					} else if (originalBal == 0) {
						t.getLeft().setBalance(0);			// Both children bal have to be 0 after double rotation
						t.getRight().setBalance(0);
					} else {
						t.getLeft().setBalance(-1);			// Left child bal has to be 0 after double rotation
						t.getRight().setBalance(0);			// Right child bal has to be 0 after double rotation
					}
				}
			}
		}
		return t;
	}
	
	// Display the tree in order (left child, root, then right child)
	private void inorder(StringAVLNode r){
		if(r != null){
			inorder(r.getLeft());
			System.out.println(r.getItem());
			inorder(r.getRight());
		}
	}

	// Display the tree in order (left child, root, then right child)
	public String toString(){
		inorder(root);
		return "";
	}
		
	// Returns a string containing my name
	public static String myName() {
		return ">>>>> Brandon Cobb <<<<<";
	}
}

// akaplan@csun.edu

 /* WORD SETTINGS
 * Word Will use the font consolas @ 7pt. Paragraph Settings: Mirror indents checked. Dont add space Checked.
 * Line spacing is single at 1pt. Default tab stop position is 0.2
 */