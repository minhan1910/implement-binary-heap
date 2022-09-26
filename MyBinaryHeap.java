package binaryheaparray;

public class MyBinaryHeap {
    int arr[];
    int sizeOfTree;
    private final static String MIN_HEAP = "min";
    private final static String MAX_HEAP = "max";

    public MyBinaryHeap(int size) {
        this.arr = new int[size + 1];
        this.sizeOfTree = 0;
    }

    public boolean isEmpty() {
        return sizeOfTree == 0;
    }

    public Integer peek() {
        return !isEmpty() ? arr[1] : null;
    }

    public int sizeOfBP() {
        return this.sizeOfTree;
    }

    public void levelOrder() {
        for (int i = 1; i <= sizeOfTree; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public boolean isFull() {
        return sizeOfTree >= arr.length - 1;
    }

    // insert, extractHeadOfBP
    private int getParent(int index) {
        return index / 2;
    }

    private int getLeft(int index) {
        return index * 2;
    }

    private int getRight(int index) {
        return index * 2 + 1;
    }

    private void swap(int elementA, int elementB) {
        int temp = arr[elementA];
        arr[elementA] = arr[elementB];
        arr[elementB] = temp;
    }

    public void heapifyBottomToTop(int index, String heapType) {
        if (index <= 1)
            return;

        int parent = getParent(index);
        if (index < 1)
            return;

        switch (heapType) {
            case MIN_HEAP:
                if (arr[index] < arr[parent])
                    swap(index, parent);
                break;

            case MAX_HEAP:
                if (arr[index] > arr[parent])
                    swap(index, parent);
                break;
        }

        heapifyBottomToTop(parent, heapType);
    }

    public void insert(int value, String heapType) {
        if (isFull())
            return;

        arr[sizeOfTree + 1] = value;
        sizeOfTree++;

        heapifyBottomToTop(sizeOfTree, heapType);
    }

    public void heapifyTopToBottom(int index, String heapType) {
        int left = getLeft(index);
        int right = getRight(index);
        int swapChild = 0;

        if (left > sizeOfTree)
            return;

        switch (heapType) {
            case MAX_HEAP:
                // one child
                if (left == sizeOfTree) {
                    if (arr[index] < arr[left])
                        swap(index, left);
                    return;
                } else {
                    // two children
                    if (arr[left] > arr[right]) // determine child of left or right will be swap
                        swapChild = left;
                    else
                        swapChild = right;

                    if (arr[index] < arr[swapChild])
                        swap(index, swapChild);
                }
                break;

            case MIN_HEAP:
                if (left == sizeOfTree) {
                    if (arr[index] > arr[left])
                        swap(index, left);
                    return;
                } else {
                    if (arr[left] < arr[right])
                        swapChild = left;
                    else
                        swapChild = right;

                    if (arr[index] > arr[swapChild])
                        swap(index, swapChild);
                }
                break;
        }

        heapifyTopToBottom(swapChild, heapType);
    }

    public int extractHeadOfBP(String heapType) {
        int extractedValue = arr[1];
        arr[1] = arr[sizeOfTree];
        --sizeOfTree;
        heapifyTopToBottom(1, heapType);

        return extractedValue;
    }
}
