package domain;

public class PaymentType {

  private CreditCard creditCard;
  private BankAccount bankAccount;

  public PaymentType(){}

  public PaymentType(CreditCard creditCard, BankAccount bankAccount) {
    this.creditCard = creditCard;
    this.bankAccount = bankAccount;
  }

  public CreditCard getCreditCard(){
    return this.creditCard;
  }

  public BankAccount getBankAccount(){
    return this.bankAccount;
  }

  public void setCreditCard(CreditCard creditCard){
    this.creditCard = creditCard;
  }

  public void setBankAccount(BankAccount bankAccount){
    this.bankAccount = bankAccount;
  }

}