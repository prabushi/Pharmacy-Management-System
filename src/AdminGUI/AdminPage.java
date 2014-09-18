/*
 Class to add jpanels to the jframe
 */
package AdminGUI;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Prabushi
 */
public final class AdminPage extends JFrame {

    private JPanel content;
    private JPanel contentPanel;
    private JPanel jpContentPanel;
    private JPanel bottomContent;
    GridBagConstraints gc;
    public static AdminPage instance = null;

//constructor
    private AdminPage() {

        setVisible(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1365, 725));
        pack();


        content = new ButtonContentPanel();
        bottomContent = new BottomPanel();
        content.setBackground(new java.awt.Color(255, 255, 255));

        bottomContent.setBackground(new java.awt.Color(255, 255, 255));

        add(content, BorderLayout.WEST);
        add(bottomContent, BorderLayout.SOUTH);
        this.Set();

    }
//method to add the Set panel which enable to add items to the system

    public void Set() {
        jpContentPanel = AdminContentPanel.getInstance();

        jpContentPanel.setLayout(new GridBagLayout());

        contentPanel = new Set();

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

    //method to add edit panel which allows to edit the items
    public void edit() {
        jpContentPanel = AdminContentPanel.getInstance();

        jpContentPanel.setLayout(new GridBagLayout());

        contentPanel = new Edit();

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

    //method to add panel to edit stock by name
    public void StockeditName() {
        jpContentPanel = AdminContentPanel.getInstance();

        jpContentPanel.setLayout(new GridBagLayout());

        contentPanel = new StockEditName();

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

    //method to edit the stock content by code
    public void StockeditCode() {
        jpContentPanel = AdminContentPanel.getInstance();

        jpContentPanel.setLayout(new GridBagLayout());

        contentPanel = new StockEditCode();

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

    //method to search stockby code
    public void codeSearch() {
        jpContentPanel = AdminContentPanel.getInstance();

        jpContentPanel.setLayout(new GridBagLayout());

        contentPanel = new CodeSearch();

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

    //method to add stock to the system
    public void getStock() {
        jpContentPanel = AdminContentPanel.getInstance();

        contentPanel = new Stock();

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

    //method to get the panel to get short expiry list
    public void getShortExpiry() {
        jpContentPanel = AdminContentPanel.getInstance();

        contentPanel = new ShortExpiry();


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

    //method to get the panel to get the revenue of this month
    public void getDailyRevenue() {
        jpContentPanel = AdminContentPanel.getInstance();

        contentPanel = new DailyRevenue();

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

    // method to get the panel to get the revenue of this year
    public void getMonthlyRevenue() {
        jpContentPanel = AdminContentPanel.getInstance();

        contentPanel = new MonthlyRevenue();

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

    //method to get the panel to get the expired items
    public void getExpiry() {
        jpContentPanel = AdminContentPanel.getInstance();

        contentPanel = new Expiry();

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

    //method to get the panel to the the shortage lists by company wise
    public void getShortageList() {
        jpContentPanel = AdminContentPanel.getInstance();

        contentPanel = new ShortageList();

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

    //method to get the instance of this class
    public static AdminPage getInstance() {
        if (instance == null) {
            instance = new AdminPage();
        }
        return instance;
    }
}
