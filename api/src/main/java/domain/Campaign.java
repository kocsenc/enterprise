package domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Campaign {

  private final String Title;
  private final String Description;
  private final double Wallet;
  private final Date startDate;
  private final Date endDate;
  private final double Goal;
  private final int user;
  private final int cid;

  public Campaign(int cid, String Title, String Description, Double Wallet, Date startDate, Date endDate, int user, double goal){
    this.cid = cid;
    this.Title = Title;
    this.Description = Description;
    this.Wallet = Wallet;
    this.startDate = startDate;
	this.endDate = endDate;
    this.user = user;
    this.Goal = goal;
  }

  @JsonCreator
  public Campaign(@JsonProperty("uid") int uid, @JsonProperty("description") String description, @JsonProperty("goal") double goal, @JsonProperty("title") String title){
    this.cid = 0;
    this.user = uid;
    this.Description = description;
    this.Goal = goal;
    this.Title = title;
    this.Wallet = 0;
    Date date = new Date();
    this.startDate = date;
    this.endDate = new Date(date.getTime() + ((1000 * 60 * 60 * 24) * 10));
  }

  public String getTitle(){
    return this.Title;
  }

  public String getDescription(){
    return this.Description;
  }

  public double getWallet(){
    return this.Wallet;
  }

  public Date getStartDate() {
    return this.startDate;
  }
  
  public Date getEndDate() {
    return this.endDate;
  }

  public int getCreator() {return this.user; }

  public double getGoal() {return this.Goal;}
}