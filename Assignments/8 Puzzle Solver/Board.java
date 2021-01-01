//Import and extend java.lang.Object is redundant, since Object has no method to be overwritten
import java.lang.Math;
import java.util.ArrayList;
import java.util.Comparator;

import packages.BinaryHeap;


public class Board{

    private final int[][] tiles;
    private final int lines;
    private final int columns;

    private ArrayList<Board> neighbors = new ArrayList<Board>();

    public final int manhattan;
    public final int hamming;

    public static final Comparator<Board> BY_MANHATTAN= new byManhattan();
    public static final Comparator<Board> BY_HAMMING = new byHamming();

    //We may assume that the constructor receives an array containing the n^2 integers between 0 and n^2 − 1 (not necessarily ordered)
    public Board(int[][] tiles) {
        lines = tiles.length;           //tiles.length is the number of lines
        columns = tiles[0].length;      //tiles[0].length is the number of elements in the first line (number of columns)

        if (lines != columns)
            throw new IllegalArgumentException("The board is not symmetrical");

        for (int i = 1; i < lines; i++) {
            if (tiles[i].length != columns)
                throw new IllegalArgumentException("A line of the board is not complete");
        }

        this.tiles = tiles;

        hamming = -1*hamming(); //My priority queue works with max values and we want to remove min values
        manhattan = -1*manhattan();
    }

    public String toString() {
        String result = "";
        result = lines+"\n";
        for (int i = 0; i < lines; i++){ 
            for (int j = 0; j < columns; j++) { 
                result += tiles[i][j]+" ";
            }
            result += "\n";
        }

        return result;
    }

    public int dimension() {
        return lines;
    }

    //Number of tiles out of place
    private int hamming() {
        int counter = 0;
        for (int i = 0; i < lines; i++){ 
            for (int j = 0; j < columns; j++) { 
                if(tiles[i][j] != coordinatesToPosition(i, j)) {
                    counter++;
                }                    
            }
        }

        counter--; //0 will always be out of place, but "0" is actually a void space, which should not count as a missplaced number

        return counter;
    }

