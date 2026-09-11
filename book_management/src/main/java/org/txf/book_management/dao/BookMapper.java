package org.txf.book_management.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.txf.book_management.model.Book;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> selectAll();

    List<Book> selectByPage(@Param("offset") int start, @Param("limit") int limit);

    long getBookCount();

    int addBook(Book book);

    int updateBook(Book book);

    Book getBookById(int id);
}
