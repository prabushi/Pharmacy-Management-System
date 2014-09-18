/*
 * To represent and display the details of company
 */
package SuperUserGUI;

import Database.DbConnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Prabushi
 */
public class Other2 extends JPanel {

    LinkedList<Object> company = new LinkedList<Object>();
    DefaultTableModel model;
    JTable table;
    String[][] data = new String[company.size()][5];
    String[] columnNames = {"Name", "Phone No.", "Address", "Created At", "Removed At"};
    public static Other2 instance = null;

    //constructor
    public Other2() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        gc.insets = new Insets(10, 10, 10, 10);


        JButton one = new JButton("Customer");
        one.setForeground(Color.WHITE);
        one.setHorizontalTextPosition(SwingConstants.CENTER);
        one.setBackground(Color.BLACK);
        one.setPreferredSize(new java.awt.Dimension(100, 25));
        one.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneActionPerformed(evt);
            }

            private void oneActionPerformed(ActionEvent evt) {
                SUserPage pg = SUserPage.getInstance();
                pg.getOther1();
            }
        });

        JButton two = new JButton("Company");
        two.setForeground(Color.WHITE);
        two.setHorizontalTextPosition(SwingConstants.CENTER);
        two.setBackground(Color.BLACK);
        two.setPreferredSize(new java.awt.Dimension(100, 25));
        two.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoActionPerformed(evt);
            }

            private void twoActionPerformed(ActionEvent evt) {
                SUserPage pg = SUserPage.getInstance();
                pg.getOther2();
            }
        });

        JButton three = new JButton("Salesman");
        three.setForeground(Color.WHITE);
        three.setHorizontalTextPosition(SwingConstants.CENTER);
        three.setBackground(Color.BLACK);
        three.setPreferredSize(new java.awt.Dimension(100, 25));
        three.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threeActionPerformed(evt);
            }

            private void threeActionPerformed(ActionEvent evt) {
                SUserPage pg = SUserPage.getInstance();
                pg.getOther3();
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
        gc.gridwidth = GridBagConstraints.REMAINDER;
        this.add(three, gc);

        company.clear();


        String sql = "select * from company";
        try {
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs4 = pst.executeQuery(sql);


            while (rs4.next()) {
                String name = rs4.getString("CompanyName");
                String phone = rs4.getString("PhoneNo");
                String address = rs4.getString("Address");
                String date = rs4.getString("Date/Time");
                String removed = rs4.getString("removed");

                Object sec = new Object(name, phone, address, date, removed);
                company.add(sec);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        data = new String[company.size()][5];
        this.setTable();

        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 300));
        JScrollPane scrollPane = new JScrollPane(table);

        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);

        gc.gridx = 1;
        gc.gridy = 7;

        add(scrollPane, gc);

    }

    //set the details of companies to the table according to the list
    public void setTable() {

        if (!company.isEmpty()) {

            for (int i = 0; i < company.size(); i++) {
                Object item = company.get(i);


                data[i][0] = item.getName();
                data[i][1] = item.getPhone();
                data[i][2] = item.getAddress();
                data[i][3] = item.getDate();
                data[i][4] = item.getRemoved();

            }
        }
    }

//returns an instance of the class
    public static Other2 getInstance() {
        if (instance == null) {
            instance = new Other2();
        }
        return instance;
    }
}
