package sorts;

public class HeapSort {

    static void heap(int arr[], int n, int i) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if(left<n && arr[left]>arr[largest]) {
            largest = left;
        }
        if(right<n && arr[right]>arr[largest]) {
            largest = right;
        }
        if(largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest]=swap;
            heap(arr,n,largest);
        }

    }

    public static void sort(int arr[]) {
        int n = arr.length;
        for(int i=n/2-1; i>=0; i--) {
            heap(arr,n,i);
        }
        for(int i=n-1; i>=0; i--) {
            int temp = arr[0];
            arr[0]=arr[i];
            arr[i]=temp;
            heap(arr,i,0);
        }
    }

    void printArray(int arr[]) {
        for(int i=0;i<arr.length;i++) {
            System.out.print(arr[i]+" ");
        }
    }
}
