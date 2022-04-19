package ex1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class GM_Output extends JFrame {
    static Connection con;
    static Statement sql;
    static ResultSet res;
    public void GM_output() {


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
            res = sql.executeQuery("select * from Warehouse_Commodity_Output");
            res.last();//游标移动到查询到的数据库数据记录的最后一条
            row = res.getRow();
            System.out.println(row);
            res.beforeFirst();//游标回到第一个记录前的位置
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        String[] arr = {"销售单号", "品种数", "金额", "销售时间"};//定义表格的列名称
        String[][] comm = new String[row][4];//row行，4列

        try {
            res.next();//游标回到第一个记录的位置
            for (int i = 0; i < row; i++) {
                comm[i][0] = res.getString("sellID");
                comm[i][1] = res.getString("pzs");
                comm[i][2] = res.getString("je");
                comm[i][3] = res.getString("xsdate");
                res.next();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        setLayout(null);
        setBounds(550,150,520,600);
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




        JButton change=new JButton("修改");
        c.add(change);
        change.setBounds(80,530,100,20);
        change.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Output_Dialog(GM_Output.this).setVisible(true);


            }
        });


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
