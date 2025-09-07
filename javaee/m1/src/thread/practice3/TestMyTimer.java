package thread.practice3;

public class TestMyTimer {
    public static void main(String[] args) {
        MyTimer myTimer = new MyTimer();
        myTimer.schedule(()->{
            System.out.println("哈哈哈1");
        }, 1000);
        myTimer.schedule(()->{
            System.out.println("哈哈哈2");
        }, 2000);
        myTimer.schedule(()->{
            System.out.println("哈哈哈3");
        }, 3000);
    }
}
