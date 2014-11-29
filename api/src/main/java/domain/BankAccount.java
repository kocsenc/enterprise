package domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccount {

  private final int routingNumber;
  private final long accountNumber;

  @JsonCreator
  public BankAccount(@JsonProperty("routingNum") int routingNumber,@JsonProperty("accountNum") Long accountNumber){
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