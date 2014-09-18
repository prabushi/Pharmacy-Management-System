/*
 * Display all the bills and their details of a corresponding month
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
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @author Prabushi
 */
public class Bills extends JPanel {

    LinkedList<Bill> bills = new LinkedList<Bill>();
    DefaultTableModel model;
    JTable table;
    JButton export;
    JComboBox month;
    String[][] data = new String[bills.size()][4];
    String[] columnNames = {"Customer Name", "Total", "Profit", "Created At"};
    public static Bills instance = null;
    String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
        "August", "September", "October", "November", "December"};

    //constructor
    public Bills() {
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new java.awt.Color(255, 255, 102)));
        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.BLACK));

        this.setBackground(new java.awt.Color(255, 255, 255));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        gc.insets = new Insets(10, 10, 10, 10);

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;

        JLabel Lcompany = new JLabel("Month :");
        Lcompany.setPreferredSize(new Dimension(110, 25));
        month = new JComboBox();

        gc.gridx = -1;
        gc.gridy = 6;
        add(Lcompany, gc);
        gc.gridx = 1;
        gc.gridy = 6;
        add(month, gc);
        month.setModel(new javax.swing.DefaultComboBoxModel(monthName));


        month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companyActionPerformed(evt);
            }

            private void companyActionPerformed(ActionEvent evt) {

                bills.clear();
                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();

                String mon = (String) month.getSelectedItem();
                String monthNo = getMonth(mon);

                try {

                    String sql = "select * from bill";
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();

                    while (rs.next()) {
                        String date = rs.getString("Date");
                        int year;
                        String billYear;
                        Calendar cal = Calendar.getInstance();
                        year = cal.get(Calendar.YEAR);
                        int tempYear = year;
                        billYear = date.substring(0, 4);

                        if (Integer.parseInt(billYear) == tempYear) {
                            int tempMonth = Integer.parseInt(date.substring(5, 7));
                            int x = Integer.parseInt(monthNo);
                            if (x == tempMonth) {
                                int cusId = rs.getInt("CustomerId");
                                String customerId = Integer.toString(cusId);
                                float total = rs.getFloat("Total");
                                float profit = rs.getFloat("Profit");
                                String createdDate = rs.getString("Date");
                                int bilId = rs.getInt("BillId");
                                String billId = Integer.toString(bilId);
                                String cusName = "";
                                LinkedList<SoldItem> sold = new LinkedList<>();

                                String sql1 = "select * from customer where CustomerId = '" + customerId + "' and removed is null";
                                Connection connection1 = DbConnection.createConnection();
                                PreparedStatement pst1 = connection1.prepareStatement(sql1);
                                ResultSet rs1 = pst1.executeQuery();
                                if (rs1.next()) {
                                    cusName = rs1.getString("Name");
                                }
                                String sql2 = "select * from sold where BillId = '" + billId + "'";
                                Connection connection2 = DbConnection.createConnection();
                                PreparedStatement pst2 = connection2.prepareStatement(sql2);
                                ResultSet rs2 = pst2.executeQuery();
                                while (rs2.next()) {
                                    int secId = rs2.getInt("SectionId");
                                    int quantity = rs2.getInt("Quantity");
                                    String itemName = "";
                                    float price = 0;
                                    String sql3 = "select Name,t.SellingPrice from item,(select SellingPrice,code from section where IndexNo = '" + secId + "') as t where t.code = item.code";
                                    Connection connection3 = DbConnection.createConnection();
                                    PreparedStatement pst3 = connection3.prepareStatement(sql3);
                                    ResultSet rs3 = pst3.executeQuery();
                                    if (rs3.next()) {
                                        itemName = rs3.getString("Name");
                                        price = rs3.getFloat("SellingPrice");
                                    }
                                    SoldItem soldItm = new SoldItem(itemName, Integer.toString(quantity), Float.toString(price));
                                    sold.add(soldItm);
                                }

                                Bill b = new Bill(sold, cusName, Float.toString(total), Float.toString(profit), createdDate);
                                bills.add(b);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                data = new String[bills.size()][4];

                setTable();
                for (int i = 0; i < bills.size(); i++) {
                    model.addRow(data[i]);
                }
            }
        });
        export = new JButton("Export Word");
        export.setBackground(new java.awt.Color(255, 255, 102));


        String mon = (String) month.getSelectedItem();
        String monthNo = getMonth(mon);

        try {

            String sql = "select * from bill";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String date = rs.getString("Date");
                int year;
                String billYear;
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                int tempYear = year;
                billYear = date.substring(0, 4);

                if (Integer.parseInt(billYear) == tempYear) {
                    int tempMonth = Integer.parseInt(date.substring(5, 7));
                    int x = Integer.parseInt(monthNo);
                    if (x == tempMonth) {
                        int cusId = rs.getInt("CustomerId");
                        String customerId = Integer.toString(cusId);
                        float total = rs.getFloat("Total");
                        float profit = rs.getFloat("Profit");
                        String createdDate = rs.getString("Date");
                        int bilId = rs.getInt("BillId");
                        String billId = Integer.toString(bilId);
                        String cusName = "";
                        LinkedList<SoldItem> sold = new LinkedList<>();

                        String sql1 = "select * from customer where CustomerId = '" + customerId + "' and removed is null";
                        Connection connection1 = DbConnection.createConnection();
                        PreparedStatement pst1 = connection1.prepareStatement(sql1);
                        ResultSet rs1 = pst1.executeQuery();
                        if (rs1.next()) {
                            cusName = rs1.getString("Name");
                        }
                        String sql2 = "select * from sold where BillId = '" + billId + "'";
                        Connection connection2 = DbConnection.createConnection();
                        PreparedStatement pst2 = connection2.prepareStatement(sql2);
                        ResultSet rs2 = pst2.executeQuery();
                        while (rs2.next()) {
                            int secId = rs2.getInt("SectionId");
                            int quantity = rs2.getInt("Quantity");
                            String itemName = "";
                            float price = 0;
                            String sql3 = "select Name,t.SellingPrice from item,(select SellingPrice,code from section where IndexNo = '" + secId + "') as t where t.code = item.code";
                            Connection connection3 = DbConnection.createConnection();
                            PreparedStatement pst3 = connection3.prepareStatement(sql3);
                            ResultSet rs3 = pst3.executeQuery();
                            if (rs3.next()) {
                                itemName = rs3.getString("Name");
                                price = rs3.getFloat("SellingPrice");
                            }
                            SoldItem soldItm = new SoldItem(itemName, Integer.toString(quantity), Float.toString(price));
                            sold.add(soldItm);
                        }

                        Bill b = new Bill(sold, cusName, Float.toString(total), Float.toString(profit), createdDate);
                        bills.add(b);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }



        data = new String[bills.size()][4];
        this.setTable();
        for (int i = 0; i < bills.size(); i++) {
            model.addRow(data[i]);
        }
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
        gc.gridx = 4;
        gc.gridy = 10;

        add(export);
        export.setPreferredSize(new java.awt.Dimension(150, 40));
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneActionPerformed(evt);
            }

            private void oneActionPerformed(ActionEvent evt) {
                String mon = (String) month.getSelectedItem();
                String content = mon + "\n" + "\n" + "\n";
                for (int i = 0; i < bills.size(); i++) {
                    content += "Customer Name        :  " + bills.get(i).getCustomerName() + "\n";
                    content += "Total                :  " + bills.get(i).getTotal() + "\n";
                    content += "Profit               :  " + bills.get(i).getProfit() + "\n";
                    content += "Billed at            :  " + bills.get(i).getDate() + "\n";
                    content += "\n";
                    LinkedList<SoldItem> list = bills.get(i).getItems();
                    int size = list.size();
                    if (size > 0) {
                        for (int j = 0; j < size; j++) {
                            content += "                " + list.get(j).getName() + "   " + list.get(j).getQuantity() + "  at Rs." + list.get(j).getPrice() + "\n";

                        }
                    }
                    content += "\n" + "\n" + "\n";
                }
                writeToFile(content, mon + "_BillDetails.doc");
            }
        });


    }
