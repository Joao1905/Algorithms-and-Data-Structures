//An optimal solution might be done with Linked Lists, but since arrays seemed more challenging, I've used arrays
import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<DataType> implements Iterable<DataType> {

    private int head = 0;
    private int tail = 0;
    private DataType[] queue = (DataType[]) new Object[2];

    // construct an empty deque
    public Deque() {
        
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == tail;
    }

    // return the number of items on the deque
    public int size() {
        return tail - head;
    }

    // add the item to the front
    public void addFirst(DataType item) {
        if (item == null)
            throw new IllegalArgumentException ("Null values not allowed");
        if (head == 0)
            shiftResize((queue.length*2-size())/2+1, queue.length*2); //queue.length*2-size() is the number of nulls in the new array   
        queue[--head] = item;
    }

    // add the item to the back
    public void addLast(DataType item) {
        if (item == null)
            throw new IllegalArgumentException ("Null values not allowed");
        if (tail == queue.length)
            shiftResize((queue.length*2-size())/2, queue.length*2);
        queue[tail++] = item;
    }

    // remove and return the item from the front
    public DataType removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
        if (head >= size())
            shiftResize((queue.length/2-size())/2, queue.length/2);
        DataType returnData = queue[head];
        queue[head++] = null;
        return returnData;
    }

    // remove and return the item from the back
    public DataType removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
        if (queue.length-tail >= size())
            shiftResize((queue.length/2-size())/2+1, queue.length/2);
        DataType returnData = queue[--tail];
        queue[tail] = null;
        return returnData;
    }

    private void shiftResize(int newIndex, int newCapacity) {
        if (head == newIndex) //Already shifted
            return;
        if (newCapacity-newIndex < size()) //[10,20,30,null] 4-1 < 3 
            throw new IllegalArgumentException("newIndex and newCapacity would cause the array to overflow");
        DataType[] copy = (DataType[]) new Object[newCapacity];
        for (int i = newIndex; i < newIndex+size(); i++)
            copy[i] = queue[head+(i-newIndex)];
        queue = copy;
        tail = newIndex+size();
        head = newIndex;
    }

    private void printDebug() {
        System.out.print("[");
        for (int i = 0; i < queue.length-1; i++)
            System.out.print(queue[i]+", ");
        System.out.print(queue[queue.length-1]+"] "+"head: "+head+", tail: "+tail+", nulls: "+(queue.length-size())+"\n");
    }

    public Iterator<DataType> iterator() {
        return new FirstToLastIterable();
    }

    private class FirstToLastIterable implements Iterator<DataType> {

        private int i = head;

        public boolean hasNext() {
            return i != tail;
        }

        public void remove() {
            throw new UnsupportedOperationException("Operation not suported");
        }

        public DataType next() {
            if (!hasNext())
                throw new NoSuchElementException("No more items to return");
            return queue[i++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        /*
        Deque test = new Deque();
        test.printDebug();
        test.addLast(null);
        */

        /*
        Deque<Integer> test = new Deque<Integer>();
        test.printDebug();
        test.addLast(1);
        test.printDebug();
        test.addFirst(2);
        test.printDebug();
        System.out.println(test.removeFirst());
        test.printDebug();
        test.addLast(3);
        test.printDebug();
        test.addLast(4);
        test.printDebug();
        for (int i : test)
            System.out.println(i);
        test.addFirst(5);
        test.printDebug();
        System.out.println(test.removeLast());
        test.printDebug();
        System.out.println(test.removeFirst());
        test.printDebug();
        System.out.println(test.removeFirst());
        test.printDebug();
        */

        /*
        Deque test = new Deque();
        test.printDebug();
        System.out.println(test.removeLast());
        test.printDebug();
        */
        
    }

}