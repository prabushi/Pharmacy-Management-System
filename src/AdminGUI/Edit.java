/*
 Allow deleting items
 */
package AdminGUI;

// When press delete button it will save the time deleted on the database to ensure non-repudiation 
import Database.DbConnection;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Prabushi
 */
public class Edit extends JPanel {

    public static Edit instance = null;
    private JButton delete;
    private JButton add;
    JComboBox Bname;
    JTextField BgName;
    JTextField Bcompany;
    JTextField Bcode;
    JTextField Btype;
    JTextField Bamount;
    JLabel name = new JLabel("Name :");
    JLabel gName = new JLabel("Generic Name :");
    JLabel company = new JLabel("Company Name :");
    JLabel code = new JLabel("Code No. :");
    JLabel expiry = new JLabel("Type :");
    JLabel amount = new JLabel("1x :");
    GridBagConstraints gc;

    //constructor
    public Edit() {
        name.setPreferredSize(new Dimension(100, 30));
        gName.setPreferredSize(new Dimension(100, 30));
        company.setPreferredSize(new Dimension(100, 30));
        code.setPreferredSize(new Dimension(100, 30));
        expiry.setPreferredSize(new Dimension(100, 30));
        amount.setPreferredSize(new Dimension(100, 30));
        this.setLayout(new GridBagLayout());

        gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.VERTICAL;
        gc.insets = new Insets(10, 10, 10, 10);

        Bname = new JComboBox();
        Bname.setPreferredSize(new Dimension(220, 30));
        Bname.setSize(400, 30);
        BgName = new JTextField();
        BgName.setPreferredSize(new Dimension(220, 30));
        Bcompany = new JTextField();
        Bcompany.setPreferredSize(new Dimension(220, 30));
        Bcode = new JTextField();
        Bcode.setPreferredSize(new Dimension(220, 30));
        Btype = new JTextField();
        Btype.setPreferredSize(new Dimension(220, 30));
        Bamount = new JTextField();
        Bamount.setPreferredSize(new Dimension(220, 30));

        delete = new JButton("Delete");
        delete.setFont(new java.awt.Font("Times New Roman", 1, 16));
        delete.setPreferredSize(new Dimension(120, 40));
        delete.setBackground(new java.awt.Color(255, 255, 102));
        add = new JButton("Add New");
        add.setFont(new java.awt.Font("Times New Roman", 1, 16));
        add.setPreferredSize(new Dimension(120, 40));
        add.setBackground(new java.awt.Color(255, 255, 102));

        gc.weightx = 1.;
        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx = 1;
        gc.gridy = 1;
        add(name, gc);
        gc.gridx = 5;
        gc.gridy = 1;
        add(Bname, gc);

        this.setItemList();

        String Tname, Tgname, Ttype = null, Tdescription, Tcom = null;
        int Tcode, TstockLimit, Tcompany, Ttyp = 0, Tamount;
        Tname = (String) Bname.getSelectedItem();

        String sql = "select * from item where Name ='" + Tname + "' and removed is null";
        try {
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                Tgname = rs1.getString("Generic");

                BgName.setText(Tgname);

                Tcompany = rs1.getInt("CompanyId");
                String sql2 = "select * from company where CompanyId ='" + Tcompany + "' and removed is null";
                Connection connection2 = DbConnection.createConnection();
                PreparedStatement pst2 = connection2.prepareStatement(sql2);
                ResultSet rs2 = pst2.executeQuery(sql2);
                if (rs2.next()) {
                    Tcom = rs2.getString("CompanyName");
                }

                Bcompany.setText(Tcom);
                Tcode = rs1.getInt("code");
                Bcode.setText(Integer.toString(Tcode));
                Tamount = rs1.getInt("eachHas");
                Bamount.setText(Integer.toString(Tamount));
                Ttyp = rs1.getInt("Type");

                String sql3 = "select * from typelist where TypeId ='" + Ttyp + "'";
                Connection connection3 = DbConnection.createConnection();
                PreparedStatement pst3 = connection3.prepareStatement(sql3);
                ResultSet rs3 = pst3.executeQuery(sql3);
                if (rs3.next()) {
                    Ttype = rs3.getString("type");
                }

                Btype.setText(Ttype);

            }
        } catch (Exception e) {
        }

