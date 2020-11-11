//Queue implementation using a Linked list basis
import java.util.Iterator;

public class LinkedListQueue<DataType> implements Iterable<DataType> {

	private Node first;
	private Node last;
	private int size;

	private class Node {
		DataType data;
		Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public DataType dequeue() {
		if (isEmpty()) 
			throw new NullPointerException("The queue is empty!");
		DataType returnData = first.data;
		first = first.next;			
		size--;
		return returnData;
	}

	public void enqueue(DataType item) {
		Node oldlast = last;
		last = new Node();
		last.data = item;
		last.next = null;
		size++;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last; //If queue is empty, oldlast=null, so there's no oldlast.next attribute
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

	public static void main(String[] args) {
		/*
		LinkedListQueue<Integer> test = new LinkedListQueue<Integer>();
		test.enqueue(1);
		test.enqueue(2);
		test.enqueue(3);
		System.out.println(test.dequeue());
		test.enqueue(4);
		for (int i : test)
			System.out.println(i);
		System.out.println(test.dequeue());
		System.out.println(test.dequeue());
		System.out.println(test.dequeue());
		*/
	}

}