//Every parent node is larger than its children
//starts at index 1, and a[1] is the largest key, the root of the binarytree
//Parent node of k is at k/2, achd children are at 2k and 2k+1
package packages;

import java.util.Comparator;

public class BinaryHeap<DataType> {

    private DataType[] heapArray;
    private int heapPointer = 0;
    private int capacity = 4;
    private Comparator comp;

    public BinaryHeap() {
        heapArray = (DataType[]) new Object[capacity+1];
    }

    public BinaryHeap(int capacity) {
        heapArray = (DataType[]) new Object[capacity+1];
        this.capacity = capacity;
    }

    public BinaryHeap(Comparator comp) {
        heapArray = (DataType[]) new Object[capacity+1];
        this.capacity = capacity;
        this.comp = comp;
    }

    public BinaryHeap(int capacity, Comparator comp) {
        heapArray = (DataType[]) new Object[capacity+1];
        this.capacity = capacity;
        this.comp = comp;
    }

    public boolean isEmpty() {
        return (heapPointer == 0);
    }

    public void insert(DataType x) {
        if (heapPointer == capacity) {
            capacity = capacity*2;
            resize(capacity);
        }

        heapArray[++heapPointer] = x;
        swapUp(heapPointer);
    }

    public DataType deleteMax() {
        if (isEmpty())
            throw new NullPointerException("Queue is empty");

        if (heapPointer <= capacity/4+1) {
                capacity = capacity/2;
                resize(capacity);
        }

        DataType max = heapArray[1];
        exchange(1, heapPointer--);
        swapDown(1);
        heapArray[heapPointer+1] = null; //If we keep referencing a[heapPointer+1], it won't be collected by the garbage collector
        return max;
    }

    //If a child's key becomes larger than it parent's key swap recursively until heal order is restored
    private void swapUp(int k) {
        if (k <= 1 || lessEqual(heapArray[k], heapArray[k/2]))   // k <= 1 tests if there's only 1 element in the queue, so it's already ordered
            return;

        exchange(k, k/2);
        swapUp(k/2);
    }

    //If a parent key becomes smaller than one or both of its children's
    private void swapDown(int k) {

        int j = 2*k;
        //This simply make sure j = the index of children with the bigger key, which we'll swap k with
        if (j < heapPointer && less(heapArray[j], heapArray[j+1]))  //j<heapPointer tests if there are 2 children or only one
            j++;

        if (j > heapPointer || lessEqual(heapArray[j],heapArray[k])) //If it's child in j index is not part of the queue anymore
            return;

        exchange(j, k);
        swapDown(j);
    }

    private void resize(int newcapacity) {
        DataType[] copy = (DataType[]) new Object[newcapacity+1];
        for (int i = 1; i <= heapPointer; i++)
            copy[i] = heapArray[i];
        heapArray = copy;
    }

    private boolean less (Comparable a, Comparable b) {
        return a.compareTo(b) < 0; //is a < b?
    }

    private boolean less (Object a, Object b) {
        if (comp == null) {
            return ((Comparable) a).compareTo(b) < 0;
        }

        return comp.compare(a, b) < 0; //is a < b?
    }

    private boolean lessEqual (Object a, Object b) {

        int result;
        if (comp == null) {
            result = ((Comparable) a).compareTo(b);
        } else {
            result = comp.compare(a, b);
        }

        if (result <= 0)
            return true;

        return false;
    }

    private void exchange(int i, int j) {
        DataType swap = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = swap;
    }

    private void printMe() {
        for(DataType i : heapArray)
            System.out.print(i+", ");
    }

    public static void main(String[] args) {
        
        /*
        BinaryHeap<Integer> test = new BinaryHeap<Integer>();
        test.insert(5); test.printMe(); System.out.println();
        test.insert(3); test.printMe(); System.out.println();
        test.insert(7); test.printMe(); System.out.println();
        test.insert(29); test.printMe(); System.out.println();
        test.insert(8); test.printMe(); System.out.println();
        test.insert(0); test.printMe(); System.out.println();
        test.insert(13); test.printMe(); System.out.println();
        test.insert(2); test.printMe(); System.out.println();
        test.insert(5); test.printMe(); System.out.println();
        test.insert(5); test.printMe(); System.out.println();
        test.insert(6); test.printMe(); System.out.println();
        test.deleteMax(); test.printMe(); System.out.println();
        test.insert(3); test.printMe(); System.out.println();
        test.deleteMax(); test.printMe(); System.out.println();
        test.deleteMax(); test.printMe(); System.out.println();
        test.deleteMax(); test.printMe(); System.out.println();
        test.deleteMax(); test.printMe(); System.out.println();
        test.deleteMax(); test.printMe(); System.out.println();
        test.deleteMax(); test.printMe(); System.out.println();
        test.deleteMax(); test.printMe(); System.out.println();
        */

        /*
        BinaryHeap<ComparatorTestClass> test = new BinaryHeap<ComparatorTestClass>(ComparatorTestClass.BY_AGE);
        ComparatorTestClass a = new ComparatorTestClass(5); ComparatorTestClass b = new ComparatorTestClass(1);
        ComparatorTestClass c = new ComparatorTestClass(7); ComparatorTestClass d = new ComparatorTestClass(23);
        ComparatorTestClass e = new ComparatorTestClass(2); ComparatorTestClass f = new ComparatorTestClass(9);

        test.insert(a); test.printMe(); System.out.println();
        test.insert(b); test.printMe(); System.out.println();
        test.insert(c); test.printMe(); System.out.println();
        test.insert(d); test.printMe(); System.out.println();
        test.insert(e); test.printMe(); System.out.println();
        test.insert(f); test.printMe(); System.out.println();

        System.out.println(test.deleteMax().getAge());
        System.out.println(test.deleteMax().getAge());
        */

    }

}


