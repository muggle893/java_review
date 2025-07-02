package code_2025_7_2;

public class _1静态内部类 {
    static class Student extends Person{
        private String stuCardId = "";
        public void study() {
            System.out.println("This student is studying.");
        }
    }
}
class Test{
    public static void main(String[] args) {
        _1静态内部类.Student student = new _1静态内部类.Student();
        student.study();
    }
}