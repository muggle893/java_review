package thread.practice2;

public class SingaltonLazy {
    private static volatile SingaltonLazy obj;
    private SingaltonLazy() {

    }
    public static SingaltonLazy getInstance() {
        if (obj == null) {
            synchronized (SingaltonLazy.class) {
                if (obj == null) {
                    obj = new SingaltonLazy();
                }
            }
        }
        return obj;
    }
}
