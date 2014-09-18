/*
 * Class to edit stock items by its name
 */
package AdminGUI;

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
 * @author Prabushi
 */
public class StockEditName extends JPanel {

    public static StockEditName instance = null;
    private JButton add;
    private JButton codeSearch;
    private JButton delete;
    JComboBox Bname;
    JTextField BgName;
    JTextField Bcompany;
    JTextField Bcode;
    JComboBox Bbatch;
    JTextField Bexpiry;
    JTextField BmanuDate;
    JTextField BcPrice;
    JTextField BsPrice;
    JTextField Bquantity;

    //constructor
    public StockEditName() {

        this.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        JLabel name = new JLabel("Name :");
        name.setPreferredSize(new Dimension(100, 30));
        JLabel gName = new JLabel("Generic Name :");
        gName.setPreferredSize(new Dimension(100, 30));
        JLabel company = new JLabel("Company Name :");
        company.setPreferredSize(new Dimension(100, 30));
        JLabel code = new JLabel("Code No. :");
        code.setPreferredSize(new Dimension(100, 30));
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

        Bname = new JComboBox();
        Bname.setPreferredSize(new Dimension(220, 30));
        BgName = new JTextField();
        BgName.setPreferredSize(new Dimension(220, 30));
        Bcompany = new JTextField();
        Bcompany.setPreferredSize(new Dimension(220, 30));
        Bcode = new JTextField();
        Bcode.setPreferredSize(new Dimension(220, 30));
        Bbatch = new JComboBox();
        Bbatch.setPreferredSize(new Dimension(220, 30));
        Bexpiry = new JTextField();
        Bexpiry.setPreferredSize(new Dimension(220, 30));
        BmanuDate = new JTextField();
        BmanuDate.setPreferredSize(new Dimension(220, 30));
        BcPrice = new JTextField();
        BcPrice.setPreferredSize(new Dimension(220, 30));
        BsPrice = new JTextField();
        BsPrice.setPreferredSize(new Dimension(220, 30));
        Bquantity = new JTextField();
        Bquantity.setPreferredSize(new Dimension(220, 30));

        Bexpiry.setText("");
        BmanuDate.setText("");
        BcPrice.setText("");
        BsPrice.setText("");
        Bquantity.setText("");

        add = new JButton("Add New");
        add.setBackground(new java.awt.Color(255, 255, 102));
        add.setPreferredSize(new Dimension(120, 40));
        add.setFont(new java.awt.Font("Times New Roman", 1, 16));
        codeSearch = new JButton("Search by code");
        codeSearch.setBackground(new java.awt.Color(255, 255, 102));
        codeSearch.setPreferredSize(new Dimension(120, 40));
        codeSearch.setFont(new java.awt.Font("Times New Roman", 1, 16));
        delete = new JButton("Delete");
        delete.setBackground(new java.awt.Color(255, 255, 102));
        delete.setPreferredSize(new Dimension(120, 40));
        delete.setFont(new java.awt.Font("Times New Roman", 1, 16));

        gc.gridx = 1;
        gc.gridy = 1;
        add(name, gc);
        gc.gridx = 5;
        gc.gridy = 1;
        add(Bname, gc);

        try {
            String sql1 = "select distinct Name from item where removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst1 = connection.prepareStatement(sql1);
            ResultSet rs = pst1.executeQuery();
            Bname.removeAllItems();
            String xname = null;
            while (rs.next()) {
                xname = rs.getString("Name");
                Bname.addItem(xname);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        String Tname, Tgname, Tcompany = null, Ttcode = null;
        int Tcode = 0, Tcom;
        Tname = (String) Bname.getSelectedItem();

        String sql = "select * from item where Name ='" + Tname + "' and removed is null";
        try {
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                Tcode = rs1.getInt("code");
                Ttcode = Integer.toString(Tcode);
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

            String sql2 = "select distinct BatchNo from (select * from section where removed is null) as t where t.code = '" + Ttcode + "'";
            Connection connection2 = DbConnection.createConnection();
            PreparedStatement pst2 = connection2.prepareStatement(sql2);
            ResultSet rs2 = pst2.executeQuery(sql2);
            Bbatch.removeAllItems();
            String xbatch = null;
            while (rs2.next()) {
                xbatch = rs2.getString("BatchNo");
                Bbatch.addItem(xbatch);
            }

            String Tbatch, Texpiry, Tmanu;
            float Tsprice, Tcprice;
            int Tquantity;
            Tbatch = (String) Bbatch.getSelectedItem();
            String sql3 = "select * from (select * from section where removed is null) as t where t.BatchNo = '" + Tbatch + "' and t.code = '" + Ttcode + "'";

            Connection connection3 = DbConnection.createConnection();
            PreparedStatement pst3 = connection3.prepareStatement(sql3);
            ResultSet rs3 = pst3.executeQuery(sql3);

            if (rs3.next()) {
                Texpiry = rs3.getString("ExpiryDate");
                Tmanu = rs3.getString("DateofManu");
                Tsprice = rs3.getFloat("SellingPrice");
                Tcprice = rs3.getFloat("WholesalePrice");
                Tquantity = rs3.getInt("Quantity");
                Bexpiry.setText(Texpiry);
                BmanuDate.setText(Tmanu);
                BsPrice.setText(Float.toString(Tsprice));
                BcPrice.setText(Float.toString(Tcprice));
                Bquantity.setText(Integer.toString(Tquantity));
            }


        } catch (Exception e) {
            System.out.println(e);
        }


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
        Bcode.setBounds(1, 3, 5, 1);
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
        add(Bexpiry, gc);

        Bexpiry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BexpiryYActionPerformed(evt);
            }
        });

