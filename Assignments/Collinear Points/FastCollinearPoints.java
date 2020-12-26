import java.util.ArrayList;

import packages.other.StdDraw;    //These were NOT implemented by me. It was available at Princeton's university webpage
import packages.other.In;    // and their usage is part of this assignment

import packages.MergeSort;
import packages.SortingTools;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
    private int numberOfSegments = 0;

    //Constructor
    public FastCollinearPoints(Point[] points) {
        Point[] slopePoints = new Point[points.length];

        //Initialize slopePoints
        for (int i = 0; i < slopePoints.length; i++) {
            slopePoints[i] = points[i];
        }

        //For every point as reference, calculate slope for every other point
        for (int i = 0; i < slopePoints.length; i++) {

            MergeSort.sort(slopePoints);    //It's very usefull to first sort by Y value because when we decide which points are part of
                                            // the segment, the points with lower and higher Y will be the extremes. Since mergesort is
                                            // stable, points with the same slope will remain sorted by Y value even after sorting by slope.
            MergeSort.sort(slopePoints, points[i].BY_SLOPE); //I could also use points[0].slopeOrder(), but this seems more natural

            //Now, we see if we have 3 or more points with same slope next to each other
            int low = 1; int high = 2; //index 0 is always the point whose slope is being compared to (slope = -Infinity)
            while(high < slopePoints.length) {
                while(high < slopePoints.length && slopePoints[high].slopeTo(points[i]) == slopePoints[low].slopeTo(points[i])) {
                    high++;
                }

                if(high-low < 3) {
                    low = high;
                    high++;
                    continue;
                }

                //If we have 3 or more points with same slope next to each other, check if this segment wasn't added already
                LineSegment tempSegment = new LineSegment(Point.min(slopePoints[low],slopePoints[0]), Point.max(slopePoints[high-1], slopePoints[0])); //Already sorted by Y, so these are the extremes
                for (int j = 0; j < numberOfSegments; j++) {
                    if (segments.get(j).getP() == tempSegment.getP() && segments.get(j).getQ() == tempSegment.getQ())
                        break;
                    if (segments.get(j).getQ() == tempSegment.getP() && segments.get(j).getP() == tempSegment.getQ())
                        break;
                    
                    //If the segment wasn't added already, add it
                    if(j == numberOfSegments-1) {
                        segments.add(numberOfSegments++, tempSegment);
                    }
                }

                //If there's still no segment added, than add it (it won't fall inside the above loop)
                if (numberOfSegments == 0) {
                    segments.add(numberOfSegments++, tempSegment);
                }

                //Back to searching for more 3-same-slope-points inside our sorted array
                low = high; 
            }
        }
    }

    //Getters and Setters
    public LineSegment[] segments() {
        LineSegment[] segmentsReturn = new LineSegment[segments.size()];
        for (int i = 0; i < segments.size(); i++) {
            segmentsReturn[i] = segments.get(i);
        }
        return segmentsReturn;
    }
    public int numberOfSegments() {
        return numberOfSegments;
    }

    public static void main(String[] args) {

        StdDraw.setCanvasSize(800, 800);
        StdDraw.setPenRadius(0.01); StdDraw.setPenColor(StdDraw.DARK_GRAY);

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        StdDraw.setPenRadius(0.005); StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            System.out.println(segment.getName());
            segment.draw();
        }
        StdDraw.show();

    }
}