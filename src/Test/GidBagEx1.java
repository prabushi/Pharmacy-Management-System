/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.awt.*;
import java.applet.Applet;
import javax.swing.JLabel;

/**
 *
 * @author DELL
 */
public class GidBagEx1 extends Applet {

    protected void makebutton(String name,
            GridBagLayout gridbag,
            GridBagConstraints c) {
        Button button = new Button(name);
        gridbag.setConstraints(button, c);
        add(button);
    }

    public void init() {
        this.setLayout(null);
        JLabel j = new JLabel();
        j.setBounds(0, 0, 1365, 725);
        j.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.jpg")));
        add(j);

        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();

        setFont(new Font("SansSerif", Font.PLAIN, 14));
        setLayout(gridbag);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        makebutton("Button1", gridbag, c);
        makebutton("Button2", gridbag, c);
        makebutton("Button3", gridbag, c);

        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        makebutton("Button4", gridbag, c);

        c.weightx = 0.0;                //reset to the default
        makebutton("Button5", gridbag, c); //another row

        c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row
        makebutton("Button6", gridbag, c);

        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        makebutton("Button7", gridbag, c);

        c.gridwidth = 1;                //reset to the default
        c.gridheight = 2;
        c.weighty = 1.0;
        makebutton("Button8", gridbag, c);

        c.weighty = 0.0;                //reset to the default
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        c.gridheight = 1;               //reset to the default
        makebutton("Button9", gridbag, c);
        makebutton("Button10", gridbag, c);

        setSize(300, 100);
    }

    public static void main(String args[]) {

        Frame f = new Frame("GridBag Layout Example");
        GidBagEx1 ex1 = new GidBagEx1();

        ex1.init();

        f.add("Center", ex1);
        f.pack();
        f.setSize(f.getPreferredSize());
        f.show();
    }
}
