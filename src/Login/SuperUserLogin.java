/*
 Autehticating super user for user registration
 */
package Login;

/**
 * @author Prabushi
 */
import Database.DbConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SuperUserLogin extends JFrame implements ActionListener {

    JLabel l1, l2, l3;
    JTextField tf1;
    JButton btn1;
    JButton btn2;
    JPasswordField p1;
    public static SuperUserLogin instance = null;
    public static JFrame frame;
    JLabel jLabel;

    //constructor
    private SuperUserLogin() {
        jLabel = new javax.swing.JLabel();
        setTitle("Login Form");
        setSize(1365, 725);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(0, 0);
        setBackground(new java.awt.Color(153, 153, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1365, 725));
        pack();
        setVisible(true);
        l1 = new JLabel("Login as Super User:");
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("User:");
        l3 = new JLabel("Password:");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        btn1 = new JButton("Submit");
        btn2 = new JButton("Go Back");

        l1.setBounds(100, 30, 400, 30);
        l2.setBounds(80, 70, 200, 30);
        l3.setBounds(80, 110, 200, 30);
        tf1.setBounds(300, 70, 200, 30);
        p1.setBounds(300, 110, 200, 30);
        btn1.setBounds(150, 160, 100, 40);
        btn1.setBackground(new java.awt.Color(255, 255, 102));
        btn2.setBounds(350, 160, 100, 40);
        btn2.setBackground(new java.awt.Color(255, 255, 102));
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.VERTICAL;

        gc.gridx = 0;
        gc.gridy = 1;
        add(l1, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        add(l2, gc);

        gc.gridx = 5;
        gc.gridy = 2;
        add(tf1, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        add(l3, gc);

        gc.gridx = 5;
        gc.gridy = 3;
        add(p1, gc);

        gc.gridx = 2;
        gc.gridy = 4;
        add(btn1, gc);

        gc.gridx = 5;
        gc.gridy = 4;
        add(btn2, gc);

        jLabel.setBackground(new java.awt.Color(0, 153, 0));
        jLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background4.jpg"))); // NOI18N
        getContentPane().add(jLabel);
        jLabel.setBounds(0, 0, 1365, 725);

        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }

            private void SubmitActionPerformed(ActionEvent evt) {

                try {
                    Connection connection = DbConnection.createConnection();
                    String username = tf1.getText();
                    char[] p = p1.getPassword();
                    String userpw = new String(p);
                    String userpassword = md5(userpw);

                    String sql = "select * from (select * from users where removed is null)as t where t.name ='" + username + "' and t.password ='" + userpassword + "'";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    String privilege;
                    int userId;
                    ResultSet rs = pst.executeQuery(sql);

                    if (rs.next()) {
                        privilege = rs.getString("privilege");
                        userId = rs.getInt("userId");
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        java.util.Date date = new java.util.Date();
                        String tempDate = dateFormat.format(date);
                        Connection connection1 = DbConnection.createConnection();
                        String sql1 = "insert into login values(" + null + ",'" + userId + "','" + tempDate + "'," + null + ")";
                        PreparedStatement pst1 = connection1.prepareStatement(sql1);

                        pst1.executeUpdate(sql1);



                        if (privilege.equals("superuser")) {
                            JOptionPane.showMessageDialog(null, rs.getString("name") + " : Regiter Form");
                            instance.setVisible(false);
                            java.awt.EventQueue.invokeLater(new Runnable() {
                                public void run() {
                                    frame = Register.getInstance();
                                    frame.setVisible(true);
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                                }
                            });
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid username or password!");
                            tf1.setText("");
                            p1.setText("");

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password!");
                        tf1.setText("");
                        p1.setText("");

                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }

            private void SubmitActionPerformed(ActionEvent evt) {

                SuperUserLogin.getInstance().setVisible(false);
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        LoginPage frame = LoginPage.getInstance();
                        frame.setVisible(true);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                });
            }
        });
    }

    //return an instance of the class
    public static SuperUserLogin getInstance() {
        if (instance == null) {
            instance = new SuperUserLogin();
        }
        return instance;
    }

    //md5 function to encrypt the passwords
    public static String md5(String input) {

        String md5 = null;

        if (null == input) {
            return null;
        }

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex) 
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
