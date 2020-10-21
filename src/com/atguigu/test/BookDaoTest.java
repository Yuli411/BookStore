package com.atguigu.test;

import com.atguigu.dao.impl.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/16 23:29
 * @Description:
 */
class BookDaoTest {

    @Test
    void deleteBookById() {
        BookDao bookDao = new BookDaoImpl();
        bookDao.deleteBookById(1);
    }

    @Test
    void addBook() {
        BookDao bookDao = new BookDaoImpl();
        bookDao.addBook(new Book(null,"国哥好帅",20.20,"191125",99999,200,null));
    }

    @Test
    void updateBook() {
        BookDao bookDao = new BookDaoImpl();
        bookDao.updateBook(new Book(21,"国哥好帅",30.2,"191125",99999,200,null));
    }

    @Test
    void queryBookById() {
        BookDao bookDao = new BookDaoImpl();
        Book book = bookDao.queryBookById(10);
        System.out.println(book);
    }
    @Test
    void queryAllBooks(){
        BookDao bookDao = new BookDaoImpl();
        List<Book> books = bookDao.queryAllBooks();
        for (Book book:books
             ) {
            System.out.println(book);
        }
    }
    @Test
    void count(){
        BookDao bookDao = new BookDaoImpl();
        Integer count = bookDao.count();
        System.out.println(count);
    }

    @Test
    void pageItems(){
        BookDao bookDao = new BookDaoImpl();
        List<Book> books = bookDao.pageItems(1, 4);
        for(Book book:books){
            System.out.println(book);
        }
    }
}