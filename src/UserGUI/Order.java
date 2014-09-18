/*
 *Class to create an order
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
public class Order extends JPanel {

    public static Order instance = null;
    private JButton add;
    private JButton search;
    JTextField Bphone;
    JTextField Baddress;
    JComboBox Bname;
    JTextField BgName;
    JTextField Bcompany;
    JTextField Bcode;
    JComboBox Bcustomer;
    JTextField Bquantity;

    //constructor
    private Order() {
        this.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(10, 10, 10, 10);

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
        JLabel address = new JLabel("Address :");
        address.setPreferredSize(new Dimension(100, 30));

        Bname = new JComboBox();
        Bname.setPreferredSize(new Dimension(220, 30));
        BgName = new JTextField();
        BgName.setPreferredSize(new Dimension(220, 30));
        Bcompany = new JTextField();
        Bcompany.setPreferredSize(new Dimension(220, 30));
        Bcode = new JTextField();
        Bcode.setPreferredSize(new Dimension(220, 30));
        Bcustomer = new JComboBox();
        Bcustomer.setPreferredSize(new Dimension(220, 30));
        Bquantity = new JTextField();
        Bquantity.setPreferredSize(new Dimension(220, 30));
        Bphone = new JTextField();
        Bphone.setPreferredSize(new Dimension(220, 30));
        Baddress = new JTextField();
        Baddress.setPreferredSize(new Dimension(220, 30));

        add = new JButton("Add");
        add.setPreferredSize(new Dimension(120, 40));
        add.setFont(new java.awt.Font("Times New Roman", 1, 16));
        add.setBackground(new java.awt.Color(255, 255, 102));
        search = new JButton("Search");
        search.setPreferredSize(new Dimension(120, 40));
        search.setFont(new java.awt.Font("Times New Roman", 1, 16));
        search.setBackground(new java.awt.Color(255, 255, 102));

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
        try {
            String sql1 = "select distinct * from item where removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst1 = connection.prepareStatement(sql1);
            ResultSet rs = pst1.executeQuery();

            String xname = null;
            while (rs.next()) {
                xname = rs.getString("Name");
                Bname.addItem(xname);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        String Tname, Tgname, Tcompany = null;
        int Tcode, Tcom;
        Tname = (String) Bname.getSelectedItem();


        try {
            String sql = "select * from item where Name ='" + Tname + "' and removed is null";
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                Tcode = rs1.getInt("code");
                Tgname = rs1.getString("Generic");
                Tcom = rs1.getInt("CompanyId");

                String sql4 = "select * from company where CompanyId = '" + Tcom + "' and removed is null";
                Connection connection4 = DbConnection.createConnection();
                PreparedStatement pst4 = connection4.prepareStatement(sql4);
                ResultSet rs4 = pst4.executeQuery();
                if (rs4.next()) {
                    Tcompany = rs4.getString("CompanyName");
                }
                Bcode.setText(Integer.toString(Tcode));
                BgName.setText(Tgname);
                Bcompany.setText(Tcompany);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

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

        try {
            String sql1 = "select distinct * from customer where removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst1 = connection.prepareStatement(sql1);
            ResultSet rs = pst1.executeQuery();

            String xcus = null;
            while (rs.next()) {
                xcus = rs.getString("Name");
                Bcustomer.addItem(xcus);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        String custom = (String) Bcustomer.getSelectedItem();
        String ph, addr;
        try {
            String sql = "select * from customer where Name ='" + custom + "' and removed is null";
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                ph = rs1.getString("PhoneNum");
                addr = rs1.getString("Address");

                Bphone.setText(ph);
                Baddress.setText(addr);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

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
        gc.gridy = 7;
        add(address, gc);
        Bquantity.setBounds(5, 10, 3, 1);
        gc.gridx = 5;
        gc.gridy = 7;
        add(Baddress, gc);
        Baddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BaddressActionPerformed(evt);
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
                String cod = Bcode.getText();
                String qun = Bquantity.getText();
                String customer = (String) Bcustomer.getSelectedItem();
                int index = 0;
                try {
                    String sql1 = "select * from customer where Name ='" + customer + "' and removed is null";
                    Connection connection1 = DbConnection.createConnection();
                    PreparedStatement pst1 = connection1.prepareStatement(sql1);
                    ResultSet rs1 = pst1.executeQuery(sql1);

                    if (rs1.next()) {
                        index = rs1.getInt("CustomerId");

                    }

                    Connection connection = DbConnection.createConnection();
                    String sql = "insert into orders values(?,?,?,?,?,?)";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, null);
                    pst.setInt(2, index);
                    pst.setInt(3, Integer.parseInt(cod));
                    pst.setInt(4, Integer.parseInt(qun));
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date date = new java.util.Date();
                    pst.setString(5, dateFormat.format(date));
                    pst.setString(6, null);
                    pst.executeUpdate();

                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        });
        gc.gridx = 7;
        gc.gridy = 13;
        add(search, gc);
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }

            private void clearActionPerformed(ActionEvent evt) {
                Page pg = Page.getInstance();
                pg.editOrder();

            }
        });
    }

    //define actions to perform whenever an item is selected
    private void BnameActionPerformed(java.awt.event.ActionEvent evt) {
        String Tname, Tgname, Tcompany = null;
        int Tcode, Tcom;
        Tname = (String) Bname.getSelectedItem();


        try {
            String sql = "select * from item where Name ='" + Tname + "' and removed is null";
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                Tcode = rs1.getInt("code");
                Tgname = rs1.getString("Generic");
                Tcom = rs1.getInt("CompanyId");

                String sql4 = "select * from company where CompanyId = '" + Tcom + "' and removed is null";
                Connection connection4 = DbConnection.createConnection();
                PreparedStatement pst4 = connection4.prepareStatement(sql4);
                ResultSet rs4 = pst4.executeQuery();
                if (rs4.next()) {
                    Tcompany = rs4.getString("CompanyName");
                }
                Bcode.setText(Integer.toString(Tcode));
                BgName.setText(Tgname);
                Bcompany.setText(Tcompany);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void BgNameActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BcodeActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BcompanyActionPerformed(java.awt.event.ActionEvent evt) {
    }
//define the actions to do whenever an customer is selected

    private void BcustomerActionPerformed(java.awt.event.ActionEvent evt) {
        String custom = (String) Bcustomer.getSelectedItem();
        String ph, add;
        try {
            String sql = "select * from customer where Name ='" + custom + "' and removed is null";
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                ph = rs1.getString("PhoneNum");
                add = rs1.getString("Address");

                Bphone.setText(ph);
                Baddress.setText(add);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void BaddressActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BphoneActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BquantityActionPerformed(java.awt.event.ActionEvent evt) {
    }

    //returns an instance of the class
    public static Order getInstance() {
        if (instance == null) {
            instance = new Order();
        }
        return instance;
    }
}
