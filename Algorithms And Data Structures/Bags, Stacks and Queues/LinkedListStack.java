//Stack implementation using a Linked list basis
import java.util.Iterator;

public class LinkedListStack<DataType> implements Iterable<DataType>{

	private Node first = null;
	private int size;

	public LinkedListStack() {

	}

	//Inner class
	private class Node {
		DataType data;
		Node next;
	}

	public void push(DataType item) {
		Node oldfirst = first;
		first = new Node();
		first.data = item;
		first.next = oldfirst;
		size++;
	}

	public DataType pop() {
		if (isEmpty())
			throw new NullPointerException("The stack is empty!"); 
		DataType returnData = first.data;
		first = first.next;
		size--;
		return returnData;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return size;
	}

	public Iterator<DataType> iterator() {
		return new LinkedListIterable();
	}

	private class LinkedListIterable implements Iterator<DataType>{
		private Node current = first;
		public boolean hasNext() {
			return current != null;
		}
		public void remove() {
			throw new UnsupportedOperationException("Operation not suported");
		}
		public DataType next() {
			DataType returnData = current.data;
			current = current.next;
			return returnData;
		}
	}

	public static void main (String[] args) {
		/*
		LinkedListStack<Integer> test = new LinkedListStack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		System.out.println(test.pop());
		test.push(4);
		for (int i : test)
			 System.out.println(i);
		System.out.println(test.pop());
		System.out.println(test.pop());
		System.out.println(test.pop());
		*/	
	}

	/*
	PERFORMANCE (Worst case)
		constructor: 1
		push: 1
		pop: 1
		size: 1
	*/

}