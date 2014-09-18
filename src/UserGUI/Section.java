/*
 * To represent an stock item in the database
 */
package UserGUI;

import java.util.Date;

/**
 *
 * @author Prabushi
 */
public class Section implements Comparable<Section> {

    private int id;
    private int quantity;
    private int consumed;
    private Date expiry;

    //constructor
    public Section(int id, int quantity, Date expiry, int consumed) {
        this.id = id;
        this.quantity = quantity;
        this.expiry = expiry;
    }

    //returns consumed number
    public int getConsumed() {
        return consumed;
    }

    //set consumed number
    public void setConsumed(int consumed) {
        this.consumed = consumed;
    }

    //set item id
    public void setId(int id) {
        this.id = id;
    }

    //set item quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //set expiry date
    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

//retruns item id
    public int getId() {
        return id;
    }

    //returns item quantity
    public int getQuantity() {
        return quantity;
    }

    //returns expiry date
    public Date getExpiry() {
        return expiry;
    }

    //comparison of sections
    @Override
    public int compareTo(Section o) {
        Date compareQuantity = ((Section) o).getExpiry();

        //ascending order
        return this.expiry.compareTo(compareQuantity);
    }
}
