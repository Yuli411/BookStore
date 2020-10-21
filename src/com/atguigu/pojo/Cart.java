package com.atguigu.pojo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Yhurri
 * @Date: 2020/5/28 23:00
 * @Description:
 */
public class Cart {

    //private Integer itemsTotal = 0;
    //private BigDecimal priceTotal = new BigDecimal(0);
    private Map<Integer, CarteItem> items = new HashMap<>();

    public Cart(Map<Integer, CarteItem> items) {
        this.items = items;
    }

    public Cart() {
    }

    public Integer getItemsTotal() {

        Integer itemsTotal = 0;

        for (Map.Entry<Integer, CarteItem> entry : items.entrySet()) {
            CarteItem carteItem = entry.getValue();
            itemsTotal += carteItem.getCount();

        }
        return itemsTotal;
    }


    //for all the projects
    public BigDecimal getPriceTotal() {
        BigDecimal priceTotal = new BigDecimal(0);
        for (Map.Entry<Integer, CarteItem> entry : items.entrySet()) {
            CarteItem carteItem = entry.getValue();
            priceTotal = priceTotal.add(carteItem.getTotalPrice());
        }
        return priceTotal;
    }


    public Map<Integer, CarteItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CarteItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "itemsTotal=" + getItemsTotal() +
                ", priceTotal=" + getPriceTotal() +
                ", items=" + items +
                '}';
    }

    public void addItem(CarteItem carteItem) {
        CarteItem carteItem1 = items.get(carteItem.getId());
        if (carteItem1 == null) {
            items.put(carteItem.getId(), carteItem);
        } else {
            carteItem.setCount(carteItem.getCount() + 1);
            carteItem.setTotalPrice(carteItem.getPrice().multiply(new BigDecimal(carteItem.getCount())));
            items.put(carteItem.getId(), carteItem);

        }

    }

    public void clean() {

        items.clear();


    }

    public void deleteItem(Integer id) {
        CarteItem carteItem = items.get(id);
        items.remove(id);

    }

    public void updateItemsCount(Integer id, Integer count) {
        CarteItem carteItem = items.get(id);
        carteItem.setCount(count);
        carteItem.setTotalPrice(carteItem.getPrice().multiply(new BigDecimal(count)));
        items.put(id, carteItem);

    }
}
