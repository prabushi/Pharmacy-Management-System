/*
 *A class to represent an item in the stock
 */
package UserGUI;

/**
 * @author Prabushi
 */
public class Item {

    private String name;
    private String price;
    private String quantity;
    private String total;
    private String profit;

    //constructor
    public Item(String name, String price, String quantity, String total, String profit) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.profit = profit;
    }

    //set profit of the item
    public void setProfit(String profit) {
        this.profit = profit;
    }

//returns the profit of the item
    public String getProfit() {
        return profit;
    }

//get the name of the item
    public String getName() {
        return name;
    }

    //return the price
    public String getPrice() {
        return price;
    }

    //return the item quantity
    public String getQuantity() {
        return quantity;
    }

    //return the total
    public String getTotal() {
        return total;
    }

    //set the name of the item
    public void setName(String name) {
        this.name = name;
    }

    //set the price of the item
    public void setPrice(String price) {
        this.price = price;
    }

    //set the quantity
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    //set the total
    public void setTotal(String total) {
        this.total = total;
    }
}
