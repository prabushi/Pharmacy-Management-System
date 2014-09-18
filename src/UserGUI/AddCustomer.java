/*
 Jpanel to add new customers to the database
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Prabushi
 */
public class AddCustomer extends JPanel {

    public static AddCustomer instance = null;
    private JButton add;
    private JButton search;
    JTextField name;
    JTextField address;
    JTextField phone;

    //constructor
    private AddCustomer() {

        this.setLayout(new GridBagLayout());
        search = new JButton("Search");
        search.setPreferredSize(new Dimension(120, 40));
        search.setFont(new java.awt.Font("Times New Roman", 1, 16));
        search.setBackground(new java.awt.Color(255, 255, 102));
        add = new JButton("Add");
        add.setPreferredSize(new Dimension(120, 40));
        add.setFont(new java.awt.Font("Times New Roman", 1, 16));
        add.setBackground(new java.awt.Color(255, 255, 102));

        JLabel lname = new JLabel("Name :");
        lname.setPreferredSize(new Dimension(100, 30));
        JLabel laddress = new JLabel("Address :");
        laddress.setPreferredSize(new Dimension(100, 30));
        JLabel lphone = new JLabel("Phone No. :");
        lphone.setPreferredSize(new Dimension(100, 30));

        name = new JTextField();
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
        gc.gridx = 5;
        gc.gridy = 1;
        add(name, gc);

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

        gc.gridx = 4;
        gc.gridy = 13;
        add(search, gc);
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }

            private void searchActionPerformed(ActionEvent evt) {
                Page p = Page.getInstance();
                p.Customer();
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
                    String tname, taddress, tphone, tdescription;
                    Connection connection = DbConnection.createConnection();

                    String sql = "insert into customer values(?,?,?,?,?,?)";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1, null);
                    tname = name.getText();
                    pst.setString(2, tname);
                    taddress = address.getText();
                    pst.setString(4, taddress);
                    tphone = phone.getText();
                    pst.setString(3, tphone);

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date date = new java.util.Date();
                    System.out.println(dateFormat.format(date));
                    pst.setString(5, dateFormat.format(date));
                    pst.setString(6, null);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Details of Customer : " + tname + " has been added.");
                    name.setText("");
                    address.setText("");
                    phone.setText("");
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        });

    }

    //return an instance of customers
    public static AddCustomer getInstance() {
        if (instance == null) {
            instance = new AddCustomer();
        }
        return instance;
    }
}
