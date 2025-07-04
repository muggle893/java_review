package op;

import book.Book;
import book.BookList;

import java.util.Scanner;

public class SerachOperation implements Operation{
    @Override
    public void work(BookList bookList) {
        System.out.println("请输入要查找的书名：");
        String name = new Scanner(System.in).nextLine();

        for (int i = 0; i < bookList.getList().length; i++) {
            Book b = bookList.getList()[i];
            if (b.getName().equals(name)) {
                System.out.println("查找成功!");
                System.out.println(b.toString());
                return;
            }
        }
        System.out.println("此书不存在!");
    }
}
