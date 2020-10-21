package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CarteItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/29 00:26
 * @Description:
 */
class CartTest {

    @Test
    void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CarteItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CarteItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CarteItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
    }

    @Test
    void clean() {
        Cart cart = new Cart();
        cart.addItem(new CarteItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CarteItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CarteItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        cart.clean();
        System.out.println(cart);

    }

    @Test
    void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CarteItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CarteItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CarteItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    void updateItemsCount() {
        Cart cart = new Cart();
        cart.addItem(new CarteItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CarteItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CarteItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        cart.updateItemsCount(2,3);
        System.out.println(cart);
    }
}