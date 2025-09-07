package thread.practice3;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadPool {
    private volatile int curSize;
    private int baseSize;
    private int maxSize;
    private Object sizeLocker = new Object();
    private CopyOnWriteArrayList<Thread> extraThread;
    private BlockingQueue<Runnable> blockingQueue;
    public MyThreadPool(int threadSize, int maxSize) {
        this.baseSize = threadSize;
        this.maxSize = maxSize;
        curSize = baseSize;
        this.blockingQueue = new LinkedBlockingQueue<>();
        for (int i = 0; i < threadSize; i++) {
            Thread t = new Thread(()->{
                while (true) {
                    try {
                        Runnable task = this.blockingQueue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }

        // 增加回收线程逻辑
        extraThread =  new CopyOnWriteArrayList<Thread>();
        Thread t = new Thread(()->{
            // 每隔3秒判断是否需要回收多余的线程
            while (true) {
                int taskSz = blockingQueue.size();
                int extraThreadSz = extraThread.size();
                if (extraThread.size() > 0 && taskSz < 2000) {
                    // 回收
                    for (int i = 0; i < extraThread.size(); i++) {
                        extraThread.get(i).destroy();
                    }
                    synchronized (sizeLocker) {
                        curSize -= extraThreadSz;
                    }
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
    public void submit(Runnable task) {
        synchronized (sizeLocker) {
            if (blockingQueue.size() >= 2000 && curSize <= baseSize) {
                for (int i = 0; i < maxSize - baseSize; i++) {
                    Thread t = new Thread(()->{
                        while (true) {
                            try {
                                Runnable job = this.blockingQueue.take();
                                job.run();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    extraThread.add(t);
                    t.start();
                }
                curSize = maxSize;
            }
        }
        this.blockingQueue.add(task);
    }
}
