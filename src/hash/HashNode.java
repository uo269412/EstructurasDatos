package hash;

public class HashNode<T> {

	public final static int EMPTY = 0;
	public final static int VALID = 1;
	public final static int DELETED = 2;

	private T element;
	private int status;

	/**
	 * Constructor for the hash node
	 */
	public HashNode() {
		this.element = null;
		this.status = EMPTY;
	}

	/**
	 * Sets the element of the hash node
	 * 
	 * @param element we want to set in the hash node
	 */
	public void setElement(T element) {
		this.element = element;
	}

	/**
	 * Returns the element of the hash node
	 * 
	 * @return element of hash node
	 */
	public T getElement() {
		return element;
	}

	/**
	 * Sets the status of the node
	 * 
	 * @param status of the node
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Returns the status of the hash node (1 if valid, 2 if deleted, 0 if empty)
	 * 
	 * @return status of the node
	 */
	public int getStatus() {
		return status;
	}

}
