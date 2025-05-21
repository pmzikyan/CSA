/**
 *	Binary Tree of Comparable values.
 *	The tree only has unique values. It does not add duplicate values.
 *	
 *	@author	Petros Mzikyan
 *	@since	5/19/2025
 */
public class BinaryTree<E extends Comparable<E>> {

	private TreeNode<E> root;		// the root of the tree
	
	private final int PRINT_SPACES = 3;	// print spaces between tree levels
										// used by printTree()
	
	/**	constructor for BinaryTree */
	public BinaryTree() { root = null; }
	
	/**	Field accessors and modifiers */
	
	/**	Add a node to the tree
	 *	@param value		the value to put into the tree
	 */
	public void add(E value) 
	{
		TreeNode<E> curNode = root;
		TreeNode<E> nextNode = null;
		
		if (curNode == null)
		{
			root = new TreeNode<E>(value);
			return;
		}
		
		do {
			if (value.compareTo(curNode.getValue()) < 0)
				nextNode = curNode.getLeft();
			else
				curNode = curNode.getRight();
		}
		while (nextNode != null);
		
		if (value.compareTo(curNode.getValue()) < 0)
			curNode.setLeft(new TreeNode<E>(value));
		else
			curNode.setRight(new TreeNode<E>(value));
	}
	
	/**
	 *	Print Binary Tree Inorder
	 */
	public void printInorder() { printInorder(root); }
	
	private void printInorder(TreeNode<E> node)
	{
		if (node == null)
			return;
		printInorder(node.getLeft());
		System.out.print(node.getValue() + " ");
		printInorder(node.getRight());
	}
	
	/**
	 *	Print Binary Tree Preorder
	 */
	public void printPreorder() { printPreorder(root); }
	
	private void printPreorder(TreeNode<E> node)
	{
		if (node == null)
			return;
		System.out.print(node.getValue() + " ");
		printInorder(node.getLeft());
		printInorder(node.getRight());
	}
	
	/**
	 *	Print Binary Tree Postorder
	 */
	public void printPostorder() { printPostorder(root); }
	
	private void printPostorder(TreeNode<E> node)
	{
		if (node == null)
			return;
		printInorder(node.getLeft());
		printInorder(node.getRight());
		System.out.print(node.getValue() + " ");
	}
		
	/**	Return a balanced version of this binary tree
	 *	@return		the balanced tree
	 */
	public BinaryTree<E> makeBalancedTree() {
		BinaryTree<E> balancedTree = new BinaryTree<E>();

		return balancedTree;
	}
	
	/**
	 *	Remove value from Binary Tree
	 *	@param value		the value to remove from the tree
	 *	Precondition: value exists in the tree
	 */
	public void remove(E value) {
		root = remove(root, value);
	}
	/**
	 *	Remove value from Binary Tree
	 *	@param node			the root of the subtree
	 *	@param value		the value to remove from the subtree
	 *	@return				TreeNode that connects to parent
	 */
	public TreeNode<E> remove(TreeNode<E> node, E value) {
		return null;
	}
	

	/*******************************************************************************/	
	/********************************* Utilities ***********************************/	
	/*******************************************************************************/	
	/**
	 *	Print binary tree
	 *	@param root		root node of binary tree
	 *
	 *	Prints in vertical order, top of output is right-side of tree,
	 *			bottom is left side of tree,
	 *			left side of output is root, right side is deepest leaf
	 *	Example Integer tree:
	 *			  11
	 *			/	 \
	 *		  /		   \
	 *		5			20
	 *				  /	  \
	 *				14	   32
	 *
	 *	would be output as:
	 *
	 *				 32
	 *			20
	 *				 14
	 *		11
	 *			5
	 ***********************************************************************/
	public void printTree() {
		printLevel(root, 0);
	}
	
	/**
	 *	Recursive node printing method
	 *	Prints reverse order: right subtree, node, left subtree
	 *	Prints the node spaced to the right by level number
	 *	@param node		root of subtree
	 *	@param level	level down from root (root level = 0)
	 */
	private void printLevel(TreeNode<E> node, int level) {
		if (node == null) return;
		// print right subtree
		printLevel(node.getRight(), level + 1);
		// print node: print spaces for level, then print value in node
		for (int a = 0; a < PRINT_SPACES * level; a++) System.out.print(" ");
		System.out.println(node.getValue());
		// print left subtree
		printLevel(node.getLeft(), level + 1);
	}
	
	
}
