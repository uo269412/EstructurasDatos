package binary_heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BinaryHeap<T extends Comparable<T>> {

	private ArrayList<T> heap;

	/**
	 * Constructor for the binary heap
	 */
	public BinaryHeap() {
		heap = new ArrayList<T>();
	}

	/**
	 * Returns the heap (the array list)
	 * 
	 * @return list of elements
	 */
	public ArrayList<T> getHeap() {
		return heap;
	}

	/**
	 * Returns true if the list is empty
	 * 
	 * @return boolean indicating whether the list is empty or not
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	/**
	 * Prints information about the list
	 */
	public void print() {
		System.out.println(toString());
	}

	/**
	 * Returns the string reading it line by line
	 */
	public String toString() {
		return heap.toString();
	}

	/**
	 * Filters up the list (used in the add method)
	 * 
	 * @param position of the element in the list that will go up
	 */
	private void filterUp(int pos) {
		while (pos > 0) {
			int father = (pos - 1) / 2;
			if (getHeap().get(pos).compareTo(getHeap().get(father)) < 0) {
				Collections.swap(heap, pos, father);
				filterUp(father);
			} else {
				break;
			}
		}
	}

	/**
	 * Adds an element to the list
	 * 
	 * @param element we want to add
	 */
	public void add(T element) {
		if (element == null) {
			throw new RuntimeException("This implementation does not support adding null elements");
		}
		getHeap().add(element);
		filterUp(getHeap().size() - 1);
	}

	/**
	 * Filters down the list (used in the getMin method)
	 * 
	 * @param position of the element in the list that will go down
	 */
	private void filterDown(int pos) {
		int leftChild = 2 * pos + 1;
		int rightChild = 2 * pos + 2;
		int replacedChild = -1;
		while (leftChild < getHeap().size() || rightChild < getHeap().size()) {
			if (leftChild < getHeap().size() && rightChild < getHeap().size()) {
				if (getHeap().get(leftChild).compareTo(getHeap().get(rightChild)) < 0) {
					replacedChild = leftChild;
				} else {
					replacedChild = rightChild;
				}
			} else if (leftChild < getHeap().size()) {
				replacedChild = leftChild;
			} else if (rightChild < getHeap().size()) {
				replacedChild = rightChild;
			}
			if (getHeap().get(pos).compareTo(getHeap().get(replacedChild)) > 0) {
				Collections.swap(heap, pos, replacedChild);
				filterDown(replacedChild);
			} else {
				break;
			}
		}
	}

	/**
	 * Returns the previous root of the list, and replaces the root with the largest
	 * element in the list, to later filter it down
	 * 
	 * @return root of the tree prior to removing it
	 */
	public T getMin() {
		if (isEmpty()) {
			throw new RuntimeException("There are no elements in the list!");
		}
		T heapRoot = getHeap().get(0);
		getHeap().set(0, getHeap().get(getHeap().size() - 1));
		getHeap().remove(getHeap().size() - 1);
		filterDown(0);
		return heapRoot;
	}

	/**
	 * Constructor of a binary heap using an array of elements
	 * 
	 * @param elements we want to add
	 */
	public BinaryHeap(T[] elements) {
		heap = new ArrayList<T>();
		getHeap().addAll(Arrays.asList(elements));
		for (int i = getHeap().size() / 2 - 1; i >= 0; i--) {
			filterDown(i);
		}

	}
}
