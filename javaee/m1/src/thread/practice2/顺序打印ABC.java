package thread.practice2;

public class 顺序打印ABC {
    public static Object lock = new Object();
    public static boolean turnA = true;
    public static boolean turnB = false;
    public static boolean turnC = false;
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while (!turnA) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("A");
                    turnA = false;
                    turnB = true;
                    lock.notifyAll();
                }
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while (!turnB) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("B");
                    turnB = false;
                    turnC = true;
                    lock.notifyAll();
                }
            }
        });
        Thread t3 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while (!turnC) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("C");
                    turnC = false;
                    turnA = true;
                    lock.notifyAll();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
