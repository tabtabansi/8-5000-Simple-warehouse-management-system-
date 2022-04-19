package ex1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class GM_Sum extends JFrame {
    static Connection con;
    static Statement sql;
    static ResultSet res;
    public void GM_sum() {


        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException d) {
            d.printStackTrace();
        }


        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Warehouse", "sa", "123456789");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int row = 0;
        try {
            sql = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            res = sql.executeQuery("select * from VIEW1");
            res.last();//游标移动到查询到的数据库数据记录的最后一条
            row = res.getRow();
            System.out.println(row);
            res.beforeFirst();//游标回到第一个记录前的位置
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        String[] arr = {"ID","商品名","入库ID", "品种数","规格", "金额", "入库时间"};//定义表格的列名称
        String[][] comm = new String[row][7];//row行，4列

        try {
            res.next();//游标回到第一个记录的位置
            for (int i = 0; i < row; i++) {
                comm[i][0] = res.getString("id");
                comm[i][1] = res.getString("spname");
                comm[i][2] = res.getString("rkid");
                comm[i][3] = res.getString("pzs");
                comm[i][4] = res.getString("dw");
                comm[i][5] = res.getString("je");
                comm[i][6] = res.getString("rkdate");
                res.next();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        setLayout(null);
        setBounds(550,150,530,600);
        Container c=getContentPane();

        JTable jTable = new JTable(comm,arr);
        jTable.setBounds(10,20,480,500);
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setBounds(30,10,450,500);
        scrollPane.setViewportView(jTable);
        c.add(scrollPane,BorderLayout.NORTH);

        setVisible(true);




        JButton exit = new JButton("返回");
        c.add(exit);
        exit.setBounds(380,530,100,20);
        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new GM_System().GM_System_JFrame();
            }
        });


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
