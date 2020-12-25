public class InsertionSort {

    public static void sort(Comparable[] a) {
        for (int i =0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (SortingTools.less(a[j], a[j-1]))
                    SortingTools.exchange(a, j, j-1); //Exchange with every element higher to the left of j, until reach the correct spot
                else
                    break;
            }
        }
    }

    public static void main(String[] args) {

        /*
        Integer[] test = {1,3,8,2,3,1,4,5,6,9,8};
        sort(test);
        for (int i : test)
            System.out.println(i);
        */

    }

    /* PERFORMANCE
    For an array of size N:
        N²/4 exchanges
        N²/4 array access
    Unlike selection sort, Insertion sort performance DOES DEPEND on the starting position of the array
    Insertion sort runs in linear time for partially sorted arrays (numb. of inversions <= constant*N)
    */

}
