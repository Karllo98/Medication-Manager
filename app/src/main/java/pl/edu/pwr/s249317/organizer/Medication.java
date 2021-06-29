package pl.edu.pwr.s249317.organizer;

public class Medication {

    private int id;
    private String name;
    private int amount;
    private String expiryDate;

    public Medication(int id, String name, int amount, String expiryDate) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.expiryDate = expiryDate;
    }

    public Medication() { }

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", expiryDate='" + expiryDate + '\'' +
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
