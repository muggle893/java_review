package thread.practice2;

public class MyBlockingQueue {
    private int size;
    private int[] data;
    private int front;
    private int rear;

    public MyBlockingQueue(int size) {
        this.size = 0;
        this.data = new int[size];
        this.front = 0;
        this.rear = front;
    }

    public static void main(String[] args) {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(1000);
        Thread t1 = new Thread(()->{
            int i = 0;
            while (true) {
                try {
                    System.out.println("生产者生产:" + i);
                    myBlockingQueue.put(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        });
        Thread t2 = new Thread(()->{
            while (true) {
                try {
                    int res = myBlockingQueue.take();
                    System.out.println("消费者消费：" + res);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
    public void put(int elem) throws InterruptedException {
        synchronized (this) {
            if (size == data.length) {
                // 队列满，线程等待
                this.wait();
            }
            data[rear++] = elem;
            size++;
            if (rear == data.length) {
                rear = 0;
            }
            this.notify();
        }
    }
    public int take() throws InterruptedException {
        synchronized (this) {
            if (size == 0) {
                // 队列空，线程等待
                this.wait();
            }
            int res = data[front];
            front++;
            size--;
            if (front == data.length) {
                front = 0;
            }
            this.notify();
            return res;
        }
    }
}
