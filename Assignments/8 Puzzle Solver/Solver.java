import java.util.ArrayList;
import java.util.Comparator;

import packages.BinaryHeap;

public class Solver {
    
    Board initialBoard;
    private ArrayList<Board> result = new ArrayList<Board>();

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial, Comparator comp) {
        if (initial == null)
            throw new IllegalArgumentException("Board is null");

        initialBoard = initial;

        if (!isSolvable())
            return;

        if (initialBoard.isGoal()) {
            result.add(initialBoard);
            return;
        }
        
        BinaryHeap<Board> priorityQueue = new BinaryHeap<Board>(comp);
        priorityQueue.insert(initialBoard);

        Board placeholder = initialBoard;
        
        while (!placeholder.isGoal()) {
            placeholder = priorityQueue.deleteMax();
            result.add(placeholder);
            for(Board b : placeholder.neighbors()) {
                if (result.size() < 2)
                    priorityQueue.insert(b);
                else if (!b.equals(result.get(result.size()-2)))
                    priorityQueue.insert(b);
            }
                
        }
    }

    
    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return initialBoard.inversions()%2 == 0;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable())
            return -1;

        return result.size()-1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable())
            return null;

        return result;
    }

    public static void main(String[] args) {

        int[][] test = {
            new int[] {0, 1, 3},
            new int[] {4, 2, 5},
            new int[] {7, 8, 6}
        };
        int[][] test2 = {
            new int[] {1, 2, 3, 4},
            new int[] {5, 0, 7, 8},
            new int[] {9, 6, 11, 12},
            new int[] {13, 10, 14, 15}
        };
        Board board1 = new Board(test);
        Solver solver1 = new Solver(board1, Board.BY_MANHATTAN);
        
        for(Board b : solver1.solution())
            System.out.println(b.toString());

        System.out.println(solver1.moves());

        //It's normal to run out of memory. Be sure to ask Java for additional memory, e.g., java -Xmx1600m Solver
        //The A* algorithm (with the Manhattan priority function) will struggle to solve some 4-by-4 and even 3-by-3 puzzles

    }

}
