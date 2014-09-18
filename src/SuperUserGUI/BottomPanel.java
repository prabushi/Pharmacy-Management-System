/*
 * Class that handles the botton panel of superuser panel which contains switch user and logout button
 */
package SuperUserGUI;

import AdminGUI.AdminPage;
import Database.DbConnection;
import UserGUI.Page;
import Login.LoginPage;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Prabushi
 */
public class BottomPanel extends JPanel {

    private JButton switchUser;
    private JButton logout;

    //constructor
    public BottomPanel() {
        this.setBackground(new java.awt.Color(255, 255, 255));
        switchUser = new JButton();
        switchUser.setForeground(Color.WHITE);
        switchUser.setFont(new java.awt.Font("Times New Roman", 1, 22));
        switchUser.setText("Admin View");
        switchUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/suserbottom1.jpg")));
        switchUser.setHorizontalTextPosition(SwingConstants.CENTER);
        switchUser.setPreferredSize(new java.awt.Dimension(150, 50));
        switchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchUserActionPerformed(evt);
            }

            private void switchUserActionPerformed(ActionEvent evt) {
                SUserPage.getInstance().setVisible(false);
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        JFrame frame = AdminPage.getInstance();
                        frame.setVisible(true);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                });
            }
        });

        logout = new JButton();
        logout.setForeground(Color.WHITE);
        logout.setFont(new java.awt.Font("Times New Roman", 1, 22));
        logout.setText("Logout");
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/suserbottom2.jpg")));
        logout.setHorizontalTextPosition(SwingConstants.CENTER);
        logout.setPreferredSize(new java.awt.Dimension(150, 50));
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutUserActionPerformed(evt);
            }

            private void logoutUserActionPerformed(ActionEvent evt) {
                try {
                    Connection connection = DbConnection.createConnection();

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date date = new java.util.Date();
                    String tempDate = dateFormat.format(date);
                    String sql1 = "select max(loginId) from login";

                    PreparedStatement pst = connection.prepareStatement(sql1);
                    ResultSet rs = pst.executeQuery(sql1);

                    if (rs.next()) {
                        int value = rs.getInt(1);
                        JOptionPane.showMessageDialog(null, "Logout!");
                        Connection connection1 = DbConnection.createConnection();
                        String sql2 = "update login set logout = '" + tempDate + "' where loginId = '" + value + "'";
                        PreparedStatement pst1 = connection1.prepareStatement(sql2);
                        pst1.executeUpdate(sql2);

                        SUserPage.getInstance().setVisible(false);
                        Page.getInstance().setVisible(false);
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                LoginPage frame = LoginPage.getInstance();
                                frame.setVisible(true);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                            }
                        });
                    }


                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        });
        this.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(0, 0, 20, 0);
        gc.gridx = 0;
        gc.gridy = 0;
        this.add(switchUser, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        this.add(logout, gc);
    }
}
