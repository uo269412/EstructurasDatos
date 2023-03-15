package hash;

import java.util.ArrayList;

public class HashTable<T> {

	public final static int LINEAR_PROBING = 0;
	public final static int QUADRATIC_PROBING = 1;
	public static final int DOUBLE_HASHING = 2;

	private final static int MAX_TRIES = 20000;

	private int B;
	private int redispersionType;
	private double minLF;
	private int R;
	private double n;

	private ArrayList<HashNode<T>> associativeArray;

	/**
	 * Constructor of the hash table
	 * 
	 * @param b                size of the hash table
	 * @param redispersionType which can be linear or quadratic
	 * @param minLF
	 */
	public HashTable(int b, int redispersionType, double minLF) {
		this.B = isPrime(b) ? b : getNextPrimeNumber(b);
		this.redispersionType = redispersionType;
		this.minLF = minLF;
		this.R = getPrevPrimeNumber(B);
		initializeList();
	}

	/**
	 * Initialises the list with b empty hash nodes
	 */
	private void initializeList() {
		this.associativeArray = new ArrayList<HashNode<T>>();
		for (int i = 0; i < getB(); i++) {
			this.associativeArray.add(new HashNode<T>());
		}

	}

	/**
	 * Returns the size of the hash table
	 * 
	 * @return int of the size of the hash table
	 */
	private int getB() {
		return this.B;
	}

	/**
	 * Previous prime number of B
	 * 
	 * @return previous number of B
	 */
	private int getR() {
		return this.R;
	}

	/**
	 * Hashing function
	 * 
	 * @param element to be stored.
	 * @param i       Attempt number.
	 * @return slot in the array where the element should be placed
	 */
	public int f(T element, int i) {
		int slot = -1; // never returns this value since it always enters in the switch case
		switch (redispersionType) {
		case LINEAR_PROBING:
			slot = (Math.abs(element.hashCode()) + i) % B;
			break;
		case QUADRATIC_PROBING:
			slot = (Math.abs(element.hashCode()) + i * i) % B;
			break;
		case DOUBLE_HASHING:
			int function = getR() - Math.abs(element.hashCode() % getR());
			slot = (Math.abs((element.hashCode() + i * function)) % B);
		}
		return slot;
	}

	/**
	 * Prints information about the hash table
	 */
	public void print() {
		System.out.println(toString());
	}

	/**
	 * String representation of the nodes in the hash table
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < getB(); i++) {
			builder.append("[" + i + "]");
			builder.append(" (" + associativeArray.get(i).getStatus() + ") ");
			builder.append("= " + associativeArray.get(i).getElement());
			builder.append(" - ");
		}
		return builder.toString();
	}

	/**
	 * Returns the low factor of the table
	 * 
	 * @return
	 */
	public double getLF() {
		return (n / getB());
	}

//	/**
//	 * Private method that returns the number of valid nodes in the table
//	 * 
//	 * @return number of valid nodes in the table (used in the add method)
//	 */
//	private int numberValidNodes() {
//		int validNodes = 0;
//		for (HashNode<T> node : associativeArray) {
//			if (node.getStatus() == HashNode.VALID) {
//				validNodes++;
//			}
//		}
//		return validNodes;
//	}

	/**
	 * Adds an element to the hash table (in reality is updating the value of a cell
	 * of the hash table)
	 * 
	 * @param element we want to add
	 */
	public void add(T element) {
		if (element != null) {
			if (n < getB()) {
				for (int i = 0; i < MAX_TRIES; i++) {
					HashNode<T> nodeToBeAdded = associativeArray.get(f(element, i));
					if (nodeToBeAdded.getStatus() == HashNode.EMPTY || nodeToBeAdded.getStatus() == HashNode.DELETED) {
						nodeToBeAdded.setElement(element);
						nodeToBeAdded.setStatus(HashNode.VALID);
						n++;
						break; // otherwise we get an error
					}
				}
			}
			if (getLF() > minLF)
				dynamicResize();
		}
	}

//	/**
//	 * Searches for an element in the hash table
//	 * 
//	 * @param element we want to find
//	 * @return true if found, false otherwise
//	 */
//	public boolean search(T element) {
//		for (HashNode<T> node : associativeArray) {
//			if (node.getStatus() == HashNode.VALID) {
//				if (node.getElement().equals(element)) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}

	/**
	 * Searches for an element in the hash table
	 * 
	 * @param element we want to find
	 * @return true if found, false otherwise
	 */
	public boolean search(T element) {
		for (int i = 0; i < MAX_TRIES; i++) {
			HashNode<T> nodeToBeSearch = associativeArray.get(f(element, i));
			if (nodeToBeSearch.getStatus() == HashNode.VALID) {
				if (nodeToBeSearch.getElement().equals(element)) {
					return true;
				}
			} else if (nodeToBeSearch.getStatus() == HashNode.EMPTY) {
				break;
			}
		}
		return false;
	}

//	/**
//	 * Removes an element from the hash table. If not found, nothing changes
//	 * 
//	 * @param element we want to remove from the table
//	 */
//	public void remove(T element) {
//		for (HashNode<T> node : associativeArray) {
//			if (node.getStatus() == HashNode.VALID) {
//				if (node.getElement().equals(element)) {
//					node.setStatus(HashNode.DELETED);
//				}
//			}
//		}
//
//	}

	/**
	 * Removes an element from the hash table. If not found, nothing changes
	 * 
	 * @param element we want to remove from the table
	 */
	public void remove(T element) {
		for (int i = 0; i < MAX_TRIES; i++) {
			HashNode<T> nodeToBeSearch = associativeArray.get(f(element, i));
			if (nodeToBeSearch.getStatus() == HashNode.VALID) {
				if (nodeToBeSearch.getElement().equals(element)) {
					nodeToBeSearch.setStatus(HashNode.DELETED);
					n--;
				}
			} else if (nodeToBeSearch.getStatus() == HashNode.EMPTY) {
				break;
			}
		}
	}

	/**
	 * Verifies whether a given number is a prime number
	 * 
	 * @param number we want to verify
	 * @return true if prime, false otherwise
	 */
	public boolean isPrime(int number) {
		if (number <= 1) {
			return false;
		}
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the prime number predecessor of a given int number
	 * 
	 * @param number we want to find the prime predecessor
	 * @return such number
	 */
	public int getPrevPrimeNumber(int number) {
		for (int i = number - 1; 1 < i; i--) {
			if (isPrime(i)) {
				return i;
			}
		}
		return 2;
	}

	/**
	 * Returns the prime number successor of a given int number
	 * 
	 * @param number we want to find the prime successor
	 * @return such number
	 */
	public int getNextPrimeNumber(int number) {
		int counter = number + 1;
		while (true) {
			if (isPrime(counter)) {
				return counter;
			}
			counter++;
		}
	}

	/**
	 * Resizes the hash table taking into consideration the dynamic resizing
	 * approach (changing references)
	 * 
	 * @param newSize of the hash table
	 */
	protected void dynamicResize(int newSize) {
		this.B = newSize;
		this.R = getPrevPrimeNumber(B);
		this.n = 0;
		ArrayList<HashNode<T>> previousAssociativeArray = associativeArray;
		this.associativeArray = new ArrayList<HashNode<T>>();
		initializeList();
		for (HashNode<T> node : previousAssociativeArray) {
			if (node.getStatus() == HashNode.VALID) {
				add(node.getElement());
			}
		}
	}

	/**
	 * Invokes the real dynamic resizing
	 */
	protected void dynamicResize() {
		dynamicResize(getNextPrimeNumber(getB() * 2));
	}
}
