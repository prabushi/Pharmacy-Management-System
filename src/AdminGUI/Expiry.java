/*
 * The classs to get the expired items that are already in the database
 */
package AdminGUI;
//we can select the items and manually remove these items
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
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Prabushi
 */
public class Expiry extends JPanel {

    public static Expiry instance = null;
    LinkedList<Section> sections = new LinkedList<Section>();
    DefaultTableModel model;
    JTable table;
    String name = null;
    String itemBatch = null;
    String itemexpiry = null;
    JButton remove;

    //constuctor
    public Expiry() {

        remove = new JButton("remove");
        remove.setBackground(new java.awt.Color(255, 255, 102));
        String[] columnNames = {"Name", "Company", "Batch No.", "Expiry Date", "Available Qt"};
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {

            String sql1 = "select * from section where removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst1 = connection.prepareStatement(sql1);
            ResultSet rs = pst1.executeQuery();
            String expiry, batch;
            int quantity, code, index;

            Date date2 = new java.util.Date();
            while (rs.next()) {
                expiry = rs.getString("ExpiryDate");
                Date date1 = formatter.parse(expiry);
                if (date2.compareTo(date1) == 0 || date2.compareTo(date1) == 1) {
                    batch = rs.getString("BatchNo");
                    code = rs.getInt("code");
                    quantity = rs.getInt("Quantity");
                    index = rs.getInt("IndexNo");
                    Section sec = new Section(expiry, code, quantity, batch, 0, null, index, 0);
                    sections.add(sec);
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        this.getSectionInfo();
        String[][] data = new String[sections.size()][5];
        for (int i = 0; i < sections.size(); i++) {
            Section sec = sections.get(i);

            data[i][0] = sec.getName();
            data[i][1] = sec.getCompany();
            data[i][2] = sec.getBatchNo();
            data[i][3] = sec.getExpiry();
            data[i][4] = Integer.toString(sec.getAvailable());

        }


        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 300));
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        table.addMouseListener(new java.awt.event.MouseListener() {
            public void tableMouseClicked(java.awt.event.MouseEvent evt) {
                mouseClicked(evt);
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                int x = table.getSelectedRow();
                name = (String) model.getValueAt(x, 0);
                itemBatch = (String) model.getValueAt(x, 2);
                itemexpiry = (String) model.getValueAt(x, 3);

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

        add(remove);
        remove.setPreferredSize(new java.awt.Dimension(120, 40));
        remove.setFont(new java.awt.Font("Times New Roman", 1, 16));
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneActionPerformed(evt);
            }

            private void oneActionPerformed(ActionEvent evt) {
                try {

                    for (int i = 0; i < sections.size(); i++) {
                        Section sec = sections.get(i);
                        if (sec.getName().equals(name) && sec.getExpiry().equals(itemexpiry) && sec.getBatchNo().equals(itemBatch)) {
                            int sectionId = sec.getSectionId();
                            String sectionid = Integer.toString(sectionId);

                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            java.util.Date date = new java.util.Date();
                            String Date = dateFormat.format(date);

                            String sql = "update section set removed ='" + Date + "' where IndexNo = '" + sectionid + "'";

                            Connection connection = DbConnection.createConnection();
                            PreparedStatement pst = connection.prepareStatement(sql);
                            pst.executeUpdate();

                            String sql2 = "select * from item where Name = '" + name + "' and removed is null";

                            Connection connection2 = DbConnection.createConnection();
                            PreparedStatement pst2 = connection2.prepareStatement(sql2);
                            ResultSet rs2 = pst2.executeQuery();
                            int quan = 0;
                            if (rs2.next()) {
                                quan = rs2.getInt("quantity");
                            }
                            quan = quan - sec.getQuantity();
                            String sql1 = "update item set quantity ='" + quan + "' where Name = '" + name + "' and removed is null";

                            Connection connection1 = DbConnection.createConnection();
                            PreparedStatement pst1 = connection1.prepareStatement(sql1);
                            pst1.executeUpdate();

                        }
                    }
                    model.removeRow(table.getSelectedRow());

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

    }

    //method to get the selescted information of an item when it was selected from the table
    public void getSectionInfo() {
        if (!sections.isEmpty()) {
            for (int i = 0; i < sections.size(); i++) {
                Section sec = sections.get(i);
                String code = Integer.toString(sec.getCode());
                try {
                    String sql = "select * from item where code ='" + code + "' and removed is null";
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    String name;
                    int companyId, eachHas;

                    if (rs.next()) {
                        name = rs.getString("Name");
                        sec.setName(name);
                        companyId = rs.getInt("CompanyId");
                        eachHas = rs.getInt("eachHas");
                        sec.setEachHas(eachHas);

                        String company = Integer.toString(companyId);

                        String sql1 = "select * from company where CompanyId ='" + company + "' and removed is null";
                        Connection connection1 = DbConnection.createConnection();
                        PreparedStatement pst1 = connection1.prepareStatement(sql1);
                        ResultSet rs1 = pst1.executeQuery();
                        if (rs1.next()) {
                            company = rs1.getString("CompanyName");
                            sec.setCompany(company);
                        }
                        String secId = Integer.toString(sec.getSectionId());
                        String sql2 = "select * from sold where SectionId ='" + secId + "'";
                        Connection connection2 = DbConnection.createConnection();
                        PreparedStatement pst2 = connection2.prepareStatement(sql2);
                        ResultSet rs2 = pst2.executeQuery();
                        int count = 0;
                        while (rs2.next()) {
                            count += rs2.getInt("Quantity");
                        }
                        sec.setAvailable(eachHas * sec.getQuantity() - count);
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

//method to get the instance of the class
    public static Expiry getInstance() {
        if (instance == null) {
            instance = new Expiry();
        }
        return instance;
    }
}
