/*
 User registration 
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

public class Register extends JFrame implements ActionListener {

    //only the superuser can add users to the database
    /*
     when ever registration button pressed it will direct to the superuser authentication 
     */
    JLabel l1, l2, l3, l4, l5;
    JTextField tf1;
    JButton btn1;
    JButton btn2;
    JComboBox com;
    JPasswordField p1, p2;
    public static JFrame frame;
    public static Register instance;
    String[] priv = {"admin", "user", "superuser"};
    JLabel jLabel;

    //constructor
    private Register() {
        jLabel = new javax.swing.JLabel();
        setTitle("Register Form");
        setVisible(true);
        setSize(1365, 725);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(0, 0);

        l1 = new JLabel("Register Form:");
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("User Name:");
        l3 = new JLabel("Password:");
        l4 = new JLabel("Retype Password:");
        l5 = new JLabel("Privilege:");
        tf1 = new JTextField();
        com = new JComboBox(priv);
        p1 = new JPasswordField();
        p2 = new JPasswordField();
        btn1 = new JButton("Submit");
        btn2 = new JButton("Super User Dashboard");


        l1.setBounds(100, 30, 400, 30);
        l2.setBounds(80, 70, 200, 30);
        l5.setBounds(80, 110, 200, 30);
        l3.setBounds(80, 150, 200, 30);
        l4.setBounds(80, 190, 200, 30);
        tf1.setBounds(300, 70, 200, 30);
        com.setBounds(300, 110, 200, 30);
        p1.setBounds(300, 150, 200, 30);
        p2.setBounds(300, 190, 200, 30);
        btn1.setBounds(150, 230, 200, 40);
        btn1.setBackground(new java.awt.Color(255, 255, 102));
        btn2.setBounds(375, 230, 200, 40);
        btn2.setBackground(new java.awt.Color(255, 255, 102));

        add(l1);

        add(l2);

        add(tf1);
        add(com);

        add(l3);
        add(l4);
        add(l5);

        add(p1);
        add(p2);
        add(btn1);
        add(btn2);

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
                    String username = tf1.getText();
                    char[] P1 = p1.getPassword();
                    char[] P2 = p2.getPassword();
                    String privi = (String) com.getSelectedItem();
                    String userpassword = new String(P1);
                    String reuserpassword = new String(P2);


                    if (userpassword.equals(reuserpassword)) {

                        Connection connection = DbConnection.createConnection();


//pst.setString(1,username);
//pst.setString(2,userpassword);
//pst.setString(3,privi
                        userpassword = md5(reuserpassword);
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        java.util.Date date = new java.util.Date();
                        String tempDate = dateFormat.format(date);
                        String sql = "insert into users values(null,'" + username + "','" + userpassword + "','" + privi + "','" + tempDate + "'," + null + ")";
                        PreparedStatement pst = connection.prepareStatement(sql);
                        pst.executeUpdate(sql);

                        JOptionPane.showMessageDialog(null, username + " has been added with " + privi + " privilege.");
                        tf1.setText("");
                        p1.setText("");
                        p2.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Passwords don't match!");
                        tf1.setText("");
                        p1.setText("");
                        p2.setText("");
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterActionPerformed(evt);
            }

            private void RegisterActionPerformed(ActionEvent evt) {
                instance.setVisible(false);
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        frame = SuperUserGUI.SUserPage.getInstance();
                        frame.setVisible(true);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    }
                });
            }
        });


    }

    //return an instance of the class
    public static Register getInstance() {
        if (instance == null) {
            instance = new Register();
        }
        return instance;
    }

    //use md5 function to encrypt the password
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