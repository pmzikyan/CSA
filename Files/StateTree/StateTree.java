import java.util.Scanner;

/**
 *	The driver program for creating and manipulating
 *	a binary tree of state information.
 *
 *	@author	Petros Mzikyan
 *	@since	5/21/2025
 */
public class StateTree
{
	// Fields
	private BinaryTree<State> bTree;
	private final String IN_FIlE = "states2.txt";	// input file
	
	public StateTree() { }
	
	public static void main ( String [] args )
	{
		StateTree treeOrder = new StateTree();
		treeOrder.mainMenu();
	}
 
	public void mainMenu ()
	{
		String choice;

		do
		{
			System.out.println("Binary Tree algorithm menu\n");
			System.out.println("(1) Read Data from a file");
			System.out.println("(2) Print the list");
			System.out.println("(3) Search the list");
			System.out.println("(4) Delete node");
			System.out.println("(5) Count nodes");
			System.out.println("(6) Clear the list");
			System.out.println("(7) Print the level");
			System.out.println("(8) Print depth of tree");
			System.out.println("(Q) Quit\n");
			choice = Prompt.getString("Choice");
			System.out.println();

			if ('1' <= choice.charAt(0) && choice.charAt(0) <= '8')
			{
				switch (choice.charAt(0))
				{
					case '1' :	
						loadData();		
						break;
					case '2' :
						System.out.println();
						System.out.println("The tree printed inorder\n");
						bTree.printInorder();
						System.out.println();
						break;
					case '3' :
						find();
						break;
					case '4' :
						delete();
						break;
					case '5' :
						System.out.println("Number of nodes = " + size(bTree.getRoot()));
						System.out.println();
						break;
					case '6' :
						clear();
						break;
					case '7' :
						printLevel();
						break;
					case '8' :
						if (depth(bTree.getRoot(), -1) > -1)
							System.out.println("Depth of tree = " + depth(bTree.getRoot(), -1));
						else
							System.out.println("Tree empty");
						System.out.println();
						break;
				}
			}
		}
		while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
	}
	
	/**	Load the data into the binary tree */
	public void loadData() { }
	
	/**	Find the node in the tree */
	public void find() { }
	
	/** Delete a node */
	public void delete() { }
	
	/**	Returns the number of nodes in the subtree - recursive
	 *	@param node		the root of the subtree
	 *	@return			the number of nodes in the subtree
	 */
	public int size(TreeNode<State> node) { return 0; }
	
	/**	Clear out the binary tree */
	public void clear() { }
	
	/**	Print the level requested */
	public void printLevel() { }
	
	/**	Returns the depth of the subtree - recursive
	 *	@param node		the root of the subtree
	 *	@return			the depth of the subtree
	 */
	public int depth(TreeNode<State> node, int depth) { return 0; }
	
}
