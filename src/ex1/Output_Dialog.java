package ex1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

class Output_Dialog extends JDialog {
    static Connection con;
    static Statement sql;
    static ResultSet res;

    public Output_Dialog(GM_Output frame) {
        super(frame, "新增出库", true);
        Container container = getContentPane();
        setLayout(null);
        setBounds(350, 100, 300, 450);

        JLabel jLabel_sellID = new JLabel("出售编号");
        JLabel jLabel_pzs = new JLabel("品种数");
        JLabel jLabel_je = new JLabel("金额");
        JLabel jLabel_xsdate = new JLabel("销售时间");

        container.add(jLabel_sellID);
        container.add(jLabel_pzs);
        container.add(jLabel_je);
        container.add(jLabel_xsdate);

        jLabel_sellID.setBounds(10, 30, 50, 50);
        jLabel_pzs.setBounds(10, 90, 50, 50);
        jLabel_je.setBounds(10, 150, 50, 50);
        jLabel_xsdate.setBounds(10, 210, 50, 50);


        JTextField jTextField_sellid = new JTextField();
        JTextField jTextField_pzs = new JTextField();
        JTextField jTextField_je = new JTextField();
        JTextField jTextField_xsdate = new JTextField();

        jTextField_sellid.setBounds(70, 40, 150, 30);
        jTextField_pzs.setBounds(70, 100, 150, 30);
        jTextField_je.setBounds(70, 160, 150, 30);
        jTextField_xsdate.setBounds(70, 220, 150, 30);


        container.add(jTextField_pzs);
        container.add(jTextField_je);
        container.add(jTextField_sellid);
        container.add(jTextField_xsdate);

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
                    res = sql.executeQuery(" insert into Warehouse_Commodity_Output(sellID,pzs,je,xsdate)" +
                            "values('"+jTextField_sellid.getText()+"','"+jTextField_pzs.getText()+"','"+jTextField_je.getText()+"','"+jTextField_xsdate.getText()+"')");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                setVisible(false);
            }
        });


        JB_Change.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sql = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    res = sql.executeQuery("update Warehouse_Commodity_Output set sellID='"+jTextField_sellid.getText()+"',pzs='"+jTextField_pzs.getText()+"',je='"+jTextField_je.getText()+"',rkdate='"+jTextField_xsdate.getText()+"' where sellID='"+jTextField_sellid.getText()+"'");
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
                    res = sql.executeQuery("DELETE  from Warehouse_Commodity_Output where sellID='"+jTextField_sellid.getText()+"'");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        JB_Cancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

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