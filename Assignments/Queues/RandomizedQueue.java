//Implemented using arrays too (since it's probably optimal to do so)
import java.util.Random;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<DataType> implements Iterable<DataType> {

	private DataType[] queue = (DataType[]) new Object[1];
	private int n = 0;

	// construct an empty randomized queue
    public RandomizedQueue() {
    	
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
    	return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
    	return n;
    }

    // add the item
    public void enqueue(DataType item) {
    	if (item == null)
    		throw new IllegalArgumentException("Null argument is not accepted");
    	if (queue.length == size())
    		resize(queue.length*2);
    	queue[n++] = item;
    }

    // remove and return a random item
    public DataType dequeue() {
    	if (size() == 0)
    		throw new NoSuchElementException("Queue is empty");
    	Random rand = new Random();
    	int random_number = rand.nextInt(n);		//Get a random number in the interval [0,n[
    	DataType return_data = queue[random_number];
    	queue[random_number] = queue[--n];
    	queue[n] = null;
    	if (queue.length/4 == size())
    		resize(queue.length/2);
    	return return_data;
    }

    // return a random item (but do not remove it)
    public DataType sample() {
    	if (size() == 0)
    		throw new NoSuchElementException("Queue is empty");
    	Random rand = new Random();
    	return queue[rand.nextInt(n)];
    }

    private void resize(int new_size) {
    	if (new_size < size())
    		throw new IllegalArgumentException("new_size is too small");
    	DataType[] copy = (DataType[]) new Object[new_size];
    	for (int i = 0; i < size(); i++)
    		copy[i] = queue[i];
    	queue = copy;
    }

    private void printDebug() {
        System.out.print("[");
        for (int i = 0; i < queue.length-1; i++)
            System.out.print(queue[i]+", ");
        System.out.print(queue[queue.length-1]+"] "+"n: "+n+", nulls: "+(queue.length-size())+"\n");
    }

    public Iterator<DataType> iterator() {
    	return new FirstToLastIterable();
    }

    private class FirstToLastIterable implements Iterator<DataType>{

    	private int i = 0;
    	private int[] shuffle = new int[n]; 

    	//Constructor to initialize vector and shuffle it
    	public FirstToLastIterable() {
    		for (int i = 0; i < n; i++)
    			shuffle[i] = i;
    		Random rand = new Random();
    		for (int i = 0; i < n; i++) {
    			int rand_number = rand.nextInt(n);
    			int holder = shuffle[i];
    			shuffle[i] = shuffle[rand_number];
    			shuffle[rand_number] = holder;
    		}
    	}

    	public boolean hasNext() {
            return i != n;
        }

        public void remove() {
            throw new UnsupportedOperationException("Operation not suported");
        }

        public DataType next() {
            if (!hasNext())
                throw new NoSuchElementException("No more items to return");
            return queue[shuffle[i++]];
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
    	
    	/*
    	RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
    	test.printDebug();
    	test.enqueue(1);
    	test.printDebug();
    	test.enqueue(2);
    	test.printDebug();
    	test.enqueue(3);
    	test.printDebug();
    	test.enqueue(4);
    	test.printDebug();
    	test.enqueue(5);
    	test.printDebug();
    	System.out.println(test.dequeue());
    	test.printDebug();
    	for (int i : test)
    		System.out.println(i);
    	test.printDebug();
    	System.out.println(test.dequeue());
    	test.printDebug();
    	System.out.println(test.dequeue());
    	test.printDebug();
    	System.out.println("Sample: "+test.sample());
    	test.printDebug();
    	*/

    	/*
    	RandomizedQueue test = new RandomizedQueue();
    	test.printDebug();
    	test.enqueue(null);
    	test.printDebug();
    	*/

    	/*
    	RandomizedQueue test = new RandomizedQueue();
    	test.dequeue();
    	*/
    }

}