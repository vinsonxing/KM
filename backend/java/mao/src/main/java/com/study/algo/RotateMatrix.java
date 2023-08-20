package com.study.algo;

//https://java2blog.com/rotate-matrix-by-90-degrees-in-java/
public class RotateMatrix {
    // Takes Matrix and Size of Matrix and performs Clockwise or Right Rotation by 90 degrees.
    static void rightRotate(int matrix[][], int n) {

        //At first we perform transpose of the matrix
        //by swapping elements of every i'th row with j'th column
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Then we reverse the elements of each row.
        for (int i = 0; i < n; i++) {
            // Logic to reverse each row i.e 1D Array.
            int low = 0;
            int high = n - 1;

            while (low < high) {
                int temp = matrix[i][low];
                matrix[i][low] = matrix[i][high];
                matrix[i][high] = temp;

                low++;
                high--;
            }
        }

        System.out.println("The Right Rotated Matrix is: ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
