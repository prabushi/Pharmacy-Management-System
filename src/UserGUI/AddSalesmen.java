/*
 Jpanel to add new salesmen to the database
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
public class AddSalesmen extends JPanel {

    public static AddSalesmen instance = null;
    private JButton add;
    private JButton search;
    JTextField name;
    JComboBox company;
    JTextField phone;

    //constructor
    private AddSalesmen() {

        this.setLayout(new GridBagLayout());
        search = new JButton("Search");
        search.setBackground(new java.awt.Color(255, 255, 102));
        search.setPreferredSize(new Dimension(120, 40));
        search.setBackground(new java.awt.Color(255, 255, 102));
        add = new JButton("Add");
        add.setBackground(new java.awt.Color(255, 255, 102));
        add.setPreferredSize(new Dimension(120, 40));
        add.setBackground(new java.awt.Color(255, 255, 102));
        
        JLabel lname = new JLabel("Name :");
        lname.setPreferredSize(new Dimension(100, 30));
        JLabel lcompany = new JLabel("Company :");
        lcompany.setPreferredSize(new Dimension(100, 30));
        JLabel lphone = new JLabel("Phone No. :");
        lphone.setPreferredSize(new Dimension(100, 30));

        name = new JTextField();
        name.setPreferredSize(new Dimension(220, 30));
        company = new JComboBox();
        company.setPreferredSize(new Dimension(220, 30));
        phone = new JTextField();
        phone.setPreferredSize(new Dimension(220, 30));

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 1;
        gc.gridy = 1;
        add(lname, gc);
        gc.gridx = 5;
        gc.gridy = 1;
        add(name, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(lcompany, gc);
        gc.gridx = 5;
        gc.gridy = 2;
        add(company, gc);

        try {
            String sql = "select distinct * from company where removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            String xname = null;
            while (rs.next()) {
                xname = rs.getString("CompanyName");
                company.addItem(xname);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        gc.gridx = 1;
        gc.gridy = 3;
        add(lphone, gc);
        gc.gridx = 5;
        gc.gridy = 3;
        add(phone, gc);

        gc.gridx = 4;
        gc.gridy = 13;
        add(search, gc);
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }

            private void searchActionPerformed(ActionEvent evt) {
                Page p = Page.getInstance();
                p.Salesmen();
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
                try {
                    String tname, tcompany, tphone, tdescription;
                    int companyId = 0;
                    Connection connection = DbConnection.createConnection();

                    String sql = "insert into salesmen values(?,?,?,?,?,?)";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, null);
                    tname = name.getText();
                    pst.setString(3, tname);
                    tcompany = (String) company.getSelectedItem();

                    String sql1 = "select * from company where CompanyName ='" + tcompany + "' and removed is null";
                    Connection connection1 = DbConnection.createConnection();
                    PreparedStatement pst1 = connection1.prepareStatement(sql1);
                    ResultSet rs1 = pst1.executeQuery(sql1);
                    if (rs1.next()) {
                        companyId = rs1.getInt("CompanyId");

                    }

                    pst.setInt(2, companyId);
                    tphone = phone.getText();
                    pst.setString(4, tphone);

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date date = new java.util.Date();
                    System.out.println(dateFormat.format(date));
                    pst.setString(5, dateFormat.format(date));
                    pst.setString(6, null);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Details of Salesmen : " + tname + " has been added.");
                    name.setText("");
                    //.setText("");
                    phone.setText("");
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        });

    }

    //returns an instance of the class
    public static AddSalesmen getInstance() {
        if (instance == null) {
            instance = new AddSalesmen();
        }
        return instance;
    }
}
