public class SelectionSort {
    
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i; j < a.length; j++) {
                if (SortingTools.less(a[j], a[min]))
                    min = j;
            }
            SortingTools.exchange(a ,i, min);
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
        N exchanges
        N²/2 array access
    */

}
