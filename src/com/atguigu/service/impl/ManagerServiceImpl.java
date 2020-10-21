package com.atguigu.service.impl;

import com.atguigu.dao.impl.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/16 23:46
 * @Description:
 */
public class ManagerServiceImpl implements ManagerService {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public int deleteBook(Integer id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public int modifyBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryAllBooks() {
        return bookDao.queryAllBooks();
    }

    /**
     * 执行分页操作
     */
    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<>();
        //总书数目
        Integer count = bookDao.count();
        List<Book> books = bookDao.pageItems(pageNo, pageSize);
        Integer pageTotal = count % pageSize > 0 ? count/pageSize+1:count/pageSize;
        page.setCount(count);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setPageTotal(pageTotal);
        page.setItems(books);
        return page;
    }

    @Override
    public Page<Book> pagePrice(Integer pageNo, Integer pageSize,Integer min, Integer max) {
        List<Book> books = bookDao.pageSelonPrice(pageNo,pageSize,min, max);
        Integer count = bookDao.pagePriceCount(min, max);
        Page<Book> page = new Page<>();
        page.setItems(books);
        page.setCount(count);
        page.setPageSize(pageSize);
        page.setPageNo(pageNo);
        Integer pageTotal = count % pageSize !=0? count/pageSize+1 :count/pageSize;
        page.setPageTotal(pageTotal);
        return page;
    }
}
