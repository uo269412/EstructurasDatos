package avl;

public class AVLTree<T extends Comparable<T>> {

	private AVLNode<T> root;

	/**
	 * Returns the root of the tree
	 * 
	 * @return root of the tree
	 */
	public AVLNode<T> getRoot() {
		return root;
	}

	/**
	 * Adds a node to the AVL tree
	 * 
	 * @param element to be added
	 */
	public void add(T element) {
		root = add(root, element);
	}

	/**
	 * Recursive and private method for the add
	 * 
	 * @param theRoot root of the tree
	 * @param element we want to add
	 * @return nodes from the tree (recursion)
	 */
	private AVLNode<T> add(AVLNode<T> theRoot, T element) {
		if (theRoot == null)
			return new AVLNode<T>(element);
		if (element.compareTo(theRoot.getElement()) == 0)
			throw new RuntimeException("element already exists!");
		if (element.compareTo(theRoot.getElement()) < 0)
			theRoot.setLeft(add(theRoot.getLeft(), element));
		if (element.compareTo(theRoot.getElement()) > 0)
			theRoot.setRight(add(theRoot.getRight(), element));
//		theRoot.updateHeight();
//		return theRoot;
		return (updateBF(theRoot));
	}

	/**
	 * Returns a string containing information about the tree
	 */
	public String toString() {
		return toString(getRoot());
	}

	/**
	 * Recursive and private method for the toString
	 * 
	 * @param theRoot root of the tree
	 * @return String that will be updated to include the information
	 */
	private String toString(AVLNode<T> theRoot) {
		if (theRoot != null) {
			return (theRoot.toString() + toString(theRoot.getLeft()) + toString(theRoot.getRight()));
		} else {
			return ("-");
		}
	}

	/**
	 * Searches for a node in the tree
	 * 
	 * @param element we want to search
	 * @return boolean true if it is, false if it is not
	 */
	public boolean search(T element) {
		return search(root, element);
	}

	/**
	 * Recursive and private method for the search
	 * 
	 * @param theRoot of the tree
	 * @param element we want to search
	 * @return boolean
	 */
	private boolean search(AVLNode<T> theRoot, T element) {
		if (theRoot == null)
			return false;
		else if (element.compareTo(theRoot.getElement()) == 0)
			return true;
		else if (element.compareTo(theRoot.getElement()) < 0)
			return search(theRoot.getLeft(), element);
		else if (element.compareTo(theRoot.getElement()) > 0)
			return search(theRoot.getRight(), element);
		return true; // code never reaches this, so either true or false are valid
	}

	/**
	 * Returns the most to the right node from the tree
	 * 
	 * @param theRoot root of the tree
	 * @return element of such node
	 */
	public T getMax(AVLNode<T> theRoot) {
		if (theRoot == null)
			return null;
		else
			return getMaxRec(theRoot);
	}

	/**
	 * Recursive and private method for the max
	 * 
	 * @param theRoot root of the tree
	 * @return element of such node
	 */
	private T getMaxRec(AVLNode<T> theRoot) {
		if (theRoot.getRight() == null)
			return theRoot.getElement();
		else
			return getMaxRec(theRoot.getRight());
	}

	/**
	 * Removes a node from the tree
	 * 
	 * @param element we want to remove
	 */
	public void remove(T element) {
		root = remove(getRoot(), element);
	}

	/**
	 * Recursive and private method for the remove
	 * 
	 * @param theRoot root of the tree
	 * @param element we want to remove
	 * @return node (from recursion)
	 */
	private AVLNode<T> remove(AVLNode<T> theRoot, T element) {
		if (theRoot == null)
			throw new RuntimeException("element does not exist!");
		if (element.compareTo(theRoot.getElement()) < 0)
			theRoot.setLeft(remove(theRoot.getLeft(), element));
		else if (element.compareTo(theRoot.getElement()) > 0)
			theRoot.setRight(remove(theRoot.getRight(), element));
		else {
			if (theRoot.getLeft() == null && theRoot.getRight() == null) {
				return null;
			} else if (theRoot.getLeft() == null) {
				return theRoot.getRight();
			} else if (theRoot.getRight() == null) {
				return theRoot.getLeft();
			} else {
				theRoot.setElement(getMax(theRoot.getLeft()));
				theRoot.setLeft(remove(theRoot.getLeft(), theRoot.getElement()));
			}
		}
//		theRoot.updateHeight();
//		return theRoot;
		return (updateBF(theRoot));
	}

