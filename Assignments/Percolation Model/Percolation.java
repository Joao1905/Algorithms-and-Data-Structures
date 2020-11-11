import packages.QuickUnion;

public class Percolation {

    private QuickUnion percolationSite;
    private int[] percolationState;
    private int size;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    // Index 0 and n*n+1 are only used to check if there's free access to the top or bottom
    public Percolation(int n) {
        this.percolationSite = new QuickUnion(n*n+2);
        this.percolationState = new int[n*n+2];
        this.size = n;
        for (int i = 1; i < n*n+1; i++) {
            percolationState[i] = 0;
        }
        percolationState[0] = 1;
        percolationState[n*n+1] = 1;
    }

    public int convert(int row, int col) {
        if (row > size || col > size)
            throw new IllegalArgumentException("Invalid Matrix Index");

        int index = ((row-1)*size)+col;         //Transform 2 dimensional coordinates into 1 dimension
        return index;        
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        
        if (isOpen(row, col))
            return;

        int index = convert(row, col);
        percolationState[index] = 1;            //Open Site
        openSites += 1;

        //Connect this node to all adjacent openned nodes
        if (percolationState[index+1] == 1 && index%size != 0) {
            percolationSite.weightedUnion(index, index+1);
        }
        if (percolationState[index-1] == 1 && (index-1)%size != 0){
            percolationSite.weightedUnion(index, index-1);
        }
        
        try {
            if (percolationState[index-size] == 1)                  //If index is out of bounds here, means it's either in the first or last row,
                percolationSite.weightedUnion(index, index-size);   //which means it must be connected to the first or last node (virtual node)
        } catch (Exception e) {
            percolationSite.weightedUnion(index, 0);
        }
        
        try {
            if (percolationState[index+size] == 1)
                percolationSite.weightedUnion(index, index+size);
        } catch (Exception e) {
            percolationSite.weightedUnion(index, size*size+1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = convert(row, col);
        return percolationState[index] == 1;
    }

    // is the site (row, col) full (meaning it has access all the way to the top)?
    public boolean isFull(int row, int col) {
        int index = convert(row, col);
        return percolationSite.connected(0, index);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolationSite.connected(0, size*size+1);
    }

    // test client (optional)
    public static void main(String[] args) {
        /*
        Percolation obj = new Percolation(4);
        obj.open(1, 4);
        obj.open(2, 1);
        obj.open(2, 4);
        obj.open(3, 3);
        obj.open(2, 2);
        obj.open(2, 3);
        obj.open(4, 3);
        obj.open(4, 4);
        
        System.out.println(obj.isFull(3,3));
        System.out.println(obj.numberOfOpenSites());
        System.out.println(obj.percolates());

        System.out.print("\n");

        for (int i = 0; i < obj.size*obj.size+2; i++) {
            System.out.print(obj.percolationSite.getNodeValue(i) + " ");
            if (i%4 == 0)
                System.out.print("\n");
        }

        System.out.print("\n");
        System.out.print("\n");

        for (int i = 0; i < obj.size*obj.size+2; i++) {
            System.out.print(obj.percolationState[i] + " ");
            if (i%4 == 0)
                System.out.print("\n");
        }

        */
    }
}