/*
 Act as the main jpanel to add jpanel to the middle panel
 */
package UserGUI;

import javax.swing.JPanel;

/**
 *
 * @author Prabushi
 */
public class BillCenterPanel extends JPanel {

    public static BillCenterPanel instance = null;

    //construstor
    private BillCenterPanel() {
        this.setBackground(new java.awt.Color(255, 255, 255));
    }

    //return an instance of the class
    public static BillCenterPanel getInstance() {
        if (instance == null) {
            instance = new BillCenterPanel();
        }
        return instance;
    }
}
