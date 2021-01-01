import java.util.Comparator;

public class ThreeWayQuickSort { 

    //This implementation optimazes cases where there are equal entries in the array
    private static void sort(Comparable[] a, int low, int high) {

        if (high <= low) return;    //Avoid infinite recursiveness
        int lt = low; int gt = high;
        Comparable key = a[low];
        int i = low; //Now gt will be our high pointer, and we'll keep equal entries between lt and i: [low----lt>--i>---<gt----high]
        while (i <= gt) {                                                                           //[low  <V  lt  =V  i  WIP  gt  >V  high]
            int result = a[i].compareTo(key);
            if (result < 0) SortingTools.exchange(a, i++, lt++);    //[low---Xlt>--i>---gt----high]
            else if (result > 0) SortingTools.exchange(a, i, gt--);   //[low---lt--i---<gtX----high]
            else i++;
        }

        sort(a, low, lt-1);
        sort(a, gt+1, high);

    }

    private static void sort(Comparator comp, Object[] a, int low, int high) {

        if (high <= low) return;
        int lt = low; int gt = high;
        Object key = a[low];
        int i = low; 
        while (i <= gt) {
            int result = comp.compare(a[i], key);
            if (result < 0) SortingTools.exchange(a, i++, lt++);
            else if (result > 0) SortingTools.exchange(a, i, gt--);
            else i++;
        }

        sort(comp, a, low, lt-1);
        sort(comp, a, gt+1, high);

    }

    public static void sort(Comparable[] a) {
        KnuthShuffle.shuffle(a);
        sort(a, 0, a.length-1);
    }

    public static void sort(Object[] a, Comparator comp) {
        KnuthShuffle.shuffle(a);
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

}