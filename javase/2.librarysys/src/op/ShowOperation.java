package op;

import book.BookList;

public class ShowOperation implements Operation{
    @Override
    public void work(BookList bookList) {
        System.out.println("显示图书操作!");
        for (int i = 0; i < bookList.getUsedSize(); i++) {
            System.out.println(bookList.getList()[i].toString());
        }
    }
}
