Given a set of 2D points, find its convex hull, which is defined by the smallest convex set that contains every point.\
\
I've implemented Graham Scan's algorithm to solve the problem, which consists in selecting the point with the lowest y value (lets call it lowYPoint),
then sorting every point P by the polar angle between the line (0,1) and (lowYPoint, P), so that we scan everypoint starting from lowYPoint in a
counter-clockwise motion. To decide if the point P was part of the convex hull, I would tested if, by connecting the 2 last points in the convex hull and P,
I'd have a counter-clockwise motion (if yes, then the point is added to the convex hull). If the next point P+1 was not to be added to the convex hull, I'd remove
the point at the front of the convex hull stack (last added) until P+1 gets added, and then I'd move on to P+2 until every point is scanned.\
\
I've used my implementation of merge sort and my implementation of stacks (implemented with arrays) to solve this problem. \
\
To test the algorithm, the following points have been given and the Convex Hull has been found successfully.\
![Image Alt-Text!](https://imgur.com/c1ZcVH8.png)
