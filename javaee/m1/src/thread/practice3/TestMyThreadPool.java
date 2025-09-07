package thread.practice3;

public class TestMyThreadPool {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(5, 10);
        for (int i = 0; i < 1000; i++) {
            int k = i;
            myThreadPool.submit(()->{
                for (int j = 0; j < 1000; j++) {
                    System.out.println("第" + k + "个任务" + "j = " + j);
                }
            });
        }
    }
}
