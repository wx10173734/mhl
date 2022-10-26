package com.lzc.mhl.service;

import com.lzc.mhl.dao.BillDao;
import com.lzc.mhl.dao.MultiTableDao;
import com.lzc.mhl.domain.Bill;
import com.lzc.mhl.domain.MultiTableBean;

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
    private MultiTableDao multiTableDao = new MultiTableDao();


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

    //查询所有账单
    public List<Bill> listBill() {
        List<Bill> list = billDao.queryMulti("select * from bill", Bill.class);
        return list;
    }

    //查询所有账单并带有菜品名
    public List<MultiTableBean> listBill2() {
        List<MultiTableBean> list = multiTableDao.queryMulti("select bill.*,name,price from bill,menu where bill.menuId = menu.id", MultiTableBean.class);
        return list;
    }

    //查看某个餐桌是否有未结账的账单
    public boolean hasPayBillByDiningTableId(int diningTableId) {
        Bill bill = billDao.querySingle("select * from bill where diningTableId=? and state='未结账' limit 0,1", Bill.class, diningTableId);
        return bill != null;
    }

    //完成结账【如果餐桌存在，并且餐桌有未结账的账单】
    public boolean payBill(int diningTableId, String payMode) {

        //1.修改bill表
        int update = billDao.update("update bill set state = ? where diningTableId=? and state='未结账'", payMode, diningTableId);
        if (update <= 0) {
            return false;
        }
        //2.修改diningtable表
        if (!diningTableService.updateDiningTableToFree(diningTableId, "空")) {
            return false;
        }
        return true;

    }
}
