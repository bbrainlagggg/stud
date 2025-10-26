import java.util.Arrays;

public class Main {

    //  СОРТИРОВКИ 

    public static void selectMinSort(int[] arr) {
        int n  arr.length;
        for (int left  0; left < n - 1; left++) {
            int minPos  left;
            for (int k  left + 1; k < n; k++) {
                if (arr[k] < arr[minPos]) {
                    minPos  k;
                }
            }
            int temp  arr[left];
            arr[left]  arr[minPos];
            arr[minPos]  temp;
        }
    }

    public static void demoSelectMinSort() {
        int[] data  {64, 25, 12, 22, 11};
        System.out.println("Сортировка выбором:");
        show(data);
        selectMinSort(data);
        System.out.println("После сортировки:");
        show(data);
    }

    public static void bubbleSortFast(int[] arr) {
        int n  arr.length;
        for (int pass  0; pass < n - 1; pass++) {
            boolean swapped  false;
            for (int i  0; i < n - pass - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int tmp  arr[i];
                    arr[i]  arr[i + 1];
                    arr[i + 1]  tmp;
                    swapped  true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void demoBubbleSortFast() {
        int[] arr  {64, 34, 25, 12, 22, 11};
        System.out.println("\nСортировка пузырьком:");
        show(arr);
        bubbleSortFast(arr);
        System.out.println("После сортировки:");
        show(arr);
    }

    public static void insertSort(int[] nums) {
        for (int i  1; i < nums.length; i++) {
            int cur  nums[i];
            int j  i - 1;
            while (j > 0 && nums[j] > cur) {
                nums[j + 1]  nums[j];
                j--;
            }
            nums[j + 1]  cur;
        }
    }

    public static void demoInsertSort() {
        int[] sample  {12, 11, 13, 5, 6};
        System.out.println("\nСортировка вставками:");
        show(sample);
        insertSort(sample);
        System.out.println("После сортировки:");
        show(sample);
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length < 1) return arr;
        int mid  arr.length / 2;
        int[] left  mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] right  mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
        return mergeParts(left, right);
    }

    private static int[] mergeParts(int[] a, int[] b) {
        int[] merged  new int[a.length + b.length];
        int i  0, j  0, k  0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) merged[k++]  a[i++];
            else merged[k++]  b[j++];
        }
        while (i < a.length) merged[k++]  a[i++];
        while (j < b.length) merged[k++]  b[j++];
        return merged;
    }

    public static void demoMergeSort() {
        int[] src  {38, 27, 43, 3, 9, 82, 10};
        System.out.println("\nСортировка слиянием:");
        show(src);
        int[] sorted  mergeSort(src);
        System.out.println("После сортировки:");
        show(sorted);
    }

    public static void shellSort(int[] arr) {
        int n  arr.length;
        for (int gap  n / 2; gap > 0; gap / 2) {
            for (int i  gap; i < n; i++) {
                int temp  arr[i];
                int j  i;
                while (j > gap && arr[j - gap] > temp) {
                    arr[j]  arr[j - gap];
                    j - gap;
                }
                arr[j]  temp;
            }
        }
    }

    public static void demoShellSort() {
        int[] nums  {23, 12, 1, 8, 34, 56, 7};
        System.out.println("\nСортировка Шелла:");
        show(nums);
        shellSort(nums);
        System.out.println("После сортировки:");
        show(nums);
    }

    public static void quickSort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int p  partition(arr, lo, hi);
            quickSort(arr, lo, p - 1);
            quickSort(arr, p + 1, hi);
        }
    }

    private static int partition(int[] arr, int lo, int hi) {
        int pivot  arr[hi];
        int i  lo - 1;
        for (int j  lo; j < hi; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp  arr[i];
                arr[i]  arr[j];
                arr[j]  temp;
            }
        }
        int temp  arr[i + 1];
        arr[i + 1]  arr[hi];
        arr[hi]  temp;
        return i + 1;
    }

    public static void demoQuickSort() {
        int[] data  {10, 7, 8, 9, 1, 5};
        System.out.println("\nБыстрая сортировка:");
        show(data);
        quickSort(data, 0, data.length - 1);
        System.out.println("После сортировки:");
        show(data);
    }

    public static void heapSort(int[] arr) {
        int n  arr.length;
        for (int i  n / 2 - 1; i > 0; i--) siftDown(arr, n, i);
        for (int end  n - 1; end > 0; end--) {
            int temp  arr[0];
            arr[0]  arr[end];
            arr[end]  temp;
            siftDown(arr, end, 0);
        }
    }

    private static void siftDown(int[] h, int size, int root) {
        int largest  root;
        int left  2 * root + 1;
        int right  2 * root + 2;
        if (left < size && h[left] > h[largest]) largest  left;
        if (right < size && h[right] > h[largest]) largest  right;
        if (largest ! root) {
            int temp  h[root];
            h[root]  h[largest];
            h[largest]  temp;
            siftDown(h, size, largest);
        }
    }

    public static void demoHeapSort() {
        int[] nums  {12, 11, 13, 5, 6, 7};
        System.out.println("\nПирамидальная сортировка:");
        show(nums);
        heapSort(nums);
        System.out.println("После сортировки:");
        show(nums);
    }

    //  ПОИСК 

    public static int findLinear(int[] arr, int key) {
        for (int i  0; i < arr.length; i++)
            if (arr[i]  key) return i;
        return -1;
    }

    public static void demoFindLinear() {
        int[] arr  {3, 8, 1, 10, 5};
        int key  10;
        int pos  findLinear(arr, key);
        if (pos ! -1)
            System.out.println("Элемент " + key + " найден на позиции " + pos);
        else
            System.out.println("Элемент " + key + " отсутствует");
    }

    public static int findBinary(int[] arr, int key) {
        int lo  0, hi  arr.length - 1;
        while (lo < hi) {
            int mid  lo + (hi - lo) / 2;
            if (arr[mid]  key) return mid;
            if (arr[mid] < key) lo  mid + 1;
            else hi  mid - 1;
        }
        return -1;
    }

    public static void demoFindBinary() {
        int[] ordered  {1, 3, 5, 7, 9, 11};
        int key  7;
        int pos  findBinary(ordered, key);
        if (pos ! -1)
            System.out.println("Значение " + key + " находится по индексу " + pos);
        else
            System.out.println("Значение " + key + " не найдено");
    }

    public static int searchInterpolation(int[] arr, int key) {
        int low  0, high  arr.length - 1;
        while (low < high && key > arr[low] && key < arr[high]) {
            if (low  high)
                return arr[low]  key ? low : -1;
            int pos  low + (key - arr[low]) * (high - low) / (arr[high] - arr[low]);
            if (arr[pos]  key) return pos;
            if (arr[pos] < key) low  pos + 1;
            else high  pos - 1;
        }
        return -1;
    }

    public static void demoSearchInterpolation() {
        int[] ordered  {10, 15, 20, 25, 30, 35, 40, 45, 50};
        int key  35;
        int pos  searchInterpolation(ordered, key);
        if (pos ! -1)
            System.out.println("Значение " + key + " найдено по индексу " + pos);
        else
            System.out.println("Значение " + key + " не найдено");
    }

    public static int searchFibonacci(int[] arr, int key) {
        int n  arr.length;
        int fm2  0, fm1  1, fm  fm2 + fm1;
        while (fm < n) {
            fm2  fm1;
            fm1  fm;
            fm  fm2 + fm1;
        }
        int offset  -1;
        while (fm > 1) {
            int i  Math.min(offset + fm2, n - 1);
            if (arr[i] < key) {
                fm  fm1;
                fm1  fm2;
                fm2  fm - fm1;
                offset  i;
            } else if (arr[i] > key) {
                fm  fm2;
                fm1  fm1 - fm2;
                fm2  fm - fm1;
            } else {
                return i;
            }
        }
        if (fm1  1 && offset + 1 < n && arr[offset + 1]  key)
            return offset + 1;
        return -1;
    }

    public static void demoSearchFibonacci() {
        int[] ordered  {10, 22, 35, 40, 45, 50, 80, 82, 85, 90, 100};
        int key  85;
        int pos  searchFibonacci(ordered, key);
        if (pos ! -1)
            System.out.println("Элемент " + key + " найден по индексу " + pos);
        else
            System.out.println("Элемент " + key + " не обнаружен");
    }

    //  ВСПОМОГАТЕЛЬНОЕ 
    public static void show(int[] arr) {
        for (int val : arr) System.out.print(val + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        demoSelectMinSort();
        demoBubbleSortFast();
        demoInsertSort();
        demoMergeSort();
        demoShellSort();
        demoQuickSort();
        demoHeapSort();
        demoFindLinear();
        demoFindBinary();
        demoSearchInterpolation();
        demoSearchFibonacci();
    }
}
