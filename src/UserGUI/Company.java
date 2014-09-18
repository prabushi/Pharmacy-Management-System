/*
 Jpanel to searchfor companies
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
public class Company extends JPanel {

    public static Company instance = null;
    private JButton add;
    private JButton delete;
    JComboBox name;
    JTextField address;
    JTextField description;
    JTextField phone;

    //constructor
    public Company() {
        this.setLayout(new GridBagLayout());
        add = new JButton("Add New");
        add.setPreferredSize(new Dimension(120, 40));
        add.setFont(new java.awt.Font("Times New Roman", 1, 16));
        add.setBackground(new java.awt.Color(255, 255, 102));
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
        JLabel ldescription = new JLabel("Description :");
        ldescription.setPreferredSize(new Dimension(100, 30));

        name = new JComboBox();
        name.setPreferredSize(new Dimension(220, 30));
        address = new JTextField();
        address.setPreferredSize(new Dimension(220, 30));
        description = new JTextField();
        description.setPreferredSize(new Dimension(220, 30));
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
            String sql = "select distinct * from company where removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            String xname = null;
            while (rs.next()) {
                xname = rs.getString("CompanyName");
                name.addItem(xname);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        String tadd, tdes, tphon;
        String Tname = (String) name.getSelectedItem();
        String sql1 = "select * from company where CompanyName ='" + Tname + "' and removed is null";

        try {
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst1 = connection1.prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery(sql1);

            if (rs1.next()) {
                tadd = rs1.getString("Address");
                tdes = rs1.getString("Description");
                tphon = rs1.getString("PhoneNo");
                address.setText(tadd);
                description.setText(tdes);
                phone.setText(tphon);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }

            private void nameActionPerformed(ActionEvent evt) {
                String tadd, tdes, tphon;
                String Tname = (String) name.getSelectedItem();
                String sql1 = "select * from company where CompanyName ='" + Tname + "' and removed is null";

                try {
                    Connection connection1 = DbConnection.createConnection();
                    PreparedStatement pst1 = connection1.prepareStatement(sql1);
                    ResultSet rs1 = pst1.executeQuery(sql1);

                    if (rs1.next()) {
                        tadd = rs1.getString("Address");
                        tdes = rs1.getString("Description");
                        tphon = rs1.getString("PhoneNo");
                        address.setText(tadd);
                        description.setText(tdes);
                        phone.setText(tphon);
                    }
                } catch (Exception e) {
                    System.out.println(e);
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

        gc.gridx = 1;
        gc.gridy = 4;
        add(ldescription, gc);
        gc.gridx = 5;
        gc.gridy = 4;
        add(description, gc);

        gc.gridx = 3;
        gc.gridy = 13;
        add(add, gc);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }

            private void addActionPerformed(ActionEvent evt) {
                Page p = Page.getInstance();
                p.addCompany();
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
                String tempcom;
                tempcom = (String) name.getSelectedItem();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date date = new java.util.Date();
                String tempDate = dateFormat.format(date);

                String sql = "update company set removed = '" + tempDate + "' where CompanyName ='" + tempcom + "'";
                try {
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.executeUpdate(sql);

                    JOptionPane.showMessageDialog(null, "Company : " + tempcom + " has been deleted.");

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

    }

    //returns an instance of the class
    public static Company getInstance() {
        if (instance == null) {
            instance = new Company();
        }
        return instance;
    }
}
