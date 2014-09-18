/*
 Jpanel to search for customers
 */
package UserGUI;

import Database.DbConnection;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Prabushi
 */
public class Customer extends JPanel {

    public static Customer instance = null;
    private JButton add;
    JComboBox name;
    JTextField address;
    JTextField phone;
    JButton bill;
    JButton delete;

    //constructor
    public Customer() {

        this.setLayout(new GridBagLayout());
        add = new JButton("Add New");
        add.setPreferredSize(new Dimension(120, 40));
        add.setFont(new java.awt.Font("Times New Roman", 1, 16));
        add.setBackground(new java.awt.Color(255, 255, 102));
        bill = new JButton("Proceed Billing");
        bill.setPreferredSize(new Dimension(120, 40));
        bill.setFont(new java.awt.Font("Times New Roman", 1, 16));
        bill.setBackground(new java.awt.Color(255, 255, 102));
        delete = new JButton("Delete");
        delete.setPreferredSize(new Dimension(120, 40));
        delete.setFont(new java.awt.Font("Times New Roman", 1, 16));
        delete.setBackground(new java.awt.Color(255, 255, 102));

        JLabel lname = new JLabel("Name :");
        lname.setPreferredSize(new Dimension(100, 30));
        JLabel laddress = new JLabel("Address :");
        laddress.setPreferredSize(new Dimension(100, 30));
        JLabel lphone = new JLabel("Phone No. :");
        lphone.setPreferredSize(new Dimension(100, 30));

        name = new JComboBox();
        name.setPreferredSize(new Dimension(220, 30));
        address = new JTextField();
        address.setPreferredSize(new Dimension(220, 30));
        phone = new JTextField();
        phone.setPreferredSize(new Dimension(220, 30));

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 1;
        gc.gridy = 1;
        add(lname, gc);
        name.setBounds(5, 1, 50, 10);
        gc.gridx = 5;
        gc.gridy = 1;
        add(name, gc);

        try {
            String sql = "select distinct * from customer where removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            String xname = null;
            while (rs.next()) {
                xname = rs.getString("Name");
                name.addItem(xname);
            }
        } catch (Exception e) {
            System.out.println("02" + e);
        }
        String tphon, tadd;
        String Tname = (String) name.getSelectedItem();
        String sql1 = "select * from customer where Name ='" + Tname + "' and removed is null";

        try {
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst1 = connection1.prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery(sql1);

            if (rs1.next()) {
                tphon = rs1.getString("PhoneNum");
                phone.setText(tphon);
                tadd = rs1.getString("Address");
                address.setText(tadd);
            }
        } catch (Exception e) {
            System.out.println("03" + e);
        }
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }

            private void nameActionPerformed(ActionEvent evt) {

                String tadd, tphon;
                String Tname = (String) name.getSelectedItem();
                String sql1 = "select * from customer where Name ='" + Tname + "' and removed is null";

                try {
                    Connection connection1 = DbConnection.createConnection();
                    PreparedStatement pst1 = connection1.prepareStatement(sql1);
                    ResultSet rs1 = pst1.executeQuery(sql1);

                    if (rs1.next()) {
                        tadd = rs1.getString("Address");
                        tphon = rs1.getString("PhoneNum");
                        address.setText(tadd);
                        phone.setText(tphon);
                    }
                } catch (Exception e) {
                    System.out.println("04" + e);
                }
            }
        });
        gc.gridx = 1;
        gc.gridy = 2;
        add(laddress, gc);
        gc.gridx = 5;
        gc.gridy = 2;
        add(address, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        add(lphone, gc);
        gc.gridx = 5;
        gc.gridy = 3;
        add(phone, gc);

        gc.gridx = 3;
        gc.gridy = 13;
        add(add, gc);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }

            private void addActionPerformed(ActionEvent evt) {
                Page p = Page.getInstance();
                p.addCustomer();
            }
        });

        gc.gridx = 5;
        gc.gridy = 13;
        add(bill, gc);
        bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billActionPerformed(evt);
            }

            private void billActionPerformed(ActionEvent evt) {

                Bill bill = Bill.getInstance();
                bill.removeSections();


                String custom = (String) name.getSelectedItem();
                try {
                    String sql = "select * from customer where Name = '" + custom + "' and removed is null";
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    int id = 0;
                    if (rs.next()) {
                        id = rs.getInt("CustomerId");
                    }

                    float total = bill.getTotal();
                    String Total = Float.toString(total);
                    float profit = bill.getProfit();
                    String Profit = Float.toString(profit);
                    int billid = 0;
                    String sql1 = "select max(BillId) as highest from bill";
                    Connection connection1 = DbConnection.createConnection();
                    PreparedStatement pst1 = connection1.prepareStatement(sql1);
                    ResultSet rs1 = pst1.executeQuery();

                    if (rs1.next()) {
                        billid = rs1.getInt("highest");
                    }
                    String index = Integer.toString(billid);
                    String cusId = Integer.toString(id);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date date = new java.util.Date();
                    String tempDate = dateFormat.format(date);

                    Connection connection2 = DbConnection.createConnection();
                    String sql2 = "update bill set CustomerId = '" + cusId + "' ,Total = '" + Total + "', Date = '" + tempDate + "' ,Profit = '" + Profit + "' where BillId = '" + index + "'";
                    PreparedStatement pst2 = connection2.prepareStatement(sql2);

                    pst2.executeUpdate();
                    //printing bill

                    PrintBill pb = new PrintBill();
                    pb.print(custom);

                    Payment payment = Payment.getInstance();
                    payment.tbalance.setText("");
                    payment.tcash.setText("");

                } catch (Exception e) {
                    System.out.println("01" + e);
                }
            }
        });
        gc.gridx = 4;
        gc.gridy = 13;
        add(delete, gc);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }

            private void deleteActionPerformed(ActionEvent evt) {
                String tempcus;
                tempcus = (String) name.getSelectedItem();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date date = new java.util.Date();
                String tempDate = dateFormat.format(date);

                String sql = "update customer set removed = '" + tempDate + "' where Name ='" + tempcus + "'";
                try {
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.executeUpdate(sql);

                    JOptionPane.showMessageDialog(null, "Customer : " + tempcus + " has been deleted.");

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

    }

    //return an instance of the class
    public static Customer getInstance() {
        if (instance == null) {
            instance = new Customer();
        }
        return instance;
    }
}
