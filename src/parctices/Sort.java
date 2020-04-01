package parctices;

public class Sort {
    /*
    Merge Sort
     */

    public int[] mergeSort(int[] array){
        if(array == null||array.length <=1){
            return array;
        }
        mergeSortHelper(array, 0, array.length - 1);
        return array;
    }

    private void mergeSortHelper(int[] array, int left, int right){
        if(right <=left){
            return;
        }

        int mid = left + (right - left) /2;
        mergeSortHelper(array, left, mid);
        mergeSortHelper(array, mid+1, right);

        // k is the index of new order array
        // l and right are the left and right parts of original unsorted array index.
        int k = left;
        int l = left;
        int r = mid + 1;
        int[] helperArray = new int[array.length];
        for (int i = 0; i <= right; i++) {
            helperArray[i]=array[i];
        }
        while(l <= mid && r <= right){
            if(helperArray[l]<= helperArray[r]){
                array[k] = helperArray[l];
                l++;
            }else{
                array[k] = helperArray[r];
                r++;
            }
            k++;
        }

        while(l <= mid){
            array[k] = helperArray[l];
            k++;
            l++;
        }
    }

    /*
    Quick sort
     */
    public int[] quickSort(int[] array){
        if(array == null || array.length <= 1){
            return array;
        }
        quickSortHelper(array, 0, array.length - 1);
        return array;
    }

    private void quickSortHelper(int[] array, int left, int right){
        if(left >= right){
            return;
        }

        int target = array[right];
        int r = right - 1;
        int l = left;
        while(l <= r){
            if(array[l] <= target){
                l++;
            }else if(array[r] > target){
                r++;
            }else{
                arraySwap(array, l, r);
                l++;
                r++;
            }
            arraySwap(array, right, l);
        }

        quickSortHelper(array, left, l - 1);
        quickSortHelper(array, r + 1, right);

    }






    private void arraySwap(int[] array, int left, int right){
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
