package com.lzc.mhl.view;

import com.lzc.mhl.domain.*;
import com.lzc.mhl.service.BillService;
import com.lzc.mhl.service.DiningTableService;
import com.lzc.mhl.service.EmployeeService;
import com.lzc.mhl.service.MenuService;
import com.lzc.mhl.utils.Utility;

import java.util.List;

/**
 * @title: MHLView
 * @Author luozouchen
 * @Date: 2022/10/26 17:13
 * @Version 1.0
 * 主界面
 */
public class MHLView {
    //控制是否退出菜单
    private boolean loop = true;
    private String key = "";//接受用户的输入
    //定义employeeService对象
    private EmployeeService employeeService = new EmployeeService();
    private DiningTableService diningTableService = new DiningTableService();
    private MenuService menuService = new MenuService();

    private BillService billService = new BillService();

    public static void main(String[] args) {
        new MHLView().mainMenu();
    }

    /**
     * 显示所有餐桌状态
     */
    public void listDiningTable() {
        List<DiningTable> list = diningTableService.list();
        System.out.println("\n餐桌编号\t\t餐桌状态");
        for (DiningTable diningTable : list) {
            System.out.println(diningTable);
        }
        System.out.println("============显示完毕============");
    }

    /**
     * 预定餐桌
     */
    public void orderDiningTable() {
        System.out.println("============预定餐桌============");
        System.out.println("请选择要预定的餐桌编号(-1退出):");
        int id = Utility.readInt(1);
        if (id == -1) {
            System.out.println("============取消预定餐桌============");
            return;
        }
        System.out.println("确认是否预定(Y/N)");
        //该方法得到的是Y或者N
        char key = Utility.readConfirmSelection();
        if (key == 'Y') {
            DiningTable diningTable = diningTableService.getDiningTableById(id);
            if (diningTable == null) {
                System.out.println("============预定餐桌不存在============");
                return;
            }
            //判断该餐桌的状态是否为空
            if (!("空".equals(diningTable.getState()))) {//说明餐桌不是空状态
                System.out.println("============该餐桌已经预定或就餐中============");
                return;
            }
        } else {
            System.out.println("============取消预定餐桌============");
            return;
        }

        System.out.println("预定人名字:");
        String orderName = Utility.readString(50);
        System.out.println("预定人电话:");
        String orderTel = Utility.readString(50);
        boolean diningTableState = diningTableService.orderDiningTable(id, orderName, orderTel);
        if (diningTableState) {
            System.out.println("============预定餐桌成功============");
        } else {
            System.out.println("============预定餐桌失败============");
        }

    }

    /**
     * 点餐
     */
    public void orderMenu() {
        System.out.println("============点餐服务============");
        System.out.println("请输入点餐的桌号(-1退出):");
        int diningTableId = Utility.readInt(1);
        if (diningTableId == -1) {
            System.out.println("============取消点餐============");
            return;
        }

        DiningTable diningTable = diningTableService.getDiningTableById(diningTableId);
        //验证桌号
        if (diningTable == null) {
            System.out.println("桌号不存在");
            return;
        }


        System.out.println("请选择要点的菜品编号(-1退出):");
        int menuId = Utility.readInt(1);
        if (menuId == -1) {
            System.out.println("============取消点餐============");
            return;
        }


        //验证菜品编号
        Menu menu = menuService.getMenuById(menuId);
        if (menu == null) {
            System.out.println("菜品不存在");
            return;
        }


        System.out.println("请选择菜品数量(-1退出):");
        int nums = Utility.readInt(1);
        if (nums == -1) {
            System.out.println("============取消点餐============");
            return;
        }

        char key = Utility.readConfirmSelection();
        if (key == 'Y') {

            boolean orderMenuState = billService.orderMenu(menuId, nums, diningTableId);
            if (orderMenuState) {
                System.out.println("============点餐成功============");
            } else {
                System.out.println("============点餐失败============");
            }
        } else {
            System.out.println("============取消点餐============");

        }


    }

