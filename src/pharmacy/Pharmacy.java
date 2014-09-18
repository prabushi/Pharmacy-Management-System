/*
 Main programme of the Pharmacy Management Software Project
 * This will direct to the login page
 */
package pharmacy;

import AdminGUI.AdminPage;
import UserGUI.Page;
import Login.LoginPage;
import SuperUserGUI.SUserPage;
import javax.swing.JFrame;

/**
 * @author Prabushi
 */
public class Pharmacy {
//Use threads to visible the login page at the beginning of the program

    //main method of the program
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                //AdminPage frame = AdminPage.getInstance();
                LoginPage frame = LoginPage.getInstance();
                //Page frame = Page.getInstance(); 
                //SUserPage frame = SUserPage.getInstance();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(frame.getPreferredSize());
                frame.show();

            }
        });
    }
}
