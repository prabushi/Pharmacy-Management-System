/*
 * To display the stock details of the database
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Prabushi
 */
public class Stock extends JPanel {

    LinkedList<Section> stockItems = new LinkedList<Section>();
    DefaultTableModel model;
    JTable table;
    JComboBox item;
    String[][] data = new String[stockItems.size()][8];
    String[] columnNames = {"Code", "BatchNo.", "ExpiryDate", "Quantity", "Selling Price", "Profit", "Created At", "Removed At"};
    public static Stock instance = null;

    //constructor
    public Stock() {

        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new java.awt.Color(255, 255, 102)));
        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.BLACK));
        this.setBackground(new java.awt.Color(255, 255, 255));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        gc.insets = new Insets(10, 10, 10, 10);

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;

        JLabel Litem = new JLabel("Item :");
        Litem.setPreferredSize(new Dimension(110, 25));
        item = new JComboBox();

        try {
            String sql1 = "select distinct * from item where removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst1 = connection.prepareStatement(sql1);
            ResultSet rs = pst1.executeQuery();

            String xname = null;
            while (rs.next()) {
                xname = rs.getString("Name");
                item.addItem(xname);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        gc.gridx = -1;
        gc.gridy = 6;
        add(Litem, gc);
        gc.gridx = 1;
        gc.gridy = 6;
        add(item, gc);

        stockItems.clear();
        String Tname;
        int Tcode;
        Tname = (String) item.getSelectedItem();

        String sql = "select * from item where Name ='" + Tname + "' and removed is null";
        try {
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                Tcode = rs1.getInt("code");

                String sql4 = "select * from section where code = '" + Tcode + "'";
                Connection connection4 = DbConnection.createConnection();
                PreparedStatement pst4 = connection4.prepareStatement(sql4);
                ResultSet rs4 = pst4.executeQuery();
                while (rs4.next()) {
                    String batch = rs4.getString("BatchNo");
                    String expiry = rs4.getString("ExpiryDate");
                    float selPrice = rs4.getFloat("SellingPrice");
                    float whoPrice = rs4.getFloat("WholesalePrice");
                    float profit = selPrice - whoPrice;
                    int quantity = rs4.getInt("Quantity");
                    String date = rs4.getString("Date/Time");
                    String removed = rs4.getString("removed");

                    Section sec = new Section(Tcode, batch, expiry, selPrice, profit, quantity, date, removed);
                    stockItems.add(sec);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthActionPerformed(evt);
            }

            private void monthActionPerformed(ActionEvent evt) {
                String Tname;
                int Tcode;
                stockItems.clear();

                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();

                Tname = (String) item.getSelectedItem();

                String sql = "select * from item where Name ='" + Tname + "' and removed is null";
                try {
                    Connection connection1 = DbConnection.createConnection();
                    PreparedStatement pst = connection1.prepareStatement(sql);
                    ResultSet rs1 = pst.executeQuery(sql);

                    if (rs1.next()) {
                        Tcode = rs1.getInt("code");

                        String sql4 = "select * from section where code = '" + Tcode + "'";
                        Connection connection4 = DbConnection.createConnection();
                        PreparedStatement pst4 = connection4.prepareStatement(sql4);
                        ResultSet rs4 = pst4.executeQuery();
                        while (rs4.next()) {
                            String batch = rs4.getString("BatchNo");
                            String expiry = rs4.getString("ExpiryDate");
                            float selPrice = rs4.getFloat("SellingPrice");
                            float whoPrice = rs4.getFloat("WholesalePrice");
                            float profit = selPrice - whoPrice;
                            int quantity = rs4.getInt("Quantity");
                            String date = rs4.getString("Date/Time");
                            String removed = rs4.getString("removed");

                            Section sec = new Section(Tcode, batch, expiry, selPrice, profit, quantity, date, removed);
                            stockItems.add(sec);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }


                data = new String[stockItems.size()][8];

                setTable();
                for (int i = 0; i < stockItems.size(); i++) {
                    model.addRow(data[i]);
                }
            }
        });

        data = new String[stockItems.size()][8];
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

    //set stock detail to the table 
    public void setTable() {

        if (!stockItems.isEmpty()) {

            for (int i = 0; i < stockItems.size(); i++) {
                Section item = stockItems.get(i);

                data[i][4] = Float.toString(item.getSelPrice());
                data[i][0] = Integer.toString(item.getCode());
                data[i][1] = item.getBatch();
                data[i][2] = item.getExpiry();
                data[i][3] = Integer.toString(item.getQuantity());
                data[i][5] = Float.toString(item.getProfit());
                data[i][6] = item.getDate();
                data[i][7] = item.getRemoved();
            }
        }
    }

//return an instance of the class
    public static Stock getInstance() {
        if (instance == null) {
            instance = new Stock();
        }
        return instance;
    }
}
