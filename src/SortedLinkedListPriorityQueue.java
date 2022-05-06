import java.util.NoSuchElementException;

/**
 * SortedLinkedListPriorityQueue
 * @author: Tom Ho, Duc Vu, Nhut Do
 * 
 * 
 */

public class SortedLinkedListPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {
	
	//=========================================================== Inner Class
	
	private class Node {
		private T data;
		private Node next;
		
		private Node(T data) {
			this(data, null);
		}
		
		private Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	
	//=========================================================== Properties
	private Node head;
	private int size;
	
	//=========================================================== Constructor
	public SortedLinkedListPriorityQueue() {
		super();
		clear();
	}
	
	public SortedLinkedListPriorityQueue(T[] entries) {
		this();
		for (T entry : entries) {
			this.add(entry);
		}
	}
	
	//=========================================================== Methods

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	@Override
	public boolean isFull() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		this.head = null;
		this.size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void add(T newEntry) {
		Node curr = head;
		Node prev = null;
		
		while (curr != null && curr.data.compareTo(newEntry) <= 0) {
			prev = curr;
			curr = curr.next;
		}
		
		if (prev == null) {
			head = new Node(newEntry, curr);
		} else {
			prev.next = new Node(newEntry, curr);
		}
		size++;
		
		
	}

	@Override
	public T peek() {
		return this.isEmpty()? null : (T) head.data;
	}

	@Override
	public T remove() {
		if (this.isEmpty())
			throw new NoSuchElementException();
		
		T result = (T) head.data;
		head = head.next;
		size--;
		return result;
	}
	
	@Override
	public String toString() {
		String result = "";
		Node curr = this.head;
		while (curr != null) {
			result += ", " + curr.data.toString();
			curr = curr.next;
		}
		
		return "[" + result.substring(2) + "]";
	}

}