package org.txf.book_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.txf.book_management.dao.BookMapper;
import org.txf.book_management.model.Book;
import org.txf.book_management.result.BookStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookMapper bookMapper;
    public List<Book> getBookList() {
        return bookMapper.selectAll();
    }

    public List<Book> getListByPage(int page, int pageSize) {
        // 处理一下页码
        int offset = (page - 1) * pageSize;
        List<Book> bookList =  bookMapper.selectByPage(offset, pageSize);
        // 处理一下图书状态，将状态码转换成中文的状态
        // 状态无效的图书数据不返回
        List<Book> ret = new ArrayList<Book>();
        for (Book book : bookList) {
            if (book.getStatus() != 0) {
                book.setStatusCN(BookStatus.fromCode(book.getStatus()).getDescription());
                ret.add(book);
            }
        }
        return ret;
    }

    public boolean addBook(Book book) {
       int ret = bookMapper.addBook(book);
       return ret > 0;
    }

    public long getBookCount() {
        return bookMapper.getBookCount();
    }

    public boolean updateBook(Book book) {
        int ret = bookMapper.updateBook(book);
        return ret > 0;
    }

    public Book getBookById(int id) {
        Book book = bookMapper.getBookById(id);
        if (book != null && book.getStatus() != 0) {
            book.setStatusCN(BookStatus.fromCode(book.getStatus()).getDescription());
        }
        return book;
    }
}
