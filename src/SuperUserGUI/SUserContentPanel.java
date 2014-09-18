/*
 * Main panel of the Super User dashboard which holds other panels with content
 */
package SuperUserGUI;

import javax.swing.JPanel;

/**
 *
 * @author Prabushi
 */
public class SUserContentPanel extends JPanel {

    public static SUserContentPanel instance = null;

    //constructor
    private SUserContentPanel() {
        this.setBackground(new java.awt.Color(255, 255, 255));
    }

    //returns an instance of the class
    public static SUserContentPanel getInstance() {
        if (instance == null) {
            instance = new SUserContentPanel();
        }
        return instance;
    }
}
