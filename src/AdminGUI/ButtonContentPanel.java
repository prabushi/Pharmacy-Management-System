/*
 Class to represents the main button panel in admin dashboard
 */
package AdminGUI;

/*
 * Main functionality of this classes are add items, add stock, get expired list,short expiry list, 
 * revenue of this month and this year and shortage lists byy company wise
 */
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Prabushi
 */
public class ButtonContentPanel extends JPanel {

    private JButton bset;
    private JButton bstock;
    private JButton breports;
    private JButton bexpiry;
    private JButton bshortage;
    AdminPage page;

    //constructor
    public ButtonContentPanel() {
        UIManager.put("ComboBox.background", new ColorUIResource(UIManager.getColor("TextField.background")));
        UIManager.put("ComboBox.foreground", new ColorUIResource(UIManager.getColor("TextField.foreground")));
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(new java.awt.Color(255, 255, 102)));
        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.BLACK));

        this.setBackground(new java.awt.Color(255, 255, 255));

        bset = new JButton();
        bset.setForeground(Color.WHITE);
        bset.setFont(new java.awt.Font("Times New Roman", 1, 30));
        bset.setText("Set");
        bset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin21.jpg")));
        bset.setHorizontalTextPosition(SwingConstants.CENTER);
        bset.setPreferredSize(new java.awt.Dimension(100, 120));
        bset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsetActionPerformed(evt);
            }

            private void bsetActionPerformed(ActionEvent evt) {
                page = AdminPage.getInstance();
                page.Set();
            }
        });

        bstock = new JButton();
        bstock.setForeground(Color.WHITE);
        bstock.setFont(new java.awt.Font("Times New Roman", 1, 28));
        bstock.setText("Stock");
        bstock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin22.jpg")));
        bstock.setHorizontalTextPosition(SwingConstants.CENTER);
        bstock.setPreferredSize(new java.awt.Dimension(100, 120));
        bstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bstockActionPerformed(evt);
            }

            private void bstockActionPerformed(ActionEvent evt) {
                page = AdminPage.getInstance();
                page.getStock();
            }
        });

        breports = new JButton();
        breports.setForeground(Color.WHITE);
        breports.setFont(new java.awt.Font("Times New Roman", 1, 22));
        breports.setText("Report");
        breports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin22.jpg")));
        breports.setHorizontalTextPosition(SwingConstants.CENTER);
        breports.setPreferredSize(new java.awt.Dimension(100, 120));
        breports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                breportActionPerformed(evt);
            }

            private void breportActionPerformed(ActionEvent evt) {
                page = AdminPage.getInstance();
                page.getShortExpiry();
            }
        });

        bexpiry = new JButton();
        bexpiry.setForeground(Color.WHITE);
        bexpiry.setFont(new java.awt.Font("Times New Roman", 1, 23));
        bexpiry.setText("Expiry");
        bexpiry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin22.jpg")));
        bexpiry.setHorizontalTextPosition(SwingConstants.CENTER);
        bexpiry.setPreferredSize(new java.awt.Dimension(100, 120));
        bexpiry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bexpiryActionPerformed(evt);
            }

            private void bexpiryActionPerformed(ActionEvent evt) {
                page = AdminPage.getInstance();
                page.getExpiry();
            }
        });
        bshortage = new JButton();
        bshortage.setForeground(Color.WHITE);
        bshortage.setFont(new java.awt.Font("Times New Roman", 1, 28));
        bshortage.setText("Lack");
        bshortage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin23.jpg")));
        bshortage.setHorizontalTextPosition(SwingConstants.CENTER);
        bshortage.setPreferredSize(new java.awt.Dimension(100, 120));
        bshortage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bshortageListActionPerformed(evt);
            }

            private void bshortageListActionPerformed(ActionEvent evt) {
                page = AdminPage.getInstance();
                page.getShortageList();
            }
        });
        this.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(0, 20, 0, 0);
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(bset, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        this.add(bstock, gc);

        gc.gridx = 0;
        gc.gridy = 5;
        this.add(breports, gc);

        gc.gridx = 0;
        gc.gridy = 7;
        this.add(bexpiry, gc);

        gc.gridx = 0;
        gc.gridy = 9;
        this.add(bshortage, gc);

    }
}
