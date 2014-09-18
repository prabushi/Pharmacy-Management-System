/*
 * A class to represent a stock item at the database
 */
package SuperUserGUI;

/**
 *
 * @author Prabushi
 */
public class Section {

    private int code;
    private String batch;
    private String expiry;
    private float profit;
    private int quantity;
    private String date;
    private String removed;
    private float selPrice;

    //constructor
    public Section(int code, String batch, String expiry, float sel, float profit, int quantity, String date, String removed) {
        this.selPrice = sel;
        this.code = code;
        this.batch = batch;
        this.expiry = expiry;
        this.profit = profit;
        this.quantity = quantity;
        this.date = date;
        this.removed = removed;
    }

    //set the selling price
    public void setSelPrice(float selPrice) {
        this.selPrice = selPrice;
    }

    //return the selling price
    public float getSelPrice() {
        return selPrice;
    }

//set the code number
    public void setCode(int code) {
        this.code = code;
    }
//set the batch number

    public void setBatch(String batch) {
        this.batch = batch;
    }

    //set the expiry date
    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    //set the profit of an item
    public void setProfit(float profit) {
        this.profit = profit;
    }

    //set the quantity of an item
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //set the date which created the stock
    public void setDate(String date) {
        this.date = date;
    }

    //set the date which removed the stock item
    public void setRemoved(String removed) {
        this.removed = removed;
    }

    //returns the code number
    public int getCode() {
        return code;
    }

    //returns the batch number
    public String getBatch() {
        return batch;
    }

    //returns the expiry date
    public String getExpiry() {
        return expiry;
    }

    //returns the profit
    public float getProfit() {
        return profit;
    }

    //returns the quantity of the item
    public int getQuantity() {
        return quantity;
    }

    //returns the stock item created date
    public String getDate() {
        return date;
    }

    //returns the stock item removed date
    public String getRemoved() {
        return removed;
    }
}
