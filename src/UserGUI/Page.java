/*
* Class to add jpanels to the jframe
 */
package UserGUI;

/**
 *
 * @author Prabushi
 */
import javax.swing.*;
import java.awt.*;

public final class Page extends JFrame {

    private JPanel content;
    private JPanel contentPanel;
    private JPanel jpContentPanel;
    private JPanel bottomContent;
    GridBagConstraints gc;
    public static Page instance = null;

    //constructor
    private Page() {
        this.setBackground(new java.awt.Color(255, 255, 255));

        setVisible(true);
        setBackground(new java.awt.Color(153, 153, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1365, 725));
        pack();

        content = ButtonContentPanel.getInstance();
        bottomContent = new BottomPanel();
        content.setBackground(new java.awt.Color(255, 255, 255));

        bottomContent.setBackground(new java.awt.Color(255, 255, 255));
        add(content, BorderLayout.WEST);
        add(bottomContent, BorderLayout.SOUTH);
        this.addBill1();

    }

    //add the panel which bii according to the doctors prescription
    public void addBill1() {
         
        jpContentPanel = ContentPanel.getInstance();
        jpContentPanel.setLayout(new GridBagLayout());
        contentPanel = BillUp1Panel.getInstance();

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        jpContentPanel.setBackground(new java.awt.Color(255, 255, 255));

        jpContentPanel.removeAll();
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        gc = new GridBagConstraints();
        gc.gridx = 3;
        gc.gridy = 0;


        jpContentPanel.add(contentPanel, gc);
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        add(jpContentPanel);
    }
    
//add the panel which bill according to a fixed quantity
    public void addBill2() {
         
        jpContentPanel = ContentPanel.getInstance();
        jpContentPanel.setLayout(new GridBagLayout());
        contentPanel = BillUp2Panel.getInstance();
        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        jpContentPanel.setBackground(new java.awt.Color(255, 255, 255));

        jpContentPanel.removeAll();
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        gc = new GridBagConstraints();
        gc.gridx = 3;
        gc.gridy = 0;

        jpContentPanel.add(contentPanel, gc);
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        add(jpContentPanel);
    }

    //add panel which bii according to the fixed price
    public void addBill3() {

        jpContentPanel = ContentPanel.getInstance();
        jpContentPanel.setLayout(new GridBagLayout());
        contentPanel = BillUp3Panel.getInstance();
        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        jpContentPanel.setBackground(new java.awt.Color(255, 255, 255));

        jpContentPanel.removeAll();
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        gc = new GridBagConstraints();
        gc.gridx = 3;
        gc.gridy = 0;

        jpContentPanel.add(contentPanel, gc);
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        add(jpContentPanel);
    }

    //add the panel to check for balance in the payment
    public void Payment() {
        jpContentPanel = ContentPanel.getInstance();
        jpContentPanel.setLayout(new GridBagLayout());
        contentPanel = Payment.getInstance();

        jpContentPanel.removeAll();
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        gc = new GridBagConstraints();
        gc.gridx = 3;
        gc.gridy = 0;

        jpContentPanel.add(contentPanel, gc);
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        add(jpContentPanel);
    }

    //panel to create an order
    public void Order() {
        jpContentPanel = ContentPanel.getInstance();
        jpContentPanel.setLayout(new GridBagLayout());
        contentPanel = Order.getInstance();

        jpContentPanel.removeAll();
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        gc = new GridBagConstraints();
        gc.gridx = 3;
        gc.gridy = 0;

        jpContentPanel.add(contentPanel, gc);
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        add(jpContentPanel);
    }

    //panel to edit the order
    public void editOrder() {
        jpContentPanel = ContentPanel.getInstance();
        jpContentPanel.setLayout(new GridBagLayout());
        contentPanel = new EditOrder();

        jpContentPanel.removeAll();
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        gc = new GridBagConstraints();
        gc.gridx = 3;
        gc.gridy = 0;

        jpContentPanel.add(contentPanel, gc);
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        add(jpContentPanel);
    }

    //panel to search for customers
    public void Customer() {
        jpContentPanel = ContentPanel.getInstance();
        jpContentPanel.setLayout(new GridBagLayout());

        contentPanel = new Customer();

        jpContentPanel.removeAll();
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        gc = new GridBagConstraints();
        gc.gridx = 3;
        gc.gridy = 0;

        jpContentPanel.add(contentPanel, gc);

        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        add(jpContentPanel);
    }

    //panel to add customers
    public void addCustomer() {
        jpContentPanel = ContentPanel.getInstance();
        jpContentPanel.setLayout(new GridBagLayout());

        contentPanel = AddCustomer.getInstance();

        jpContentPanel.removeAll();
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        gc = new GridBagConstraints();
        gc.gridx = 3;
        gc.gridy = 0;

        jpContentPanel.add(contentPanel, gc);

        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        add(jpContentPanel);
    }

    //panel to search for companies 
    public void Company() {
        jpContentPanel = ContentPanel.getInstance();

        contentPanel = new Company();

        jpContentPanel.removeAll();
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        gc = new GridBagConstraints();
        gc.gridx = 3;
        gc.gridy = 0;

        jpContentPanel.add(contentPanel, gc);

        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        add(jpContentPanel);
    }

    //panel to search for salesmen details
    public void Salesmen() {
        jpContentPanel = ContentPanel.getInstance();

        contentPanel = new Salesmen();

        jpContentPanel.removeAll();
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        gc = new GridBagConstraints();
        gc.gridx = 3;
        gc.gridy = 0;

        jpContentPanel.add(contentPanel, gc);

        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        add(jpContentPanel);
    }

    //panel to add companies
    public void addCompany() {
        jpContentPanel = ContentPanel.getInstance();

        contentPanel = AddCompany.getInstance();

        jpContentPanel.removeAll();
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        gc = new GridBagConstraints();
        gc.gridx = 3;
        gc.gridy = 0;

        jpContentPanel.add(contentPanel, gc);

        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        add(jpContentPanel);
    }

    //panel to add salesmen details
    public void addSalesmen() {
        jpContentPanel = ContentPanel.getInstance();

        contentPanel = AddSalesmen.getInstance();

        jpContentPanel.removeAll();
        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        gc = new GridBagConstraints();
        gc.gridx = 3;
        gc.gridy = 0;

        jpContentPanel.add(contentPanel, gc);

        jpContentPanel.repaint();
        jpContentPanel.revalidate();

        add(jpContentPanel);
    }

    //returns an instance of the class
    public static Page getInstance() {
        if (instance == null) {
            instance = new Page();
        }
        return instance;
    }
}
