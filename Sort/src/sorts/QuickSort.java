package sorts;

public class QuickSort {
/*
    static int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }


    // The main function that implements QuickSort()
    //  arr[] --> Array to be sorted,
   //  low  --> Starting index,
     // high  --> Ending index
   static public void sort(int arr[], int low, int high)
    {
        if (low < high)
        {
            // pi is partitioning index, arr[pi] is
            //now at right place
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }

    public static int maxValue(int[] sequence)
    {
        int maxValue = 0;
        for (int i = 0; i < sequence.length; i++)
            if (sequence[i] > maxValue)
                maxValue = sequence[i];
        return maxValue;
    }

    public static int minValue(int[] sequence)
    {
        int minValue = 0;
        for (int i = 0; i < sequence.length; i++)
            if (sequence[i] < minValue)
                minValue = sequence[i];
        return minValue;
    }
    //A utility function to print array of size n

    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    // Driver program
    public static void main(String args[])
    {
        int arr[] = {10, 7, 8, 9, 1, 5};
        int n = arr.length;

        QuickSort ob = new QuickSort();
        ob.sort(arr, 0, n-1);

        System.out.println("sorted array");
        printArray(arr);
    }
*/
private static final int INSERTION_SORT_THRESHOLD = 55;

    private static void insertionsort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo && a[j] < a[j - 1]; j--) {
                swap(a, j, j - 1);
            }
        }
    }

    // for reference
    public static void quicksort1Pivot(int[] A, int lo, int hi) {
        int length = hi - lo + 1;
        if (length < INSERTION_SORT_THRESHOLD) {
            if (length > 1)
                insertionsort(A, lo, hi);
            return;
        }
        int midpoint = (lo + hi) >>> 1;
        swap(A, lo, midpoint);

        int p = A[lo];
        int i = lo+1;
        int j = hi;
        while (i <= j) {
            while (A[i] < p && i <= j) i++;
            while (A[j] > p && i <= j) j--;
            if (i <= j) {
                swap(A, i, j);
                i++; j--;
            }
        }
        i--; j++;
        swap(A, i, lo);

        quicksort1Pivot(A, lo, i-1);
        quicksort1Pivot(A, i+1, hi);
    }


    public static void quicksort3PivotBasic(int[] A, int lo, int hi) {
        int length = hi - lo + 1;
        if (length < INSERTION_SORT_THRESHOLD) {
            if (length > 1)
                insertionsort(A, lo, hi);
            return;
        }

        int midpoint = (lo + hi) >>> 1;
        // insertion sort lo,mid,hi elements
        if (A[midpoint] < A[lo]) { int t = A[midpoint]; A[midpoint] = A[lo]; A[lo] = t; }
        if (A[hi] < A[midpoint]) { int t = A[hi]; A[hi] = A[midpoint]; A[midpoint] = t;
            if (t < A[lo]) { A[midpoint] = A[lo]; A[lo] = t; }
        }

        int p = A[lo];
        int q = A[midpoint];
        int r = A[hi];
        // p,q & r are now sorted, place them at a[lo], a[lo+1] & a[hi]
        swap(A, lo+1, midpoint);

        // Pointers a and b initially point to the first element of the array while c
        // and d initially point to the last element of the array.
        int a = lo + 2;
        int b = lo + 2;
        int c = hi - 1;
        int d = hi - 1;

        while (b <= c) {
            while (A[b] < q && b <= c) {
                if (A[b] < p) {
                    swap(A, a, b);
                    a++;
                }
                b++;
            }
            while (A[c] > q && b <= c) {
                if (A[c] > r) {
                    swap(A, c, d);
                    d--;
                }
                c--;
            }
            if (b <= c) {
                if (A[b] > r) {
                    if (A[c] < p) {
                        swap(A, b, a); swap(A, a, c);
                        a++;
                    } else {
                        swap(A, b, c);
                    }
                    swap(A, c, d);
                    b++; c--; d--;
                } else {
                    if (A[c] < p) {
                        swap(A, b, a); swap(A, a, c);
                        a++;
                    } else {
                        swap(A, b, c);
                    }
                    b++; c--;
                }
            }
        }
        // swap the pivots to their correct positions
        a--; b--; c++; d++;
        swap(A, lo + 1, a); swap(A, a, b);
        a--;
        swap(A, lo, a);
        swap(A, hi, d);

        quicksort3PivotBasic(A, lo, a-1);
        quicksort3PivotBasic(A, a+1, b-1);
        quicksort3PivotBasic(A, b+1, d-1);
        quicksort3PivotBasic(A, d+1, hi);
    }


    public static void quicksort3Pivot(int[] A, int lo, int hi) {
        int length = hi - lo + 1;
        if (length < INSERTION_SORT_THRESHOLD) {
            if (length > 1)
                insertionsort(A, lo, hi);
            return;
        }

        // approximation of length / 7
        int seventh = (length >> 3) + (length >> 6) + 1;

        int e4 = (lo + hi) >>> 1; // The midpoint
        int e3 = e4 - seventh;
        int e2 = e3 - seventh;
        int e1 = lo;
        int e5 = e4 + seventh;
        int e6 = e5 + seventh;
        int e7 = hi;

        // Sort using insertion sort
        if (A[e2] < A[e1]) { int t = A[e2]; A[e2] = A[e1]; A[e1] = t; }

        if (A[e3] < A[e2]) { int t = A[e3]; A[e3] = A[e2]; A[e2] = t;
            if (t < A[e1]) { A[e2] = A[e1]; A[e1] = t; }
        }
        if (A[e4] < A[e3]) { int t = A[e4]; A[e4] = A[e3]; A[e3] = t;
            if (t < A[e2]) { A[e3] = A[e2]; A[e2] = t;
                if (t < A[e1]) { A[e2] = A[e1]; A[e1] = t; }
            }
        }
        if (A[e5] < A[e4]) { int t = A[e5]; A[e5] = A[e4]; A[e4] = t;
            if (t < A[e3]) { A[e4] = A[e3]; A[e3] = t;
                if (t < A[e2]) { A[e3] = A[e2]; A[e2] = t;
                    if (t < A[e1]) { A[e2] = A[e1]; A[e1] = t; }
                }
            }
        }
        if (A[e6] < A[e5]) { int t = A[e6]; A[e6] = A[e5]; A[e5] = t;
            if (t < A[e4]) { A[e5] = A[e4]; A[e4] = t;
                if (t < A[e3]) { A[e4] = A[e3]; A[e3] = t;
                    if (t < A[e2]) { A[e3] = A[e2]; A[e2] = t;
                        if (t < A[e1]) { A[e2] = A[e1]; A[e1] = t; }
                    }
                }
            }
        }
        if (A[e7] < A[e6]) { int t = A[e7]; A[e7] = A[e6]; A[e6] = t;
            if (t < A[e5]) { A[e6] = A[e5]; A[e5] = t;
                if (t < A[e4]) { A[e5] = A[e4]; A[e4] = t;
                    if (t < A[e3]) { A[e4] = A[e3]; A[e3] = t;
                        if (t < A[e2]) { A[e3] = A[e2]; A[e2] = t;
                            if (t < A[e1]) { A[e2] = A[e1]; A[e1] = t; }
                        }
                    }
                }
            }
        }

        int p = A[e2];
        int q = A[e4];
        int r = A[e6];
        swap(A, lo, e2);
        swap(A, lo+1, e4);
        swap(A, hi, e6);
        // Pointers a and b initially point to the first element of the array while c
        // and d initially point to the last element of the array.
        int a = lo + 2;
        int b = lo + 2;
        int c = hi - 1;
        int d = hi - 1;

        while (b <= c) {
            while (A[b] < q && b <= c) {
                if (A[b] < p) {
                    swap(A, a, b);
                    a++;
                }
                b++;
            }
            while (A[c] > q && b <= c) {
                if (A[c] > r) {
                    swap(A, c, d);
                    d--;
                }
                c--;
            }
            if (b <= c) {
                if (A[b] > r) {
                    if (A[c] < p) {
                        swap(A, b, a); swap(A, a, c);
                        a++;
                    } else {
                        swap(A, b, c);
                    }
                    swap(A, c, d);
                    b++; c--; d--;
                } else {
                    if (A[c] < p) {
                        swap(A, b, a); swap(A, a, c);
                        a++;
                    } else {
                        swap(A, b, c);
                    }
                    b++; c--;
                }
            }
        }
        // swap the pivots to their correct positions
        a--; b--; c++; d++;
        swap(A, lo + 1, a); swap(A, a, b);
        a--;
        swap(A, lo, a);
        swap(A, hi, d);

        quicksort3Pivot(A, lo, a-1);
        quicksort3Pivot(A, a+1, b-1);
        quicksort3Pivot(A, b+1, d-1);
        quicksort3Pivot(A, d+1, hi);
    }

    private static final void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    private static boolean isSorted(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static int maxValue(int[] sequence)
    {
        int maxValue = 0;
        for (int i = 0; i < sequence.length; i++)
            if (sequence[i] > maxValue)
                maxValue = sequence[i];
        return maxValue;
    }

    public static int minValue(int[] sequence)
    {
        int minValue = 0;
        for (int i = 0; i < sequence.length; i++)
            if (sequence[i] < minValue)
                minValue = sequence[i];
        return minValue;
    }
    //Multi-pivot Quicksort
}


