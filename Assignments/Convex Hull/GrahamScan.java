import packages.*;

public class GrahamScan {

    private static void swapMin (Point[] setOfPoints) {

        int min = 0;
        for (int i =0; i < setOfPoints.length; i++) {
            if (setOfPoints[i].getY() < setOfPoints[min].getY())
                min = i;
        }

        SortingTools.exchange(setOfPoints, 0, min);

    }

    private static void organizePoints(Point[] setOfPoints) {

        swapMin(setOfPoints);
        for (int i = 1; i < setOfPoints.length; i++) {
            setOfPoints[i].setPolarAngle(Point.polarAngle(setOfPoints[0], setOfPoints[i]));
            //System.out.println("Point: "+setOfPoints[i].getName()+" - "+setOfPoints[i].getPolarAngle());
        }

        MergeSort.sort(setOfPoints);
        
    }

    public static Point[] scan(Point[] setOfPoints) {

        organizePoints(setOfPoints);
        ArrayStack<Point> convexHull = new ArrayStack<Point>();

        convexHull.push(setOfPoints[0]);convexHull.push(setOfPoints[1]);
        for (int i = 2; i < setOfPoints.length; i++) {
            if (Point.isCounterClockwise(convexHull.get(1), convexHull.get(0), setOfPoints[i]) >= 0)
                convexHull.push(setOfPoints[i]);
            else {
                while (Point.isCounterClockwise(convexHull.get(1), convexHull.get(0), setOfPoints[i]) < 0)
                    convexHull.pop();
                convexHull.push(setOfPoints[i]);
            }
            //System.out.println(convexHull.get(0).getName() + convexHull.get(1).getName() + convexHull.get(2).getName());
        }

        Point[] returnArray = new Point[convexHull.size()];

        int size = convexHull.size(); //Carefull, every time you pop(), stack.size() gets smaller
        for (int i = 0; i < size; i++) {
            returnArray[i] = convexHull.pop();
        }

        return returnArray;

    }

    public static void main (String[] args) {

        /*
        Point[] myPoints = new Point[13];
        Point p9 = new Point(0.6f, 2.8f); myPoints[0] = p9;     //Should be in Convex Hull
        Point p2 = new Point(4.8f, 4.6f); myPoints[1] = p2;
        Point p7 = new Point(3.9f, 2.9f); myPoints[2] = p7;
        Point p11 = new Point(4.2f, 1.1f); myPoints[3] = p11;
        Point p4 = new Point(3.8f, 4.6f); myPoints[4] = p4;
        Point p10 = new Point(1.6f, 2.1f); myPoints[5] = p10;
        Point p3 = new Point(5.3f, 2.4f); myPoints[6] = p3;
        Point p6 = new Point(3.8f, 2.8f); myPoints[7] = p6;
        Point p0 = new Point(6, 0.5f); myPoints[8] = p0;        //Should be in Convex Hull
        Point p12 = new Point(1.7f, 1.2f); myPoints[9] = p12;   //Should be in Convex Hull
        Point p1 = new Point(6, 4.9f); myPoints[10] = p1;       //Should be in Convex Hull
        Point p5 = new Point(2.5f, 5.7f); myPoints[11] = p5;    //Should be in Convex Hull
        Point p8 = new Point(1.3f, 3.4f); myPoints[12] = p8;

        Point[] hull = scan(myPoints);
        for (Point i : hull)
            System.out.println(i.getName());
        */

    }

}