package gvprojects.Test.practicecode;

public class MatrixHelp
{
    public static int[][] getSubMatrix(int[][] input, int start_row, int start_column, int height, int width)
    {


	return input;
    }

    public static void main(String[] args)
    {
	int[][] input = {{4, 7, 3, 2, 9}, 
			 {1, 6, 8, 4, 7}, 
			 {2, 1, 3, 2, 4}, 
			 {4, 5, 1, 7 ,8}, 
			 {3, 2,5, 1, 4}};


	int[][] output = getSubMatrix(input, 2, 1, 3, 2);
	for (int[] x : output) {
	    System.out.println(java.util.Arrays.toString(x));
	}
    } // end main


}