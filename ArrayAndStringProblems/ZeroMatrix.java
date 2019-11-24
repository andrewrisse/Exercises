/*
ZeroMatrix.java
Creator: Andrew Risse
This program takes an N x M matrix of integers and when it finds a 0 it 
marks the entire row and column of that 0 as 0. To save memory, the row and column zeroing information
is stored in the 1st row and column of the table itself. Rows and columns are zeroized at the end, after 
all the zeroes have been found.
*/
package com.andrewrisse.maven;
import java.util.Random;

public class ZeroMatrix {
    public static void zeroMatrix (int[][] matrix) {
        int n = matrix.length;// number of rows
        int m = matrix[0].length; // number of columns
        int firstColumnHasZero = 0;
        int firstRowHasZero = 0;
        //see if the first column has zeroes so we can zeroize it out at the end if necessary
        for (int i =0; i < n; i++){
            if (matrix[i][0] == 0){
                firstColumnHasZero = 1;
            }
        }
        //see if the first row has zeroes so we can zeroize it out at the end if necessary
        for(int j = 0; j < m; j++){
            if (matrix[0][j] == 0){
                firstRowHasZero = 1;
            }
        }
        //search the rest of the matrix for zeroes, if found mark the 1st row and column of that
        //element as zero
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++ ){
                if(matrix[i][j] == 0){
                    ZeroMatrix.markZero(i,j, matrix);
                }
            }
        }
        // search first column for zeroes, then mark those '0' rows with all zeroes
        for(int i = 0; i < n; i++){
            if(matrix[i][0] == 0){
                ZeroMatrix.markRowZero(i, m, matrix);
            }
        }
        // search first row for zeroes, then mark those '0' columns with all zeroes 
        for(int j = 0; j < m; j++){
            if(matrix[0][j] == 0){
                ZeroMatrix.markColumnZero(j, n, matrix);
            }
        }
        //zero out first row if necessary
        if(firstRowHasZero == 1){
            for(int j = 0; j < m; j++){
                matrix[0][j] = 0;
            }
        }
        //zero out first column if necessary
        if(firstColumnHasZero == 1){
            for(int i = 0; i < n; i++){
                matrix[i][0] = 0;
            }
        }
        
    }
    // marks the corresponding 1st row and column with a zero
    private static void markZero(int i, int j, int[][] matrix){
        matrix[0][j] = 0;
        matrix[i][0] = 0;
    }
    //inserts 0's for entire row
    private static void markRowZero(int i, int m, int[][] matrix){
        for(int j = 0; j < m; j++)
            matrix[i][j] = 0;
    }
    //inserts 0's for entire column
    private static void markColumnZero(int j, int n, int[][] matrix){
        for(int i = 0; i < n; i++){
            matrix[i][j] = 0;
        }
    }
    //print the matrix
    public static void printMatrix(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i ++){
            for(int j = 0; j < m; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    

       public static void main(String[] args) {
        //create a matrix with random integers
        Random rand = new Random();
        int rows = 8;
        int columns = 8;
        int[][] matrix = new int[rows][columns]; //create a matrix with random numbers
        for( int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                int randNum = rand.nextInt(10); //random numbers are between 0-9
                matrix[i][j] = randNum;
            }

        }
        ZeroMatrix.printMatrix(matrix); //print before matrix
        ZeroMatrix.zeroMatrix(matrix);
        System.out.print("\n");
        ZeroMatrix.printMatrix(matrix); //print after matrix
    }
}
