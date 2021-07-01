package pl.edu.pwr.s249317.manager;

public class Medication {

    private int id;
    private String name;
    private int packagingAmount;
    private int amountInOnePackage;
    private int amountInNewOne;
    private String expiryDate;
    private String comments;

    public Medication(int id, String name, int packagingAmount, int amountInOnePackage, int amountInNewOne, String expiryDate, String comments) {
        this.id = id;
        this.name = name;
        this.packagingAmount = packagingAmount;
        this.amountInOnePackage = amountInOnePackage;
        this.amountInNewOne = amountInNewOne;
        this.expiryDate = expiryDate;
        this.comments = comments;
    }

    public Medication() { }

    @Override
    public String toString() {
        return  name + "\n" + "\n" +
                "Amount: " + packagingAmount + "\n" +
                "Amount in one package: " + amountInOnePackage + "\n" +
                "Expiry Date: " + expiryDate + "\n" +
                "Comments: " + comments + "\n";
    }

    /* Getters and Setters */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPackagingAmount() {
        return packagingAmount;
    }

    public void setPackagingAmount(int packagingAmount) {
        this.packagingAmount = packagingAmount;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getComments() {
        return comments;
    }

    public int getAmountInOnePackage() {
        return amountInOnePackage;
    }

    public void setAmountInOnePackage(int amountInOnePackage) {
        this.amountInOnePackage = amountInOnePackage;
    }

    public int getAmountInNewOne() {
        return amountInNewOne;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
