package op;

import book.BookList;

public class ExitOperation implements Operation{
    @Override
    public void work(BookList bookList) {
        System.out.println("退出系统!");
        System.exit(0);
    }
}
