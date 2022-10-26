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
}
