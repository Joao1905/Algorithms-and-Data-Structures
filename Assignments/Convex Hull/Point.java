import java.lang.Math;

public class Point implements Comparable<Point>{
    
    private final float x;
    private final float y;
    private float polarAngle;
    private final String name;

    //Constructor
    public Point(float myX,float myY) {
        x = myX;
        y = myY;
        name = "("+x+", "+y+")";
    }

    //Getters and Setters
    public float getX () {
        return x;
    }
    public float getY () {
        return y;
    }
    public float getPolarAngle () {
        return polarAngle;
    }
    public String getName () {
        return name;
    }

    public void setPolarAngle (float somePolarAngle) {
        polarAngle = somePolarAngle;
    }

    //Comparable method
    @Override
    public int compareTo(Point that) {
        if (this.polarAngle < that.polarAngle)
            return -1;
        else if (this.polarAngle > that.polarAngle)
            return 1;
        else
            return 0;
    }

    //Other Methods
    public static int isCounterClockwise(Point a, Point b, Point c) {

        //The determinant of 3 points gives us 2*Area of the triangle made by those points
        float determinant = ( (b.getX() - a.getX())*(c.getY()-a.getY()) ) - ( (b.getY()-a.getY())*(c.getX()-a.getX()) );

        //If area > 0, a->b->c is counter clockwise
        if (determinant > 0)
            return 1;   //Counter-clockwise
        else if (determinant < 0)
            return -1;  //Clockwise
        else
            return 0;   //Colinear
    }

    //The angle between the line made of a and b, and the line made by a and (a.x+1,a.y)
    //It assumes a.y < b.y
    public static float polarAngle(Point a, Point b) { 

        if (a.getY() > b.getY())
            throw new IllegalArgumentException("a.y is bigger than b.y");

        if (a.getX() == b.getX() && a.getY() == b.getY())
            throw new IllegalArgumentException("a and b are the same points");

        float RAD_DEGREE = 57.2958f;

        //This coordinates are relative to a, so a would be in (0,0) in this system
        float x1 = b.getX() - a.getX(); float y1 =  b.getY() - a.getY(); //Relative b.x and b.y
        float x2 = a.getX()+1; float y2 = 0; //new line (1,0)

        //Cos 0 = 1, cos 180 = -1, so the higher the cos, the smaller the polar angle
        float polarAngleCos = (float) (x1*x2 + y1*y2)/ (float) ( Math.sqrt(Math.pow(x1,2)+Math.pow(y1,2))*Math.sqrt(Math.pow(x2,2)+Math.pow(y2,2)) );
        return (float) Math.acos(polarAngleCos)*RAD_DEGREE; //arc cos and conversion from rad to 

    }

    public static void main(String[] args) {

        /*
        Point a = new Point(0,0); Point b = new Point(1,0); Point c = new Point(0.5f,1); Point d = new Point(2,0);
        System.out.println(isCounterClockwise(a,b,c));
        */

        /*
        Point a = new Point(1,0); Point b = new Point(0,0);
        System.out.println(polarAngle(a,b));
        */

    }
}
