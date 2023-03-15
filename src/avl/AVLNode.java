package avl;

public class AVLNode<T> {

	private T element;
	private AVLNode<T> left;
	private AVLNode<T> right;
	private int height;

	public AVLNode(T element) {
		this.element = element;
		setHeight(0);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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
		return getElement().toString() + "(" + getBF() + ")";
	}

	public void print() {
		System.out.println(toString());
	}

	/**
	 * Updates the height of the node considering its children
	 * 
	 * @return updated node with the new height
	 */
	public void updateHeight() {
		if (getRight() == null && getLeft() != null) {
			getLeft().updateHeight();
			setHeight(getLeft().getHeight() + 1);
		} else if (getLeft() == null && getRight() != null) {
			getRight().updateHeight();
			setHeight(getRight().getHeight() + 1);
		} else if (getLeft() != null && getRight() != null) {
			getLeft().updateHeight();
			getRight().updateHeight();
			setHeight((Math.max(getLeft().getHeight(), getRight().getHeight()) + 1));
		} else { // getLeft() == null && getRight() == null
			setHeight(0);
		}
	}

	/**
	 * Returns the balance factor of such node
	 * @return balance factor
	 */
	public int getBF() {
		if (getRight() == null) {
			if (getLeft() != null) {
				return -(getLeft().getHeight() + 1);
			} else {
				return 0;
			}
		} else {
			if (getLeft() == null) {
				return (getRight().getHeight() + 1);
			} else {
				return getRight().getHeight() - getLeft().getHeight();
			}
		}
	}
}
