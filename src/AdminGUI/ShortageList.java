/*
 * The class to get the shortage lists of each item. 
 * Initially when the item was set to the database,system gives to set a minimum stock limit to the item
 * Whenever the item limit go below that stock limit it will be added to the shortage list.
 * Also orders made by customers will be added to the system
 */
package AdminGUI;

import Database.DbConnection;
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
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @author Prabushi
 */
public class ShortageList extends JPanel {

    LinkedList<Shortage> items = new LinkedList<Shortage>();
    DefaultTableModel model;
    JTable table;
    JButton export;
    JComboBox company;
    String[][] data = new String[items.size()][4];
    String[] columnNames = {"Name", "Each Has", "Available Qt", "Shortage"};
    public static ShortageList instance = null;

    //constructor
    public ShortageList() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        gc.insets = new Insets(20, 10, 10, 10);

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;

        JLabel Lcompany = new JLabel("Company Name :");
        Lcompany.setPreferredSize(new Dimension(110, 30));
        company = new JComboBox();
        company.setPreferredSize(new Dimension(220, 30));

        gc.gridx = -1;
        gc.gridy = 6;
        add(Lcompany, gc);
        gc.gridx = 1;
        gc.gridy = 6;
        add(company, gc);

        try {
            String sql1 = "select distinct * from company where removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst1 = connection.prepareStatement(sql1);
            ResultSet rs = pst1.executeQuery();

            String xname = null;
            while (rs.next()) {
                xname = rs.getString("CompanyName");
                company.addItem(xname);
            }
        } catch (Exception e) {
            System.out.println("14" + e);
        }

        company.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companyActionPerformed(evt);
            }

            private void companyActionPerformed(ActionEvent evt) {

                items.clear();
                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
                for (int i = 0; i < table.getRowCount(); i++) {
                    model.removeRow(i);
                }
                String comp = (String) company.getSelectedItem();

                try {

                    String sql = "select * from company where CompanyName ='" + comp + "' and removed is null";
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {

                        int index = rs.getInt("CompanyId");

                        String sql1 = "select * from item where CompanyId ='" + index + "' and removed is null";
                        Connection connection1 = DbConnection.createConnection();
                        PreparedStatement pst1 = connection1.prepareStatement(sql1);
                        ResultSet rs1 = pst1.executeQuery();
                        int available, eachHas, limit, code;
                        String itemName;
                        while (rs1.next()) {

                            available = rs1.getInt("quantity");
                            eachHas = rs1.getInt("eachHas");
                            limit = rs1.getInt("StorageLimit");
                            itemName = rs1.getString("Name");
                            code = rs1.getInt("code");
                            int now = Math.round(available / eachHas);
                            int shortItems = limit - now;
                            if (shortItems > 0) {

                                Shortage sh = new Shortage(itemName, eachHas, available, shortItems);
                                items.add(sh);
                            }

                            String sql2 = "select * from orders where code ='" + code + "' and removed is null";
                            Connection connection2 = DbConnection.createConnection();
                            PreparedStatement pst2 = connection2.prepareStatement(sql2);
                            ResultSet rs2 = pst2.executeQuery();

                            while (rs2.next()) {
                                int q = rs2.getInt("quantity");
                                int need = Math.round(q / eachHas);
                                if (need > 0) {
                                    Shortage sht = new Shortage(itemName, eachHas, 0, need);
                                    items.add(sht);
                                }
                            }
                        }

                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                data = new String[items.size()][4];

                setTable();
                for (int i = 0; i < items.size(); i++) {
                    model.addRow(data[i]);
                }
            }
        });

        export = new JButton("Export Word");
        export.setFont(new java.awt.Font("Times New Roman", 1, 16));
        export.setPreferredSize(new Dimension(150, 40));
        export.setBackground(new java.awt.Color(255, 255, 102));


        String comp = (String) company.getSelectedItem();

        try {

            String sql = "select * from company where CompanyName ='" + comp + "' and removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                int index = rs.getInt("CompanyId");

                String sql1 = "select * from item where CompanyId ='" + index + "' and removed is null";
                Connection connection1 = DbConnection.createConnection();
                PreparedStatement pst1 = connection1.prepareStatement(sql1);
                ResultSet rs1 = pst1.executeQuery();
                int available, eachHas, limit, code;
                String itemName;
                while (rs1.next()) {
                    code = rs1.getInt("code");
                    itemName = rs1.getString("Name");
                    available = rs1.getInt("quantity");
                    eachHas = rs1.getInt("eachHas");
                    limit = rs1.getInt("StorageLimit");

                    int now = Math.round(available / eachHas);
                    int shortItems = limit - now;
                    if (shortItems > 0) {

                        Shortage sh = new Shortage(itemName, eachHas, available, shortItems);
                        items.add(sh);
                    }

                    String sql2 = "select * from orders where code ='" + code + "' and removed is null";
                    Connection connection2 = DbConnection.createConnection();
                    PreparedStatement pst2 = connection2.prepareStatement(sql2);
                    ResultSet rs2 = pst2.executeQuery();

                    while (rs2.next()) {
                        int q = rs2.getInt("quantity");
                        int need = Math.round(q / eachHas);
                        if (need > 0) {
                            Shortage sht = new Shortage(itemName, eachHas, 0, need);
                            items.add(sht);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        data = new String[items.size()][4];
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
        gc.gridx = 4;
        gc.gridy = 10;

        add(export);
        export.setPreferredSize(new java.awt.Dimension(150, 40));
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneActionPerformed(evt);
            }

            private void oneActionPerformed(ActionEvent evt) {
                String comp = (String) company.getSelectedItem();
                String content = comp + "\n" + "\n" + "\n";
                for (int i = 0; i < items.size(); i++) {
                    content += "Name        :  " + items.get(i).getName() + "\n";
                    content += "Each Has    :  " + Integer.toString(items.get(i).getEachHas()) + "\n";
                    content += "Available Qt:  " + Integer.toString(items.get(i).getAvailable()) + "\n";
                    content += "Need to Buy :  " + Integer.toString(items.get(i).getToBuy()) + "\n";
                    content += "\n" + "\n" + "\n";
                }
                writeToFile(content, comp + ".doc");
            }
        });


    }

    //wite to an word doc
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

    //set the table according to the linkedlist's values
    public void setTable() {
        if (!items.isEmpty()) {

            for (int i = 0; i < items.size(); i++) {
                Shortage item = items.get(i);

                data[i][0] = item.getName();
                data[i][1] = Integer.toString(item.getEachHas());
                data[i][2] = Integer.toString(item.getAvailable());
                data[i][3] = Integer.toString(item.getToBuy());


            }
        }
    }

    //return an instance of the class
    public static ShortageList getInstance() {
        if (instance == null) {
            instance = new ShortageList();
        }
        return instance;
    }
}
