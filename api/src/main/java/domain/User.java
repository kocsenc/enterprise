package domain;

public class User {

    private int id;
    private String name;
    private double wallet;
    private String email;

    public User(){}

    public User(int id, String name, double wallet, String email) {
        this.id = id;
        this.name = name;
        this.wallet = wallet;
        this.email = email;
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

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}