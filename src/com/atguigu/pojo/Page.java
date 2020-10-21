package com.atguigu.pojo;

import java.util.List;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/22 18:53
 * @Description:
 */
public  class Page <T>{
    public static final Integer PAGE_SIZE =4;
    //与数据库交互要写成integer
    //当前页数
    private Integer pageNo;
    //总页数
    private Integer pageTotal;
    //总记录数
    private Integer count;
    //每页记录数
    private Integer pageSize=PAGE_SIZE;
    //当前页数据
    private List<T> items;
    //url
    private String url;

    public Page() {
    }

    public Page(Integer pageNo, Integer pageTotal, Integer count, Integer pageSize, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.count = count;
        this.pageSize = pageSize;
        this.items = items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", count=" + count +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
       
    }
}
