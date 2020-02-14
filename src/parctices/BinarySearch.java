package parctices;

public class BinarySearch {

    /*
    Search In Sorted Matrix I
    Given a 2D matrix that contains integers only, which each row is sorted in an ascending order.
    The first element of next row is larger than (or equal to) the last element of previous row.

    solution : use the relationship between 1D index and 2D index to transfer 2D to 1D.
    Then use the regular Binary Search solution.
     */

    public int[] search(int[][] matrix, int target){
        int N = matrix.length;
        int M = matrix[0].length;
        //coner case
        if(matrix ==null){
            return null;
        }
        if(N == 0|| M == 0){
            return new int[]{-1, -1};
        }
        if(matrix[0][0] > target){
            return new int[]{-1,-1};
        }
        // right left and mid use the 1D index
        // row and column indicate the 2D index
        int right = N*M - 1;
        int left = 0;
        while(right > left){
            int mid = left + (right-left) / 2;
            int row = mid/M;
            int column = mid%M;
            if(matrix[row][column] == target){
                return new int[]{row, column};
            }else if(matrix[row][column]> target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

}
