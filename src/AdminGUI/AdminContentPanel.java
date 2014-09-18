/*
 *The class that represent the content panel of the admin dashboard
 */
package AdminGUI;

import javax.swing.JPanel;

/**
 *
 * @author Prabushi
 */
//have used singleton pattern to implement 
public class AdminContentPanel extends JPanel {

    public static AdminContentPanel instance = null;

    //constructor
    private AdminContentPanel() {
        this.setBackground(new java.awt.Color(255, 255, 255));
    }

    //method to get the instance of the class
    public static AdminContentPanel getInstance() {
        if (instance == null) {
            instance = new AdminContentPanel();
        }
        return instance;
    }
}
