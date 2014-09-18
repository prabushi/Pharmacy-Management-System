/*
 * All the manipulations of the bill happen through this class
 * (update database according to the billing and displaying the bill)
 */
package UserGUI;

import Database.DbConnection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Prabushi
 */
public class Bill extends JPanel {

    public static Bill instance = null;
    JScrollPane jScrollPane1;
    JTable table;
    DefaultTableModel model;
    JButton clear;
    JButton bill;
    ArrayList<Item> billingList;
    JTextField total;
    int id;

    //constructor
    private Bill() {

        this.setBackground(new java.awt.Color(255, 255, 255));

        try {
            Connection connection = DbConnection.createConnection();
            String sql = "insert into bill values(?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, null);
            pst.setString(2, "0");
            pst.setString(3, "0");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date = new java.util.Date();
            String tempDate = dateFormat.format(date);
            pst.setString(4, tempDate);
            pst.setString(5, "0");
            pst.executeUpdate();

            String sql1 = "select max(BillId) as highest from bill";
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst1 = connection1.prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery(sql1);

            if (rs1.next()) {
                id = rs1.getInt("highest");
            }

        } catch (Exception e) {
            System.out.println("a-" + e);
        }
        total = new JTextField();
        billingList = new ArrayList<Item>();
        bill = new JButton("Print  Bill");
        bill.setFont(new java.awt.Font("Times New Roman", 1, 16));
        bill.setPreferredSize(new Dimension(120, 40));
        bill.setBackground(new java.awt.Color(255, 255, 102));
        clear = new JButton("Clear Table");
        clear.setFont(new java.awt.Font("Times New Roman", 1, 16));
        clear.setPreferredSize(new Dimension(120, 40));
        clear.setBackground(new java.awt.Color(255, 255, 102));

        String columnNames[] = {"Item", "Price", "Quantity", "Total"};

        Object dataValues[][] = {};
        model = new DefaultTableModel(dataValues, columnNames);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(600, 70));
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVisible(true);
        add(scrollPane, BorderLayout.CENTER);

