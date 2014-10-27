package domain;

public class BankAccount {

  private final int routingNumber;
  private final long accountNumber;

  public BankAccount(int routingNumber, Long accountNumber){
    this.routingNumber = routingNumber;
    this.accountNumber = accountNumber;
  }

  public int getRoutingNumber(){
    return this.routingNumber;
  }

  public Long getAccountNumber(){
    return this.accountNumber;
  }
}