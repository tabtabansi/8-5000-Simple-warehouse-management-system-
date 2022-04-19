package ex1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class User_Add extends JDialog {


    static Connection con;
    static Statement sql;
    static ResultSet res;

    public User_Add(GM_System_Op frame) {
        super(frame, "修改用户", true);
        Container container = getContentPane();
        setLayout(null);
        setBounds(350, 100, 300, 450);

        JLabel jLabel_user = new JLabel("账号");
        JLabel jLabel_password = new JLabel("密码");


        container.add(jLabel_user);
        container.add(jLabel_password);


        jLabel_user.setBounds(10, 30, 50, 50);
        jLabel_password.setBounds(10, 90, 50, 50);


        JTextField jTextField_user = new JTextField();
        JTextField jTextField_password = new JTextField();


        jTextField_user.setBounds(70, 40, 150, 30);
        jTextField_password.setBounds(70, 100, 150, 30);



        container.add(jTextField_user);

        container.add(jTextField_password);


        JButton JB_Add = new JButton("提交");
        JButton JB_Cancel = new JButton("退出");
        JButton JB_Delete=new JButton("删除");
        JButton JB_Change=new JButton("修改");

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
                    res = sql.executeQuery(" insert into 账户(UserText,PasswordField)" +
                            "values('"+jTextField_user.getText()+"','"+jTextField_password.getText()+"')");
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

        JB_Change.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sql = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    res = sql.executeQuery("update 账户 set UserText='"+jTextField_user.getText()+"',PasswordField='"+jTextField_password.getText()+"'");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });

        JB_Delete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sql = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    res = sql.executeQuery("DELETE  from 账户 where UserText='"+jTextField_user.getText()+"'");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        container.add(JB_Add);
        container.add(JB_Cancel);
        container.add(JB_Delete);
        container.add(JB_Change);

        JB_Cancel.setBounds(150, 350, 80, 20);
        JB_Add.setBounds(10, 280, 80, 20);
        JB_Delete.setBounds(10,350,80,20);
        JB_Change.setBounds(150,280,80,20);


    }
}