    // sum of Manhattan distances between tiles and goal
    private int manhattan() {
        int[] goalPos = new int[2];
        int result = 0;

        for (int i = 0; i < lines; i++){ 
            for (int j = 0; j < columns; j++) {

                if (tiles[i][j] == 0)
                    continue;

                 goalPos[0] = positionToCoordinates(tiles[i][j])[0];
                 goalPos[1] = positionToCoordinates(tiles[i][j])[1];
                 result += Math.abs(goalPos[0]-i)+Math.abs(goalPos[1]-j);
            }
        }
        return result;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    //Since I want to overwrite the method equals(), I must take an Object parameter, and not a Board parameter
    public boolean equals(Object y){

        if (!y.getClass().equals(getClass()))
            return false;

        Board tempBoard = (Board) y;

        if (tempBoard.dimension() != dimension())
            return false;

        for (int i = 0; i < lines; i++){ 
            for (int j = 0; j < columns; j++) {
                if (tiles[i][j] != tempBoard.tiles[i][j])
                    return false;
            }
        }

        return true;
    }

    public Iterable<Board> neighbors() {
        getNeighbors();
        return neighbors;
    }

    private void getNeighbors() {

        neighbors.clear();
        int[] coord = new int[2];
        int[][][] placeholderTile = new int[4][lines][columns];

        //First we populate our Tiles and find the "0" coordinates
        for (int i = 0; i < lines; i++){ 
            for (int j = 0; j < columns; j++) { 
                if(tiles[i][j] == 0) {
                    coord[0] = i;
                    coord[1] = j;
                }
                
                for (int k = 0; k<4; k++) {
                    placeholderTile[k][i][j] = tiles[i][j];
                }

            }
        }

        //Finally, we add those boards to our neighbor attribute
        int[] a = {1, 0, -1, 0, 0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            try {
                placeholderTile[i][coord[0]][coord[1]] = placeholderTile[i][coord[0]+a[i]][coord[1]+a[i+4]];
                placeholderTile[i][coord[0]+a[i]][coord[1]+a[i+4]] = 0;
                neighbors.add(new Board(placeholderTile[i]));
            } catch (Exception e) {

            }
        }

    }

    public Board twin() {
        int[][] placeholderTile = new int[lines][columns];
        
        if (tiles[0][0] == 0 || tiles[0][1] == 0) {
            for (int i = 0; i < lines; i++){ 
                for (int j = 0; j < columns; j++) { 
                    placeholderTile[i][j] = tiles[i][j];
                }
            }
            int swap = placeholderTile[lines-1][columns-1];
            placeholderTile[lines-1][columns-1] = placeholderTile[lines-1][columns-2];
            placeholderTile[lines-1][columns-2] = swap;
            return new Board(placeholderTile);
        }

        for (int i = 0; i < lines; i++){ 
            for (int j = 0; j < columns; j++) { 
                placeholderTile[i][j] = tiles[i][j];
            }
        }
        int swap = placeholderTile[0][0];
        placeholderTile[0][0] = placeholderTile[0][1];
        placeholderTile[0][1] = swap;
        return new Board(placeholderTile);
    }

    public int inversions() {
        int counter = 0;
        int[] copy = new int[lines*lines];
        for (int i = 0; i < lines; i++){ 
            for (int j = 0; j < columns; j++)
                copy[i*lines+j] = tiles[i][j];
        }

        for (int i = 0; i < copy.length; i++) {
            if (copy[i] == 0)
                continue;
            for (int j = i; j < copy.length; j++) { 
                if (copy[j] == 0)
                    continue;
                if (copy[i] > copy[j])
                    counter++;
            }
        }
        return counter;
    }

    public Board createCopy() {
        int[][] copy = new int[lines][columns];
        for (int i = 0; i < lines; i++){ 
            for (int j = 0; j < columns; j++)
                copy[i][j] = tiles[i][j];
        }
        return new Board(copy);
    }

    //Line and column starting from 0, position starting from 1
    private int coordinatesToPosition(int x, int y) {
        if (x >= lines || y >= columns)
            throw new IllegalArgumentException("Position out of bounds");

        return x*columns+y+1;
    }

    //Position starting from 1, Line and column starting from 0
    private int[] positionToCoordinates(int pos) {

        if(pos > lines*columns)
            throw new IllegalArgumentException("Position out of bounds");

        int[] result = new int[2];
        result[0] = (pos-1)/columns;
        if (pos<=columns)
            result[1] = pos-1;
        else
            result[1] = (pos-1)%columns;
        return result;
    }

    private static class byManhattan implements Comparator<Board> {
        @Override
        public int compare (Board a, Board b) {
            //Should return 0 if a==b, -1 if a<b and 1 if a>b. For wrapper types we can simply return compareTo()
            if (a.manhattan == b.manhattan)
                return 0;
            else if (a.manhattan < b.manhattan)
                return -1;
            else
                return 1;  
        }
    }

    private static class byHamming implements Comparator<Board> {
        @Override
        public int compare (Board a, Board b) {
            //Should return 0 if a==b, -1 if a<b and 1 if a>b. For wrapper types we can simply return compareTo()
            if (a.hamming == b.hamming)
                return 0;
            else if (a.hamming < b.hamming)
                return -1;
            else
                return 1;  
        }
    }

    public static void main(String[] args) {

        /*
        int[][] test = {
            new int[] {0, 2, 3},
            new int[] {4, 5, 6},
            new int[] {7, 8, 1}
        };
        int[][] test2 = {
            new int[] {2, 1, 3},
            new int[] {4, 5, 6},
            new int[] {7, 8, 0}
        };
        int[][] test3 = {
            new int[] {2, 1, 3},
            new int[] {4, 6, 5},
            new int[] {7, 8, 0}
        };
        int[][] test4 = {
            new int[] {0, 2, 6},
            new int[] {3, 5, 4},
            new int[] {1, 7, 8}
        };
        int[][] test5 = {
            new int[] {0, 2, 6},
            new int[] {8, 5, 4},
            new int[] {1, 7, 3}
        };

        Board testBoard = new Board(test); System.out.println(testBoard.hamming);
        Board testBoard2 = new Board(test2); System.out.println(testBoard2.hamming);
        Board testBoard3 = new Board(test3); System.out.println(testBoard3.hamming);
        Board testBoard4 = new Board(test4); System.out.println(testBoard4.hamming);
        Board testBoard5 = new Board(test5); System.out.println(testBoard5.hamming);

        BinaryHeap<Board> myHeap = new BinaryHeap(5, BY_HAMMING);
        myHeap.insert(testBoard);
        myHeap.insert(testBoard3);
        myHeap.insert(testBoard4);
        myHeap.insert(testBoard5);
        myHeap.insert(testBoard2);

        System.out.println(myHeap.deleteMax());
        System.out.println(myHeap.deleteMax());
        System.out.println(myHeap.deleteMax());
        System.out.println(myHeap.deleteMax());
        System.out.println(myHeap.deleteMax());
        
        System.out.println(testBoard4.inversions());
        */

    }

}
