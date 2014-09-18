/*
 * The class that represents the bill (this is s collection of items and their details)
 */
package SuperUserGUI;

import java.util.LinkedList;

/**
 *
 * @author Prabushi
 */
public class Bill {

    private LinkedList<SoldItem> items;
    private String customerName;
    private String total;
    private String profit;
    private String date;

    //constuctor
    public Bill(LinkedList<SoldItem> items, String customerName, String total, String profit, String date) {
        this.items = items;
        this.customerName = customerName;
        this.total = total;
        this.profit = profit;
        this.date = date;
    }

    //returns the item list
    public LinkedList<SoldItem> getItems() {
        return items;
    }

    //returns the customer name
    public String getCustomerName() {
        return customerName;
    }

    //returns the total of bill
    public String getTotal() {
        return total;
    }

    //returns the profit of the bill
    public String getProfit() {
        return profit;
    }

    //returns the date and time at bill purchased
    public String getDate() {
        return date;
    }

    //set items to the list
    public void setItems(LinkedList<SoldItem> items) {
        this.items = items;
    }

    //set customer name
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    //set total to the bill
    public void setTotal(String total) {
        this.total = total;
    }

    //set profit of the bill
    public void setProfit(String profit) {
        this.profit = profit;
    }

    //set the data at which the bill was purchased
    public void setDate(String date) {
        this.date = date;
    }
}
