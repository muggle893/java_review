package op;

import book.Book;
import book.BookList;

import java.util.Scanner;

public class BorrowOperation implements Operation{
    @Override
    public void work(BookList bookList) {
        System.out.println("请输入要借阅的书名：");
        String name = new Scanner(System.in).nextLine();

        for (int i = 0; i < bookList.getList().length; i++) {
            Book b = bookList.getList()[i];
            if (b.getName().equals(name) && !b.isBorrowed()) {
                System.out.println("借阅成功!");
                b.setBorrowed(true);
                return;
            } else if (b.getName().equals(name) && b.isBorrowed()) {
                System.out.println("这本书已经被借阅!");
            }
        }
        System.out.println("此书不存在!");
    }
}
