/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;


public class MatrixTester {
    public static void main(String[] args) {
        Matrix M1 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });

        Matrix M2 = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });

        // this is the known correct result of multiplying M1 by M2
        Matrix referenceMultiply = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });

        /*
         * Note that none of the tests below will be correct until you have
         * implemented all methods. This is just one example of a test, you must
         * write more tests and cover all cases.
         */

        // get the matrix computed by your times method
        Matrix computedMultiply = M1.times(M2);

        // exercises your toString method
        System.out.println("Computed result for M1 * M2:\n" + computedMultiply);

        // exercises your .equals method, and makes sure that your computed
        // result is the same as the known correct result
        if (!referenceMultiply.equals(computedMultiply)) {
            System.out.println("Should be:\n" + referenceMultiply);
        }
        //Test the plus method, if this method is correct, it should return null
        Matrix computedAdd = M1.plus(M2);
        //Test the null situation
        if(computedAdd == null){
        System.out.println("Computed result for M1 + M2 = " + computedAdd + "\n" + "plus method is correct");
       }
        /**
         * Test Case 2,  3x4 matrix multiply 2x3 matrix       
         */
        Matrix M3 = new Matrix(new int[][] { { 1, 2, 3, 6}, { 2, 5, 6, 9 }, { 2, 3, 4, 5}});
        Matrix M4 = new Matrix(new int[][] { { 4, 5, 6}, { 3, 2, 1}, });
        Matrix computedMultiply2 = M3.times(M4);
        Matrix computedAdd2 = M3.plus(M4);
        if(computedAdd2 == null){
            System.out.println("Computed result for M3 + M4 = " + computedAdd2 + "\n" + "times method is correct");
        }
        if(computedMultiply2 == null){
            System.out.println("Computed result for M3 * M4 = " + computedMultiply2 + "\n" + "times method is correct");
        }
        /**
         * Test Case 3,  2x2 matrix multiply and plus 2x2 matrix, and test equals method   
         */
        Matrix M5 = new Matrix(new int[][] { { 1, 2}, { 2, 5 }});
        Matrix M6 = new Matrix(new int[][] { { 4, 5}, { 3, 2 }});
        //Times method
        Matrix referenceMultiply3 = new Matrix(new int[][] { { 10, 9 }, { 23, 20 }});
        //Plus method
        Matrix referenceAdd3 = new Matrix(new int[][] { { 5, 7 }, { 5, 7 } });
        Matrix computedMultiply3 = M5.times(M6);
        Matrix computedAdd3 = M5.plus(M6);
        //Test equals method
        if(referenceMultiply3.equals(computedMultiply3)){
        System.out.println("Computed result for M5 * M6 " + "\n" + computedMultiply3);
        }
        if(referenceAdd3.equals(computedAdd3)){
        System.out.println("Computed result for M5 + M6 " + "\n" + computedAdd3);
        }
        //Test toString method
        System.out.println("Test toString method" + "\n" + M5.toString());
    }
}