        Bname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BnameActionPerformed(evt);
            }
        });
        gc.gridx = 1;
        gc.gridy = 2;
        add(gName, gc);
        gc.gridx = 5;
        gc.gridy = 2;
        add(BgName, gc);
        BgName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BgNameActionPerformed(evt);
            }
        });

        gc.gridx = 1;
        gc.gridy = 3;
        add(code, gc);
        gc.gridx = 5;
        gc.gridy = 3;
        add(Bcode, gc);
        Bcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BcodeActionPerformed(evt);
            }
        });
        gc.gridx = 1;
        gc.gridy = 4;
        add(company, gc);
        gc.gridx = 5;
        gc.gridy = 4;
        add(Bcompany, gc);
        Bcompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BcompanyActionPerformed(evt);
            }
        });
        gc.gridx = 1;
        gc.gridy = 6;
        add(expiry, gc);
        gc.gridx = 5;
        gc.gridy = 6;
        add(Btype, gc);

        Btype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BexpiryYActionPerformed(evt);
            }
        });

        gc.gridx = 1;
        gc.gridy = 12;
        add(amount, gc);
        Bamount.setBounds(5, 10, 3, 1);
        gc.gridx = 5;
        gc.gridy = 12;
        add(Bamount, gc);
        Bamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BamountActionPerformed(evt);
            }

            private void BamountActionPerformed(ActionEvent evt) {
            }
        });

        gc.gridx = 2;
        gc.gridy = 14;
        add(delete, gc);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    addActionPerformed(evt);

                } catch (SQLException ex) {
                    Logger.getLogger(Set.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void addActionPerformed(ActionEvent evt) throws SQLException {

                String tempName, tempCode;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date date = new java.util.Date();
                String tempDate = dateFormat.format(date);
                tempName = (String) Bname.getSelectedItem();
                tempCode = (String) Bcode.getText();

                String sql2 = "update item set removed = '" + tempDate + "' where Name ='" + tempName + "' and code ='" + tempCode + "'";
                try {
                    Connection connection2 = DbConnection.createConnection();
                    PreparedStatement pst = connection2.prepareStatement(sql2);
                    pst.executeUpdate(sql2);

                    JOptionPane.showMessageDialog(null, tempCode + ": " + tempName + " has been deleted.");

                    Bname.removeAllItems();

                    String sql1 = "select * from item where removed is null";
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst1 = connection.prepareStatement(sql1);
                    ResultSet rs = pst1.executeQuery();

                    String xname = null;
                    while (rs.next()) {
                        xname = rs.getString("Name");
                        Bname.addItem(xname);
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        });

        gc.gridx = 7;
        gc.gridy = 14;
        add(add, gc);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }

            private void addActionPerformed(ActionEvent evt) {
                AdminPage ap = AdminPage.getInstance();
                ap.Set();
            }
        });
    }

    //actions to be performed when the item name has been selected
    private void BnameActionPerformed(java.awt.event.ActionEvent evt) {

        String Tname, Tgname, Tcom = null, Ttype = null, Tdescription;
        int Tcode, TstockLimit, Tcompany, Ttyp, Tamount;
        Tname = (String) Bname.getSelectedItem();

        String sql = "select * from item where Name ='" + Tname + "' and removed is null";
        try {
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);

            if (rs1.next()) {
                Tgname = rs1.getString("Generic");
                BgName.setText(Tgname);
                Tcompany = rs1.getInt("CompanyId");
                String sql2 = "select * from company where CompanyId ='" + Tcompany + "' and removed is null";
                Connection connection2 = DbConnection.createConnection();
                PreparedStatement pst2 = connection2.prepareStatement(sql2);
                ResultSet rs2 = pst2.executeQuery(sql2);
                if (rs2.next()) {
                    Tcom = rs2.getString("CompanyName");
                }

                Bcompany.setText(Tcom);
                Tcode = rs1.getInt("code");
                Bcode.setText(Integer.toString(Tcode));
                Tamount = rs1.getInt("eachHas");
                Bamount.setText(Integer.toString(Tamount));
                Ttyp = rs1.getInt("Type");

                String sql3 = "select * from typelist where TypeId ='" + Ttyp + "'";
                Connection connection3 = DbConnection.createConnection();
                PreparedStatement pst3 = connection3.prepareStatement(sql3);
                ResultSet rs3 = pst3.executeQuery(sql3);
                if (rs3.next()) {
                    Ttype = rs3.getString("type");
                }

                Btype.setText(Ttype);

            }
        } catch (Exception e) {
        }

    }

    private void BgNameActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BcodeActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BcompanyActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BexpiryYActionPerformed(java.awt.event.ActionEvent evt) {
    }

    //gives an instance of the system
    public static Edit getInstance() {
        if (instance == null) {
            instance = new Edit();
        }
        return instance;
    }

    //method to set the item list to name combobox
    private void setItemList() {
        try {
            String sql1 = "select * from item where removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst1 = connection.prepareStatement(sql1);
            ResultSet rs = pst1.executeQuery();

            String xname = null;
            while (rs.next()) {
                xname = rs.getString("Name");
                Bname.addItem(xname);
            }
        } catch (Exception e) {
            System.out.println();
        }
    }
}
