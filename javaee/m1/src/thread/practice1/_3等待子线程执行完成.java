package thread.practice1;

public class _3等待子线程执行完成 {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            final int tmp = i;
            Thread thread = new Thread(()->{
                System.out.println(tmp);
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ok");
    }
}
