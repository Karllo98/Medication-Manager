package pl.edu.pwr.s249317.organizer;

public class Medication {

    private int id;
    private String name;
    private int amount;
    private String expiryDate;
    private String comments;

    public Medication(int id, String name, int amount, String expiryDate, String comments) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.expiryDate = expiryDate;
        this.comments = comments;
    }

    public Medication() { }

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", expiryDate='" + expiryDate + '\'' +
                ", comments='" + comments + '\'' +
                '}';
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