	/**
	 * Joins two trees taking into consideration the preorder approach
	 * 
	 * @param tree we want to merge
	 * @return merged tree
	 */
	public AVLTree<T> joins(AVLTree<T> tree) {
		AVLTree<T> result = new AVLTree<T>();
		result.preOrderAdd(getRoot());
		result.preOrderAdd(tree.getRoot());
		return result;
	}

	/**
	 * Recursion method to keep adding the nodes in the merged tree
	 * 
	 * @param node (from recursion)
	 */
	private void preOrderAdd(AVLNode<T> node) {
		if (node != null) {
			if (!search(node.getElement())) {
				this.add(node.getElement());
			}
			preOrderAdd(node.getLeft());
			preOrderAdd(node.getRight());
		}
	}

	/**
	 * Creates a tree from the intersection of two trees
	 * 
	 * @param tree that will be taken into consideration in the intersection
	 * @return intersected tree
	 */
	public AVLTree<T> intersection(AVLTree<T> tree) {
		AVLTree<T> result = new AVLTree<T>();
		compareAndAdd(result, getRoot(), tree);
		return result;
	}

	/**
	 * Recursive method for the intersection
	 * 
	 * @param result         tree obtained from the intersection
	 * @param node           (from recursion)
	 * @param toBeMergedTree
	 */
	private void compareAndAdd(AVLTree<T> result, AVLNode<T> node, AVLTree<T> toBeMergedTree) {
		if (node != null) {
			if (toBeMergedTree.search(node.getElement())) {
				result.add(node.getElement());
			}
			compareAndAdd(result, node.getLeft(), toBeMergedTree);
			compareAndAdd(result, node.getRight(), toBeMergedTree);
		}
	}

	/**
	 * Updates the balance factor of the node
	 * 
	 * @param theRoot of the tree
	 * @return updated node with the updated balance factor
	 */
	private AVLNode<T> updateBF(AVLNode<T> theRoot) {
		if (theRoot.getBF() == -2) // Left Rotation
		{
			if (theRoot.getLeft().getBF() <= 0) {
				theRoot = singleLeftRotation(theRoot);
			} else {
				theRoot = doubleLeftRotation(theRoot);
			}
		} else {
			if (theRoot.getBF() == 2) // Right Rotation
			{
				if (theRoot.getRight().getBF() >= 0) {
					theRoot = (singleRightRotation(theRoot));
				} else {
					theRoot = (doubleRightRotation(theRoot));
				}
			}
		}
		theRoot.updateHeight();
		return theRoot;
	}

	/**
	 * Performs the single right rotation of the tree
	 * 
	 * @param theRoot of the tree
	 * @return updated tree
	 */
	private AVLNode<T> singleRightRotation(AVLNode<T> theRoot) {
		AVLNode<T> newRoot = theRoot.getRight();
		theRoot.setRight(newRoot.getLeft());
		newRoot.setLeft(theRoot);
		return newRoot;
	}

	/**
	 * Performs the single left rotation of the tree
	 * 
	 * @param theRoot of the tree
	 * @return updated tree
	 */
	private AVLNode<T> singleLeftRotation(AVLNode<T> theRoot) {
		AVLNode<T> newRoot = theRoot.getLeft();
		theRoot.setLeft(newRoot.getRight());
		newRoot.setRight(theRoot);
		return newRoot;
	}

