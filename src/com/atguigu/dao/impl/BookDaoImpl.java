package com.atguigu.dao.impl;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/16 23:04
 * @Description:
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name` , `author` , `price` , `sales` , `stock` , `img_path`) values(?,?,?,?,?,?) ";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path());

    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path(),book.getId());


    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select * from t_book where id=?";
        return (Book)queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryAllBooks() {
        String sql ="select * from t_book";
        return (List<Book>) queryList(Book.class,sql);
    }

    @Override
    public Integer count() {
        String sqlCount = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sqlCount);
        return  count.intValue();

    }

    @Override
    public List<Book> pageItems(Integer pageNo, Integer pageSize) {
        //通过选择在数据库中不同的页数 给出边界条件查询
        String sqlList ="select * from t_book limit ?, ?";
        return queryList(Book.class, sqlList, (pageNo - 1) * pageSize, pageSize);

    }

    @Override
    public List<Book> pageSelonPrice(Integer pageNo,Integer pageSize,Integer minPrice,Integer maxPrice) {
        String sqlPrice = "select * from (select * from t_book where price between ? and ?) as temp limit ?,?";
        return queryList(Book.class,sqlPrice,minPrice,maxPrice,(pageNo - 1) * pageSize, pageSize);
    }

    @Override
    public Integer pagePriceCount(Integer minPrice, Integer maxPrice){
        String sqlCount = "select count(*) from t_book where price between ? and ?";
        Number count =(Number) queryForSingleValue(sqlCount, minPrice, maxPrice);
        return count.intValue();
    }


}
