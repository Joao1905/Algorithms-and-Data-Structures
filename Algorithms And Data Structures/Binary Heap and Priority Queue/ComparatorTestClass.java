import java.util.Comparator;

public class ComparatorTestClass {
    
    private final int age;
    private final int size = 0;
    public static final Comparator<ComparatorTestClass> BY_AGE = new ByAge();

    public ComparatorTestClass(int my_age) {
        age = my_age;
    }

    public int getAge() {
        return age;
    }

    private static class ByAge implements Comparator<ComparatorTestClass> {
        @Override
        public int compare (ComparatorTestClass a, ComparatorTestClass b) {
            //Should return 0 if a==b, -1 if a<b and 1 if a>b. For wrapper types we can simply return compareTo()
            if (a.age==b.age)
                return 0;
            else if (a.age < b.age)
                return -1;
            else
                return 1;  
        }
    }

    public static void main(String[] args) {

    }

}
