import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private boolean[] sites;
	private WeightedQuickUnionUF wqu;
	private WeightedQuickUnionUF wUpper;
	private int size;
	private int VIRTUAL_TOP, VIRTUAL_BOTTOM;
	
	/* 
	 * Contructor for the Percolation: 
	 * 
	 * Create n-by-n grid, with all sites blocked
	 */
	
	public Percolation(int n){
		// Check for n < 1
		if (n < 1){
			throw new IllegalArgumentException();
		}
		size = n;
		VIRTUAL_TOP = 0;
		VIRTUAL_BOTTOM = n*n + 1;
		sites = new boolean[n*n+2];
		wqu = new WeightedQuickUnionUF(n*n+2);
		wUpper = new WeightedQuickUnionUF(n*n+1);
		sites[VIRTUAL_TOP] = true;
		sites[VIRTUAL_BOTTOM] = true;
	}
	
	/**
	 * Check for the Valid Row and Column
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean isValidRowAndCol(int row, int col){
		if (row < 1 || row > size)
			throw new java.lang.IndexOutOfBoundsException( row + "Row is not in the range ");
		if (col < 1 || col > size)
			throw new java.lang.IndexOutOfBoundsException( col + "Row is not in the range ");
		return true;
	}
	
	/**
	 * Convert the row and col to Id
	 * Id = (row - 1) * size + col
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	
	private int convertRowAndColToId(int row, int col){
		int rowColToId = 0;
		if (isValidRowAndCol(row, col))
			rowColToId =  (row - 1) * size + col;
		return rowColToId;
	}
	
	
	// open site (row i, column j) if it is not open already
	/**
	 * (i) Opens (row = i, col = j) 
	 * (ii) Open the neighbours of the (row = i, col = j) as follows:
	 *  - if i = topmost row (1) than wqu.union(id(i,j), VIRTUAL_TOP) & wUpper.union(id(i,j), VIRTUAL_TOP)
	 *  - if i = last row (size) then wqu.union(id(i,j), VIRTUAL_BOTTOM)
	 *  - if j > 1st col then join left site(i, j-1) if open with wqu and wUpper.
	 *  - if j < last(size) col then join right site (i, j+1) with wqu and wupper.
	 *  - if i > 1 then join bottom site (i+1, j) with wqu and wUpper.
	 *  - if i < size then join upper site (i-1, j) with wqu and wUpper
	 * @param row
	 * @param col
	 */
	public void open(int rowI, int colJ){
		int rowColToId;
		if (isValidRowAndCol(rowI, colJ)){
			rowColToId = convertRowAndColToId(rowI, colJ);
			if (!isOpen(rowI, colJ)){
				sites[rowColToId] = true;
				// If the first row then join to the top
				if (rowI == 1){
					wqu.union(rowColToId, VIRTUAL_TOP);
					wUpper.union(rowColToId, VIRTUAL_TOP);
				}
				// If the last row then join to the bottom
				if (rowI == size){
					wqu.union(rowColToId, VIRTUAL_BOTTOM);
				}
				// If more first col then join to the left neighbour if open
				if (colJ > 1 && isOpen(rowI, colJ-1)){
					wqu.union(rowColToId, convertRowAndColToId(rowI, colJ-1));
					wUpper.union(rowColToId, convertRowAndColToId(rowI, colJ-1));
				}
				if (colJ < size && isOpen(rowI, colJ+1)){
					wqu.union(rowColToId, convertRowAndColToId(rowI, colJ+1));
					wUpper.union(rowColToId, convertRowAndColToId(rowI, colJ+1));
				}
				if (rowI > 1 && isOpen(rowI - 1, colJ)){
					wqu.union(rowColToId, convertRowAndColToId(rowI - 1, colJ));
					wUpper.union(rowColToId, convertRowAndColToId(rowI - 1, colJ));
				}
				if (rowI < size && isOpen(rowI + 1, colJ)){
					wqu.union(rowColToId, convertRowAndColToId(rowI + 1 , colJ));
					wUpper.union(rowColToId, convertRowAndColToId(rowI + 1, colJ));
				}
			}
			
		}
	}
	
	

	// is site (row i, column j) full?
	/***
	 * Check if the the site is connected to VIRTUAL_TOP on wqu 
	 * and site is connected with VIRTUAL_TOP on wUpper(to avoid backwash)
	 * 
	 * @param row
	 * @param col 
	 */
	public boolean isFull(int i, int j) {	
		if (isValidRowAndCol(i,j)){
			int id_of_element = convertRowAndColToId(i,j);
			return wqu.connected(id_of_element, VIRTUAL_TOP) && wUpper.connected(id_of_element, VIRTUAL_TOP);
		}
		return false;
	}    
	
    // does the system percolate?
	/**
	 * Check if the VIRTUAL_TOP is connected to VIRTUAL_BOTTOM on wqu.
	 * 
	 */
	public boolean percolates(){
		// Check to the bottom elements if the VIRTUAL_BOTTOM is connected to VIRTUAL_TOP
		return wqu.connected(VIRTUAL_TOP, VIRTUAL_BOTTOM);
	}
	
	
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j){
		if (isValidRowAndCol(i,j)){
			int id_of_element = convertRowAndColToId(i,j);
			return sites[id_of_element];
		}
		return false;
	}
}
