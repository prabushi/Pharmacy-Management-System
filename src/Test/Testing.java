/*
 Testing
 */
package Test;

import Database.DbConnection;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Prabushi
 */
public class Testing {

    public void testdate() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String sql = "select * from section where code = '" + 12 + "' and removed is null";
            Connection connection = DbConnection.createConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            LinkedList<Date> exp = new LinkedList<Date>();
            String x;

            while (rs.next()) {
                x = rs.getString("ExpiryDate");
                exp.add(formatter.parse(x));
                //System.out.println(" jii");    
            }

            for (int i = 0; i < exp.size(); i++) {
                System.out.println(exp.get(i));
            }
            Collections.sort(exp);
            java.util.Date date = new java.util.Date();


            System.out.println(exp.get(0));



        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] arg){
        String date = "2014-05-28 15:45:10";
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        System.out.println(year);
        System.out.println(month);
        
    }
}
