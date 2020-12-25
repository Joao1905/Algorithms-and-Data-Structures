public class MergeSort {

    private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high) {

        assert SortingTools.isSorted(a, low, mid);
        assert SortingTools.isSorted(a, mid+1, high);

        //Copy array
        for (int i = low; i <= high; i++) {
            aux[i] = a[i];  
        }

        //Merge sorted halves
        int i = low; int j = mid+1;
        for (int k = low; k <= high; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > high)
                a[k] = aux[i++];
            else if (SortingTools.less(aux[j], aux[i]))   //If aux[i] > aux[j]
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }

        assert SortingTools.isSorted(a, low, high);

    }

    private static void sort(Comparable[] a, Comparable[] aux, int low, int high) {

        if (low >= high)
            return;

        int mid = (low+high)/2;
        sort(a, aux, low, mid);         //Will be called until low+1=High (after that, will return)
        sort(a, aux, mid+1, high);
        merge(a, aux, low, mid, high);

    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length]; //DO NOT create the aux array inside the recursive routine
        sort(a, aux, 0, a.length - 1);
    }

    public static void sortBottomUp (Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        for (int size = 1; size < n; size = size+size) //Clever way to iterate 2^n: 1, 2, 4, 8, 16...
            for (int low = 0; low < n-size; low += size+size) //Here, we'll iterate through 0,2,4,6... then 0,4,8,12... until our incremet is equal to n
                merge(a, aux, low, low+size-1, Math.min(low+size+size-1, n-1)); //Finally, merge 2 by 2, then 4 by 4, checking if we arrived at the end of the array
    }

    public static void main(String[] args) {

        /*
        Integer[] a = {4,3,7,1,2,9,5,4,8};
        sort(a);
        for (int i : a)
            System.out.println(i);
        */

        /*
        Integer[] a = {4,3,7,1,2,9,5,4,8};
        sortBottomUp(a);
        for (int i : a)
            System.out.println(i);
        */

    }

    /*PERFORMANCE 
    For an array of size N:
        N lg N compares
        6N lg N array access
    Mergesort is not an Inplace sorting algorithm
    Improvements would be to use insertion sort on small arrays, to check if the array is already sorted before merging
      and to not initialize the aux array at the beginning, but while the sorting and merging is happening
    */

}
