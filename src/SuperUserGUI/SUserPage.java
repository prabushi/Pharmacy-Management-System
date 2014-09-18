/*
 * Class to add jpanels to the jframe
 */
package SuperUserGUI;

/*
 * Prabushi
 */
import javax.swing.*;
import java.awt.*;

public final class SUserPage extends JFrame {

    private JPanel content;
    private JPanel contentPanel;
    private JPanel jpContentPanel;
    private JPanel bottomContent;
    GridBagConstraints gc;
    public static SUserPage instance = null;

    //constructor
    private SUserPage() {
        setBackground(new java.awt.Color(153, 153, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1365, 725));
        pack();
        setVisible(true);

        content = ButtonContentPanel.getInstance();
        bottomContent = new BottomPanel();

        add(content, BorderLayout.WEST);
        add(bottomContent, BorderLayout.SOUTH);
        this.getDailyProfit();
    }

    //method to add JPanel with daily profit
    public void getDailyProfit() {
        jpContentPanel = SUserContentPanel.getInstance();

        jpContentPanel.setLayout(new GridBagLayout());

        contentPanel = DailyProfit.getInstance();


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

    //to add the JPanel with monthly profit 
    public void getMonthlyProfit() {
        jpContentPanel = SUserContentPanel.getInstance();

        jpContentPanel.setLayout(new GridBagLayout());

        contentPanel = MonthlyProfit.getInstance();


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

    //add login detail wi th corresponding JPanel
    public void getLoginDetails() {
        jpContentPanel = SUserContentPanel.getInstance();

        jpContentPanel.setLayout(new GridBagLayout());

        contentPanel = LoginDetails.getInstance();


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

    //add the JPanel with stock details
    public void getStock() {
        jpContentPanel = SUserContentPanel.getInstance();

        contentPanel = Stock.getInstance();


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

    //add the JPanel with customer details
    public void getOther1() {
        jpContentPanel = SUserContentPanel.getInstance();

        contentPanel = Other1.getInstance();


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

    //add the JPanel with company details
    public void getOther2() {
        jpContentPanel = SUserContentPanel.getInstance();

        contentPanel = Other2.getInstance();


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

    //add the JPanel with salesmen details
    public void getOther3() {
        jpContentPanel = SUserContentPanel.getInstance();

        contentPanel = Other3.getInstance();


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

    //add the JPanel with bill details
    public void getBills() {
        jpContentPanel = SUserContentPanel.getInstance();

        contentPanel = Bills.getInstance();


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

    //add the JPanel with details of the profitability of the items
    public void getProfitability() {
        jpContentPanel = SUserContentPanel.getInstance();

        contentPanel = Profitability.getInstance();


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
    public static SUserPage getInstance() {
        if (instance == null) {
            instance = new SUserPage();
        }
        return instance;
    }
}
