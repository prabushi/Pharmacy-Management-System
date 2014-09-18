/*
 * To implement the billing process of the pharmacy
 */
package UserGUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Prabushi
 */
public class Payment extends JPanel {

    public static Payment instance = null;
    JTextField tcash;
    JTextField tbalance;
    JButton print;

    //coonstructor
    private Payment() {
         this.setBackground(new java.awt.Color(255, 255, 255));
         
        JLabel cash = new JLabel("Cash");
        JLabel balance = new JLabel("Balanace");
        print = new JButton("Print");
        print.setBackground(new java.awt.Color(255, 255, 102));
        tcash = new JTextField();
        tbalance = new JTextField();
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        gc.insets = new Insets(10, 10, 10, 10);
        
        cash.setPreferredSize(new java.awt.Dimension(220, 50));
        balance.setPreferredSize(new java.awt.Dimension(220, 50));
        tcash.setPreferredSize(new java.awt.Dimension(220, 50));
        tbalance.setPreferredSize(new java.awt.Dimension(220, 50));
        print.setPreferredSize(new java.awt.Dimension(100, 50));
        
        gc.gridx = 0;
        gc.gridy = 0;
        add(cash, gc);
        gc.gridx = 3;
        gc.gridy = 0;
        add(tcash, gc);
        
        tcash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcashActionPerformed(evt);
            }
            
            private void tcashActionPerformed(ActionEvent evt) {
               
                tbalance.setText("");
                String q = tcash.getText();
                float Tprice = Float.parseFloat(q);
                Bill b = Bill.getInstance();
                float total = b.getTotal();
                float bal = Tprice - total;
                String bal1 = String.valueOf(bal);
                tbalance.setText(bal1);
            }
        });
        gc.gridx = 0;
        gc.gridy = 1;
        add(balance, gc);
        gc.gridx = 3;
        gc.gridy = 1;
        add(tbalance, gc);
        
        gc.gridx = 2;
        gc.gridy =2;
        add(print,gc);
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }

            private void printActionPerformed(ActionEvent evt) { 
                Page p = Page.getInstance();
                p.Customer();
            }
        });
    }

    //returns an instance of the class
    public static Payment getInstance() {
        if (instance == null) {
            instance = new Payment();
        }
        return instance;
    }
}
