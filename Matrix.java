package assignment01;

/**
 * Provides couple matrix operator
 * 
 * @author Haoze Zhang
 */

public class Matrix
{
    int numRows;
    int numColumns;
    int data[][];

    // Constructor with data for new matrix (automatically determines dimensions)
    public Matrix (int data[][])
    {
        numRows = data.length; // d.length is the number of 1D arrays in the 2D
                               // array
        if (numRows == 0)
        {
            numColumns = 0;
        }
        else
        {
            numColumns = data[0].length; // d[0] is the first 1D array
        }
        this.data = new int[numRows][numColumns]; // create a new matrix to hold the data
        // copy the data over
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numColumns; j++)
            {
                this.data[i][j] = data[i][j];
            }
        }
    }

    /**
     * The method operate times between two matrix. I put the times method at the first, because I want to follow the
     * method order made on Canvas. So I put times method at first,plus second, equals third and toString last.
     */
    public Matrix times (Matrix matrix)
    {
        // If the format is not correct, return null
        if (this.numColumns != matrix.numRows)
        {
            return null;
        }
        // Create the result method
        Matrix result = new Matrix(new int[this.numRows][matrix.numColumns]);
        // made matrix
        for (int i = 0; i < this.numRows; i++)
        {
            for (int j = 0; j < matrix.numColumns; j++)
            {
                int sum = 0;
                for (int k = 0; k < this.numColumns; k++)
                {
                    sum += data[i][k] * matrix.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }
        // Return result
        return result;
    }

    // The plus method
    public Matrix plus (Matrix matrix)
    {
        Matrix result = new Matrix(new int[this.numRows][this.numColumns]);
        // When the method is correct format, the method works
        if (this.numColumns == matrix.numColumns && this.numRows == matrix.numRows)
        {
            for (int i = 0; i < this.numRows; i++)
            {
                for (int j = 0; j < this.numColumns; j++)
                {
                    result.data[i][j] = matrix.data[i][j] + this.data[i][j];
                }
            }
        }
        // If not, it will return null
        else
        {
            return result = null;
        }
        return result;
    }

    @Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object)
              // version
    // This is the method that judge whether two matrix is equals
    public boolean equals (Object other)
    {
        if (!(other instanceof Matrix))
        { // make sure the Object we're
          // comparing to is a Matrix
            return false;
        }
        Matrix matrix = (Matrix) other; // if the above was not true, we know
                                        // it's safe to treat 'o' as a Matrix
        // Create the method that check "Is matrix the same"
        if (matrix.numRows == this.numRows && matrix.numColumns == this.numColumns)
        {
            for (int i = 0; i < numRows; i++)
            {
                for (int j = 0; j < numColumns; j++)
                {
                    if (this.data[i][j] != matrix.data[i][j])
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object)
              // version
    // This method provides the method toString
    public String toString ()
    {
        String s = "";
        for (int i = 0; i < numRows; i++)
        {
            for (int j = 0; j < numColumns; j++)
            {
                if (j < numRows - 1 && j != numRows - 1)
                {
                    s += data[i][j] + " ";
                }
                if (j == numRows - 1)
                {
                    s += data[i][j] + " " + "\n";
                }
            }
        }

        return s;
    }

}
