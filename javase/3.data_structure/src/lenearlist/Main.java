package lenearlist;

public class Main {
    public static void main(String[] args) {
        MyArraylist myArraylist = new MyArraylist();
        myArraylist.add(1);
        myArraylist.add(2);
        myArraylist.add(3);
        myArraylist.display();
        myArraylist.remove(2);
        myArraylist.display();
        int i = myArraylist.indexOf(3);
        System.out.println(i);
    }
}
