package org.txf.book_management.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.txf.book_management.model.Book;

@SpringBootTest
class BookMapperTest {

    @Autowired
    BookMapper bookMapper;

    @Test
    void selectAll() {
        // 测试查询数据库中所有的图书
        bookMapper.selectAll().forEach(System.out::println);
    }

    @Test
    void testSelectAll() {
        // 测试查询数据库中所有的图书
        bookMapper.selectAll().forEach(System.out::println);
    }

    @Test
    void selectByPage() {
        // 查询第一页的数据测试一下
        bookMapper.selectByPage(0, 10).forEach(System.out::println);
    }

    @Test
    void getBookCount() {
        bookMapper.getBookCount();
    }

    @Test
    void addBook() {
        Book book = new Book();
        book.setBookName("《123木头人》");
        book.setAuthor("无名人");
        book.setPublish("...");
        book.setPrice(0.0);
        book.setCount(1);
        bookMapper.addBook(book);

    }
}