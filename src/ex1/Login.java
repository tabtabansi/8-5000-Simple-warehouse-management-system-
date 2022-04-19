package ex1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;


public class Login extends JFrame {
    public void login(String title) {
        JFrame login = new JFrame(title);
        Container container = login.getContentPane();
        container.setLayout(null);
        login.setVisible(true);


        login.setResizable(false);


        //标签
        JLabel welcome = new JLabel("***-欢迎使用仓库商品管理系统-***");
        welcome.setBounds(150, 10, 1000, 40);
        JLabel username = new JLabel("账号");
        username.setBounds(30, 100, 50, 20);
        JLabel password = new JLabel("密码");
        password.setBounds(30, 150, 50, 20);

        container.add(welcome);
        container.add(username);
        container.add(password);
        container.setBackground(Color.GRAY);


        //创建单选按钮
        JRadioButton jr2=new JRadioButton("管理员登陆");
        login.add(jr2);
        jr2.setBounds(100, 210, 100, 20);



        //文本框
        final JTextField jt = new JTextField();
        container.add(jt);
        jt.setBounds(100, 100, 300, 20);


        JPasswordField jp = new JPasswordField();
        jp.setEchoChar('*');


        container.add(jp);


        JButton JButton_login = new JButton("登陆");
        JButton_login.setBounds(100, 250, 100, 40);





        JButton_login.addActionListener(new AbstractAction() {
            String u_user;
            String p_password;


            @Override
            public void actionPerformed(ActionEvent e) {
                if (jr2.isSelected()) {

                    try {

                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    } catch (ClassNotFoundException d) {
                        d.printStackTrace();
                    }
                    Connection con;


                    try {
                        con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Warehouse", "sa", "123456789");
                        Statement s = con.createStatement();

                        String sql = "select * from 管理员";
                        ResultSet rs = s.executeQuery(sql);
                        rs.next();
                        u_user = rs.getString("UserText").trim();//trim()方法去空格
                        p_password = rs.getString("PasswordField").trim();
                        s.close();
                        con.close();

                        String Username = jt.getText();
                        String PassWord = String.valueOf(jp.getPassword());


                        boolean flag = u_user.equals(Username) && p_password.equals(PassWord);
                        if (flag) {
                            new GM_System_Op().GM_System_Op_JFrame();
                            login.setVisible(false);
                        } else {
                            new Login_Erro(Login.this).setVisible(true);
                        }
                    } catch (SQLException d) {
                        d.printStackTrace();
                    }

                }
               else
               {

                    try {

                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    } catch (ClassNotFoundException d) {
                        d.printStackTrace();
                    }
                    Connection con;


                    try {
                        con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Warehouse", "sa", "123456789");
                        Statement s = con.createStatement();

                        String sql = "select * from 账户";
                        ResultSet rs = s.executeQuery(sql);
                        rs.next();
                        u_user = rs.getString("UserText").trim();
                        p_password = rs.getString("PasswordField").trim();
                        s.close();
                        con.close();

                        String Username = jt.getText();
                        String PassWord = String.valueOf(jp.getPassword());


                        boolean flag = u_user.equals(Username) && p_password.equals(PassWord);
                        if (flag) {
                            new GM_System().GM_System_JFrame();
                            login.setVisible(false);
                        } else {
                            new Login_Erro(Login.this).setVisible(true);
                        }
                    } catch (SQLException d) {
                        d.printStackTrace();
                    }

                }

            }


        });
        container.add(JButton_login);

        JButton JButton_exit = new JButton("退出");
        JButton_exit.setBounds(300, 250, 100, 40);
        JButton_exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);//退出

            }
        });
        container.add(JButton_exit);


        jp.setBounds(100, 150, 300, 20);

        login.setBounds(500, 250, 500, 400);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }


    public static class Login_Erro extends JDialog {
        public Login_Erro(Login frame) {
            super(frame, "登陆失败",true);
            Container container=getContentPane();
            setLayout(null);

            JLabel jLabel=new JLabel("您输入的账户名或者密码不正确。");
            container.add(jLabel);

            JLabel jLabel1=new JLabel("请注意区分大小写。");
            container.add(jLabel1);

            JLabel jLabel2=new JLabel("忘记密码请联系管理员。");
            container.add(jLabel2);

            JButton JB_Retry=new JButton("重试");
            JButton JB_Exit=new JButton("退出");

            JB_Retry.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });

            JB_Exit.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);//退出

                }
            });
            container.add(JB_Exit);
            container.add(JB_Retry);

            JB_Exit.setBounds(200,200,100,50);
            JB_Retry.setBounds(80,200,100,50);
            jLabel.setBounds(100,50,200,20);
            jLabel1.setBounds(130,80,200,20);
            jLabel2.setBounds(120,110,200,20);
            setBounds(550,300,400,300);

        }

    }
}




