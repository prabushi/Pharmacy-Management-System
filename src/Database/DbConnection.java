/*
 * Handle all the connections to the database
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Prabushi
 */
public class DbConnection {

    private static Connection con = null;

    //return an db connection
    public static Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD);
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    //create an db connection if there is no connection, else return the existing connection
    private static Connection getConnection(Connection con) {
        if (con == null) {
            con = createConnection();
            return con;
        } else {
            return con;
        }
    }
}
