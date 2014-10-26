package domain;

import java.sql.Date;

public class Request {

  private final int rid;
  private final int sender;
  private final int reciever;
  private final String description;
  private final Double amount;
  private final Date startDate;
  private final Date endDate;
  private boolean fulfilled;

  public Request(int rid,int sender, int reciever, String description,
                 Double amount, Date startDate, Date endDate, boolean fulfilled){
    this.rid = rid;
    this.sender =sender;
    this.reciever = reciever;
    this.description = description;
    this.amount = amount;
    this.startDate = startDate;
    this.endDate = endDate;
    this.fulfilled = fulfilled;
  }

  public int getRId() {
    return this.rid;
  }

  public int getSender(){
    return this.sender;
  }

  public int getReciever(){
    return this.reciever;
  }

  public String getDescription(){
    return this.description;
  }

  public Double getAmount(){
    return this.amount;
  }

  public Date getEndDate(){
    return this.endDate;
  }

  public Date getStartDate(){
    return this.startDate;
  }

  public boolean getFulfilled(){
    return this.fulfilled;
  }

  public void setFulfilled(boolean state){
    this.fulfilled = state;
  }


}