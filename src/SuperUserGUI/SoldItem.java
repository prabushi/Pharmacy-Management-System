/*
 *Class to represent an sold item
 */
package SuperUserGUI;

/**
 *
 * @author Prabushi
 */
public class SoldItem {

    private String name;
    private String quantity;
    private String price;

    //constructor
    public SoldItem(String name, String quantity, String price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    //set price of the item
    public void setPrice(String price) {
        this.price = price;
    }

    //returns price of the item
    public String getPrice() {
        return price;
    }

    //return the name of the item
    public String getName() {
        return name;
    }

    //returns the quantity
    public String getQuantity() {
        return quantity;
    }

    //set the name of the item
    public void setName(String name) {
        this.name = name;
    }

    //set the quantity of the item
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
