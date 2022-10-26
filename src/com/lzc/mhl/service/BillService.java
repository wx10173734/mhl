package com.lzc.mhl.service;

import com.lzc.mhl.dao.BillDao;
import com.lzc.mhl.domain.Bill;

import java.util.List;
import java.util.UUID;

/**
 * @title: BillService
 * @Author luozouchen
 * @Date: 2022/10/26 21:18
 * @Version 1.0
 * 处理和账单相关的业务逻辑
 */
public class BillService {
    private BillDao billDao = new BillDao();
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();


    //编写点餐的方法
    //1. 生成账单
    //2.需要更新对应餐桌的状态
    //3.如果成功，返回true,否则返回false
    public boolean orderMenu(int menuId, int nums, int diningTableId) {
        //生成一个账单号，UUID
        String billId = UUID.randomUUID().toString();

        //将账单生成到bill表,要求直接计算出账单金额
        Double price = menuService.getMenuById(menuId).getPrice();
        int update = billDao.update("insert into bill values(null,?,?,?,?,?,now(),'未结账')", billId, menuId, nums, price * nums, diningTableId);
        if (update <= 0) {
            return false;
        }

        return diningTableService.updateDiningTableState(diningTableId, "就餐中");
    }

//    //查询所有账单
//    public List<Bill> listBill() {
//        List<Bill> list = billDao.queryMulti("select * from bill", Bill.class);
//        return list;
//    }
}
