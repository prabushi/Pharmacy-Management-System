/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author DELL
 */
public class Testdbconnection {

    public void testing() {
        try {
            String temp = "Evion 200";
            String sql = "update item set removed = abc where Name = '" + temp + "'";
            Connection connection1 = DbConnection.createConnection();
            PreparedStatement pst = connection1.prepareStatement(sql);
            ResultSet rs1 = pst.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
