package binaryheaparray;

public class BinaryHeap {
    int arr[];
    int sizeOfTree;
    private final static String MIN_HEAP = "min";
    private final static String MAX_HEAP = "max";

    public BinaryHeap(int size) {
        arr = new int[size + 1];
        this.sizeOfTree = 0;
    }

    public boolean isEmpty() {
        return sizeOfTree == 0;
    }

    public boolean isFull() {
        return sizeOfTree >= arr.length - 1;
    }

    public Integer peek() {
        return !isEmpty() ? arr[1] : null;
    }

    public int sizeOfBP() {
        return sizeOfTree;
    }

    public void levelOrder() {
        for (int i = 1; i <= sizeOfTree; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Heapify for insert - O(LogN)
    public void heapifyBottomToTop(int index, String heapType) {
        if (index <= 1)
            return;
        int parent = index / 2; // 2x = index => index / 2

        switch (heapType) {
            case MIN_HEAP:
                if (arr[index] < arr[parent])
                    swap(arr, index, parent);
                break;

            case MAX_HEAP:
                if (arr[index] > arr[parent])
                    swap(arr, index, parent);
                break;
        }

        heapifyBottomToTop(parent, heapType);
    }

    private void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public void insert(int value, String heapType) {
        if (isFull())
            return;
        arr[sizeOfTree + 1] = value;
        sizeOfTree++;
        heapifyBottomToTop(sizeOfTree, heapType); // LogN
    }

    // heapifyTopToBottom - chưa hiểu não đang bão hòa :>
    // xem lại video khúc này nhiều lần
    private void heapifyTopToBottom(int index, String heapType) {
        int left = index * 2;
        int right = index * 2 + 1;
        int swapChild = 0; // cho nó mặc định là bao nhiêu đó
        if (sizeOfTree < left) // no child
            return;

        switch (heapType) {
            case MAX_HEAP:
                if (sizeOfTree == left) { // one child
                    if (arr[index] < arr[left])
                        swap(arr, index, left);
                    return;
                } else { // two children
                    if (arr[left] > arr[right])
                        swapChild = left;
                    else
                        swapChild = right;

                    if (arr[index] < arr[swapChild])
                        swap(arr, index, swapChild);
                }
                break;

            case MIN_HEAP:
                if (sizeOfTree == left) {
                    if (arr[index] > arr[left])
                        swap(arr, index, left);
                    return;
                } else {
                    if (arr[left] < arr[right])
                        swapChild = left;
                    else
                        swapChild = right;

                    if (arr[index] > arr[swapChild])
                        swap(arr, index, swapChild);
                }
                break;
        }

        heapifyTopToBottom(swapChild, heapType);
    }

    public int extractHeadOfBP(String heapType) {
        if (isEmpty())
            return -1;
        int extractedValue = arr[1];
        arr[1] = arr[sizeOfTree--];
        heapifyTopToBottom(1, heapType);

        return extractedValue;
    }
}
