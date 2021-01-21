import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Joseph Cantrell
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Cannot pass in a null"
                    + " array or comparator.");
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int swaps = 0;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) >= 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                }
            }
            if (swaps == 0) {
                i = arr.length;
            }
        }
    }
    
    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *for i from 1 to N
     key = a[i]
     j = i - 1
     while j >= 0 and a[j] > key
     a[j+1] = a[j]
     j = j - 1
     a[j+1] = key
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Cannot pass in a null"
                    + " array or comparator.");
        }
        for (int i = 0; i < arr.length; i++) {
            T insert = arr[i];
            int j = i - 1;
            //backtrack across the array
            while (j >= 0 && comparator.compare(arr[j], insert) >= 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = insert;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Cannot pass in a null"
                    + " array or comparator.");
        }
        for (int i = 0; i < arr.length - 1; i++) {
            T element = arr[i];
            int smallest = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[smallest]) <= 0) {
                    smallest = j;
                }
            }
            T temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;
        }
    }


    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     * 
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Cannot pass in a null"
                    + " array or comparator or Random object.");
        }
        int pivot = rand.nextInt(arr.length);
        arr = quickSortHelper(arr, comparator, 0, pivot);
        arr = quickSortHelper(arr, comparator, pivot, arr.length - 1);
    }

    /**
     * A recursive helper method for quickSort.
     * @param arr The array to sort
     * @param comparator The object used to make comparisons
     * @param l The left bound of arr's subarray
     * @param r The right bound of arr's subarray
     * @param <T> The data type to sort
     * @return The array with a sorted subarray
     */
    private static <T> T[] quickSortHelper(T[] arr, Comparator<T> comparator,
                                           int l, int r) {
        int left = l;
        int right = r - 1;
        if (r <= l) {
            return arr;
        }
        T pivot = arr[r];
        T temp;
        while (left <= right) {
            while (left <= right
                    && comparator.compare(arr[left], pivot) <= 0) {
                left++;
            }
            while (left <= right
                    && comparator.compare(arr[right], pivot) >= 0) {
                right--;
            }
            if (left <= right) {
                temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        temp = arr[left];
        arr[left] = arr[r];
        arr[r] = temp;
        quickSortHelper(arr, comparator, l, left - 1);
        quickSortHelper(arr, comparator, left + 1, r);
        return arr;
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Cannot pass in a"
                    + " null array or comparator.");
        }
        arr = mergeSortHelper(arr, comparator);

    }

    /**
     * A recursive mergeSort helper method.
     * @param arr The array to sort
     * @param comparator An object for making comparisons
     * @param <T> The data type to sort
     * @return The sorted array
     */
    private static <T> T[] mergeSortHelper(T[] arr, Comparator<T> comparator) {
        T[] left = (T[]) new Object[arr.length / 2 + 1];
        T[] right = (T[]) new Object[arr.length / 2 + 1];
        T[] result = (T[]) new Object[arr.length];
        //terminate recursion when the array length is 1
        if (left.length <= 2 || right.length <= 2) {
            return arr;
        } else {
            //divide arr into two subarrays
            int middle = arr.length / 2;
            for (int i = 0; i < middle; i++) {
                left[i] = arr[i];
            }
            for (int i = middle; i < arr.length - 1; i++) {
                right[i - middle] = arr[i];
            }
            //sort the left and right subarrays
            left = mergeSortHelper(left, comparator);
            right = mergeSortHelper(right, comparator);
            //merge the two sorted arrays
            result = merge(left, right, comparator);
            return result;
        }
    }

    /**
     * Merge two sorted arrays together
     * @param left The left array
     * @param right THe right array
     * @param comparator An object for making comparisons
     * @param <T> The data to to sort
     * @return The sorted, merged left and right arrays
     */
    private static <T> T[] merge(T[] left, T[] right,
                                 Comparator<T> comparator) {
        T[] result = (T[]) new Object[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;
        //compare elements from both arrays sequentially
        while (i < left.length && j < right.length) {
            if (left[i] != null && right[i] != null
                    && comparator.compare(left[i], right[i]) <= 0) {
                result[k] = left[i];
                i++;
            } else {
                result[k] = right[j];
                j++;
            }
            k++;
        }
        //add the remaining elements in the left array, if any
        while (i < left.length) {
            result[k] = left[i];
            i++;
            k++;
        }
        //add the remaining elements int the right array, if any
        while (j < right.length) {
            result[k] = right[j];
            j++;
            k++;
        }
        return result;
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort a null array.");
        }
        return lsdRadixSortHelper(arr);
    }

    /**
     * A helper method for LSD Radix Sorting
     * @param arr The array to be sorted
     * @return The sorted array
     */
    private static int[] lsdRadixSortHelper(int[] arr) {
        //find maximum number
        int max = arr[0];
        int temp;
        int digits = 0;
        LinkedList<Integer>[] buckets
                = (LinkedList<Integer>[]) new LinkedList<?>[10];
        for (int i = 0; i < 10; i++) {
            buckets[i] = new LinkedList<Integer>();
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //find number of digits
        temp = max;
        while (temp != 0) {
            temp /= 10;
            digits++;
        }
        //sort by least significant digit
        //make passes equal to the number of digits
        for (int i = 0; i < digits; i++) {
            //place into buckets
            for (int j = 0; j < arr.length; j++) {
                int digit;
                temp = arr[j];
                for (int k = i; k > 0; k--) {
                    temp /= 10;
                }
                digit = temp % 10;
                buckets[digit].add((Integer) arr[j]);
            }
            //grab from buckets for another pass
            int counter = 0;
            for (int j = 0; j < 10; j++) {
                if (buckets[j].size() != 0) {
                    arr[counter] = (int) buckets[j].pollFirst();
                    counter++;
                }
            }
        }
        return arr;
    }
    
    /**
     * Implement MSD (most significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should:
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Do NOT use {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] msdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort a null array.");
        }
        return msdRadixSortHelper(arr);
    }

    /**
     * A helper method for MSD Radix Sorting
     * @param arr The array to be sorted
     * @return The sorted array
     */
    private static int[] msdRadixSortHelper(int[] arr) {
        //find maximum number
        int max = arr[0];
        int temp;
        int digits = 0;
        LinkedList<Integer>[] buckets
                = (LinkedList<Integer>[]) new LinkedList<?>[10];
        for (int i = 0; i < 10; i++) {
            buckets[i] = new LinkedList<Integer>();
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //find number of digits
        temp = max;
        while (temp != 0) {
            temp /= 10;
            digits++;
        }
        //sort by most significant digit
        //make passes equal to the number of digits
        for (int i = digits; i > 0; i--) {
            //place into buckets
            //iterate through the array
            for (int j = 0; j < arr.length; j++) {
                int digit;
                temp = arr[j];
                //reduce number to most significant digit
                for (int k = i; k > 0; k--) {
                    temp = arr[j] / 10;
                }
                digit = temp % 10;
                buckets[digit].add((Integer) arr[j]);
            }
            //grab from buckets for another pass
            int counter = 0;
            for (int j = 0; j < 10; j++) {
                if (buckets[j].size() != 0) {
                    arr[counter] = (int) buckets[j].pollFirst();
                    counter++;
                }
            }
        }
        return arr;
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sorts instead of {@code Math.pow()}.
     * 
     * DO NOT MODIFY THIS METHOD.
     *
     * @throws IllegalArgumentException if both {@code base} and {@code exp} are
     * 0
     * @throws IllegalArgumentException if {@code exp} is negative
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, (exp / 2) + 1);
        }
    }
}
