public class ShellSort {

    //Basically insertion sort with h spaces between exchanges
    private static void hSort(Comparable[] a, int h) {

        if (h >= a.length)
            throw new IndexOutOfBoundsException("h value is higher then array's size");

        for (int i=h; i < a.length; i++) {
            for (int j=i; j >= h; j -= h) {
                if (SortingTools.less(a[j], a[j-h]))
                    SortingTools.exchange(a, j, j-h);
                else
                    break;
            }
        }
    }

    //Create best h sequence until it reaches 1 (we'll use h = h*3+1)
    private static int createSequence(Comparable[] a) {
        int h = 1;
        while(h < a.length/3)
            h = h*3+1;

        return h;
    }

    //Shellsort is a series of insertion sorts with decreasingly h values
    public static void sort(Comparable[] a) {

        int h = createSequence(a);
        while(h >= 1) {
            hSort(a, h);
            h = h/3;
        }

    }

    public static void main(String[] args) {

        /*
        Integer[] test = {5,2,4,6,1,3};
        hSort(test,2); //h = 1 is insertion sort
        for (int i : test)
            System.out.println(i);
        */

        /*
        Integer[] test = {1,3,8,2,3,1,4,5,6,9,8};
        sort(test);
        for (int i : test)
            System.out.println(i);
        */
        
    }

    /* PERFORMANCE
    Analysis for shellsort is still open
    But worst case scenario for 3x+1 incrmente is
        O( N^(3/2) )
        In practice, much faster than this, but no one knows how much for sure
    */

}