        total.setPreferredSize(new Dimension(75, 50));
        add(total);
        add(clear);
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }

            private void clearActionPerformed(ActionEvent evt) {
                Bill b = Bill.getInstance();
                DefaultTableModel model = b.model;
                try {
                    String sql = "delete from bill where Total = 0";
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.executeUpdate();

                } catch (Exception e) {
                    System.out.println(e);
                }
                model.setRowCount(0);
                b.billingList.clear();
                b.total.setText("0.00");
                b.id++;

            }
        });

        add(bill);
        bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billActionPerformed(evt);
            }

            private void billActionPerformed(ActionEvent evt) {
                Page pg = Page.getInstance();
                pg.Payment();


            }
        });

        table.addMouseListener(new java.awt.event.MouseListener() {
            public void tableMouseClicked(java.awt.event.MouseEvent evt) {
                mouseClicked(evt);
            }

            public void mouseClicked(MouseEvent e) {
                BillUp2Panel bill2 = BillUp2Panel.getInstance();
                bill2.setTable();
                BillUp3Panel bill3 = BillUp3Panel.getInstance();
                bill3.setTable();
                BillUp1Panel bill1 = BillUp1Panel.getInstance();
                bill1.setTable();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

    }
    //A method to remove sections(with the nearest expiry date) which are sold
    //sections will be removed according to the billing quantity

    public void removeSections() {
        if (!billingList.isEmpty()) {
            for (int i = 0; i < billingList.size(); i++) {
                Item item = billingList.get(i);
                String tempName = item.getName();
                int tempquan = Integer.parseInt(item.getQuantity());

                try {
                    String sql = "select * from item where Name = '" + tempName + "'";
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {

                        int available = rs.getInt("quantity");
                        int eachhas = rs.getInt("eachHas");
                        int code = rs.getInt("code");
                        this.secRemove(tempquan, eachhas, code, available);
                        int now = available - tempquan;
//remove from total quantity
                        String sql1 = "update item set quantity = '" + now + "' where Name = '" + tempName + "'";
                        Connection connection1 = DbConnection.createConnection();
                        PreparedStatement pst1 = connection1.prepareStatement(sql1);
                        pst1.executeUpdate(sql1);
                    }
                } catch (Exception e) {
                }
            }
        }
    }
//resursively remove sections

    public void secRemove(int quantity, int eachHas, int code, int available) {

        try {
            String sql = "select * from section where code = '" + code + "' and removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            String exp;
            int index, quan;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            LinkedList<Section> sec = new LinkedList<Section>();
            while (rs.next()) {
                index = rs.getInt("IndexNo");
                exp = rs.getString("ExpiryDate");
                quan = rs.getInt("Quantity");

                Section section = new Section(index, quan, formatter.parse(exp), 0);
                sec.add(section);
            }
            Collections.sort(sec);

            if (!sec.isEmpty()) {
                for (int i = 0; i < sec.size(); i++) {
                    Section s = sec.get(i);
                    String ind = Integer.toString(s.getId());
                    String sql1 = "select * from sold where SectionId = '" + ind + "'";
                    Connection connection1 = DbConnection.createConnection();
                    PreparedStatement pst1 = connection.prepareStatement(sql1);
                    ResultSet rs1 = pst1.executeQuery();
                    int count = 0;
                    while (rs1.next()) {
                        int quant = rs1.getInt("Quantity");
                        count += quant;
                    }
                    s.setConsumed(count);
                }
            }


            Section s = sec.get(0);
            int remain = eachHas * s.getQuantity() - s.getConsumed();
            if (remain == quantity) {
                this.remove(code, quantity, s.getExpiry(), s.getId());
            } else if (remain > quantity) {
                this.removeDefault(code, quantity, s.getExpiry(), s.getId());
            } else {
                this.remove(code, remain, s.getExpiry(), s.getId());
                this.secRemove(quantity - remain, eachHas, code, available - remain);
            }

        } catch (Exception e) {
            System.out.println("-d" + e);
        }

    }

    //set the removed date and time for an stock
    public void remove(int code, int quantity, Date ex, int ind) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date1 = new java.util.Date();

            String tempDate = dateFormat.format(date1);
            String date = formatter.format(ex);
            int index = ind;
            Connection connection = DbConnection.createConnection();
            String sql = "insert into sold values(?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, index);
            pst.setInt(2, id);
            pst.setInt(3, quantity);
            pst.executeUpdate();

            //add sold part
            String sql7 = "update section set removed = '" + tempDate + "' where ExpiryDate = '" + date + "' and code = '" + code + "'";
            Connection connection7 = DbConnection.createConnection();
            PreparedStatement pst7 = connection7.prepareStatement(sql7);
            pst7.executeUpdate();

        } catch (Exception e) {
            System.out.println("b-" + e);
        }
    }

    //defaut setter for removing items
    public void removeDefault(int code, int quantity, Date ex, int ind) {//quantity number of drugs prescribed,val
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date1 = new java.util.Date();

            String tempDate = dateFormat.format(date1);

            String date = formatter.format(ex);

            int index = ind;
            Connection connection = DbConnection.createConnection();
            String sql = "insert into sold values(?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, index);
            pst.setInt(2, id);
            pst.setInt(3, quantity);
            pst.executeUpdate();


        } catch (Exception e) {
            System.out.println("c-" + e);
        }
    }

    //retruns the total of the bill
    public float getTotal() {
        float Ftotal;
        Ftotal = 0;
        if (billingList.isEmpty()) {
            total.setText("0.00");
            Ftotal = 0;
        } else {
            for (int i = 0; i < billingList.size(); i++) {

                Item item = billingList.get(i);
                float val = Float.valueOf(item.getTotal());

                Ftotal += val;
            }
            total.setText(Float.toString(Ftotal));
        }
        return Ftotal;
    }

    //retruns the profit of the bill
    public float getProfit() {
        float profit;

        profit = 0;
        if (billingList.isEmpty()) {
            profit = 0;
        } else {
            for (int i = 0; i < billingList.size(); i++) {

                Item item = billingList.get(i);
                float val = Float.valueOf(item.getProfit());

                profit += val;
            }
        }
        return profit;
    }

    //returns an instance of the class
    public static Bill getInstance() {
        if (instance == null) {
            instance = new Bill();
        }
        return instance;
    }
}
