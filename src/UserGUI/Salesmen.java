/*
 * To search for salesmen details
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
 * @author Prabushi
 */
public class Salesmen extends JPanel {

    public static Salesmen instance = null;
    private JButton add;
    JComboBox name;
    JTextField company;
    JTextField phone;
    JButton delete;

    //constructor
    public Salesmen() {

        this.setLayout(new GridBagLayout());
        delete = new JButton("Delete");
        delete.setPreferredSize(new Dimension(120, 40));
        delete.setFont(new java.awt.Font("Times New Roman", 1, 16));
        delete.setBackground(new java.awt.Color(255, 255, 102));
        add = new JButton("Add New");
        add.setBackground(new java.awt.Color(255, 255, 102));
        add.setPreferredSize(new Dimension(120, 40));
        add.setFont(new java.awt.Font("Times New Roman", 1, 16));

        JLabel lname = new JLabel("Name :");
        lname.setPreferredSize(new Dimension(100, 30));
        JLabel laddress = new JLabel("Company :");
        laddress.setPreferredSize(new Dimension(100, 30));
        JLabel lphone = new JLabel("Phone No. :");
        lphone.setPreferredSize(new Dimension(100, 30));

        name = new JComboBox();
        name.setPreferredSize(new Dimension(220, 30));
        company = new JTextField();
        company.setPreferredSize(new Dimension(220, 30));
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
            String sql = "select distinct * from salesmen where removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            String xname = null;
            while (rs.next()) {
                xname = rs.getString("Name");
                name.addItem(xname);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        String tphon, tcom, tcompany;
        int tadd;
        String Tname = (String) name.getSelectedItem();
        String sql1 = "select * from salesmen where Name ='" + Tname + "' and removed is null";

        try {
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst1 = connection1.prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery(sql1);

            if (rs1.next()) {
                tphon = rs1.getString("Phone");
                phone.setText(tphon);
                tadd = rs1.getInt("CompanyId");
                tcom = Integer.toString(tadd);

                String sql2 = "select * from company where CompanyId ='" + tadd + "' and removed is null";
                Connection connection2 = DbConnection.createConnection();
                PreparedStatement pst2 = connection2.prepareStatement(sql2);
                ResultSet rs2 = pst1.executeQuery(sql2);

                if (rs2.next()) {
                    tcompany = rs2.getString("CompanyName");
                    company.setText(tcompany);
                }


            }
        } catch (Exception e) {
            System.out.println(e);
        }
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }

            private void nameActionPerformed(ActionEvent evt) {
                phone.setText("");
                company.setText("");

                String tcompany, tphon, tcom;
                int tadd;
                String Tname = (String) name.getSelectedItem();
                String sql1 = "select * from salesmen where Name ='" + Tname + "' and removed is null";

                try {
                    Connection connection1 = DbConnection.createConnection();
                    PreparedStatement pst1 = connection1.prepareStatement(sql1);
                    ResultSet rs1 = pst1.executeQuery(sql1);

                    if (rs1.next()) {

                        tphon = rs1.getString("Phone");
                        phone.setText(tphon);
                        tadd = rs1.getInt("CompanyId");
                        tcom = Integer.toString(tadd);

                        String sql2 = "select * from company where CompanyId ='" + tadd + "' and removed is null";
                        Connection connection2 = DbConnection.createConnection();
                        PreparedStatement pst2 = connection2.prepareStatement(sql2);
                        ResultSet rs2 = pst1.executeQuery(sql2);

                        if (rs2.next()) {
                            tcompany = rs2.getString("CompanyName");
                            company.setText(tcompany);
                        }
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
        add(company, gc);

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
                p.addSalesmen();
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
                String tempsalesman;
                tempsalesman = (String) name.getSelectedItem();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date date = new java.util.Date();
                String tempDate = dateFormat.format(date);

                String sql = "update salesmen set removed = '" + tempDate + "' where Name ='" + tempsalesman + "'";
                try {
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.executeUpdate(sql);

                    JOptionPane.showMessageDialog(null, "Salesman : " + tempsalesman + " has been deleted.");

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    //returns an instance of the class
    public static Salesmen getInstance() {
        if (instance == null) {
            instance = new Salesmen();
        }
        return instance;
    }
}
