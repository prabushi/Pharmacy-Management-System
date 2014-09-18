/*
 Add items to the database
 */
package AdminGUI;

/**
 *
 * @author Prabushi
 */
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

public class Set extends JPanel {

    public static Set instance = null;
    private JButton add;
    private JButton edit;
    JTextField Bname;
    JTextField BgName;
    JComboBox Bcompany;
    JTextField Bcode;
    JTextField Bamount;
    JComboBox Btype;
    JTextField BstockLimit;
    JTextArea BDescription;

    //constructor
    public Set() {

        this.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.VERTICAL;
        gc.insets = new Insets(10, 10, 10, 10);

        JLabel name = new JLabel("Name :");
        name.setPreferredSize(new Dimension(100, 30));
        JLabel gName = new JLabel("Generic Name :");
        gName.setPreferredSize(new Dimension(100, 30));
        JLabel company = new JLabel("Company Name :");
        company.setPreferredSize(new Dimension(100, 30));
        JLabel code = new JLabel("Code No. :");
        code.setPreferredSize(new Dimension(100, 30));
        JLabel expiry = new JLabel("Type :");
        expiry.setPreferredSize(new Dimension(100, 30));
        JLabel amount = new JLabel("1x :");
        amount.setPreferredSize(new Dimension(100, 30));
        JLabel stockLimit = new JLabel("Stock Limit");
        stockLimit.setPreferredSize(new Dimension(100, 30));
        JLabel description = new JLabel("Description");
        description.setPreferredSize(new Dimension(100, 30));

        Bname = new JTextField();
        Bname.setPreferredSize(new Dimension(150, 30));
        BgName = new JTextField();
        BgName.setPreferredSize(new Dimension(150, 30));
        Bcompany = new JComboBox();
        Bcompany.setPreferredSize(new Dimension(150, 30));
        Bcode = new JTextField();
        Bcode.setPreferredSize(new Dimension(150, 30));
        Bamount = new JTextField();
        Bamount.setPreferredSize(new Dimension(150, 30));
        Btype = new JComboBox();
        Btype.setPreferredSize(new Dimension(150, 30));

        BstockLimit = new JTextField();
        BstockLimit.setPreferredSize(new Dimension(150, 30));
        BDescription = new JTextArea();
        BDescription.setPreferredSize(new Dimension(150, 30));

        add = new JButton("Add");
        add.setFont(new java.awt.Font("Times New Roman", 1, 16));
        add.setBackground(new java.awt.Color(255, 255, 102));
        add.setPreferredSize(new Dimension(120, 40));
        edit = new JButton("Edit Content");
        edit.setFont(new java.awt.Font("Times New Roman", 1, 16));
        edit.setBackground(new java.awt.Color(255, 255, 102));
        edit.setPreferredSize(new Dimension(120, 40));

        gc.weightx = 1.;
        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridx = 1;
        gc.gridy = 1;
        add(name, gc);
        gc.gridx = 5;
        gc.gridy = 1;
        add(Bname, gc);

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
        try {
            String sql1 = "select * from company where removed is null";
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst1 = connection1.prepareStatement(sql1);
            ResultSet rs = pst1.executeQuery();
            String comp = null;
            while (rs.next()) {
                comp = rs.getString("CompanyName");
                Bcompany.addItem(comp);
            }
        } catch (Exception e) {
        }
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
        try {
            String sql1 = "select * from typelist";
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst1 = connection1.prepareStatement(sql1);
            ResultSet rs = pst1.executeQuery();
            String type = null;
            while (rs.next()) {
                type = rs.getString("type");
                Btype.addItem(type);
            }
        } catch (Exception e) {
        }

        Btype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BexpiryYActionPerformed(evt);
            }
        });

        gc.gridx = 1;
        gc.gridy = 11;
        add(stockLimit, gc);
        BstockLimit.setBounds(5, 10, 3, 1);
        gc.gridx = 5;
        gc.gridy = 11;
        add(BstockLimit, gc);
        BstockLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BstockLimitActionPerformed(evt);
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
                BamountLimitActionPerformed(evt);
            }

            private void BamountLimitActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        gc.gridx = 1;
        gc.gridy = 13;
        add(description, gc);
        BDescription.setBounds(5, 10, 3, 1);
        gc.gridx = 5;
        gc.gridy = 13;
        add(BDescription, gc);


        gc.gridx = 2;
        gc.gridy = 14;
        add(add, gc);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    addActionPerformed(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(Set.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void addActionPerformed(ActionEvent evt) throws SQLException {
                try {
                } catch (Exception e) {
                }

                try {

                    String name, gname, company = null, code, amount;
                    company = (String) Bcompany.getSelectedItem();
                    String sql1 = "select * from company where CompanyName = '" + company + "' and removed is null";
                    Connection connection1 = DbConnection.createConnection();
                    PreparedStatement pst1 = connection1.prepareStatement(sql1);
                    ResultSet rs = pst1.executeQuery();
                    int comp = 0, type = 0;
                    if (rs.next()) {
                        comp = rs.getInt("CompanyId");
                    }


                    String temp = (String) Btype.getSelectedItem();
                    String sql2 = "select * from typelist where type = '" + temp + "'";
                    Connection connection2 = DbConnection.createConnection();
                    PreparedStatement pst2 = connection2.prepareStatement(sql2);
                    ResultSet rs2 = pst2.executeQuery();
                    if (rs2.next()) {
                        type = rs2.getInt("TypeId");
                    }

                    Connection connection = DbConnection.createConnection();
                    String sql = "insert into item values(?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement pst = connection.prepareStatement(sql);

                    name = Bname.getText();
                    pst.setString(1, name);
                    gname = BgName.getText();
                    pst.setString(2, gname);

                    pst.setInt(3, comp);
                    code = Bcode.getText();
                    pst.setInt(4, Integer.parseInt(code));
                    pst.setInt(5, type);
                    pst.setInt(6, Integer.parseInt(BstockLimit.getText()));
                    pst.setString(7, BDescription.getText());
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date date = new java.util.Date();
                    System.out.println(dateFormat.format(date));
                    pst.setString(8, dateFormat.format(date));
                    pst.setString(9, null);
                    amount = Bamount.getText();
                    pst.setInt(10, Integer.parseInt(amount));
                    pst.setInt(11, 0);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, code + ": " + name + "(" + gname + ") from " + company + " has been added.");

                    Bname.setText("");
                    BgName.setText("");
                    Bcode.setText("");
                    BstockLimit.setText("");
                    BDescription.setText("");
                    Bamount.setText("");
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        });
        gc.gridx = 7;
        gc.gridy = 14;
        add(edit, gc);
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }

            private void clearActionPerformed(ActionEvent evt) {
                AdminPage ap = AdminPage.getInstance();
                ap.edit();
            }
        });
    }

    private void BstockLimitActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BnameActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BgNameActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BcodeActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BcompanyActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void BexpiryYActionPerformed(java.awt.event.ActionEvent evt) {
    }

    //returns an instance of the class
    public static Set getInstance() {
        if (instance == null) {
            instance = new Set();
        }
        return instance;
    }
}
