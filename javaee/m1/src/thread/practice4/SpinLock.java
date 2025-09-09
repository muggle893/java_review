package thread.practice4;

public class SpinLock {
    private static Object lock = null;

    // CAS方法里不用实现因为是模拟CAS
    public boolean CAS(Object a, Object b, Object c){
        // 如果ab一样则交换ac，返回true
        // 如果ab不一样返回false
        return false;
    }
    public SpinLock() {

    }
    public void lock() {
        while (!CAS(lock, null, new Object())) {

        }
    }

    public void unLock() {
        // 假设修改lock操作是原子操作
        lock = null;
    }
}
