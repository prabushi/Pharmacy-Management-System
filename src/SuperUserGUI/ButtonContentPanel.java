/*
 * The class that contains the main button panel of super user dashboard
 */
package SuperUserGUI;

//Jpanel keeps the main buttons on the left side
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

//return an instance of the class
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
        bbill.setFont(new java.awt.Font("Times New Roman", 1, 25));
        bbill.setText("Profit");
        bbill.setForeground(Color.WHITE);
        bbill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/suser1.jpg")));
        bbill.setHorizontalTextPosition(SwingConstants.CENTER);
        bbill.setPreferredSize(new java.awt.Dimension(100, 120));
        bbill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbillActionPerformed(evt);
            }

            private void bbillActionPerformed(ActionEvent evt) {
                SUserPage pg = SUserPage.getInstance();
                pg.getDailyProfit();
            }
        });

        bSEntry = new JButton();
        bSEntry.setForeground(Color.WHITE);
        bSEntry.setFont(new java.awt.Font("Times New Roman", 1, 25));
        bSEntry.setText("Login");
        bSEntry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/suser2.jpg")));
        bSEntry.setHorizontalTextPosition(SwingConstants.CENTER);
        bSEntry.setPreferredSize(new java.awt.Dimension(100, 120));
        bSEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSEntryActionPerformed(evt);
            }

            private void bSEntryActionPerformed(ActionEvent evt) {
                SUserPage pg = SUserPage.getInstance();
                pg.getLoginDetails();
            }
        });

        bCompany = new JButton();
        bCompany.setForeground(Color.WHITE);
        bCompany.setFont(new java.awt.Font("Times New Roman", 1, 25));
        bCompany.setText("Stock");
        bCompany.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/suser4.jpg")));
        bCompany.setHorizontalTextPosition(SwingConstants.CENTER);
        bCompany.setPreferredSize(new java.awt.Dimension(100, 120));
        bCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCompanyActionPerformed(evt);
            }

            private void bCompanyActionPerformed(ActionEvent evt) {
                SUserPage pg = SUserPage.getInstance();
                pg.getStock();
            }
        });

        bCustomer = new JButton();
        bCustomer.setForeground(Color.WHITE);
        bCustomer.setFont(new java.awt.Font("Times New Roman", 1, 25));
        bCustomer.setText("Bills");
        bCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/suser3.jpg")));
        bCustomer.setHorizontalTextPosition(SwingConstants.CENTER);
        bCustomer.setPreferredSize(new java.awt.Dimension(100, 120));
        bCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCustomerActionPerformed(evt);
            }

            private void bCustomerActionPerformed(ActionEvent evt) {
                SUserPage pg = SUserPage.getInstance();
                pg.getBills();
            }
        });

        bSalesmen = new JButton();
        bSalesmen.setForeground(Color.WHITE);
        bSalesmen.setFont(new java.awt.Font("Times New Roman", 1, 25));
        bSalesmen.setText("Other");
        bSalesmen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/suser5.jpg")));
        bSalesmen.setHorizontalTextPosition(SwingConstants.CENTER);
        bSalesmen.setPreferredSize(new java.awt.Dimension(100, 120));
        bSalesmen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalesmenActionPerformed(evt);
            }

            private void bSalesmenActionPerformed(ActionEvent evt) {
                SUserPage pg = SUserPage.getInstance();
                pg.getOther1();
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
