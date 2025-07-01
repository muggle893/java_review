package code_2025_7_1;

public class Robot implements IRun{
    @Override
    public void run() {
        System.out.println("机器人正在跑...");
    }

    public static void main(String[] args) {
        new Robot().run();
    }
}
