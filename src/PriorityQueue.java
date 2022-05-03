/**
 * @author Tom Ho
 * 
 * This class is implementation of Priority Queue using Heap
 */
import java.util.Arrays;
import java.util.NoSuchElementException;
public class PriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface <T>{

	private T[] elements;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	private final double CAPACITY_THRESHOLD = 2;
	
	public PriorityQueue() {
		this(DEFAULT_CAPACITY);
	}

	public PriorityQueue(int initialCapacity) {
		elements = (T[]) new Comparable[initialCapacity + 1];
		size = 0;
	}

	public PriorityQueue(T[] entries) {
		this();
		
		for (int i = 0; i < entries.length; i ++) {
			add(entries[i]);
		}
	}

	  
	@Override
	public boolean isEmpty() {
		return elements[1] == null;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public void clear() {
		elements = (T[]) new Comparable[DEFAULT_CAPACITY + 1];
    	size = 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void add(T newEntry) {
		// TODO Auto-generated method stub
		verifyCapacity();
		elements[++size] = newEntry;
		reheapUp(size);
	}
	
	private void reheapUp(int index) {

		int parentIndex = index / 2;
		while (parentIndex > 0 && elements[index].compareTo(elements[parentIndex]) > 0) {
			swap(index, parentIndex);
			index = parentIndex;
			parentIndex = index / 2;
		}
	}

	private void swap(int index, int parentIndex) {
		T tmp = elements[index];
		elements[index] = elements[parentIndex];
		elements[parentIndex] = tmp;
	}
	
	private void verifyCapacity() {
		
		if (size * CAPACITY_THRESHOLD >= elements.length) {
			
			int newSize = elements.length * 2 + 1;
		    T[] newElements = (T[]) new Comparable[newSize];
	
		    for (int i = 1; i < size + 1; i++) {
		    	newElements[i] = elements[i];
		    }
	
		    this.elements = newElements;
	    }
	}

	@Override
	public T peek() {
		if (isEmpty())
		    return null;
		return elements[1];
	}

	@Override
	public T remove() {
		if (isEmpty())
		    throw new NoSuchElementException();

	    T ret = elements[1];
	    elements[1] = elements[size];
	    elements[size] = null;
	    reheapDown(1);
	    size--;
	    return ret;
	}
	
	private void reheapDown(int index) {
		int leftChild = index * 2;
	    int rightChild = index * 2 + 1;
	    int largerChild = elements[leftChild].compareTo(elements[rightChild]) > 0  ? leftChild : rightChild;
	    
	    if (elements[leftChild] == null) 
	    	return;
	    
	    while (elements[largerChild] != null && elements[index].compareTo(elements[largerChild]) < 0) {
		    swap(index, largerChild);
	        index = largerChild;
	        leftChild = index * 2;
		    rightChild = index * 2 + 1;
		    largerChild = elements[leftChild].compareTo(elements[rightChild]) > 0  ? leftChild : rightChild;
	    }
	}
	
	@Override
	public String toString() {
		if (isEmpty())
			return "[ ]";
		return Arrays.toString(elements);
	}

}
