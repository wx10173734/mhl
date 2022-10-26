package com.lzc.mhl.service;

import com.lzc.mhl.dao.DiningTableDao;
import com.lzc.mhl.domain.DiningTable;

import java.util.List;

/**
 * @title: DiningTableService
 * @Author luozouchen
 * @Date: 2022/10/26 19:33
 * @Version 1.0
 */
public class DiningTableService {
    private DiningTableDao diningTableDao = new DiningTableDao();


    //返回所有餐桌的信息
    public List<DiningTable> list() {
        List<DiningTable> list = diningTableDao.queryMulti("select id,state from diningtable", DiningTable.class);
        return list;
    }

    //根据id查询对应的餐桌 diningtable对象
    //如果diningtable对象返回空表示id编号对应的餐桌不存在
    public DiningTable getDiningTableById(int id) {
        DiningTable diningTable = diningTableDao.querySingle("select id,state from diningtable where id = ?", DiningTable.class, id);
        return diningTable;
    }

    //如果餐桌可以预定，调用方法，对其状态进行更新，（包括预定人的名字和电话）
    public boolean orderDiningTable(int id, String orderName, String orderTel) {
        int update = diningTableDao.update("update diningtable set state='已经预定',orderName=?,orderTel =? where id =?", orderName, orderTel, id);
        return update > 0;
    }
}
