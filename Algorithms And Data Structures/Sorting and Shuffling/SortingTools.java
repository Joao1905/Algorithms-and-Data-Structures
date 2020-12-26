import java.util.Comparator;

public class SortingTools {

    public static boolean less (Comparable a, Comparable b) {
        return a.compareTo(b) < 0; //is a < b?
    }

    public static boolean less (Comparator comp, Object a, Object b) {
        return comp.compare(a, b) < 0; //is a < b?
    }

    public static void exchange(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static boolean isSorted(Comparable[] a) {

        for(int i=1; i < a.length; i++) {
            if (less(a[i], a[i-1]))   //a[i] > a[i+1]
                return false;
        }

        return true;

    }

    public static boolean isSorted(Comparator comp, Object[] a) {

        for(int i=1; i < a.length; i++) {
            if (less(comp, a[i], a[i-1]))   //a[i] > a[i+1]
                return false;
        }

        return true;

    }

    public static boolean isSorted(Comparable[] a, int low, int high) {

        for(int i=low; i < high; i++) {
            if (less(a[i+1],a[i]))   //a[i] > a[i+1]
                return false;
        }

        return true;

    }

    public static boolean isSorted(Comparator comp, Object[] a, int low, int high) {

        for(int i=low; i < high; i++) {
            if (less(comp, a[i+1],a[i]))   //a[i] > a[i+1]
                return false;
        }

        return true;

    }

    public static void main(String[] args) {

    }

}
