package com.lzc.mhl.domain;

import java.time.LocalDateTime;

/**
 * @title: Bill
 * @Author luozouchen
 * @Date: 2022/10/26 21:15
 * @Version 1.0
 * id int primary key auto_increment, #自增主键
 * billId varchar(50) not null default '',#账单号可以按照自己规则生成 UUID
 * menuId int not null default 0,#菜品的编号, 也可以使用外键
 * nums SMALLINT not null default 0,#份数
 * money double not null default 0, #金额
 * diningTableId int not null default 0, #餐桌
 * billDate datetime not null ,#订单日期
 * state varchar(50) not null default '' # 状态 '未结账' , '已经结账', '挂单'
 */
public class Bill {
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private Double money;
    private Integer diningTableId;
    private LocalDateTime billDate;
    private String state;

    public Bill() {
    }

    public Bill(Integer id, String billId, Integer menuId, Integer nums, Double money, Integer diningTableId, LocalDateTime billDate, String state) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
    }

    public Integer getId() {
        return id;
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

//    @Override
//    public String toString() {
//        return id + "\t\t" + menuId + "\t\t" + nums + "\t\t" + money + "\t\t" + diningTableId + "\t\t" + billDate + "\t\t" + state;
//    }
}