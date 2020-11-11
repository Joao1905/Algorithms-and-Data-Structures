import java.util.Random;
import java.lang.Math;

public class PercolationStats {

    private Percolation[] percolationObject;
    private double[] percolationTreshold;
    private int numberOfTrials;
    private int size;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        
        percolationTreshold = new double[trials];
        percolationObject = new Percolation[trials];
        numberOfTrials = trials;
        size = n*n;

        Random rand = new Random();
        for (int i = 0; i < trials; i++) {        
            percolationObject[i] = new Percolation(n);
            while (percolationObject[i].percolates() == false) {
                int[] index = new int[2];
                index[0] = rand.nextInt(n)+1;
                index[1] = rand.nextInt(n)+1;
                percolationObject[i].open(index[0],index[1]);
            }
            percolationTreshold[i] = (double) percolationObject[i].numberOfOpenSites()/size;
            System.out.println("i: "+i+", Open Sites: "+percolationObject[i].numberOfOpenSites()+", Treshold: "+percolationTreshold[i]);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for (int i = 0; i < numberOfTrials; i++)
            sum += percolationTreshold[i];
        return sum/numberOfTrials;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double variance = 0;
        double sum = 0;
        double mean = mean();
        for (int i = 0; i < numberOfTrials; i++)
            sum += (percolationTreshold[i]-mean)*(percolationTreshold[i]-mean);
        variance = sum/(numberOfTrials-1);
        return Math.sqrt(variance);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();
        return mean-(1.96*stddev)/(Math.sqrt(numberOfTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double mean = mean();
        double stddev = stddev();
        return mean+(1.96*stddev)/(Math.sqrt(numberOfTrials));
    }

   // test client (see below)
    public static void main(String[] args) {
        PercolationStats test = new PercolationStats(4, 30);
        System.out.println(test.mean());
        System.out.println(test.stddev());
        System.out.println(test.confidenceLo());
        System.out.println(test.confidenceHi());
    }

}