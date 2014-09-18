/*
 * The claa to represent an unique item in a stock section
 */
package AdminGUI;

/**
 * @author Prabushi
 */
public class Section {

    private String expiry;
    private int code;
    private int quantity;
    private String batchNo;
    private int available;
    private String name;
    private String company;
    private int sectionId;
    private int eachHas;

    //constructor
    public Section(String expiry, int code, int quantity, String batchNo, int available, String name, int secId, int each) {
        this.expiry = expiry;
        this.code = code;
        this.quantity = quantity;
        this.batchNo = batchNo;
        this.available = available;
        this.name = name;
        this.sectionId = secId;
        this.eachHas = each;
    }

    //returns number of elements each has
    public int getEachHas() {
        return eachHas;
    }

    //setter for section id
    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    //returns section id
    public int getSectionId() {
        return sectionId;
    }

    //set expiry date
    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    //set code number
    public void setCode(int code) {
        this.code = code;
    }

    //set quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //set batch number
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    //set number of available item count
    public void setAvailable(int available) {
        this.available = available;
    }

    //set name of the item
    public void setName(String name) {
        this.name = name;
    }

    //get expiry date
    public String getExpiry() {
        return expiry;
    }

    //returns code number
    public int getCode() {
        return code;
    }

    //set the number of each has elements
    public void setEachHas(int eachHas) {
        this.eachHas = eachHas;
    }

    //set the company name
    public void setCompany(String company) {
        this.company = company;
    }

    //return the company name
    public String getCompany() {
        return company;
    }

    //returns the quantity
    public int getQuantity() {
        return quantity;
    }

    //retruns the batch number
    public String getBatchNo() {
        return batchNo;
    }

    //returns the available amount
    public int getAvailable() {
        return available;
    }

    //returns the name of the item
    public String getName() {
        return name;
    }
}
