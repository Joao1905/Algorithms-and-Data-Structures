import java.util.Comparator;

public class QuickSort {

    //This implementation can get tricky fast (if done wrong, it can take quadratic time)
    public static int partition(Comparable[] a, int low, int high) {
    
        int i =low; int j=high+1; //We first decrement/increment and then check. This will avoid an infinite loop when a[j]==a[i]==a[low]
        while(true) {

            //If we try to solve this with only the above while loop and if statements, we'll run into the infinite loop described above
            while(SortingTools.less(a[++i],a[low])) //a[i] < a[low]
                if (i == high) break;
            while(SortingTools.less(a[low],a[--j])) //a[j] > a[low]
                if (j == low) break;
            
            if (i >= j) break;
            SortingTools.exchange(a, i, j);

        }
        SortingTools.exchange(a, low, j);
        return j;
    }

    public static int partition(Comparator comp, Object[] a, int low, int high) {
    
        int i =low; int j=high+1; 
        while(true) {

            while(SortingTools.less(comp, a[++i],a[low])) //a[i] < a[low]
                if (i == high) break;
            while(SortingTools.less(comp, a[low],a[--j])) //a[j] > a[low]
                if (j == low) break;
            
            if (i >= j) break;
            SortingTools.exchange(a, i, j);

        }
        SortingTools.exchange(a, low, j);
        return j;
    }

    private static void sort(Comparable[] a, int low, int high) {
        if (high <= low)
            return;
        int j = partition(a, low, high);
        sort(a, low, j-1);
        sort(a, j+1, high);
    }

    private static void sort(Comparator comp, Object[] a, int low, int high) {
        if (high <= low)
            return;
        int j = partition(comp, a, low, high);
        sort(comp, a, low, j-1);
        sort(comp, a, j+1, high);
    }

    public static void sort(Comparable[] a) {
        KnuthShuffle.shuffle(a);    //Shuffle needed for performance guarantee
        sort(a, 0, a.length-1);
    }

    public static void sort(Object[] a, Comparator comp) {
        KnuthShuffle.shuffle(a);    //Shuffle needed for performance guarantee
        sort(comp, a, 0, a.length-1);
    }

    public static void main(String[] args) {

        /*
        String[] test = {"q","u","i","c","k","s","o","r","t","e","x","a","m","p","l","e"};
        sort(test, 0, test.length-1);
        for (String x : test)
            System.out.print(x+", ");
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
        1.39*N lg N compares (worst case is 1/2 N^2 on an already sorted array)
        
    Inplace sorting
    NOT stable (would need an auxiliar array to be stable)
    Despite of more compares, quicksort is faster than mergesort because it doesn't do much data movement on it's compares
      (increment a pointer vs move item to aux array)
    An improvement is to use insertion sort if the array to be sorted has length <= 10 for example
    */

}
