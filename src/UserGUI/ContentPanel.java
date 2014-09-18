/*
 * The main panel of use dashboard for which other panels are added
 */
package UserGUI;

import javax.swing.JPanel;

/**
 *
 * @author Prabushi
 */
public class ContentPanel extends JPanel {

    public static ContentPanel instance = null;

    //constructor
    private ContentPanel() {
        this.setBackground(new java.awt.Color(255, 255, 255));
    }

    //retuns an instance of the class
    public static ContentPanel getInstance() {
        if (instance == null) {
            instance = new ContentPanel();
        }
        return instance;
    }
}