	/**
	 * Performs the double right rotation of the tree
	 * 
	 * @param theRoot of the tree
	 * @return updated tree
	 */
	private AVLNode<T> doubleRightRotation(AVLNode<T> theRoot) {
		AVLNode<T> newRoot = theRoot.getRight();
		theRoot.setRight(singleLeftRotation(newRoot));
		return singleRightRotation(theRoot);
	}

	/**
	 * Performs the double left rotation of the tree
	 * 
	 * @param theRoot of the tree
	 * @return updated tree
	 */
	private AVLNode<T> doubleLeftRotation(AVLNode<T> theRoot) {
		AVLNode<T> newRoot = theRoot.getLeft();
		theRoot.setLeft(singleRightRotation(newRoot));
		return singleLeftRotation(theRoot);
	}

	/**
	 * Returns the height of the tree without using the getHeight method of the node
	 * 
	 * @return height of the tree
	 */
	public int getHeight() {
		if (getRoot() == null) {
			return 0;
		}
		int finalHeight = 1 + processHeight(getRoot());
		return finalHeight;
	}

	/**
	 * Process the height of the root by traversing the tree
	 * 
	 * @param root2 used for recursion
	 * @return size of the root tree minus one
	 */
	private int processHeight(AVLNode<T> root2) {
		if (root2.getRight() != null && root2.getLeft() != null) {
			int leftBranchHeight = 1 + processHeight(root2.getLeft());
			int rightBranchHeight = 1 + processHeight(root2.getRight());
			return (Math.max(leftBranchHeight, rightBranchHeight));
		}
		if (root2.getRight() != null) {
			return 1 + processHeight(root2.getRight());
		} else if (root2.getLeft() != null) {
			return 1 + processHeight(root2.getLeft());
		}
		return 0; // null children
	}

	public T getFather(AVLNode<T> node) {
		return searchParent(getRoot(), node.getElement());
	}

	private T searchParent(AVLNode<T> theRoot, T element) {
		if (theRoot.getLeft() != null) {
			if (theRoot.getLeft().getElement().compareTo(element) == 0) {
				return theRoot.getElement();
			}
			if (theRoot.getElement().compareTo(element) > 0) {
				return searchParent(theRoot.getLeft(), element);
			}
		}
		if (theRoot.getLeft() != null) {
			if (theRoot.getLeft().getElement().compareTo(element) == 0) {
				return theRoot.getElement();
			}
			if (theRoot.getElement().compareTo(element) < 0) {
				return searchParent(theRoot.getRight(), element);
			}
		}
		return null;
	}

	public int getAvgLength() {
		System.out.println("H: " + getAvgLengthNodes(getRoot()));
		System.out.println("N: " + getNumberNodes(getRoot()));
		return getAvgLengthNodes(getRoot()) / getNumberNodes(getRoot());
	}

	private int getAvgLengthNodes(AVLNode<T> avlNode) {
		int leftheight = 0;
		int rightheight = 0;
		if (avlNode.getLeft() != null) {
			leftheight = getAvgLengthNodes(avlNode.getLeft());
		}
		if (avlNode.getRight() != null) {
			rightheight = getAvgLengthNodes(avlNode.getRight());
		}
		return avlNode.getHeight() + leftheight + rightheight;
	}

	private int getNumberNodes(AVLNode<T> avlNode) {
		int height = 1;
		if (avlNode.getRight() != null) {
			height += getNumberNodes(avlNode.getRight());
//			System.out.println("left" + avlNode.toString());
		}
		if (avlNode.getLeft() != null) {
			height += getNumberNodes(avlNode.getLeft());
//			System.out.println("right" + avlNode.toString());
		}
		
		return height;
	}

	public int countChildNodes(AVLNode<T> node) {
		int counter = 0;
		if (node.getRight() != null) {
			counter += countChildNodes(node.getRight()) + 1;
		}
		if (node.getLeft() != null) {
			counter += countChildNodes(node.getLeft()) + 1;
		}
		return counter;
	}
}
