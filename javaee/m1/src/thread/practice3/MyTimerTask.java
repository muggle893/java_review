package thread.practice3;

import java.util.Comparator;

public class MyTimerTask implements Comparable<MyTimerTask> {
    long executeTime;
    Runnable task;
    public MyTimerTask(Runnable task) {
        this.task = task;
        this.executeTime = System.currentTimeMillis();
    }

    public Runnable getTask() {
        return task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(long executeTime) {
        this.executeTime = executeTime;
    }

    @Override
    public int compareTo(MyTimerTask o) {
        return (int)(this.executeTime - o.executeTime);
    }
}