//method to write to a word doc

    private static void writeToFile(String content, String path) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem();
            DirectoryEntry directory = fs.getRoot();
            directory.createDocument("WordDocument", new ByteArrayInputStream(content.getBytes()));
            FileOutputStream out = new FileOutputStream(path);

            fs.writeFilesystem(out);
            out.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //methos sets the bill details to the table
    public void setTable() {
        if (!bills.isEmpty()) {

            for (int i = 0; i < bills.size(); i++) {
                Bill item = bills.get(i);

                data[i][0] = item.getCustomerName();
                data[i][1] = item.getTotal();
                data[i][2] = item.getProfit();
                data[i][3] = item.getDate();


            }
        }
    }

    //returns the date of the month when the month name given
    public String getMonth(String m) {
        String date;
        String x = null;

        if (m.equals("January")) {
            x = "01";
        }
        if (m.equals("February")) {
            x = "02";
        }
        if (m.equals("March")) {
            x = "03";
        }
        if (m.equals("April")) {
            x = "04";
        }
        if (m.equals("May")) {
            x = "05";
        }
        if (m.equals("June")) {
            x = "06";
        }
        if (m.equals("July")) {
            x = "07";
        }
        if (m.equals("August")) {
            x = "08";
        }
        if (m.equals("September")) {
            x = "09";
        }
        if (m.equals("October")) {
            x = "10";
        }
        if (m.equals("November")) {
            x = "11";
        }
        if (m.equals("December")) {
            x = "12";
        }


        return x;
    }

    //returns an instance of the class
    public static Bills getInstance() {
        if (instance == null) {
            instance = new Bills();
        }
        return instance;
    }
}
