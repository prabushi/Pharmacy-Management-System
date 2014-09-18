/*
 * The class to get the total daily revenue of this month 
 */
package AdminGUI;

import Database.DbConnection;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author Prabushi
 */
public class DailyRevenue extends JPanel {

    float[] bill = new float[31];
    public static DailyRevenue instance = null;
    JLabel Ltotal;
    JTextField total;
    JButton export;
    int month;
    int year;
    String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
        "August", "September", "October", "November", "December"};

    //costructor
    public DailyRevenue() {

        Ltotal = new JLabel("Total : ");
        export = new JButton("Export Word");
        export.setBackground(new java.awt.Color(255, 255, 102));
        total = new JTextField();
        for (int i = 0; i < 31; i++) {
            bill[i] = 0;
        }
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        setFont(new Font("SansSerif", Font.PLAIN, 14));
        gc.insets = new Insets(10, 10, 10, 10);


        JButton one = new JButton("Short Expiry");
        one.setForeground(Color.WHITE);
        one.setHorizontalTextPosition(SwingConstants.CENTER);
        one.setBackground(new java.awt.Color(36, 36, 75));
        one.setPreferredSize(new java.awt.Dimension(100, 25));
        one.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneActionPerformed(evt);
            }

            private void oneActionPerformed(ActionEvent evt) {
                AdminPage pg = AdminPage.getInstance();
                pg.getShortExpiry();
            }
        });

        JButton two = new JButton("This Month");
        two.setForeground(Color.WHITE);
        two.setHorizontalTextPosition(SwingConstants.CENTER);
        two.setBackground(new java.awt.Color(36, 36, 75));
        two.setPreferredSize(new java.awt.Dimension(100, 25));
        two.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twoActionPerformed(evt);
            }

            private void twoActionPerformed(ActionEvent evt) {
                AdminPage pg = AdminPage.getInstance();
                pg.getDailyRevenue();
            }
        });

        JButton three = new JButton("This Year");
        three.setForeground(Color.WHITE);
        three.setHorizontalTextPosition(SwingConstants.CENTER);
        three.setBackground(new java.awt.Color(36, 36, 75));
        three.setPreferredSize(new java.awt.Dimension(100, 25));
        three.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threeActionPerformed(evt);
            }

            private void threeActionPerformed(ActionEvent evt) {
                AdminPage pg = AdminPage.getInstance();
                pg.getMonthlyRevenue();
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

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH);

        try {

            String sql = "select * from bill";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            String billDate;

            while (rs.next()) {
                billDate = rs.getString("Date");
                String d = billDate.substring(8, 10);
                Date date = formatter.parse(billDate);
                int tempMonth = date.getMonth();
                if (month == tempMonth) {
                    year = cal.get(Calendar.YEAR);
                    int tempYear = date.getYear() + 1900;
                    if (year == tempYear) {
                      
                        int day = Integer.parseInt(d);
                        String dy = formatter.format(date);
                        int x = Integer.parseInt(dy.substring(8, 10));
                        
                        float total = rs.getFloat("Total");
                        bill[day - 1] += total;
                    }
                }
            }

            DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
            float tot = 0;
            for (int i = 0; i < 31; i++) {
                tot += bill[i];
                barChartData.setValue(bill[i], "Total Revenue", Integer.toString(i + 1));
            }
            total.setText(Float.toString(tot));
            JFreeChart barChart = ChartFactory.createBarChart("Daily Revenue", "Day", "Revenue(Rs.)", barChartData, PlotOrientation.VERTICAL, false, false, false);
            CategoryPlot barchrt = barChart.getCategoryPlot();
            barchrt.setRangeGridlinePaint(Color.magenta);

            ChartPanel barPanel = new ChartPanel(barChart);
            gc.gridx = 1;
            gc.gridy = 3;
            this.add(barPanel, gc);


            export.setPreferredSize(new java.awt.Dimension(150, 40));
            export.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    oneActionPerformed(evt);
                }

                private void oneActionPerformed(ActionEvent evt) {

                    String content = "Year : " + Integer.toString(year) + "\n" + "Month : " + monthName[month] + "\n" + "\n" + "\n" + "\n";
                    for (int i = 0; i < 31; i++) {
                        content += " Day : " + Integer.toString(i + 1) + "     Total : " + Float.toString(bill[i]) + "\n";
                        content += "\n" + "\n";
                    }
                    content += "\n" + "\n" + "Monthly Revenue : " + total.getText();

                    writeToFile(content, monthName[month] + "_Revenue.doc");
                }
            });

            gc.gridx = 1;
            gc.gridy = 10;
            add(Ltotal, gc);

            gc.gridx = 3;
            gc.gridy = 10;
            add(total, gc);

            gc.gridx = 5;
            gc.gridy = 11;
            add(export, gc);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //method to write to a word doc
    private static void writeToFile(String content, String path) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem();
            DirectoryEntry directory = fs.getRoot();
            directory.createDocument("WordDocument", new ByteArrayInputStream(content.getBytes()));
            FileOutputStream out = new FileOutputStream(path);

            fs.writeFilesystem(out);
            out.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //method to get an instance of the class 
    public static DailyRevenue getInstance() {
        if (instance == null) {
            instance = new DailyRevenue();
        }
        return instance;
    }
}
