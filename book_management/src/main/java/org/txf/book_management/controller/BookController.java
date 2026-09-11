package org.txf.book_management.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.txf.book_management.model.Book;
import org.txf.book_management.result.PageResult;
import org.txf.book_management.result.RequestResult;
import org.txf.book_management.service.BookService;
import org.txf.book_management.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @RequestMapping("/getBookById")
    public RequestResult<Book> getBookById(@RequestParam("bookId") Integer bookId) {
        // 图书id参数校验
        if (bookId == null || bookId < 1) {
            log.info("图书的为空或者<1!");
            return RequestResult.fail("图书id有误!!!");
        }

        // 查询图书
        Book book = bookService.getBookById(bookId);
        if (book == null) {
            log.info("查不到图书!!! 图书的id：" + bookId);
            return RequestResult.fail("查不到图书!!!");
        }
        return RequestResult.success(book);
    }

    @RequestMapping("/updateBook")
    public RequestResult updateBook(Book book) {
        // 参数校验
        if (book.getId() == null) {
            log.info("图书的id为空");
            return RequestResult.fail("图书的ID不能为空！");
        }
        if (!StringUtils.hasLength(book.getBookName())
                && !StringUtils.hasLength(book.getAuthor())
                && !StringUtils.hasLength(book.getPublish())
                && book.getPrice() == null
                && book.getCount() == null) {
            log.info("图书参数校验失败!!!");
            return RequestResult.fail("图书参数校验失败！");
        }

        // 更新数据，判断数据是否更新成功
        boolean updateSuccess = bookService.updateBook(book);
        if(!updateSuccess) {
            log.info("图书更新失败，id为：" + book.getId());
            return RequestResult.fail("图书信息更新失败！！");
        }
        // 返回统一对象
        return RequestResult.success();
    }

    @RequestMapping("/addBook")
    public RequestResult addBook(Book book) {
        // 参数校验
        if (!StringUtils.hasLength(book.getBookName())
        || !StringUtils.hasLength(book.getAuthor())
        || !StringUtils.hasLength(book.getPublish())
        || book.getPrice() == null
        || book.getCount() == null) {
            log.info("图书参数校验失败!!!");
            return RequestResult.fail("图书参数校验失败");
        }
        // 调用service接口
        log.info("调用添加图书的service接口！！！");
        boolean addSuccess = bookService.addBook(book);
        log.info("调用添加图书的service接口完成！！！");
        if (!addSuccess) {
            log.info("图书添加失败!!!");
            return RequestResult.fail("图书添加失败！");
        }
        // 判断是否添加成功
        return RequestResult.success("图书添加成功!");
    }

    @RequestMapping("/getall")
    public List<Book> getBookList() {
        // 调用service的接口
        // 返回获取到的图书列表
        return bookService.getBookList();
    }
    @RequestMapping("/getListByPage")
    public PageResult<Book> getListByPage(@RequestParam("currentPage")Integer page, @RequestParam("pageSize")Integer pageSize) {
        PageResult<Book> pageResult = new PageResult<>();
        // 校验参数
        if (page == null || pageSize == null) {
            pageResult.setCount(-1);
            return pageResult;
        }
        // 这里可以对一页的记录数量做限制，如果有需要的话，就是限制pageSize的大小
        // 这里跳过这个检查

        // 先去查对应这一页的图书记录
        // 再去查总记录数
        // 最后设置PageResult对象然后返回给前端
        // 页码的设置由service处理
        List<Book> listByPage = bookService.getListByPage(page, pageSize);
        long cnt = bookService.getBookCount();
        pageResult.setRecords(listByPage);
        pageResult.setCount(cnt);
        return pageResult;
    }
}
