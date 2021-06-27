public class Test1 {

	public static void main(String[] args) {
		StringAVLTreeXtra t = new StringAVLTreeXtra();
		String str;
		int line = 1;
		char action;
		String s = "imaoinaoioaoipaoiqaoilaoikaoikdikgikfikeo";
		
		// add lines like this when you have delete working
		// s = s + "dkeodkfodpao";
		
		do {
			action = s.charAt(0);
			if (action == 'i') {   // insert
				str = s.substring(1,3);
				s = s.substring(3, s.length());
				t.insert(str);
			} else if (action == 'd') {  // delete
				str = s.substring(1,3);
				s = s.substring(3, s.length());
				t.delete(str);
			} else if (action == 'n') {  // new tree -- wipe out the tree and start over
				s = s.substring(1, s.length());
				t = new StringAVLTreeXtra();
			} else {  // no other choice, then output the tree
				s = s.substring(1, s.length());
				t.display();
				System.out.println(" - " + line++ + ".\n");
			}
		} while (s.length() != 0);
	}
}

class StringAVLTreeXtra extends StringAVLTree {

	public StringAVLTreeXtra() {
		super();
	}
	
	public StringAVLNode getRoot() {
		return root;
	}

	public void display() {
		paren_out(getRoot());
		System.out.println();
		bal_out(getRoot());
	}

	public void paren_out(StringAVLNode t) {
		if (t == null) {
		} else {
			System.out.print("(");
			paren_out(t.getLeft());
			if (t.getItem().length() <= 1)
				System.out.print(" ");
			System.out.print(t.getItem());
			paren_out(t.getRight());
			System.out.print(")");
		}
	}

	public void bal_out(StringAVLNode t) {
		if (t == null) {
		} else {
			System.out.print("(");
			bal_out(t.getLeft());
			if (t.getBalance() == -1)
				System.out.print(t.getBalance());
			else
				System.out.print(" " + t.getBalance());
			bal_out(t.getRight());
			System.out.print(")");
		}
	}
}