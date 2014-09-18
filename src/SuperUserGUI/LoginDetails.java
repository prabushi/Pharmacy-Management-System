/*
 * Class to display logindetails of the users
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
import java.util.Calendar;
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
 * @author Prabushi
 */
public class LoginDetails extends JPanel {

    LinkedList<Item> session = new LinkedList<Item>();
    DefaultTableModel model;
    JTable table;
    JComboBox month;
    String[][] data = new String[session.size()][5];
    String[] columnNames = {"UserId", "Name", "Privilege", "Login At", "Logout At"};
    public static LoginDetails instance = null;
    String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
        "August", "September", "October", "November", "December"};

//constructor
    public LoginDetails() {

        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new java.awt.Color(255, 255, 102)));
        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.BLACK));
        this.setBackground(new java.awt.Color(255, 255, 255));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        gc.insets = new Insets(10, 10, 10, 10);

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;

        JLabel Lmonth = new JLabel("Month :");
        Lmonth.setPreferredSize(new Dimension(110, 25));
        month = new JComboBox();
        month.setModel(new javax.swing.DefaultComboBoxModel(monthName));

        gc.gridx = -1;
        gc.gridy = 6;
        add(Lmonth, gc);
        gc.gridx = 1;
        gc.gridy = 6;
        add(month, gc);


        month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthActionPerformed(evt);
            }

            private void monthActionPerformed(ActionEvent evt) {

                session.clear();
                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();

                String selectedMonth = (String) month.getSelectedItem();
                String monthNo = getMonth(selectedMonth);

                try {

                    String sql = "SELECT userId, name, privilege, login, logout FROM users NATURAL JOIN login";
                    Connection connection = DbConnection.createConnection();
                    PreparedStatement pst = connection.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        String login = rs.getString("login");
                        int year;
                        String sessionYear;
                        Calendar cal = Calendar.getInstance();
                        year = cal.get(Calendar.YEAR);
                        int tempYear = year;
                        sessionYear = login.substring(0, 4);

                        if (Integer.parseInt(sessionYear) == tempYear) {
                            int tempMonth = Integer.parseInt(login.substring(5, 7));
                            int x = Integer.parseInt(monthNo);
                            if (x == tempMonth) {
                                int userId;
                                String name, privilege, logout;
                                userId = rs.getInt("userId");
                                name = rs.getString("name");
                                privilege = rs.getString("privilege");
                                logout = rs.getString("logout");
                                Item item = new Item(userId, name, privilege, login, logout);
                                session.add(item);
                            }
                        }

                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
                data = new String[session.size()][5];

                setTable();
                for (int i = 0; i < session.size(); i++) {
                    model.addRow(data[i]);
                }
            }
        });


        session.clear();

        String selectedMonth = (String) month.getSelectedItem();
        String monthNo = getMonth(selectedMonth);

        try {

            String sql = "SELECT userId, name, privilege, login, logout FROM users NATURAL JOIN login";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String login = rs.getString("login");
                int year;
                String sessionYear;
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                int tempYear = year;
                sessionYear = login.substring(0, 4);

                if (Integer.parseInt(sessionYear) == tempYear) {
                    int tempMonth = Integer.parseInt(login.substring(5, 7));
                    int x = Integer.parseInt(monthNo);
                    if (x == tempMonth) {
                        int userId;
                        String name, privilege, logout;
                        userId = rs.getInt("userId");
                        name = rs.getString("name");
                        privilege = rs.getString("privilege");
                        logout = rs.getString("logout");
                        Item item = new Item(userId, name, privilege, login, logout);
                        session.add(item);
                    }
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        data = new String[session.size()][5];
        this.setTable();

        for (int i = 0; i < session.size(); i++) {
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

    }

    //method to set login details to the table
    public void setTable() {

        if (!session.isEmpty()) {

            for (int i = 0; i < session.size(); i++) {
                Item item = session.get(i);

                data[i][0] = Integer.toString(item.getUserId());
                data[i][1] = item.getName();
                data[i][2] = item.getPrivilege();
                data[i][3] = item.getLogin();
                data[i][4] = item.getLogout();

            }
        }
    }

    //get month number when the monthe name was given
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
    public static LoginDetails getInstance() {
        if (instance == null) {
            instance = new LoginDetails();
        }
        return instance;
    }
}
