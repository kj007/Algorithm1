import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
  
	private int expCount;
	private double[] fractions;
	
	// perform trials independent experiments on an n-by-n grid
	/***
	 * Perform the trials independent experiments on n-by-n grid
	 * 
	 */
   public PercolationStats(int n, int trials){
	   if (n <= 0 || trials <= 0) {
           throw new IllegalArgumentException("Given n <= 0 || trails <= 0");
       }
	   expCount = trials;
	   fractions = new double[trials];
	   for (int trailNum=0;trailNum<trials;trailNum++){
		   // Initiansiate the  Percolation
		   Percolation pr = new Percolation(n);
		   // Count of number of Open sites
		   int numberOpen = 0;
		   while(!pr.percolates()){
			   int row = StdRandom.uniform(1,n+1);
			   int col = StdRandom.uniform(1,n+1);
			   pr.open(row, col);
			   numberOpen++;
		   }
		   fractions[trailNum] = (double)numberOpen / (n * n);
	   }
	   
   } 
   
   // sample mean of percolation threshold
   public double mean(){
	   return StdStats.mean(fractions);
   }
   
   // sample standard deviation of percolation threshold
   public double stddev(){
	   return StdStats.stddev(fractions);
	   
   }
   
   // low  endpoint of 95% confidence interval
   public double confidenceLo(){
	   return mean() - ((1.96 * stddev()) / Math.sqrt(expCount));
   }               
   
   // high endpoint of 95% confidence interval
   public double confidenceHi() {
	   return mean() + ((1.96 * stddev()) / Math.sqrt(expCount));  
   }
   
   // test client (described below)
   public static void main(String[] args)  {
	   int N = 2000;
       int T =20000;
       PercolationStats ps = new PercolationStats(N, T);

       String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
       StdOut.println("mean                    = " + ps.mean());
       StdOut.println("stddev                  = " + ps.stddev());
       StdOut.println("95% confidence interval = " + confidence);
	   
   }  
}