    /**
     * 查看所有菜品
     */
    public void menuList() {
        System.out.println("\n菜品编号\t\t菜品名\t\t类别\t\t价格");
        List<Menu> list = menuService.list();
        for (Menu menu : list) {
            System.out.println(menu);
        }
    }

    /**
     * 查看账单
     */
    public void listBill() {
//        System.out.println("编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态");
//        List<Bill> bills = billService.listBill();
//        for (Bill bill : bills) {
//            System.out.println(bill);
//        }
        List<MultiTableBean> multiTableBeans = billService.listBill2();
        System.out.println("编号\t\t菜品号\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t\t\t\t状态\t\t菜品名\t\t价格");
        for (MultiTableBean multiTableBean : multiTableBeans) {
            System.out.println(multiTableBean);
        }

    }

    /**
     * 完成结账
     */
    public void payBill() {
        System.out.println("============结账服务============");
        System.out.println("请选择要结账的餐桌编号(-1退出)");
        int diningTableId = Utility.readInt(1);
        if (diningTableId == -1) {
            System.out.println("取消结账");
            return;
        }
        //验证餐桌号是否存在
        DiningTable diningTable = diningTableService.getDiningTableById(diningTableId);
        if (diningTable == null) {
            System.out.println("结账的餐桌号不存在");
            return;
        }
        //验证餐桌是否有需要结账的账单
        boolean payBillState = billService.hasPayBillByDiningTableId(diningTableId);
        if (!payBillState) {
            System.out.println("结账的餐桌不存在");
            return;
        }
        System.out.println("结账的方式(现金/支付宝/微信)回车表示退出");
        String payMode = Utility.readString(50);
        if ("".equals(payMode)) {
            System.out.println("取消结账");
            return;
        }
        char key = Utility.readConfirmSelection();
        if (key == 'Y') {
            //调用完成结账方法
            if (billService.payBill(diningTableId, payMode)) {
                System.out.println("结账成功");
            } else {
                System.out.println("结账失败");

            }
        } else {
            System.out.println("取消结账");

        }

    }


    //显示主菜单
    public void mainMenu() {
        while (loop) {
            System.out.println("============满汉楼============");
            System.out.println("\t\t 1 登录满汉楼");
            System.out.println("\t\t 2 退出满汉楼");
            System.out.println("请输入你的选择:");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.println("请输入员工号:");
                    String empId = Utility.readString(50);
                    System.out.println("请输入密  码:");
                    String pwd = Utility.readString(50);
                    //到数据库去判断
                    Employee employee = employeeService.getEmployeeByIdAndPwd(empId, pwd);
                    if (employee != null) {//说明存在该用户
                        System.out.println("============登陆成功[" + employee.getName() + "]============\n");
                        //显示二级菜单,这里二级菜单是循环操作，做成while
                        while (loop) {
                            System.out.println("============满汉楼二级============");
                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预定餐桌");
                            System.out.println("\t\t 3 显示所有菜品");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 查看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 9 退出满汉楼");
                            System.out.println("请输入你的选择:\n");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    listDiningTable();//显示餐桌状态
                                    break;
                                case "2":
                                    orderDiningTable();//预定餐桌
                                    break;
                                case "3":
                                    menuList();
                                    break;
                                case "4":
                                    orderMenu();
                                    break;
                                case "5":
                                    listBill();
                                    break;
                                case "6":
                                    payBill();
                                    break;
                                case "9":
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("你输入有误，请重新输入");
                            }
                        }

                    } else {
                        System.out.println("============登陆失败============\n");
                    }
                    break;
                case "2":
                    System.out.println("退出满汉楼");
                    loop = false;
                    break;
                default:
                    System.out.println("你输入有误，请重新输入");
            }
        }
        System.out.println("你退出了满汉楼系统~");
    }
}
