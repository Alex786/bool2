public class ArrayMatrix implements BooleanMatrix {
	// The array used to store the boolean values.
	private boolean[][] matrixArr;

	// The number of columns in the Boolean Matrix. Note that this may be different than the number of columns in the array matrixArr.
	private int numCols;

	// The number of rows in the Boolean Matrix. Note that this may be different than the number of rows in the array matrixArr.
    private int numRows;

	public ArrayMatrix() {
        // Creates a 1 x 1 matrix with a single false value. This constructor calls the reset() method.
		reset();
	}

    public final void reset() {
        // Resets the Boolean matrix to a 1 x 1 matrix that contains a single false value.
		matrixArr = new boolean[1][1];
		matrixArr[0][0] = false;
		numRows = 1;
		numCols = 1;
    }

    public int getNumberRows() {
		// Returns the number of rows.
		return numRows;
	}

	public int getNumberCols() {
		// Returns the number of columns.
		return numCols;
	}

	public void set(int row, int col) throws IndexOutOfBoundsException {
		// Sets the element at the specified position to true. Adds new rows and columns if needed, filled with false. This method should call the BooleanMatrix.put(int row, int col, boolean value) method.
		put(row, col, true);
	}

	public void clear(int row, int col) throws IndexOutOfBoundsException {
		// Sets the element at the specified position to false. Adds new rows and columns if needed, filled with false. This method should call the BooleanMatrix.put(int row, int col, boolean value) method.
		put(row, col, false);
	}

	public void put(int row, int col, boolean value) throws IndexOutOfBoundsException {
		// Stores the element at the specified position to the specified value. Adds new rows and columns if needed, filled with false.
		if (row < 0 || col < 0) throw new IndexOutOfBoundsException();

    	if (row >= numRows || col >= numCols) {
        	int newRows = Math.max(row + 1, numRows);
        	int newCols = Math.max(col + 1, numCols);
        	boolean[][] newMatrix = new boolean[newRows][newCols];

        	for (int i = 0; i < numRows; i++) {
            	for (int j = 0; j < numCols; j++) {
                	newMatrix[i][j] = matrixArr[i][j];
            	}
        	}
        	matrixArr = newMatrix;
        	numRows = newRows;
        	numCols = newCols;
    	}
    	matrixArr[row][col] = value;
	}

	public void toggle(int row, int col) throws IndexOutOfBoundsException {
		// Changes the element at the specified position. If the current value is true it is changed to false. If the current value is false it is changed to true.
		if (row < 0 || col < 0 || row >= numRows || col >= numCols) 
        	throw new IndexOutOfBoundsException();
    	matrixArr[row][col] = !matrixArr[row][col];
	}

	public void setAll() {
		// Sets all elements to true. This method should call the BooleanMatrix.putAll(boolean value) method.
		putAll(true);
	}

	public void clearAll() {
		// Clears all elements to false. This method should call the BooleanMatrix.putAll(boolean value) method.
		putAll(false);
	}

	public void putAll(boolean value) {
		// Sets all elements to the specified value.
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				matrixArr[i][j] = value;
			}
		}
	}

	public boolean get(int row, int col) throws IndexOutOfBoundsException {
		// Returns the element at the specified position.
		if (row < 0 || col < 0 || row >= numRows || col >= numCols) 
        	throw new IndexOutOfBoundsException();
    	return matrixArr[row][col];
	}

	public int[][] getTruePositions() {
		// Returns the indices of the elements whose value is true. The indices are returned in a P x 2 array of integers, where P is the number of elements with a value of true. Each row in the returned array contains the row and column index of an element with a value of true. The returned array is sorted in increasing order by row and column. This method should call the BooleanMatrix.getPositions(boolean value) method.
		return getPositions(true);
	}

	public int[][] getFalsePositions() {
		// Returns the indices of the elements whose value is false. The indices are returned in a P x 2 array of integers, where P is the number of elements with a value of false. Each row in the returned array contains the row and column index of an element with a value of false. The returned array is sorted in increasing order by row and column. This method should call the BooleanMatrix.getPositions(boolean value) method.
		return getPositions(false);
	}

	public int[][] getPositions(boolean value) {
		// Returns the indices of the elements whose value matches the parameter. The indices are returned in a P x 2 array of integers, where P is the number of elements whose value matches the parameter. Each row in the returned array contains the row and column index of an element whose value matches the parameter. The returned array is sorted in increasing order by row and column. This method should call the BooleanMatrix.getPositions(boolean value) method.
		int count = getNumberValues(value);
		int[][] positions = new int[count][2];
		int index = 0;
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (matrixArr[i][j] == value) {
					positions[index][0] = i;
					positions[index][1] = j;
					index++;
				}
			}
		}
		return positions;
	}

	public int getNumberTrueValues() {
		// Returns the number of elements whose value is true.
		return getNumberValues(true);
	}

	public int getNumberFalseValues() {
		// Returns the number of elements whose value is false.
		return getNumberValues(false);
	}

	public int getNumberValues(boolean value) {
		// Returns the number of elements with the specified value.
		int count = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (matrixArr[i][j] == value) count++;
			}
		}
		return count;
    }

	public String toString() {
		// Returns a string showing: 1) the number of rows, 2) the number of columns, 3) the number of true values, and 4) the number of false values.
		return "Rows: " + numRows + ", Columns: " + numCols + ", True values: " + getNumberTrueValues() + ", False values: " + getNumberFalseValues();
	}
}