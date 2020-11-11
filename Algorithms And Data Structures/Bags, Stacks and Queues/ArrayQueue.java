import java.util.Iterator;

public class ArrayQueue<DataType> implements Iterable<DataType>{

	private int head = 0;
	private int tail = 0;
	private DataType[] queue = (DataType[]) new Object[1];

	public boolean isEmpty() {
		return head == tail;
	}

	private int size() {	//This is NOT the array.length, this is the number of elements in the queue
		return tail - head;
	}

	private void enqueue(DataType data) {
		if (tail == queue.length) { //Remember the tail always stays at the index where the new element will be placed, so here tail is already outOfBounds
			if (head != 0) {
				shift();
			} else {
				resize(queue.length*2);
			}
		}

		queue[tail++] = data;
	}

	private DataType dequeue () {
		if (size() == 0)
			throw new NullPointerException("The queue is empty!");
		DataType returnData = queue[head++];
		queue[head-1] = null;

		if (size() == queue.length/4) {
			if (head != 0)
				shift();
			resize(queue.length/2);
		}

		return returnData;
	}

	private void resize(int newcapacity) {
		DataType[] copy = (DataType[]) new Object[newcapacity];
		for (int i = 0; i < (tail-head); i++)
			copy[i] = queue[i];
		queue = copy;
	}

	private void shift() {
		if (head == 0) //Already shifted
			return;
		int initialSize = size();
		int counter = 0;
		while(head != tail) {
			queue[counter] = queue[head];
			queue[head] = null;
			head++;
			counter++;
		}
		head = 0;
		tail = initialSize;
	}

	private void printDebug() {
		System.out.print("[");
		for (int i = 0; i < queue.length-1; i++)
			System.out.print(queue[i]+", ");
		System.out.print(queue[queue.length-1]+"] "+"head: "+head+", tail: "+tail+"\n");
	}

	public Iterator<DataType> iterator() {
		return new ArrayIterator();
	}

	private class ArrayIterator implements Iterator<DataType> {
		private int i = head;

		public boolean hasNext() {
			return i != tail;
		}

		public void remove() {
			throw new UnsupportedOperationException("Operation not suported");
		}

		public DataType next() {
			return queue[i++];
		}
	}

	public static void main (String[] args) {
		
		ArrayQueue<Integer> test = new ArrayQueue<Integer>();
		test.printDebug();
		test.enqueue(1);
		test.printDebug();
		test.enqueue(2);
		test.printDebug();
		test.enqueue(3);
		test.printDebug();
		test.dequeue();
		test.printDebug();
		for (int i : test)
			System.out.println(i);
		test.dequeue();
		test.printDebug();
		
	}

}