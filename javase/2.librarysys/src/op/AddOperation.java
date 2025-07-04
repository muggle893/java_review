package op;

import book.Book;
import book.BookList;

import java.util.Arrays;
import java.util.Scanner;

public class AddOperation implements Operation{
    @Override
    public void work(BookList bookList) {
        Scanner scanner = new Scanner(System.in);
        Book book = new Book();
        System.out.println("请输入书名：");
        book.setName(scanner.nextLine());
        System.out.println("请输入作者：");
        book.setAuthor(scanner.nextLine());
        System.out.println("请输入价格：");
        book.setPrice(scanner.nextFloat());
        scanner.nextLine(); // 处理回车
        System.out.println("请输入类型：");
        book.setType(scanner.nextLine());

        // 判断是否存在此书
        for (Book b : bookList.getList()) {
            if (book.getName().equals(b.getName())) {
                System.out.println("已经存在此书!");
                return;
            }
        }

        // 添加前先扩容
        if (bookList.getList().length == bookList.getUsedSize()) {
            Book[] books = Arrays.copyOf(bookList.getList(), bookList.getUsedSize()*2);
            bookList.setList(books);
        }
        bookList.getList()[bookList.getUsedSize()] = book;
    }
}
