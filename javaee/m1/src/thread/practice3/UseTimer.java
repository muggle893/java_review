package thread.practice3;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class UseTimer {
    public static void main(String[] args) {
//        Timer timer = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("哈哈哈");
//            }
//        };
//        timer.schedule(timerTask, new Date(), 1000);
        Timer timer2 = new Timer();
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < 100000000; i++) {
                    System.out.println(i);
                }
            }
        };
        timer2.scheduleAtFixedRate(timerTask2, new Date(), 1000);
    }
}
