import packages.other.StdDraw;
import java.util.Comparator;

public class Point implements Comparable<Point> {
    
    private final int x;
    private final int y;
    private final String name;
    public final Comparator<Point> BY_SLOPE = new SlopeComparator();

    //Constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        name = "("+x+", "+y+")";
    }

    //Getters and Setters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getName() {
        return name;
    }

    //Drawing Methods
    public void draw() {
        StdDraw.point(x, y);
    }
    public void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }
    public String toString() {
        return name;
    }

    //Other Methods
    public double slopeTo(Point that) {

        if (x == that.x && y == that.y)
            return Double.NEGATIVE_INFINITY;

        if (x == that.x)
            return Double.POSITIVE_INFINITY;

        double slope = (double)(y - that.y)/(double)(x - that.x);

        return slope;
    }
    public Comparator<Point> slopeOrder() {
        return BY_SLOPE;
    }
    public static Point min(Point a, Point b) {
        if (a.compareTo(b) == -1)
            return a;
        if (a.compareTo(b) == 1)
            return b;
        return a;
    }
    public static Point max(Point a, Point b) {
        if (a.compareTo(b) == 1)
            return a;
        if (a.compareTo(b) == -1)
            return b;
        return a;
    }

    //Override Methods
    @Override //Compares 2 points by its y coordinates
    public int compareTo(Point that) {
        if (y < that.y)
            return -1;
        else if (y > that.y)
            return 1;
        else {
            if (x < that.x)
                return -1;
            else if (x > that.x)
                return 1;
            else
                return 0;
        }
            
    }

    public class SlopeComparator implements Comparator<Point> {  //Here, since class is NOT static, we'll use my_point.BY_SLOPE instead of Point.BY_SLOPE
        @Override                                                // on the comparator parameter required
        public int compare(Point a, Point b) {
            double aSlope = slopeTo(a); double bSlope = slopeTo(b);
            if (aSlope < bSlope)
                return -1;
            else if (aSlope > bSlope)
                return 1;
            else
                return 0;
        }
    }

    public static void main(String[] args) {

    }

}
