/*
 *Give a graph of profitabilities corresponding to each item
 */
package SuperUserGUI;

import Database.DbConnection;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Prabushi
 */
public class Profitability extends JPanel {

    public static Profitability instance = null;
    LinkedList<ItemProfitability> item = new LinkedList<ItemProfitability>();

    //constructor
    public Profitability() {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        gc.insets = new Insets(10, 10, 10, 10);


        JButton one = new JButton("This Month");
        one.setForeground(Color.WHITE);
        one.setHorizontalTextPosition(SwingConstants.CENTER);
        one.setBackground(Color.BLACK);
        one.setPreferredSize(new java.awt.Dimension(100, 25));
        one.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneActionPerformed(evt);
            }

            private void oneActionPerformed(ActionEvent evt) {
                SUserPage pg = SUserPage.getInstance();
                pg.getDailyProfit();
            }
        });

        JButton two = new JButton("This Year");
        two.setForeground(Color.WHITE);
        two.setHorizontalTextPosition(SwingConstants.CENTER);
        two.setBackground(Color.BLACK);
        two.setPreferredSize(new java.awt.Dimension(100, 25));
        two.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoActionPerformed(evt);
            }

            private void twoActionPerformed(ActionEvent evt) {
                SUserPage pg = SUserPage.getInstance();
                pg.getMonthlyProfit();
            }
        });

        JButton three = new JButton("Profitability");
        three.setForeground(Color.WHITE);
        three.setHorizontalTextPosition(SwingConstants.CENTER);
        three.setBackground(Color.BLACK);
        three.setPreferredSize(new java.awt.Dimension(100, 25));
        three.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threeActionPerformed(evt);
            }

            private void threeActionPerformed(ActionEvent evt) {
                SUserPage pg = SUserPage.getInstance();
                pg.getProfitability();
            }
        });

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.gridx = 1;
        gc.gridy = 1;
        this.add(one, gc);


        gc.gridx = 3;
        gc.gridy = 1;
        this.add(two, gc);

        gc.gridx = 5;
        gc.gridy = 1;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        this.add(three, gc);

        try {

            String sql = "SELECT section.code,SUM(sold.Quantity*(section.SellingPrice-section.WholesalePrice)) AS Profit FROM sold LEFT JOIN section ON sold.SectionId=section.IndexNo GROUP BY code";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int code = rs.getInt("code");
                float profit = rs.getFloat("Profit");
                String name="";
                String sql1 = "SELECT Name from item where code = '" + code + "' and removed is null";
                Connection connection1 = DbConnection.createConnection();
                PreparedStatement pst1 = connection1.prepareStatement(sql1);
                ResultSet rs1 = pst1.executeQuery();
                if (rs1.next()) {
                    name = rs1.getString("Name");
                }
                ItemProfitability ip = new ItemProfitability(name, profit);
                item.add(ip);
            }

            DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
            for (int i = 0; i < item.size(); i++) {
                
                barChartData.setValue(item.get(i).getProfit(), "Total Profit", item.get(i).getName());
            }
            JFreeChart barChart = ChartFactory.createBarChart("Profitability", "Item", "Profit(Rs.)", barChartData, PlotOrientation.VERTICAL, false, false, false);
            CategoryPlot barchrt = barChart.getCategoryPlot();
            barchrt.setRangeGridlinePaint(Color.magenta);

            ChartPanel barPanel = new ChartPanel(barChart);
            gc.gridx = 1;
            gc.gridy = 3;
            this.add(barPanel, gc);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //returns an instance of the class
    public static Profitability getInstance() {
        if (instance == null) {
            instance = new Profitability();
        }
        return instance;
    }
}
