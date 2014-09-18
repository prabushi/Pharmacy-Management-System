/*
 * Class to edit customer orders 
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
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Prabushi
 */
public class EditOrder extends JPanel {

    public static EditOrder instance = null;
    private JButton add;
    private JButton delete;
    JTextField Bphone;
    JTextField Bname;
    JTextField BgName;
    JTextField Bcompany;
    JTextField Bcode;
    JTextField Bcustomer;
    JTextField Bquantity;
    JComboBox Border;

    //constructor
    public EditOrder() {

        this.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(10, 10, 10, 10);

        JLabel order = new JLabel("Order :");
        order.setPreferredSize(new Dimension(100, 30));
        JLabel name = new JLabel("Name :");
        name.setPreferredSize(new Dimension(100, 30));
        JLabel gName = new JLabel("Generic Name :");
        gName.setPreferredSize(new Dimension(100, 30));
        JLabel company = new JLabel("Company Name :");
        company.setPreferredSize(new Dimension(100, 30));
        JLabel code = new JLabel("Code No. :");
        code.setPreferredSize(new Dimension(100, 30));
        JLabel customer = new JLabel("Customer :");
        customer.setPreferredSize(new Dimension(100, 30));
        JLabel quantity = new JLabel("Quantity :");
        quantity.setPreferredSize(new Dimension(100, 30));
        JLabel phone = new JLabel("Phone No. :");
        phone.setPreferredSize(new Dimension(100, 30));

        Bname = new JTextField();
        Bname.setPreferredSize(new Dimension(220, 30));
        BgName = new JTextField();
        BgName.setPreferredSize(new Dimension(220, 30));
        Bcompany = new JTextField();
        Bcompany.setPreferredSize(new Dimension(220, 30));
        Bcode = new JTextField();
        Bcode.setPreferredSize(new Dimension(220, 30));
        Bcustomer = new JTextField();
        Bcustomer.setPreferredSize(new Dimension(220, 30));
        Bquantity = new JTextField();
        Bquantity.setPreferredSize(new Dimension(220, 30));
        Bphone = new JTextField();
        Bphone.setPreferredSize(new Dimension(220, 30));
        Border = new JComboBox();
        Border.setPreferredSize(new Dimension(220, 30));


        add = new JButton("Add New");
        add.setPreferredSize(new Dimension(120, 40));
        add.setFont(new java.awt.Font("Times New Roman", 1, 16));
        add.setBackground(new java.awt.Color(255, 255, 102));
        delete = new JButton("Delete");
        delete.setPreferredSize(new Dimension(120, 40));
        delete.setFont(new java.awt.Font("Times New Roman", 1, 16));
        delete.setBackground(new java.awt.Color(255, 255, 102));

        gc.gridx = 1;
        gc.gridy = 0;
        add(order, gc);
        gc.gridx = 5;
        gc.gridy = 0;
        add(Border, gc);

        Border.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorderActionPerformed(evt);
            }
        });
        try {
            String sql1 = "select distinct * from orders where removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst1 = connection.prepareStatement(sql1);
            ResultSet rs = pst1.executeQuery();

            int xname = 0;
            while (rs.next()) {
                xname = rs.getInt("orderId");
                Border.addItem(xname);
            }
        } catch (Exception e) {
            System.out.println("10" + e);
        }

        int Torder, Tcode, Tcustomer, Tquantity;

        Torder = (int) Border.getSelectedItem();
        String torder = Integer.toString(Torder);


        try {
            String sql = "select * from orders where orderId ='" + Torder + "' and removed is null";
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                Tcode = rs1.getInt("code");
                Tcustomer = rs1.getInt("customerId");
                Tquantity = rs1.getInt("quantity");

                String tcode = Integer.toString(Tcode);
                String tcustomer = Integer.toString(Tcustomer);

                String Tname = null, Tgname = null;
                int TcompanyId = 0;

                String sql4 = "select * from item where code = '" + tcode + "' and removed is null";
                Connection connection4 = DbConnection.createConnection();
                PreparedStatement pst4 = connection4.prepareStatement(sql4);
                ResultSet rs4 = pst4.executeQuery();
                if (rs4.next()) {
                    Tname = rs4.getString("Name");
                    Tgname = rs4.getString("Generic");
                    TcompanyId = rs4.getInt("CompanyId");
                }
                String companyId = Integer.toString(TcompanyId);
                String comp = null;

                String sql5 = "select * from company where CompanyId = '" + companyId + "' and removed is null";
                Connection connection5 = DbConnection.createConnection();
                PreparedStatement pst5 = connection5.prepareStatement(sql5);
                ResultSet rs5 = pst5.executeQuery();
                if (rs5.next()) {
                    comp = rs5.getString("CompanyName");
                }

                String customerId = Integer.toString(Tcustomer);
                String cname = null, cphone = null;

                String sql6 = "select * from customer where CustomerId = '" + customerId + "' and removed is null";
                Connection connection6 = DbConnection.createConnection();
                PreparedStatement pst6 = connection6.prepareStatement(sql6);
                ResultSet rs6 = pst6.executeQuery();
                if (rs6.next()) {
                    cname = rs6.getString("Name");
                    cphone = rs6.getString("PhoneNum");
                }
                Bphone.setText(cphone);
                Bcustomer.setText(cname);
                Bcompany.setText(comp);
                Bcode.setText(tcode);
                Bname.setText(Tname);
                BgName.setText(Tgname);
                Bquantity.setText(Integer.toString(Tquantity));
            }
        } catch (Exception e) {
            System.out.println("11" + e);
        }

        gc.gridx = 1;
        gc.gridy = 1;
        add(name, gc);
        gc.gridx = 5;
        gc.gridy = 1;
        add(Bname, gc);
        Bname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BnameActionPerformed(evt);
            }
        });

        gc.gridx = 1;
        gc.gridy = 2;
        add(gName, gc);
        gc.gridx = 5;
        gc.gridy = 2;
        add(BgName, gc);
        BgName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BgNameActionPerformed(evt);
            }
        });
        gc.gridx = 1;
        gc.gridy = 3;
        add(code, gc);
        gc.gridx = 5;
        gc.gridy = 3;
        add(Bcode, gc);
        Bcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BcodeActionPerformed(evt);
            }
        });
        gc.gridx = 1;
        gc.gridy = 4;
        add(company, gc);
        gc.gridx = 5;
        gc.gridy = 4;
        add(Bcompany, gc);
        Bcompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BcompanyActionPerformed(evt);
            }
        });

        gc.gridx = 1;
        gc.gridy = 5;
        add(customer, gc);
        gc.gridx = 5;
        gc.gridy = 5;
        gc.weightx = 1.;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(Bcustomer, gc);
        Bcustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BcustomerActionPerformed(evt);
            }
        });

        gc.gridx = 1;
        gc.gridy = 6;
        add(phone, gc);
        Bquantity.setBounds(5, 10, 3, 1);
        gc.gridx = 5;
        gc.gridy = 6;
        add(Bphone, gc);
        Bphone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BphoneActionPerformed(evt);
            }
        });


        gc.gridx = 1;
        gc.gridy = 8;
        add(quantity, gc);
        Bquantity.setBounds(5, 10, 3, 1);
        gc.gridx = 5;
        gc.gridy = 8;
        add(Bquantity, gc);
        Bquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BquantityActionPerformed(evt);
            }
        });
        gc.gridx = 2;
        gc.gridy = 13;

        add(add, gc);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }

            private void addActionPerformed(ActionEvent evt) {
                Page pg = Page.getInstance();
                pg.Order();
            }
        });
        gc.gridx = 7;
        gc.gridy = 13;
        add(delete, gc);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }

            private void clearActionPerformed(ActionEvent evt) {
                int index = (int) Border.getSelectedItem();
                String oindex = Integer.toString(index);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date date = new java.util.Date();
                String Date = dateFormat.format(date);
                try {
                    String sql4 = "update orders set removed = '" + Date + "' where orderId = '" + oindex + "'";
                    Connection connection4 = DbConnection.createConnection();
                    PreparedStatement pst4 = connection4.prepareStatement(sql4);
                    pst4.executeUpdate();
                } catch (Exception e) {
                    System.out.println("12" + e);
                }
            }
        });
    }

    private void BnameActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BgNameActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BcodeActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BcompanyActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BcustomerActionPerformed(java.awt.event.ActionEvent evt) {
    }

    //defines the actions to be doen whenever the order id combobox has changed
    private void BorderActionPerformed(java.awt.event.ActionEvent evt) {
        int Torder, Tcode, Tcustomer, Tquantity;

        Torder = (int) Border.getSelectedItem();
        String torder = Integer.toString(Torder);


        try {
            String sql = "select * from orders where orderId ='" + Torder + "' and removed is null";
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                Tcode = rs1.getInt("code");
                Tcustomer = rs1.getInt("customerId");
                Tquantity = rs1.getInt("quantity");

                String tcode = Integer.toString(Tcode);

                String Tname = null, Tgname = null;
                int TcompanyId = 0;

                String sql4 = "select * from item where code = '" + tcode + "' and removed is null";
                Connection connection4 = DbConnection.createConnection();
                PreparedStatement pst4 = connection4.prepareStatement(sql4);
                ResultSet rs4 = pst4.executeQuery();
                if (rs4.next()) {
                    Tname = rs4.getString("Name");
                    Tgname = rs4.getString("Generic");
                    TcompanyId = rs4.getInt("CompanyId");
                }
                String companyId = Integer.toString(TcompanyId);
                String comp = null;

                String sql5 = "select * from company where CompanyId = '" + companyId + "' and removed is null";
                Connection connection5 = DbConnection.createConnection();
                PreparedStatement pst5 = connection5.prepareStatement(sql5);
                ResultSet rs5 = pst5.executeQuery();
                if (rs5.next()) {
                    comp = rs5.getString("CompanyName");
                }

                String customerId = Integer.toString(Tcustomer);
                String cname = null, cphone = null;

                String sql6 = "select * from customer where CustomerId = '" + customerId + "' and removed is null";
                Connection connection6 = DbConnection.createConnection();
                PreparedStatement pst6 = connection6.prepareStatement(sql6);
                ResultSet rs6 = pst6.executeQuery();
                if (rs6.next()) {
                    cname = rs6.getString("Name");
                    cphone = rs6.getString("PhoneNum");
                }
                Bphone.setText(cphone);
                Bcustomer.setText(cname);
                Bcompany.setText(comp);
                Bcode.setText(tcode);
                Bname.setText(Tname);
                BgName.setText(Tgname);
                Bquantity.setText(Integer.toString(Tquantity));
            }
        } catch (Exception e) {
            System.out.println("13" + e);
        }
    }

    private void BphoneActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BquantityActionPerformed(java.awt.event.ActionEvent evt) {
    }

    //returns an instance of the class
    public static EditOrder getInstance() {
        if (instance == null) {
            instance = new EditOrder();
        }
        return instance;
    }
}
