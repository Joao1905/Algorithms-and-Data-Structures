import java.util.Comparator;

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

    public static void sort(Object[] a, Comparator comp) {
        for (int i =0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (SortingTools.less(comp, a[j], a[j-1]))
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

        /*
        ComparatorTestClass[] test2 = new ComparatorTestClass[6];
        ComparatorTestClass a = new ComparatorTestClass(5); test2[0] = a; ComparatorTestClass b = new ComparatorTestClass(1); test2[1] = b;
        ComparatorTestClass c = new ComparatorTestClass(7); test2[2] = c; ComparatorTestClass d = new ComparatorTestClass(23); test2[3] = d;
        ComparatorTestClass e = new ComparatorTestClass(2); test2[4] = e; ComparatorTestClass f = new ComparatorTestClass(9); test2[5] = f;
        sort(test2, ComparatorTestClass.BY_AGE); System.out.print("\n");
        for (ComparatorTestClass i : test2)
            System.out.println(i.getAge());
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
