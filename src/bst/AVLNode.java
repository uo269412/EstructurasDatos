package bst;

public class AVLNode<T> {

	private T element;
	private AVLNode<T> left;
	private AVLNode<T> right;

	public AVLNode(T element) {
		this.element = element;
	}

	public AVLNode(T element, AVLNode<T> left, AVLNode<T> right) {
		this.element = element;
		this.left = left;
		this.right = right;
	}

	public void setLeft(AVLNode<T> left) {
		this.left = left;
	}

	public AVLNode<T> getLeft() {
		return left;
	}

	public void setRight(AVLNode<T> right) {
		this.right = right;
	}

	public AVLNode<T> getRight() {
		return right;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public T getElement() {
		return element;
	}

	@Override
	public String toString() {
		return getElement().toString();
	}

	public void print() {
		System.out.println(toString());
	}

}
