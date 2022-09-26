package binaryheaparray;

public class Main {
    public static void main(String[] args) {
        MyBinaryHeap bh = new MyBinaryHeap(5);
        bh.insert(5, "max");
        bh.insert(10, "max");
        bh.insert(40, "max");
        bh.insert(50, "max");
        bh.levelOrder();

        System.out.println(bh.extractHeadOfBP("max"));
        bh.levelOrder();
    }
}
