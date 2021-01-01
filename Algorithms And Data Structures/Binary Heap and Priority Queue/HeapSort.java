public class HeapSort {

    public static void sort(Comparable[] array) {
        int heapPointer = array.length;

        //First transform the array into a heap. We start at the last+1 layer, and work our way up node by node
        for (int k = heapPointer/2; k >= 1; k--) {
            swapDown(array, k, heapPointer);
        }
        
        while(heapPointer > 1) {
            exchange(array, 1, heapPointer);
            swapDown(array, 1, --heapPointer);
        }
        
    }

    private static void swapDown(Comparable[] array, int k, int heapPointer) {

        int j = 2*k;
        //This simply make sure j = the index of children with the bigger key, which we'll swap k with
        if (j < heapPointer && less(array, j, j+1))  //j<heapPointer tests if there are 2 children or only one
            j++;

        if (j > heapPointer || lessEqual(array, j,k)) //If it's child in j index is not part of the queue anymore
            return;

        exchange(array, j, k);
        swapDown(array, j, heapPointer);
    }

    //Always -1 because our heap implementation started at index 1, so when dealing with arrays, we can correct by decreasing 1 here
    private static boolean less (Comparable[] array, int a, int b) {
        return array[a-1].compareTo(array[b-1]) < 0; //is a < b?
    }

    private static boolean lessEqual (Comparable[] array, int a, int b) {
        int result = array[a-1].compareTo(array[b-1]);
        if (result <= 0)
            return true;

        return false;
    }

    private static void exchange(Comparable[] array, int i, int j) {
        Comparable swap = array[i-1];
        array[i-1] = array[j-1];
        array[j-1] = swap;
    }

    public static void main(String[] args) {

        Integer[] a = {4,3,7,1,2,9,5,4,8};
        sort(a);
        for (int i : a)
            System.out.println(i);

    }

    /*PERFORMANCE 
    For an array of size N:
        Heap construction: 2N compares and exchanges
        Sort: 2N lg N compares and exchanges (even worst case)
    HeapSort IS an inplace sorting algorithm
    Heapsort IS NOT stable
    Not widely used because it has much more to do in every loop than mergesort, and also
      because it makes poor use of cache RAM, since it's always accessing distant positions in the array
    */

}
