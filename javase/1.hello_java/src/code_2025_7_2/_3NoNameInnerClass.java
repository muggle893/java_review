package code_2025_7_2;

public class _3NoNameInnerClass {
    public static void main(String[] args) {
        new Animal(){
            @Override
            public void peek() {
                System.out.println("正在观察...");
            }
        }.peek();
    }
}
