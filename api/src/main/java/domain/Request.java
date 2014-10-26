package domain;

import java.sql.Date;

public class Request {

  private int rid;
  private int sender;
  private int reciever;
  private String description;
  private Double amount;
  private Date startDate;
  private Date endDate;
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
    return rid;
  }

  public int getSender(){
    return sender;
  }

  public int getReciever(){
    return reciever;
  }

  public String getDescription(){
    return description;
  }

  public Double getAmount(){
    return amount;
  }

  public Date getEndDate(){
    return endDate;
  }

  public Date getStartDate(){
    return startDate;
  }

  public void setFulfilled(boolean state){
    this.fulfilled = state;
  }


}