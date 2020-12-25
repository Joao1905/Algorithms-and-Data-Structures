import java.util.Random;

public class KnuthShuffle {

    //Random number between low(inclusive) and high(inclusive)
    private static int generateRand(int low, int high) {
        
        if (low > high)
            throw new IllegalArgumentException("Low parameter is higher than High parameter");

        high++; //Makes high inclusive and not exclusive
        Random r = new Random();
        return r.nextInt(high-low) + low;

    }

    public static void shuffle(Object[] a) {
        for (int i = 0; i < a.length; i++)
            SortingTools.exchange(a, generateRand(0,i), i); //Must not swap it with a random place in the whole array, or result will not be entirely random
    }

    public static void main(String[] args) {

        Integer[] test = {1,2,3,4,5,6,7,8,9,10};
        shuffle(test);
        for (int i : test) 
            System.out.println(i);

        /*
        for (int i =0; i < 10; i++)
            System.out.println(generateRand(0,3));
        */

    }

}
