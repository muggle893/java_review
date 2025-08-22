package thread.practice1;

public class _2创建线程的几种方法 {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("线程1正在运行");
        }
    }

    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println("线程2正在运行");
        }
    }
    public static void main(String[] args) {
        //继承 Thread, 重写 run
        //实现 Runnable, 重写 run
        //继承 Thread, 重写 run, 使用匿名内部类
        //实现 Runnable, 重写 run, 使用匿名内部类
        //使用 lambda 表达式
        Thread thread1 = new MyThread();
        Thread thread2 = new Thread(new MyRunnable());
        Thread thread3 = new Thread() {
            @Override
            public void run() {
                System.out.println("线程3正在运行");
            }
        };
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程4正在运行");
            }
        });
        Thread thread5 = new Thread(()->{
            System.out.println("线程5正在运行");
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
