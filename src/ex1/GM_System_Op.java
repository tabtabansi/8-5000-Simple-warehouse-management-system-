package ex1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



public class GM_System_Op extends JFrame {
    public void GM_System_Op_JFrame(){

        Container container=getContentPane();
        container.setLayout(null);
        JLabel JL_system_GM_welcome= new JLabel("欢迎您！");
        JL_system_GM_welcome.setBounds(10,10,60,20);
        container.add(JL_system_GM_welcome);
        setBounds(400,10,400,400);
        setVisible(true);

        //定义四个按钮
        JButton JB_GM_System_input = new JButton("入库管理");
        JButton JB_GM_System_output = new JButton("出库管理");
        JButton JB_GM_System_manage = new JButton("库存管理");
        JButton JB_GM_System_cont = new JButton("统计报表");
        JButton JB_GM_System_exit = new JButton("退出登陆");
        JButton JB_GM_System_add =new JButton("新增加员工");

        //四个按钮的位置
        JB_GM_System_input.setBounds(10,50,100,50);
        JB_GM_System_output.setBounds(10,110,100,50);
        JB_GM_System_manage.setBounds(10,170,100,50);
        JB_GM_System_cont.setBounds(10,230,100,50);
        JB_GM_System_exit.setBounds(250,300,100,50);
        JB_GM_System_add.setBounds(250,200,100,50);


        //将四个按钮加入到容器中
        container.add(JB_GM_System_input);
        container.add(JB_GM_System_output);
        container.add(JB_GM_System_manage);
        container.add(JB_GM_System_cont);
        container.add(JB_GM_System_exit);
        container.add(JB_GM_System_add);
        //四个按钮的监听器
        JB_GM_System_input.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GM_Input().GM_input();
                setVisible(false);

            }
        });

        JB_GM_System_output.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GM_Output().GM_output();
                setVisible(false);
            }
        });

        JB_GM_System_manage.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GM_Manage().GM_manage();
                setVisible(false);

            }
        });

        JB_GM_System_cont.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GM_Sum().GM_sum();
                setVisible(false);

            }
        });

        JB_GM_System_exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login().login("仓库商品管理系统");
                setVisible(false);


            }
        });

        JB_GM_System_add.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new User_Add(GM_System_Op.this).setVisible(true);
            }
        });

        container.setBackground(Color.gray);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
