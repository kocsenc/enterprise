package domain;

import java.sql.Date;

public class CreditCard {

  private final Long ccnum;
  private final Date expirationDate;
  private final int ccv;

  public CreditCard(Long ccnum, Date expirationDate, int ccv){
    this.ccnum = ccnum;
    this.expirationDate = expirationDate;
    this.ccv = ccv;
  }

  public Long getCcnum(){
    return this.ccnum;
  }

  public Date getExpirationDate(){
    return this.expirationDate;
  }

  public int getCCV(){
    return this.ccv;
  }


}