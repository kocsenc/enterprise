package domain;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditCard {

  private final Long ccnum;
  private final Date expirationDate;
  private final int ccv;

  @JsonCreator
  public CreditCard(@JsonProperty("ccnum") Long ccnum, @JsonProperty("exp") Date expirationDate, @JsonProperty("ccv") int ccv){
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