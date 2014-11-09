package domain;

public class User {

    private final int id;
    private final String name;
    private double wallet;
    private String email;

    public User(int id, String name, double wallet, String email) {
        this.id = id;
        this.name = name;
        this.wallet = wallet;
        this.email = email;
    }

    public User(String name, String email){
        this.id = 0;
        this.name = name;
        this.email = email;
        this.wallet = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWallet() {
        return wallet;
    }

    public String getEmail() {
        return email;
    }

    public void setWallet(double value) {
        this.wallet += value; 
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }
}