        gc.gridx = 1;
        gc.gridy = 7;
        add(manuDate, gc);
        gc.gridx = 5;
        gc.gridy = 7;
        add(BmanuDate, gc);
        BmanuDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BmanuDateYActionPerformed(evt);
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

        gc.gridx = 2;
        gc.gridy = 13;
        add(add, gc);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }

            private void addActionPerformed(ActionEvent evt) {
                AdminPage ap = AdminPage.getInstance();
                ap.getStock();

            }
        });
        gc.gridx = 8;
        gc.gridy = 13;
        add(delete, gc);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }

            private void deleteActionPerformed(ActionEvent evt) {
                String tempbatch, tempCode;
                tempbatch = (String) Bbatch.getSelectedItem();
                tempCode = (String) Bcode.getText();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date date = new java.util.Date();
                String tempDate = dateFormat.format(date);

                String sql = "update section set removed = '" + tempDate + "' where code ='" + tempCode + "' and BatchNo = '" + tempbatch + "'";
                try {
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.executeUpdate(sql);

                    JOptionPane.showMessageDialog(null, tempCode + ": " + tempbatch + " has been deleted.");

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        gc.gridx = 5;
        gc.gridy = 13;
        add(codeSearch, gc);
        codeSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeSearchActionPerformed(evt);
            }

            private void codeSearchActionPerformed(ActionEvent evt) {
                AdminPage ap = AdminPage.getInstance();
                ap.codeSearch();
            }
        });
        gc.gridx = 2;
        gc.gridy = 14;

    }

    //defines the action when ever an item name is selected
    private void BnameActionPerformed(java.awt.event.ActionEvent evt) {
        String Tname, Tgname, Tcompany = null, Ttcode = null;
        int Tcode, Tcom;
        Tname = (String) Bname.getSelectedItem();

        String sql = "select * from item where Name ='" + Tname + "' and removed is null";
        try {
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                Tcode = rs1.getInt("code");
                Ttcode = Integer.toString(Tcode);
                Tgname = rs1.getString("Generic");
                Tcom = rs1.getInt("CompanyId");

                String sql4 = "select * from company where CompanyId = '" + Tcom + "' and removed is null";
                Connection connection4 = DbConnection.createConnection();
                PreparedStatement pst4 = connection4.prepareStatement(sql4);
                ResultSet rs4 = pst4.executeQuery();
                if (rs4.next()) {
                    Tcompany = rs4.getString("CompanyName");
                }
                Bcode.setText(Ttcode);
                BgName.setText(Tgname);
                Bcompany.setText(Tcompany);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        Bexpiry.setText("");
        BmanuDate.setText("");
        BcPrice.setText("");
        BsPrice.setText("");
        Bquantity.setText("");

        try {

            String sql1 = "select distinct BatchNo from (select * from section where removed is null) as t where t.code = '" + Ttcode + "'";
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst1 = connection1.prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery();
            Bbatch.removeAllItems();
            String xbatch = null;
            while (rs1.next()) {
                xbatch = rs1.getString("BatchNo");
                Bbatch.addItem(xbatch);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        String Tbatch, Texpiry, Tmanu;
        float Tsprice, Tcprice;
        int Tquantity;
        Tbatch = (String) Bbatch.getSelectedItem();
        String sql3 = "select * from (select * from section where removed is null) as t where t.BatchNo = '" + Tbatch + "' and t.code = '" + Ttcode + "'";
        try {
            Connection connection3 = DbConnection.createConnection();
            PreparedStatement pst3 = connection3.prepareStatement(sql3);
            ResultSet rs3 = pst3.executeQuery(sql3);

            if (rs3.next()) {
                Texpiry = rs3.getString("ExpiryDate");
                Tmanu = rs3.getString("DateofManu");
                Tsprice = rs3.getFloat("SellingPrice");
                Tcprice = rs3.getFloat("WholesalePrice");
                Tquantity = rs3.getInt("Quantity");
                Bexpiry.setText(Texpiry);
                BmanuDate.setText(Tmanu);
                BsPrice.setText(Float.toString(Tsprice));
                BcPrice.setText(Float.toString(Tcprice));
                Bquantity.setText(Integer.toString(Tquantity));
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

    //defines the action whenevr the bat number is selected
    private void BbatchActionPerformed(java.awt.event.ActionEvent evt) {
        String Tbatch, Texpiry, Tmanu, Tcode;
        float Tsprice, Tcprice;
        int Tquantity;
        Tcode = Bcode.getText();
        Tbatch = (String) Bbatch.getSelectedItem();
        Bexpiry.setText("");
        BmanuDate.setText("");
        BcPrice.setText("");
        BsPrice.setText("");
        Bquantity.setText("");

        String sql3 = "select * from (select * from section where removed is null) as t where t.BatchNo = '" + Tbatch + "' and t.code = '" + Tcode + "'";
        try {
            Connection connection3 = DbConnection.createConnection();
            PreparedStatement pst3 = connection3.prepareStatement(sql3);
            ResultSet rs3 = pst3.executeQuery(sql3);

            if (rs3.next()) {
                Texpiry = rs3.getString("ExpiryDate");
                Tmanu = rs3.getString("DateofManu");
                Tsprice = rs3.getFloat("SellingPrice");
                Tcprice = rs3.getFloat("WholesalePrice");
                Tquantity = rs3.getInt("Quantity");
                Bexpiry.setText(Texpiry);
                BmanuDate.setText(Tmanu);
                BsPrice.setText(Float.toString(Tsprice));
                BcPrice.setText(Float.toString(Tcprice));
                Bquantity.setText(Integer.toString(Tquantity));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void BexpiryYActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BmanuDateYActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BcPriceActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BsPriceActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BquantityActionPerformed(java.awt.event.ActionEvent evt) {
    }

    //gives an instance of the class
    public static StockEditName getInstance() {
        if (instance == null) {
            instance = new StockEditName();
        }
        return instance;
    }
}
