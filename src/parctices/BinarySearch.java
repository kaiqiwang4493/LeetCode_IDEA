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

    /*
    Two sorted integer arrays, how to find the k-th smallest element from them
    solution : binary search + recursion.
        recursion rule : find the k/2^n elements in each array. Ans compare the largest elements in each subarray.
        we can remove the subarray with the smaller one.
        base case: whene the k/2^n == 0, we return the larger one.
     */

    public int kthLargest(int K, int[] array1, int[] array2){
        if(array1 == null && array2 == null || (array1.length == 0 && array2.length == 0)){
            return 0;
        }
        if(array1==null || array1.length == 0){
            return array2[K - 1];
        }
        if(array2 == null || array2.length == 0){
            return array1[K - 1];
        }

        return kthLargestHelper(array1, 0, array2, 0, K);
    }

    private int kthLargestHelper(int[] a, int aleft, int[] b, int bleft, int k){
        if(aleft >= a.length){
            return b[bleft + k - 1];
        }
        if(bleft >= b.length){
            return a[aleft +k - 1];
        }
        if(k == 1){
            return Math.min(a[aleft],b[bleft]);
        }

        int amid = aleft + k / 2 -1;
        int bmid = bleft + k / 2 -1;

        int aval = amid > a.length? Integer.MAX_VALUE : a[amid];
        int bval = bmid > b.length? Integer.MAX_VALUE : b[bmid];

        if(aval <= bval){
            return kthLargestHelper(a, amid + 1, b, bleft, k - k /2);
        }else{
            return kthLargestHelper(a, aleft, b, bmid + 1, k - k / 2);
        }
    }

    /*
    fins the top K elements in an array that are closest to a target number?


     */


}
