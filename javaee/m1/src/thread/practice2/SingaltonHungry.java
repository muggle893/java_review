package thread.practice2;

public class SingaltonHungry {
    private static SingaltonHungry obj = new SingaltonHungry();
    private SingaltonHungry() {

    }
    public static SingaltonHungry getInstance() {
       return obj;
    }
}
