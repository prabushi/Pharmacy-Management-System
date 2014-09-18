/*
 * Class to uniquely represent a shortage item of the database
 */
package AdminGUI;

/**
 *
 * @author Prabushi
 */
public class Shortage {

    private String name;
    private int eachHas;
    private int available;
    private int toBuy;

    //constructor
    public Shortage(String name, int eachHas, int available, int toBuy) {
        this.name = name;
        this.eachHas = eachHas;
        this.available = available;
        this.toBuy = toBuy;
    }

    //returns item name
    public String getName() {
        return name;
    }

    //returns number of item section each has
    public int getEachHas() {
        return eachHas;
    }

    //set the item name
    public void setName(String name) {
        this.name = name;
    }

    //set the number of secctions eash item has
    public void setEachHas(int eachHas) {
        this.eachHas = eachHas;
    }

    //set the available quantity
    public void setAvailable(int available) {
        this.available = available;
    }

    //set the quantity to be bought
    public void setToBuy(int toBuy) {
        this.toBuy = toBuy;
    }

    //returns the available quantity
    public int getAvailable() {
        return available;
    }

    //returns the number of items to be bought
    public int getToBuy() {
        return toBuy;
    }
}
