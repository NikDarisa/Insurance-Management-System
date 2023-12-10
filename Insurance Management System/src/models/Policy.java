package models;

public class Policy {
    private int id;
    private String name;
    private double premium;
    private int payout;

    public Policy(int id, String name, double premium, int payout) {
        this.id = id;
        this.name = name;
        this.premium = premium;
        this.payout = payout;
    }

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

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public int getPayout() {
        return payout;
    }

    public void setPayout(int payout) {
        this.payout = payout;
    }
}
