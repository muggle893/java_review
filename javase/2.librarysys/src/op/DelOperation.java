package op;

import book.Book;
import book.BookList;

import java.util.Scanner;

public class DelOperation implements Operation{
    @Override
    public void work(BookList bookList) {
        System.out.println("请输入要删除的书的名字：");
        String name = new Scanner(System.in).nextLine();
        for (int i = 0; i < bookList.getList().length; i++) {
            Book b = bookList.getList()[i];
            if (b.getName().equals(name)) {
                System.out.println("删除成功!");
                bookList.setUsedSize(bookList.getUsedSize() - 1);
                for (int j = i; j < bookList.getUsedSize(); j++) {
                    bookList.getList()[j] = bookList.getList()[j + 1];
                }
                return;
            }
        }
        System.out.println("找不到此书!");
    }
}
