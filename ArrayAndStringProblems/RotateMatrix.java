/*
RotateMatrix.java
Creator: Andrew Risse
This program takes an NxN matrix of integers and rotates it 90 degrees to the right. O(n^2) time, O(1) memory
*/

package com.andrewrisse.maven;

import java.util.Random;

public class RotateMatrix {
    public static void rotate(int[][] matrix){
        int n = matrix.length;
        for(int layer = 0; layer < n/2; layer ++){ // work in layers outside in
            int last = n - 1 - layer;
            for(int offset = 0; offset < last - layer; offset ++){
                    
                    int top = matrix[layer][offset + layer];
            
                    //left to top
                    matrix[layer][offset + layer] = matrix[last - offset][layer];

                    //bottom to left
                    matrix[last - offset][layer] = matrix[last][last - offset];

                    //right to bottom
                    matrix[last][last - offset] = matrix[offset + layer][last];

                    //top to right
                    matrix[offset + layer][last] = top;
                    
            }      
         }
    }

    // prints the matrix
    public static void printMatrix(int[][] matrix){
        int n = matrix.length;
        for (int i = 0; i < n; i ++){
            for(int j = 0; j < n; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    public static void main(String[] args) {
        Random rand = new Random();
        int size = 8;
        int[][] matrix = new int[size][size]; //create a matrix with random numbers
        for( int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                int randNum = rand.nextInt(10); //random numbers are between 0-9
                matrix[i][j] = randNum;
            }
        }
        RotateMatrix.printMatrix(matrix); //print before matrix
        RotateMatrix.rotate(matrix);
        System.out.print("\n");
        RotateMatrix.printMatrix(matrix); //print after matrix
    }
}
