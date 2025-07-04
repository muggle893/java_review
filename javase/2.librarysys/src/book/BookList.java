package book;

import book.Book;

import java.util.Arrays;

public class BookList {
    private Book[] list;
    private int usedSize;

    public int getUsedSize() {
        return usedSize;
    }

    public void setUsedSize(int usedSize) {
        this.usedSize = usedSize;
    }

    public BookList() {
        this.list = new Book[]{
                new Book("三国演义", "罗贯中", 10, "小说"),
                new Book("西游记", "吴承恩", 9, "小说"),
                new Book("红楼梦", "曹雪芹", 19, "小说")
        };
        this.usedSize = 3;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.list);
    }

    public Book[] getList() {
        return list;
    }

    public void setList(Book[] list) {
        this.list = list;
    }
}
