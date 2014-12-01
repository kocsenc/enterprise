package domain;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

  private final Integer rid;
  private Integer sender;
  private Integer reciever;
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

  @JsonCreator
  public Request(@JsonProperty("amount") double amount, @JsonProperty("receiver") int receiver, @JsonProperty("description")String description){
    this.rid = null;
    this.reciever = receiver;
    this.description = description;
    this.amount = amount;
    this.startDate  = new Date(new java.util.Date().getTime());
    this.endDate = new Date(new java.util.Date().getTime());
    this.fulfilled = false;
    this.sender = null;

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

  public void setSender(int id){
    this.sender = id;
  }

  public void setReciever(int id){
    this.reciever = id;
  }


}