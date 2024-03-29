/*
 * The class to search the stock by code number of each item
 */
package AdminGUI;

import Database.DbConnection;
import SMS.TcpSms;
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
public class CodeSearch extends JPanel {

    public static CodeSearch instance = null;
    private JButton add;
    private JButton nameSearch;
    private JButton edit;
    String[] year = new String[100];
    String[] month = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
    String[] day = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
        "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    JComboBox Blcode;
    JTextField BgName;
    JTextField Bcompany;
    JTextField Bname;
    JTextField Bbatch;
    JComboBox BexpiryY;
    JComboBox BexpiryM;
    JComboBox BexpiryD;
    JComboBox BmanuDateY;
    JComboBox BmanuDateM;
    JComboBox BmanuDateD;
    JTextField BcPrice;
    JTextField BsPrice;
    JTextField Bquantity;

    //constructor
    public CodeSearch() {

        this.setLayout(new GridBagLayout());

        for (int i = 0; i < 100; i++) {
            year[i] = Integer.toString(i + 2005);
        }

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        JLabel lcode = new JLabel("Code No :");
        lcode.setPreferredSize(new Dimension(100, 30));
        JLabel gName = new JLabel("Generic Name :");
        gName.setPreferredSize(new Dimension(100, 30));
        JLabel company = new JLabel("Company Name :");
        company.setPreferredSize(new Dimension(100, 30));
        JLabel name = new JLabel("Name :");
        name.setPreferredSize(new Dimension(100, 30));
        JLabel batch = new JLabel("Batch No. :");
        batch.setPreferredSize(new Dimension(100, 30));
        JLabel expiry = new JLabel("Expiry Date :");
        expiry.setPreferredSize(new Dimension(100, 30));
        JLabel manuDate = new JLabel("Manufacturing Date :");
        manuDate.setPreferredSize(new Dimension(100, 30));
        JLabel cPrice = new JLabel("Company Price :");
        cPrice.setPreferredSize(new Dimension(100, 30));
        JLabel sPrice = new JLabel("Selling Price :");
        sPrice.setPreferredSize(new Dimension(100, 30));
        JLabel quantity = new JLabel("Quantity :");
        quantity.setPreferredSize(new Dimension(100, 30));


        Blcode = new JComboBox();
        Blcode.setPreferredSize(new Dimension(220, 30));
        BgName = new JTextField();
        BgName.setPreferredSize(new Dimension(220, 30));
        Bcompany = new JTextField();
        Bcompany.setPreferredSize(new Dimension(220, 30));
        Bname = new JTextField();
        Bname.setPreferredSize(new Dimension(220, 30));
        Bbatch = new JTextField();
        Bbatch.setPreferredSize(new Dimension(220, 30));
        BexpiryY = new JComboBox();
        BexpiryY.setPreferredSize(new Dimension(220, 30));
        BexpiryM = new JComboBox();
        BexpiryM.setPreferredSize(new Dimension(100, 30));
        BexpiryD = new JComboBox();
        BexpiryD.setPreferredSize(new Dimension(100, 30));
        BmanuDateY = new JComboBox();
        BmanuDateY.setPreferredSize(new Dimension(220, 30));
        BmanuDateM = new JComboBox();
        BmanuDateM.setPreferredSize(new Dimension(100, 30));
        BmanuDateD = new JComboBox();
        BmanuDateD.setPreferredSize(new Dimension(100, 30));
        BcPrice = new JTextField();
        BcPrice.setPreferredSize(new Dimension(220, 30));
        BsPrice = new JTextField();
        BsPrice.setPreferredSize(new Dimension(220, 30));
        Bquantity = new JTextField();
        Bquantity.setPreferredSize(new Dimension(220, 30));

        add = new JButton("Add");
        add.setPreferredSize(new Dimension(120, 40));
        add.setFont(new java.awt.Font("Times New Roman", 1, 16));
        add.setBackground(new java.awt.Color(255, 255, 102));
        nameSearch = new JButton("Search by name");
        nameSearch.setPreferredSize(new Dimension(120, 40));
        nameSearch.setFont(new java.awt.Font("Times New Roman", 1, 16));
        nameSearch.setBackground(new java.awt.Color(255, 255, 102));
        edit = new JButton("Edit");
        edit.setBackground(new java.awt.Color(255, 255, 102));
        edit.setPreferredSize(new Dimension(120, 40));
        edit.setFont(new java.awt.Font("Times New Roman", 1, 16));

        gc.gridx = 1;
        gc.gridy = 1;
        add(lcode, gc);
        gc.gridx = 5;
        gc.gridy = 1;
        add(Blcode, gc);

        try {
            String sql1 = "select distinct * from item where removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst1 = connection.prepareStatement(sql1);
            ResultSet rs = pst1.executeQuery();

            int xname = 0;
            while (rs.next()) {
                xname = rs.getInt("code");
                Blcode.addItem(xname);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        String Tname, Tgname, Tcom = null;
        int Tcode = 0, Tcompany;
        Tcode = (int) Blcode.getSelectedItem();

        String sql = "select * from item where code ='" + Tcode + "' and removed is null";
        try {
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                Tname = rs1.getString("Name");
                Tgname = rs1.getString("Generic");
                Tcompany = rs1.getInt("CompanyId");
                String sql2 = "select * from company where CompanyId ='" + Tcompany + "' and removed is null";
                Connection connection2 = DbConnection.createConnection();
                PreparedStatement pst2 = connection2.prepareStatement(sql2);
                ResultSet rs2 = pst2.executeQuery(sql2);
                if (rs2.next()) {
                    Tcom = rs2.getString("CompanyName");
                }

                Bcompany.setText(Tcom);

                Bname.setText(Tname);
                BgName.setText(Tgname);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        Blcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlcodeActionPerformed(evt);
            }
        });
        gc.gridx = 1;
        gc.gridy = 3;
        add(gName, gc);
        gc.gridx = 5;
        gc.gridy = 3;
        add(BgName, gc);


        BgName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BgNameActionPerformed(evt);
            }
        });
        gc.gridx = 1;
        gc.gridy = 2;
        add(name, gc);
        gc.gridx = 5;
        gc.gridy = 2;
        add(Bname, gc);

        Bname.addActionListener(new java.awt.event.ActionListener() {
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
        add(batch, gc);
        gc.gridx = 5;
        gc.gridy = 5;
        gc.weightx = 1.;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(Bbatch, gc);
        Bbatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BbatchActionPerformed(evt);
            }
        });
        gc.gridx = 1;
        gc.gridy = 6;
        add(expiry, gc);
        gc.gridx = 5;
        gc.gridy = 6;
        add(BexpiryY, gc);

        BexpiryY.setModel(new javax.swing.DefaultComboBoxModel(year));
        BexpiryY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BexpiryYActionPerformed(evt);
            }
        });

        gc.gridx = 6;
        gc.gridy = 6;
        add(BexpiryM, gc);
        BexpiryM.setModel(new javax.swing.DefaultComboBoxModel(month));
        BexpiryM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BexpiryMActionPerformed(evt);
            }
        });
        gc.gridx = 7;
        gc.gridy = 6;
        add(BexpiryD, gc);
        BexpiryD.setModel(new javax.swing.DefaultComboBoxModel(day));
        BexpiryD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BexpiryDActionPerformed(evt);
            }
        });
        gc.gridx = 1;
        gc.gridy = 7;
        add(manuDate, gc);
        gc.gridx = 5;
        gc.gridy = 7;
        add(BmanuDateY, gc);
        BmanuDateY.setModel(new javax.swing.DefaultComboBoxModel(year));
        BmanuDateY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BmanuDateYActionPerformed(evt);
            }
        });
        gc.gridx = 6;
        gc.gridy = 7;
        add(BmanuDateM, gc);
        BmanuDateM.setModel(new javax.swing.DefaultComboBoxModel(month));
        BmanuDateM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BmanuDateMActionPerformed(evt);
            }
        });
        gc.gridx = 7;
        gc.gridy = 7;
        add(BmanuDateD, gc);
        BmanuDateD.setModel(new javax.swing.DefaultComboBoxModel(day));
        BmanuDateD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BmanuDateDActionPerformed(evt);
            }
        });
        gc.gridx = 1;
        gc.gridy = 8;
        BcPrice.setBounds(5, 8, 3, 1);
        add(cPrice, gc);
        gc.gridx = 5;
        gc.gridy = 8;
        add(BcPrice, gc);
        BcPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BcPriceActionPerformed(evt);
            }
        });
        gc.gridx = 1;
        gc.gridy = 9;
        add(sPrice, gc);
        BsPrice.setBounds(5, 9, 3, 1);
        gc.gridx = 5;
        gc.gridy = 9;
        add(BsPrice, gc);
        BsPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BsPriceActionPerformed(evt);
            }
        });
        gc.gridx = 1;
        gc.gridy = 10;
        add(quantity, gc);
        Bquantity.setBounds(5, 10, 3, 1);
        gc.gridx = 5;
        gc.gridy = 10;
        add(Bquantity, gc);
        Bquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BquantityActionPerformed(evt);
            }
        });


        gc.gridx = 7;
        gc.gridy = 13;
        add(add, gc);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }

            private void addActionPerformed(ActionEvent evt) {
                try {
                    Connection connection = DbConnection.createConnection();

                    String sql = "insert into section values(?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, null);

                    String batchNo, y, m, d, sPrice, quantity, cPrice, my, mm, md, currentTime;
                    int code;
                    batchNo = Bbatch.getText();
                    pst.setString(2, batchNo);

                    y = (String) BexpiryY.getSelectedItem();
                    m = (String) BexpiryM.getSelectedItem();
                    d = (String) BexpiryD.getSelectedItem();
                    String Edate = getDate(y, m, d);
                    pst.setString(3, Edate);

                    sPrice = BsPrice.getText();
                    pst.setFloat(4, Float.valueOf(sPrice));

                    quantity = Bquantity.getText();
                    pst.setInt(5, Integer.parseInt(quantity));

                    cPrice = BcPrice.getText();
                    pst.setFloat(6, Float.valueOf(cPrice));

                    my = (String) BmanuDateY.getSelectedItem();
                    mm = (String) BmanuDateM.getSelectedItem();
                    md = (String) BmanuDateD.getSelectedItem();
                    String Mdate = getDate(my, mm, md);
                    pst.setString(7, Mdate);

                    code = (int) Blcode.getSelectedItem();
                    pst.setInt(8, code);

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date date = new java.util.Date();
                    currentTime = dateFormat.format(date);
                    pst.setString(9, currentTime);
                    pst.setString(10, null);
                    pst.executeUpdate();

                    String sql6 = "select * from item where code = '" + code + "' and removed is null";
                    Connection connection6 = DbConnection.createConnection();
                    PreparedStatement pst6 = connection6.prepareStatement(sql6);
                    ResultSet rs6 = pst6.executeQuery();
                    int x = 0, e;
                    if (rs6.next()) {
                        x = rs6.getInt("quantity");
                        e = rs6.getInt("eachHas");
                        x += (Integer.parseInt(quantity) * e);
                    }
                    String sql5 = "update item set quantity = '" + x + "' where code ='" + code + "' and removed is null";
                    Connection connection5 = DbConnection.createConnection();
                    PreparedStatement pst5 = connection5.prepareStatement(sql5);
                    pst5.executeUpdate();

                    JOptionPane.showMessageDialog(null, quantity + " " + Integer.toString(code) + ": " + Bname.getText() + " have been added to stock.");

                    Bbatch.setText("");
                    BcPrice.setText("");
                    BsPrice.setText("");
                    Bquantity.setText("");

                    String sql7 = "select * from orders where removed is null";
                    Connection connection7 = DbConnection.createConnection();
                    PreparedStatement pst7 = connection7.prepareStatement(sql7);
                    ResultSet rs7 = pst7.executeQuery();
                    int orderId;
                    while (rs7.next()) {
                        int codeNo = rs7.getInt("code");
                        int Tcode = (int) Blcode.getSelectedItem();
                        if (codeNo == Tcode) {
                            int quan = rs7.getInt("quantity");
                            if (x >= quan) {
                                String itemName = Bname.getText();
                                TcpSms sms = new TcpSms();

                                int customerId = rs7.getInt("customerId");
                                orderId = rs7.getInt("orderId");
                                String orderid = Integer.toString(orderId);
                                String customerid = Integer.toString(customerId);

                                String sql8 = "select * from customer where CustomerId = '" + customerid + "'";
                                Connection connection8 = DbConnection.createConnection();
                                PreparedStatement pst8 = connection8.prepareStatement(sql8);
                                ResultSet rs8 = pst8.executeQuery();
                                if (rs8.next()) {
                                    String phone = rs8.getString("PhoneNum");
                                    sms.sendSMS(phone, itemName);

                                    String sql9 = "update orders set removed = '" + currentTime + "' where orderId = '" + orderid + "'";
                                    Connection connection9 = DbConnection.createConnection();
                                    PreparedStatement pst9 = connection9.prepareStatement(sql9);
                                    pst9.executeUpdate();

                                }


                            }
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        });
        gc.gridx = 6;
        gc.gridy = 13;
        add(edit, gc);
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }

            private void deleteActionPerformed(ActionEvent evt) {
                AdminPage ap = AdminPage.getInstance();
                ap.StockeditCode();

            }
        });

        gc.gridx = 5;
        gc.gridy = 13;
        add(nameSearch, gc);
        nameSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeSearchActionPerformed(evt);
            }

            private void codeSearchActionPerformed(ActionEvent evt) {
                AdminPage ap = AdminPage.getInstance();
                ap.getStock();
            }
        });
        gc.gridx = 2;
        gc.gridy = 14;
    }

    private void BlcodeActionPerformed(java.awt.event.ActionEvent evt) {
        String Tname, Tgname, Tcom = null;
        int Tcode, Tcompany;
        Tcode = (int) Blcode.getSelectedItem();

        String sql = "select * from item where code ='" + Tcode + "' and removed is null";
        try {
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                Tname = rs1.getString("Name");
                Tgname = rs1.getString("Generic");

                Tcompany = rs1.getInt("CompanyId");
                String sql2 = "select * from company where CompanyId ='" + Tcompany + "' and removed is null";
                Connection connection2 = DbConnection.createConnection();
                PreparedStatement pst2 = connection2.prepareStatement(sql2);
                ResultSet rs2 = pst2.executeQuery(sql2);
                if (rs2.next()) {
                    Tcom = rs2.getString("CompanyName");
                }

                Bcompany.setText(Tcom);
                Bname.setText(Tname);
                BgName.setText(Tgname);
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

    private void BbatchActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BexpiryYActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BexpiryMActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BexpiryDActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BmanuDateYActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BmanuDateMActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BmanuDateDActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BcPriceActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BsPriceActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BquantityActionPerformed(java.awt.event.ActionEvent evt) {
    }

    //metod to get the number of the month
    public String getDate(String y, String m, String d) {
        String date;
        String x = null;

        if (m.equals("JAN")) {
            x = "01";
        }
        if (m.equals("FEB")) {
            x = "02";
        }
        if (m.equals("MAR")) {
            x = "03";
        }
        if (m.equals("APR")) {
            x = "04";
        }
        if (m.equals("MAY")) {
            x = "05";
        }
        if (m.equals("JUN")) {
            x = "06";
        }
        if (m.equals("JUL")) {
            x = "07";
        }
        if (m.equals("AUG")) {
            x = "08";
        }
        if (m.equals("SEP")) {
            x = "09";
        }
        if (m.equals("OCT")) {
            x = "10";
        }
        if (m.equals("NOV")) {
            x = "11";
        }
        if (m.equals("DEC")) {
            x = "12";
        }

        date = y + "-" + x + "-" + d;
        return date;
    }

    //method to get the instance of the class
    public static CodeSearch getInstance() {
        if (instance == null) {
            instance = new CodeSearch();
        }
        return instance;
    }
}
