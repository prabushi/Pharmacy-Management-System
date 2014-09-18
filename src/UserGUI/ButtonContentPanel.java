/*
 Jpanel keeps the main buttons on the left side
 */
package UserGUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Prabushi
 */
public class ButtonContentPanel extends JPanel {

    public static ButtonContentPanel instance = null;
    private JButton bbill;
    private JButton bSEntry;
    private JButton bCompany;
    private JButton bCustomer;
    private JButton bSalesmen;
    Page page;

    //returns an instance of the class
    public static ButtonContentPanel getInstance() {
        if (instance == null) {
            instance = new ButtonContentPanel();
        }
        return instance;
    }

    //constructor
    private ButtonContentPanel() {
        this.setBackground(new java.awt.Color(255, 255, 255));

        bbill = new JButton();
        bbill.setFont(new java.awt.Font("Times New Roman", 1, 16));
        bbill.setText("Bill");
        bbill.setForeground(Color.WHITE);
        bbill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user1.jpg")));
        bbill.setHorizontalTextPosition(SwingConstants.CENTER);
        bbill.setPreferredSize(new java.awt.Dimension(100, 120));
        bbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbillActionPerformed(evt);
            }

            private void bbillActionPerformed(ActionEvent evt) {
                page = Page.getInstance();
                page.addBill1();
            }
        });

        bSEntry = new JButton();
        bSEntry.setForeground(Color.WHITE);
        bSEntry.setFont(new java.awt.Font("Times New Roman", 1, 16));
        bSEntry.setText("Order");
        bSEntry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user2.jpg")));
        bSEntry.setHorizontalTextPosition(SwingConstants.CENTER);
        bSEntry.setPreferredSize(new java.awt.Dimension(100, 120));
        bSEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSEntryActionPerformed(evt);
            }

            private void bSEntryActionPerformed(ActionEvent evt) {
                page = Page.getInstance();
                page.Order();
            }
        });

        bCompany = new JButton();
        bCompany.setForeground(Color.WHITE);
        bCompany.setFont(new java.awt.Font("Times New Roman", 1, 16));
        bCompany.setText("Company");
        bCompany.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user4.jpg")));
        bCompany.setHorizontalTextPosition(SwingConstants.CENTER);
        bCompany.setPreferredSize(new java.awt.Dimension(100, 120));
        bCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCompanyActionPerformed(evt);
            }

            private void bCompanyActionPerformed(ActionEvent evt) {
                page = Page.getInstance();
                page.Company();
            }
        });

        bCustomer = new JButton();
        bCustomer.setForeground(Color.WHITE);
        bCustomer.setFont(new java.awt.Font("Times New Roman", 1, 16));
        bCustomer.setText("Customer");
        bCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user3.jpg")));
        bCustomer.setHorizontalTextPosition(SwingConstants.CENTER);
        bCustomer.setPreferredSize(new java.awt.Dimension(100, 120));
        bCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCustomerActionPerformed(evt);
            }

            private void bCustomerActionPerformed(ActionEvent evt) {
                page = Page.getInstance();
                page.Customer();
            }
        });

        bSalesmen = new JButton();
        bSalesmen.setForeground(Color.WHITE);
        bSalesmen.setFont(new java.awt.Font("Times New Roman", 1, 16));
        bSalesmen.setText("Salesmen");
        bSalesmen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user5.jpg")));
        bSalesmen.setHorizontalTextPosition(SwingConstants.CENTER);
        bSalesmen.setPreferredSize(new java.awt.Dimension(100, 120));
        bSalesmen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalesmenActionPerformed(evt);
            }

            private void bSalesmenActionPerformed(ActionEvent evt) {
                page = Page.getInstance();
                page.Salesmen();
            }
        });

        this.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(0, 10, 0, 0);
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(bbill, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        this.add(bSEntry, gc);

        gc.gridx = 0;
        gc.gridy = 5;
        this.add(bCustomer, gc);

        gc.gridx = 0;
        gc.gridy = 7;
        this.add(bCompany, gc);

        gc.gridx = 0;
        gc.gridy = 9;
        this.add(bSalesmen, gc);

    }
}
