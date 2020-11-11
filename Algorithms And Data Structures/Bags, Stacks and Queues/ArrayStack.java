//Stack implementation using an array basis
//Still vulnerable to underflow (pop from empty stack). Allows null items.
import java.util.Iterator;

public class ArrayStack<DataType> implements Iterable<DataType> {

	private DataType[] stack = (DataType[]) new Object[1];
	private int n = 0;

	public ArrayStack() {

	}

	public boolean isEmpty() {
		return n == 0;
	}

	public void push (DataType data) {
		if (n == stack.length)
			resize(2*stack.length);
		stack[n++] = data; //Same as stack[n] = data; n++;
	}

	public DataType pop () {
		if (isEmpty())
			throw new NullPointerException("The stack is empty!"); 
		//This 3 step procedure avoids loitering, which is to hold a reference to an object that is no longer being used
		DataType item = stack[--n]; //Since we want to access n-1 index, decrement n first and then use it as index
		stack[n] = null;
		if (n>0 && n == stack.length/4) //If we did n==stack.length/2, then the client might push, pop, push, pop... resulting in quadratic runtime
			resize(stack.length/2);
		return item;
	}

	private void resize(int newCapacity) {
		DataType[] copy = (DataType[]) new Object[newCapacity];
		for (int i = 0; i < n; i++)
			copy[i] = stack[i];
		stack = copy;
	}

	private int size() {
		return n;
	}

	//An Iterable has a method that returns an Iterator
	//An Iterator has methods hasNext() and next()
	//When you make your classes Iterable, java supports code such as for(String s : stack)
	public Iterator<DataType> iterator() { 		//This is an iterable, it returns an Iterator
		return new ReverseArrayIterator(); 		//This is an iterator
	}

	private class ReverseArrayIterator implements Iterator<DataType> {	//This is the Iterator returned above
		private int i = n;

		public boolean hasNext() {
			return i > 0;
		}

		public void remove() {
			throw new UnsupportedOperationException("Operation not suported");
		}

		public DataType next() {
			return stack[--i]; //Remember that i is always in the spot where the next element will be placed
		}
	}

	public static void main (String[] args) {
		/*
		ArrayStack<Integer> test = new ArrayStack<Integer>();
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
		push: N
		pop: N
		size: 1
	In spite of slower worst case than linked list implementation, on average is faster and consumes less memory
	*/

}