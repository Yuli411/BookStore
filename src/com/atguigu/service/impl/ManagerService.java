package com.atguigu.service.impl;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/16 23:45包含订单模块,
 * @Description:
 */
public interface ManagerService {
    public int deleteBook(Integer id);
    public int addBook(Book book);
    public int modifyBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryAllBooks();
    public Page<Book> page(Integer pageNo, Integer pageSize);
    public Page<Book> pagePrice(Integer pageNo, Integer pageSize,Integer min, Integer max);
}
