import packages.other.StdDraw;

public class LineSegment {

    private final Point p;
    private final Point q;
    private final String name;

    //Constructor
    public LineSegment(Point p, Point q) {
        this.p = p;
        this.q = q;
        name = "("+p.getX()+", "+p.getY()+")("+q.getX()+", "+q.getY()+")";
    }

    //Getters and Setters
    public Point getP() {
        return p;
    }
    public Point getQ() {
        return q;
    }
    public String getName() {
        return name;
    }

    //Drawing Methods
    public void draw() {
        StdDraw.line(p.getX(), p.getY(), q.getX(), q.getY());
    }
    public String toString() {
        return name;
    }

    public static void main(String[] args) {

    }

}
