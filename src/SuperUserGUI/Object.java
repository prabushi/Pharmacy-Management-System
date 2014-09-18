/*
 * The claa to represent an unique element in the database
 */
package SuperUserGUI;

/**
 *
 * @author DELL
 */
public class Object {

    private String name;
    private String phone;
    private String address;
    private String date;
    private String removed;

    //constructor
    public Object(String name, String phone, String address, String date, String removed) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.date = date;
        this.removed = removed;
    }

    //set name of the object
    public void setName(String name) {
        this.name = name;
    }

    //set phone number
    public void setPhone(String phone) {
        this.phone = phone;
    }

    //set address
    public void setAddress(String address) {
        this.address = address;
    }

    //set date which that object has added to the database
    public void setDate(String date) {
        this.date = date;
    }

    //set the date which that object has been deleted from the database
    public void setRemoved(String removed) {
        this.removed = removed;
    }

    //return the name of the object
    public String getName() {
        return name;
    }

    //returns the phone number
    public String getPhone() {
        return phone;
    }

    //returns the address
    public String getAddress() {
        return address;
    }

    //returns the crated date
    public String getDate() {
        return date;
    }

    //returns the removed date
    public String getRemoved() {
        return removed;
    }
}
