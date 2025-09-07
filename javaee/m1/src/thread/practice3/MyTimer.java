package thread.practice3;

import java.util.PriorityQueue;
import java.util.TimerTask;

public class MyTimer {
    private PriorityQueue<MyTimerTask> priorityQueue = new PriorityQueue<>();
    private Object lock = new Object();
    public MyTimer()  {
        // 创建一个线程，然后从优先级队列中取任务
        Thread t = new Thread(() ->{
            while (true) {
                // 取任务，如果任务队列中没有任务就停下来等
                MyTimerTask task = null;
                synchronized (lock) {
                    if (priorityQueue.size() == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = priorityQueue.poll();
                }
                // 执行任务
                task.getTask().run();
            }
        });
        t.start();
    }

    public void schedule(Runnable job, long delay) {
        MyTimerTask myTimerTask = new MyTimerTask(job);
        myTimerTask.setExecuteTime(System.currentTimeMillis() + delay);
        synchronized (lock) {
            priorityQueue.offer(myTimerTask);
            lock.notify();
        }
    }
}
