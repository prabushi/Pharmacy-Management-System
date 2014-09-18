/*
 JPanel to bill items according to the quantity going to buy
 */
package UserGUI;

import Database.DbConnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Prabushi
 */
public class BillUp3Panel extends JPanel {

    public static BillUp3Panel instance = null;
    private JButton add;
    private JButton delete;
    private JButton edit;
    Object[][] data;
    JComboBox Bname;
    JTextField Bgname;
    JTextField Bcompany;
    JTextField Bcode;
    JTextField Bbatch;
    JTextField Btype;
    JTextField Bavailable;
    JTextField Btotal;
    JTextField Bexpiry;
    JTextField Bprice;
    JTextField Bquantity;
    JTextArea Bdescription;
    float companyPrice;
    Bill b;

    //constructor
    private BillUp3Panel() {
        this.removeAll();
        this.repaint();
        this.revalidate();
        UIManager.put("ComboBox.background", new ColorUIResource(UIManager.getColor("TextField.background")));
        UIManager.put("ComboBox.foreground", new ColorUIResource(UIManager.getColor("TextField.foreground")));
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new java.awt.Color(255, 255, 102)));
        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.BLACK));
        this.setBackground(new java.awt.Color(255, 255, 255));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        gc.insets = new Insets(10, 10, 10, 10);


        JButton one = new JButton("As in Prescription");
        one.setHorizontalTextPosition(SwingConstants.CENTER);
        one.setForeground(Color.WHITE);
        one.setBackground(new java.awt.Color(102, 0, 102));
        one.setPreferredSize(new java.awt.Dimension(100, 25));
        one.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneActionPerformed(evt);
            }

            private void oneActionPerformed(ActionEvent evt) {
                Page pg = Page.getInstance();
                pg.addBill1();
            }
        });

        JButton two = new JButton("Fixed Quantity");
        two.setForeground(Color.WHITE);
        two.setHorizontalTextPosition(SwingConstants.CENTER);
        two.setBackground(new java.awt.Color(102, 0, 102));
        two.setPreferredSize(new java.awt.Dimension(100, 25));
        two.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoActionPerformed(evt);
            }

            private void twoActionPerformed(ActionEvent evt) {
                Page pg = Page.getInstance();
                pg.addBill2();
            }
        });

        JButton three = new JButton("Fixed Amount");
        three.setForeground(Color.WHITE);
        three.setHorizontalTextPosition(SwingConstants.CENTER);
        three.setBackground(new java.awt.Color(102, 0, 102));
        three.setPreferredSize(new java.awt.Dimension(100, 25));
        three.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threeActionPerformed(evt);
            }

            private void threeActionPerformed(ActionEvent evt) {
                Page pg = Page.getInstance();
                pg.addBill3();
            }
        });


        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.gridx = 1;
        gc.gridy = 1;
        this.add(one, gc);


        gc.gridx = 3;
        gc.gridy = 1;
        this.add(two, gc);

        gc.gridx = 5;
        gc.gridy = 1;
        this.add(three, gc);

        gc.weightx = 0.0;

        JLabel name = new JLabel("Name :");
        name.setPreferredSize(new Dimension(110, 25));
        JLabel gName = new JLabel("Generic Name :");
        gName.setPreferredSize(new Dimension(110, 25));
        JLabel company = new JLabel("Company Name :");
        company.setPreferredSize(new Dimension(110, 25));
        JLabel code = new JLabel("Code No. :");
        code.setPreferredSize(new Dimension(110, 25));
        JLabel batch = new JLabel("Batch No. :");
        batch.setPreferredSize(new Dimension(110, 25));
        JLabel type = new JLabel("Type :");
        type.setPreferredSize(new Dimension(110, 25));
        JLabel available = new JLabel("Available Amount :");
        available.setPreferredSize(new Dimension(110, 25));
        JLabel quantity = new JLabel("Total :");
        quantity.setPreferredSize(new Dimension(110, 25));
        JLabel description = new JLabel("Description :");
        description.setPreferredSize(new Dimension(110, 25));
        JLabel expiry = new JLabel("Expiry :");
        expiry.setPreferredSize(new Dimension(110, 25));
        JLabel price = new JLabel("Price :");
        price.setPreferredSize(new Dimension(110, 25));
        JLabel total = new JLabel("Quantity :");
        total.setPreferredSize(new Dimension(110, 25));

        Bname = new JComboBox();
        Bname.setPreferredSize(new java.awt.Dimension(220, 25));
        Bgname = new JTextField();
        Bgname.setPreferredSize(new java.awt.Dimension(220, 25));
        Bcompany = new JTextField();
        Bcompany.setPreferredSize(new java.awt.Dimension(220, 25));
        Bcode = new JTextField();
        Bcode.setPreferredSize(new java.awt.Dimension(220, 25));
        Bbatch = new JTextField();
        Bbatch.setPreferredSize(new java.awt.Dimension(220, 25));
        Btype = new JTextField();
        Btype.setPreferredSize(new java.awt.Dimension(220, 25));
        Bavailable = new JTextField();
        Btype.setPreferredSize(new java.awt.Dimension(220, 25));
        Btotal = new JTextField();
        Btotal.setPreferredSize(new java.awt.Dimension(220, 25));
        Bdescription = new JTextArea();
        Bdescription.setPreferredSize(new java.awt.Dimension(220, 25));
        Bexpiry = new JTextField();
        Bexpiry.setPreferredSize(new java.awt.Dimension(220, 25));
        Bprice = new JTextField();
        Bprice.setPreferredSize(new java.awt.Dimension(220, 25));
        this.Bquantity = new JTextField();
        this.Bquantity.setPreferredSize(new java.awt.Dimension(220, 25));

        add = new JButton("Add");
        add.setBackground(new java.awt.Color(255, 255, 102));
        delete = new JButton("Delete");
        delete.setBackground(new java.awt.Color(255, 255, 102));
        edit = new JButton("Edit");
        edit.setBackground(new java.awt.Color(255, 255, 102));

        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = -1;
        gc.gridy = 6;
        add(name, gc);
        gc.gridx = 1;
        gc.gridy = 6;
        add(Bname, gc);

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
            System.out.println("2" + e);
        }

        String Tname, Tgname, Tcompany = null, Tdes, Ttype = null, Tbatch;
        int Tcode, Tcom, Ttyp, TeachHas, Tavailable;
        float Tprice = 0;
        Tname = (String) Bname.getSelectedItem();

        String sql = "select * from item where Name ='" + Tname + "' and removed is null";
        try {
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                Tcode = rs1.getInt("code");
                Tgname = rs1.getString("Generic");
                Tcom = rs1.getInt("CompanyId");
                Ttyp = rs1.getInt("Type");
                Tdes = rs1.getString("Description");
                TeachHas = rs1.getInt("eachHas");
                Tavailable = rs1.getInt("quantity");

                String sql4 = "select * from company where CompanyId = '" + Tcom + "' and removed is null";
                Connection connection4 = DbConnection.createConnection();
                PreparedStatement pst4 = connection4.prepareStatement(sql4);
                ResultSet rs4 = pst4.executeQuery();
                if (rs4.next()) {
                    Tcompany = rs4.getString("CompanyName");
                }
                String sql5 = "select * from typelist where TypeId = '" + Ttyp + "'";
                Connection connection5 = DbConnection.createConnection();
                PreparedStatement pst5 = connection5.prepareStatement(sql5);
                ResultSet rs5 = pst5.executeQuery();
                if (rs5.next()) {
                    Ttype = rs5.getString("type");
                }

                Bcode.setText(Integer.toString(Tcode));
                Bgname.setText(Tgname);
                Bcompany.setText(Tcompany);
                Bdescription.setText(Tdes);
                Btype.setText(Ttype);
                Bavailable.setText(Integer.toString(Tavailable));

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String sql6 = "select * from section where code = '" + Tcode + "' and removed is null";
                Connection connection6 = DbConnection.createConnection();
                PreparedStatement pst6 = connection6.prepareStatement(sql6);
                ResultSet rs6 = pst6.executeQuery();
                LinkedList<Date> exp = new LinkedList<Date>();
                String x;

                while (rs6.next()) {
                    x = rs6.getString("ExpiryDate");
                    exp.add(formatter.parse(x));
                }


                Collections.sort(exp);
                String date = formatter.format(exp.get(0));
                Bexpiry.setText(date);

                String sql7 = "select * from (select * from section where removed is null and code = '" + Tcode + "')as t where t.ExpiryDate = '" + date + "'";
                Connection connection7 = DbConnection.createConnection();
                PreparedStatement pst7 = connection7.prepareStatement(sql7);
                ResultSet rs7 = pst7.executeQuery();

                if (rs7.next()) {
                    Tbatch = rs7.getString("BatchNo");
                    Bbatch.setText(Tbatch);
                    Tprice = rs7.getFloat("SellingPrice");
                    companyPrice = rs7.getFloat("WholesalePrice");
                    Bprice.setText(Float.toString(Tprice));
                }
                Btotal.setText("0");
                String q = Btotal.getText();
                float t = Float.parseFloat(q) / Tprice;
                int a = (int) t;
                this.Bquantity.setText(Integer.toString(a));
            }
        } catch (Exception e) {
            System.out.println("4" + e);
        }
        Bname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BnameActionPerformed(evt);
            }

            private void BnameActionPerformed(ActionEvent evt) {
                Bbatch.setText("");
                Bprice.setText("");
                Bexpiry.setText("");
                Bgname.setText("");
                Bcode.setText("");
                Bcompany.setText("");
                Btype.setText("");
                Bavailable.setText("");
                Bdescription.setText("");
                Btotal.setText("");
                Bquantity.setText("");

                String Tname, Tgname, Tcompany = null, Tdes, Ttype = null, Tbatch;
                int Tcode, Tcom, Ttyp, TeachHas, Tavailable;
                float Tprice;
                Tname = (String) Bname.getSelectedItem();

                String sql = "select * from item where Name ='" + Tname + "' and removed is null";
                try {
                    Connection connection1 = DbConnection.createConnection();
                    PreparedStatement pst = connection1.prepareStatement(sql);
                    ResultSet rs1 = pst.executeQuery(sql);

                    if (rs1.next()) {
                        Tcode = rs1.getInt("code");
                        Tgname = rs1.getString("Generic");
                        Tcom = rs1.getInt("CompanyId");
                        Ttyp = rs1.getInt("Type");
                        Tdes = rs1.getString("Description");
                        TeachHas = rs1.getInt("eachHas");
                        Tavailable = rs1.getInt("quantity");

                        String sql4 = "select * from company where CompanyId = '" + Tcom + "' and removed is null";
                        Connection connection4 = DbConnection.createConnection();
                        PreparedStatement pst4 = connection4.prepareStatement(sql4);
                        ResultSet rs4 = pst4.executeQuery();
                        if (rs4.next()) {
                            Tcompany = rs4.getString("CompanyName");
                        }
                        String sql5 = "select * from typelist where TypeId = '" + Ttyp + "'";
                        Connection connection5 = DbConnection.createConnection();
                        PreparedStatement pst5 = connection5.prepareStatement(sql5);
                        ResultSet rs5 = pst5.executeQuery();
                        if (rs5.next()) {
                            Ttype = rs5.getString("type");
                        }

                        Bcode.setText(Integer.toString(Tcode));
                        Bgname.setText(Tgname);
                        Bcompany.setText(Tcompany);
                        Bdescription.setText(Tdes);
                        Btype.setText(Ttype);
                        Bavailable.setText(Integer.toString(Tavailable));

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String sql6 = "select * from section where code = '" + Tcode + "' and removed is null";
                        Connection connection6 = DbConnection.createConnection();
                        PreparedStatement pst6 = connection6.prepareStatement(sql6);
                        ResultSet rs6 = pst6.executeQuery();
                        LinkedList<Date> exp = new LinkedList<Date>();
                        String x;

                        while (rs6.next()) {
                            x = rs6.getString("ExpiryDate");
                            exp.add(formatter.parse(x));
                        }

                        Collections.sort(exp);
                        String date = formatter.format(exp.get(0));
                        Bexpiry.setText(date);

                        String sql7 = "select * from (select * from section where removed is null and code = '" + Tcode + "')as t where t.ExpiryDate = '" + date + "'";
                        Connection connection7 = DbConnection.createConnection();
                        PreparedStatement pst7 = connection7.prepareStatement(sql7);
                        ResultSet rs7 = pst7.executeQuery();

                        if (rs7.next()) {
                            Tbatch = rs7.getString("BatchNo");
                            Bbatch.setText(Tbatch);
                            Tprice = rs7.getFloat("SellingPrice");
                            companyPrice = rs7.getFloat("WholesalePrice");
                            Bprice.setText(Float.toString(Tprice));
                        }

                    }
                } catch (Exception e) {
                    System.out.println("5" + e);
                }
            }
        });
        gc.gridx = -1;
        gc.gridy = 7;
        add(gName, gc);
        gc.gridx = 1;
        gc.gridy = 7;
        add(Bgname, gc);


        Bgname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BgNameActionPerformed(evt);
            }

            private void BgNameActionPerformed(ActionEvent evt) {
            }
        });
        gc.gridx = -1;
        gc.gridy = 8;
        Bcode.setBounds(1, 3, 5, 1);
        add(code, gc);
        gc.gridx = 1;
        gc.gridy = 8;
        add(Bcode, gc);
        Bcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BcodeActionPerformed(evt);
            }

            private void BcodeActionPerformed(ActionEvent evt) {
            }
        });
        gc.gridx = -1;
        gc.gridy = 9;
        add(company, gc);
        gc.gridx = 1;
        gc.gridy = 9;
        add(Bcompany, gc);

        Bcompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BcompanyActionPerformed(evt);
            }

            private void BcompanyActionPerformed(ActionEvent evt) {
            }
        });
        gc.gridx = 2;
        gc.gridy = 6;
        add(batch, gc);
        gc.gridx = 3;
        gc.gridy = 6;
        gc.weightx = 1.0;

        add(Bbatch, gc);
        Bbatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BbatchActionPerformed(evt);
            }

            private void BbatchActionPerformed(ActionEvent evt) {
            }
        });
        gc.gridx = 2;
        gc.gridy = 7;
        add(type, gc);
        gc.gridx = 3;
        gc.gridy = 7;
        add(Btype, gc);

        Btype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtypeYActionPerformed(evt);
            }

            private void BtypeYActionPerformed(ActionEvent evt) {
            }
        });
        gc.gridx = 2;
        gc.gridy = 8;
        add(available, gc);
        gc.gridx = 3;
        gc.gridy = 8;
        add(Bavailable, gc);

        Bavailable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BavailableYActionPerformed(evt);
            }

            private void BavailableYActionPerformed(ActionEvent evt) {
            }
        });
        gc.gridx = 2;
        gc.gridy = 9;
        add(expiry, gc);
        gc.gridx = 3;
        gc.gridy = 9;
        add(Bexpiry, gc);

        Bexpiry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BexpiryActionPerformed(evt);
            }

            private void BexpiryActionPerformed(ActionEvent evt) {
            }
        });

        gc.gridx = 4;
        gc.gridy = 6;
        add(description, gc);
        gc.gridx = 5;
        gc.gridy = 6;
        add(Bdescription, gc);

        gc.gridx = 4;
        gc.gridy = 7;
        add(price, gc);
        gc.gridx = 5;
        gc.gridy = 7;
        add(Bprice, gc);

        Bprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BpriceActionPerformed(evt);
            }

            private void BpriceActionPerformed(ActionEvent evt) {
            }
        });

        gc.gridx = 4;
        gc.gridy = 8;
        add(quantity, gc);
        gc.gridx = 5;
        gc.gridy = 8;
        add(Btotal, gc);

        Btotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtotalActionPerformed(evt);
            }

            private void BtotalActionPerformed(ActionEvent evt) {

                Bquantity.setText("");
                String q = Btotal.getText();
                float Tprice, tot;
                String t = Bprice.getText();
                Tprice = Float.parseFloat(t);
                tot = Integer.parseInt(q) / Tprice;
                int quan = (int) tot;
                Bquantity.setText(Integer.toString(quan));
            }
        });

        gc.gridx = 4;
        gc.gridy = 9;
        add(total, gc);

        gc.gridx = 5;
        gc.gridy = 9;
        add(this.Bquantity, gc);

        this.Bquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtotalActionPerformed(evt);
            }

            private void BtotalActionPerformed(ActionEvent evt) {
            }
        });

        gc.gridx = 1;
        gc.gridy = 10;
        add(add, gc);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }

            private void addActionPerformed(ActionEvent evt) {
                String total = Btotal.getText();
                String each = Bprice.getText();
                float temptotal = Float.valueOf(total);
                float tempeach = Float.valueOf(each);
                int tempquan = (int) (temptotal / tempeach);
                String tempQuan = Integer.toString(tempquan);
                String tempName = (String) Bname.getSelectedItem();
                try {
                    String sql = "select * from item where Name = '" + tempName + "'";
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {

                        int available = rs.getInt("quantity");

                        if (tempquan <= available) {

                            Bill bill = Bill.getInstance();
                            DefaultTableModel model = bill.model;

                            String Pri = Bprice.getText();
                            float tempPri = Float.parseFloat(Pri);
                            float tempTot = tempquan * tempPri;
                            String tempTotal = Float.toString(tempTot);
                            float tempprofit = tempTot - companyPrice * tempquan;

                            Item item = new Item(tempName, Pri, tempQuan, tempTotal, Float.toString(tempprofit));
                            bill.billingList.add(item);
                            model.insertRow(model.getRowCount(), new Object[]{tempName, Pri, tempQuan, tempTotal});
                            bill.getTotal();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("6" + e);
                }
            }
        });

        gc.gridx = 3;
        gc.gridy = 10;
        add(edit, gc);
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }

            private void editActionPerformed(ActionEvent evt) {
                Bill bill = Bill.getInstance();
                DefaultTableModel model = bill.model;

                String total = Btotal.getText();
                String each = Bprice.getText();
                float temptotal = Float.valueOf(total);
                float tempeach = Float.valueOf(each);
                int tempquan = (int) (temptotal / tempeach);
                String tempQuan = Integer.toString(tempquan);


                String tempName = (String) Bname.getSelectedItem();

                try {
                    String sql = "select * from item where Name = '" + tempName + "'";
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {

                        int available = rs.getInt("quantity");

                        if (tempquan <= available) {
                            String Pri = Bprice.getText();
                            float tempPri = Float.parseFloat(Pri);
                            float tempTot = tempquan * tempPri;
                            float tempprofit = tempTot - tempquan * companyPrice;
                            String tempProfit = Float.toString(tempprofit);

                            String tempTotal = Float.toString(tempTot);

                            for (int i = 0; i < bill.billingList.size(); i++) {
                                Item item = bill.billingList.get(i);
                                if (tempName.equals(item.getName())) {
                                    item.setPrice(Pri);
                                    item.setQuantity(tempQuan);
                                    item.setTotal(tempTotal);
                                    item.setProfit(tempProfit);
                                }
                            }
                            model.setValueAt(tempName, bill.table.getSelectedRow(), 0);
                            model.setValueAt(Pri, bill.table.getSelectedRow(), 1);
                            model.setValueAt(tempQuan, bill.table.getSelectedRow(), 2);
                            model.setValueAt(tempTotal, bill.table.getSelectedRow(), 3);
                            bill.getTotal();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("7" + e);
                }
            }
        });

        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.gridx = 5;
        gc.gridy = 10;
        add(delete, gc);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }

            private void clearActionPerformed(ActionEvent evt) {
                Bill bill = Bill.getInstance();
                DefaultTableModel model = bill.model;
                model.removeRow(bill.table.getSelectedRow());
                String tempName = (String) Bname.getSelectedItem();
                String tempTotal = (String) Bquantity.getText();

                for (int i = 0; i < bill.billingList.size(); i++) {
                    Item item = bill.billingList.get(i);
                    if (tempName.equals(item.getName()) && tempTotal.equals(item.getTotal())) {
                        bill.billingList.remove(i);
                    }
                }
                bill.getTotal();
            }
        });

        gc.gridx = 0;
        gc.gridy = 11;
        b = Bill.getInstance();
        b.setLayout(new FlowLayout());

        add(b, gc);
        b.setVisible(true);
        this.repaint();
        this.revalidate();
    }

    //Method to set the revelent details tp the textfiels and comboboxes when a item is selected(mouse clicked) from the table
    public void setTable() {
        Bill bill = Bill.getInstance();
        String Tname;
        Tname = String.valueOf(bill.model.getValueAt(bill.table.getSelectedRow(), 0));
        Bname.setSelectedItem(Tname);
        Bquantity.setText(String.valueOf(bill.model.getValueAt(bill.table.getSelectedRow(), 2)));
        Btotal.setText(String.valueOf(bill.model.getValueAt(bill.table.getSelectedRow(), 3)));

        String Tgname, Tcompany = null, Tdes, Ttype = null, Tbatch;
        int Tcode, Tcom, Ttyp, TeachHas, Tavailable;
        float Tprice = 0;
        String sql = "select * from item where Name ='" + Tname + "' and removed is null";

        try {
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                Tcode = rs1.getInt("code");
                Tgname = rs1.getString("Generic");
                Tcom = rs1.getInt("CompanyId");
                Ttyp = rs1.getInt("Type");
                Tdes = rs1.getString("Description");
                TeachHas = rs1.getInt("eachHas");
                Tavailable = rs1.getInt("quantity");

                String sql4 = "select * from company where CompanyId = '" + Tcom + "' and removed is null";
                Connection connection4 = DbConnection.createConnection();
                PreparedStatement pst4 = connection4.prepareStatement(sql4);
                ResultSet rs4 = pst4.executeQuery();
                if (rs4.next()) {
                    Tcompany = rs4.getString("CompanyName");
                }
                String sql5 = "select * from typelist where TypeId = '" + Ttyp + "'";
                Connection connection5 = DbConnection.createConnection();
                PreparedStatement pst5 = connection5.prepareStatement(sql5);
                ResultSet rs5 = pst5.executeQuery();
                if (rs5.next()) {
                    Ttype = rs5.getString("type");
                }

                Bcode.setText(Integer.toString(Tcode));
                Bgname.setText(Tgname);
                Bcompany.setText(Tcompany);
                Bdescription.setText(Tdes);
                Btype.setText(Ttype);
                Bavailable.setText(Integer.toString(Tavailable));

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String sql6 = "select * from section where code = '" + Tcode + "' and removed is null";
                Connection connection6 = DbConnection.createConnection();
                PreparedStatement pst6 = connection6.prepareStatement(sql6);
                ResultSet rs6 = pst6.executeQuery();
                LinkedList<Date> exp = new LinkedList<Date>();
                String x;

                while (rs6.next()) {
                    x = rs6.getString("ExpiryDate");
                    exp.add(formatter.parse(x));

                }

                Collections.sort(exp);
                String date = formatter.format(exp.get(0));
                Bexpiry.setText(date);

                String sql7 = "select * from (select * from section where removed is null and code = '" + Tcode + "')as t where t.ExpiryDate = '" + date + "'";
                Connection connection7 = DbConnection.createConnection();
                PreparedStatement pst7 = connection7.prepareStatement(sql7);
                ResultSet rs7 = pst7.executeQuery();

                if (rs7.next()) {
                    Tbatch = rs7.getString("BatchNo");
                    Bbatch.setText(Tbatch);
                    Tprice = rs7.getFloat("SellingPrice");
                    //System.out.println(Float.toString(Tprice));
                    Bprice.setText(Float.toString(Tprice));
                }

            }
        } catch (Exception e) {
            System.out.println("1" + e);
        }

    }

    //retruns an instance of the class
    public static BillUp3Panel getInstance() {
        if (instance == null) {
            instance = new BillUp3Panel();
        }
        return instance;
    }
}
