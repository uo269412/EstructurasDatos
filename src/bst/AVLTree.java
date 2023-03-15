package bst;

public class AVLTree<T extends Comparable<T>> {

	private AVLNode<T> root;

	public void add(T element) {
		root = add(root, element);
	}

	public AVLNode<T> getRoot() {
		return root;
	}

	private AVLNode<T> add(AVLNode<T> theRoot, T element) {
		if (theRoot == null)
			return new AVLNode<T>(element);
		if (element.compareTo(theRoot.getElement()) == 0)
			throw new RuntimeException("element already exists!");
		if (element.compareTo(theRoot.getElement()) < 0)
			theRoot.setLeft(add(theRoot.getLeft(), element));
		if (element.compareTo(theRoot.getElement()) > 0)
			theRoot.setRight(add(theRoot.getRight(), element));
		return theRoot;
	}

	public String toString() {
		return toString(getRoot());
	}

	public String toString(AVLNode<T> theRoot) {
		if (theRoot != null) {
			return (theRoot.toString() + toString(theRoot.getLeft()) + toString(theRoot.getRight()));
		} else {
			return ("-");
		}
	}

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

	public boolean search(T element) {
		return search(root, element);
	}

	public T getMax(AVLNode<T> theRoot) {
		if (theRoot == null)
			return null;
		else
			return getMaxRec(theRoot);
	}

	private T getMaxRec(AVLNode<T> theRoot) {
		if (theRoot.getRight() == null)
			return theRoot.getElement();
		else
			return getMaxRec(theRoot.getRight());
	}

	public AVLNode<T> remove(AVLNode<T> theRoot, T element) {
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
		return theRoot;
	}

	public void remove(T element) {
		remove(getRoot(), element);
	}
}
