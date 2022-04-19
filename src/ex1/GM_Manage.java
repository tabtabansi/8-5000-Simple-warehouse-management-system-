package ex1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class GM_Manage extends JFrame {
    static Connection con;
    static Statement sql;
    static ResultSet res;
    public void GM_manage(){
        setTitle("库存管理");
        Container container = getContentPane();
        setBounds(500,180,500,500);
        setLayout(null);
        setVisible(true);
        container.setBackground(Color.gray);




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
            res = sql.executeQuery("select * from Warehouose_Commodity_Message");
            res.last();//游标移动到查询到的数据库数据记录的最后一条
            row = res.getRow();
            System.out.println(row);
            res.beforeFirst();//游标回到第一个记录前的位置
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        String[] arr = {"商品ID", "商品名", "规格"};//定义表格的列名称
        String[][] comm = new String[row][3];//row行，4列

        try {
            res.next();//游标回到第一个记录的位置
            for (int i = 0; i < row; i++) {
                comm[i][0] = res.getString("id");
                comm[i][1] = res.getString("spname");
                comm[i][2] = res.getString("dw");
                res.next();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        JTable jTable = new JTable(comm,arr);
        jTable.setBounds(0,10,200,200);
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setBounds(0,5,500,250);
        scrollPane.setViewportView(jTable);
        container.add(scrollPane,BorderLayout.NORTH);









        JButton jb_input = new JButton("新增入库");
        container.add(jb_input);
        jb_input.setBounds(30,350,100,20);
        jb_input.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GM_Manage_Inpute(GM_Manage.this).setVisible(true);

            }
        });

                JButton exit = new JButton("返回上一层");
        container.add(exit);
        exit.setBounds(330,350,100,20);
        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new GM_System().GM_System_JFrame();
            }
        });
    }

}
 class GM_Manage_Inpute extends JDialog {
     static Connection con;
     static Statement sql;
     static ResultSet res;

    public GM_Manage_Inpute(GM_Manage frame) {
        super(frame, "新增入库",true);
        Container container=getContentPane();
        setLayout(null);
        setBounds(350,100,300,350);

        JLabel jLabel_id = new JLabel("商品ID");
        JLabel jLabel_spname=new JLabel("商品名");
        JLabel jLabel_dw =new JLabel("规格");

        container.add(jLabel_id);
        container.add(jLabel_spname);
        container.add(jLabel_dw);

        jLabel_id.setBounds(10,30,50,50);
        jLabel_spname.setBounds(10,90,50,50);
        jLabel_dw.setBounds(10,150,50,50);




        JTextField jTextField_id=new JTextField();
        JTextField jTextField_spname=new JTextField();
        JTextField jTextField_dw=new JTextField();

        jTextField_id.setBounds(70,40,150,30);
        jTextField_spname.setBounds(70,100,150,30);
        jTextField_dw.setBounds(70,160,150,30);

        container.add(jTextField_spname);
        container.add(jTextField_dw);
        container.add(jTextField_id);

        JButton JB_Add=new JButton("提交");
        JButton JB_Cancel=new JButton("退出");


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

        JB_Add.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sql = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    res = sql.executeQuery(" insert into Warehouose_Commodity_Message(id,spname,dw)"+
                            "values('"+jTextField_id.getText()+"','"+jTextField_spname.getText()+"','"+jTextField_dw.getText()+"')");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                setVisible(false);
            }
        });

        JB_Cancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);//退出

            }
        });
        container.add(JB_Add);
        container.add(JB_Cancel);

        JB_Cancel.setBounds(170,270,100,20);
        JB_Add.setBounds(20,270,100,20);


    }

}