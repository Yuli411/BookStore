package com.atguigu.dao.impl;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/16 22:55
 * @Description:
 */
public interface BookDao {
    public int deleteBookById(Integer id);
    public int addBook(Book book);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryAllBooks();
    public Integer count();
    public List<Book> pageItems(Integer pageNo, Integer pageSize);
    public List<Book> pageSelonPrice(Integer pageNo,Integer pageSize,Integer minPrice, Integer maxPrice);
    public Integer pagePriceCount(Integer minPrice, Integer maxPrice);

}
