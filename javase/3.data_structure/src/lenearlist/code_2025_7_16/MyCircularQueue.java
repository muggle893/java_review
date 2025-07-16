package lenearlist.code_2025_7_16;

public class MyCircularQueue {
    private int[] elems;
    private int front;
    private int rear;
    public MyCircularQueue(int k) {
        this.elems = new int[k + 1];
        this.front = 0;
        this.rear = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        elems[rear] = value;
        rear = (rear + 1) % elems.length;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % elems.length;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return elems[front];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        int pos = (rear - 1 + elems.length) % elems.length;
        return elems[pos];
    }

    public boolean isEmpty() {
        if (rear == front) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if ((rear + 1) % elems.length == front) {
            return true;
        }
        return false;
    }
}
