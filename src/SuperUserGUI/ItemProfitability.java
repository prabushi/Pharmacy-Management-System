/*
 * To represent the profit of an item
 */
package SuperUserGUI;

/**
 *
 * @author Prabushi
 */
public class ItemProfitability {

    private String name;
    private float profit;

    //constructor
    public ItemProfitability(String name, float profit) {
        this.name = name;
        this.profit = profit;
    }

    //set item name
    public String getName() {
        return name;
    }

    //returns profit of an item
    public float getProfit() {
        return profit;
    }
}
