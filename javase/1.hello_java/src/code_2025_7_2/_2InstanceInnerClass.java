package code_2025_7_2;

public class _2InstanceInnerClass {
    private class Dog{
        public void bark() {
            System.out.println("狗在叫...");
        }
    }

    public static void main(String[] args) {
        _2InstanceInnerClass instanceInnerClass = new _2InstanceInnerClass();
        Dog dog = instanceInnerClass.new Dog();
        dog.bark();
    }
}
