package com.lzc.mhl.domain;

import java.time.LocalDateTime;

/**
 * @title: MultiTableBean
 * @Author luozouchen
 * @Date: 2022/10/26 23:27
 * @Version 1.0
 * 这是一个javabean 可以和多张表进行对应
 */
public class MultiTableBean {
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private Double money;
    private Integer diningTableId;
    private LocalDateTime billDate;
    private String state;
    //增加一个来自menu表的列 name
    //思考 这里的属性名是否一定要和表的列名保持一致 可以设置别名解决
    private String name;
    //增加来自Menu表的列price
    private Double price;//默认null


    public MultiTableBean() {
        System.out.println("反射调用");
    }

    public MultiTableBean(Integer id, String billId, Integer menuId, Integer nums, Double money, Integer diningTableId, LocalDateTime billDate, String state, String name) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return id + "\t\t" + menuId + "\t\t\t" + nums + "\t\t\t" + money + "\t" + diningTableId + "\t\t" + billDate + "\t\t" + state + "\t\t" + name + "\t\t" + price;
    }
}
