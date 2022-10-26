package com.lzc.mhl.test;

import com.lzc.mhl.utils.JDBCUtilsDruid;
import com.lzc.mhl.utils.Utility;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @title: TestUtils
 * @Author luozouchen
 * @Date: 2022/10/26 17:08
 * @Version 1.0
 */
public class TestUtils {
    public static void main(String[] args) throws SQLException {
        //测试工具类
        System.out.println("请输入一个整数");
        int i = Utility.readInt();
        System.out.println(i);


        //测试jdbc
        Connection connection = JDBCUtilsDruid.getConnection();
        System.out.println(connection);
    }
}
