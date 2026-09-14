package thread.practice1;

public class _7顺序打印 {
    // 有三个线程，线程名称分别为：a，b，c。
    //每个线程打印自己的名称。
    //需要让他们同时启动，并按 c，b，a的顺序打印
    public static void main(String[] args) throws InterruptedException {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread tc = new Thread(()->{
            System.out.println(Thread.currentThread().getName());

        });
        Thread tb = new Thread(()->{
            try {
                tc.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });
        Thread ta = new Thread(()->{
            try {
                tb.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });
        ta.setName("线程a");
        tb.setName("线程b");
        tc.setName("线程c");
        tc.start();
        tb.start();
        ta.start();
    }
}
